package cn.edu.xmu.dorm.rabbitMq;

import cn.edu.xmu.dorm.aop.MqttConvert;
import cn.edu.xmu.dorm.config.RabbitMQConfig;
import cn.edu.xmu.dorm.controller.vo.SensorVo;
import cn.edu.xmu.dorm.mapper.DevicePoMapper;
import cn.edu.xmu.dorm.mapper.SensorPoMapper;
import cn.edu.xmu.dorm.mapper.po.DevicePo;
import cn.edu.xmu.dorm.mapper.po.SensorPo;
import cn.edu.xmu.dorm.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
@Slf4j
public class MQSensorReceiver {

    @Autowired
    private SensorPoMapper sensorPoMapper;

    @RabbitListener(queues = RabbitMQConfig.DORM_QUEUE_SENSOR)
    public void receive(SensorVo mqttVo) throws IOException {
        log.info("收到消息{}", mqttVo);
        try {
            SensorPo sensorPo = SensorPo.builder().sensorId(mqttVo.getSensorId()).sensorName(mqttVo.getSensorName())
                    .sensorType(Math.toIntExact(mqttVo.getLongType()))
                    .sensorData(mqttVo.getMessage()).dormId(mqttVo.getDormId())
                    .receiveTime(new Date()).build();
            log.info("sensorPo{}", sensorPo);
            sensorPoMapper.save(sensorPo);
        } catch (Exception e) {
            log.info(e.getMessage());
        }

    }

}
