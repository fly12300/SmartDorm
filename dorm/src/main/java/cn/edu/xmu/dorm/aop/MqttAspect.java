//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.dorm.aop;

import cn.edu.xmu.dorm.config.RabbitMQConfig;
import cn.edu.xmu.dorm.controller.vo.SensorVo;
import cn.edu.xmu.dorm.mapper.DormPoMapper;
import cn.edu.xmu.dorm.mapper.po.DormPo;
import cn.edu.xmu.dorm.mapper.po.SensorPo;
import cn.edu.xmu.dorm.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;


@Aspect
@Component
@Slf4j
public class MqttAspect {

    private final Logger logger = LoggerFactory.getLogger(MqttAspect.class);
    private final RabbitTemplate rabbitTemplate;
    private final DormPoMapper dormPoMapper;

    @Autowired
    public MqttAspect(RabbitTemplate rabbitTemplate, DormPoMapper dormPoMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.dormPoMapper = dormPoMapper;
    }

    @Around("cn.edu.xmu.dorm.aop.CommonPointCuts.mqttConvertAnnotation()")
    public Object aroundAudit(JoinPoint joinPoint) throws  Throwable{
        logger.info("1111");
        MqttMessage mqttMessage = (MqttMessage) joinPoint.getArgs()[1];
        try {
            SensorVo mqttVo = JsonUtil.objectMapper.readValue(new String(mqttMessage.getPayload()).
                    getBytes("UTF-8"), SensorVo.class);
            SensorPo sensorPo = SensorPo.builder().sensorId(mqttVo.getSensorId()).sensorName(mqttVo.getSensorName())
                    .sensorType(Math.toIntExact(mqttVo.getLongType()))
                    .sensorData(mqttVo.getMessage()).dormId(mqttVo.getDormId())
                    .receiveTime(new Date()).build();
            log.info("sensorPo{}", sensorPo);
            rabbitTemplate.convertAndSend(RabbitMQConfig.DORM_QUEUE_SENSOR,sensorPo);
//            Object[] args = joinPoint.getArgs();
//            args[1] = mqttVo;
//            // 调用目标方法
//            ((ProceedingJoinPoint) joinPoint).proceed(args);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }


}
