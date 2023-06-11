package cn.edu.xmu.dorm.Listener;

import cn.edu.xmu.dorm.config.RabbitMQConfig;
import cn.edu.xmu.dorm.controller.vo.SensorVo;
import cn.edu.xmu.dorm.mapper.AlarmPoMapper;
import cn.edu.xmu.dorm.mapper.DormPoMapper;
import cn.edu.xmu.dorm.mapper.po.DormPo;
import cn.edu.xmu.dorm.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class FormMessageListener implements IMqttMessageListener {

    private final RabbitTemplate rabbitTemplate;
    private final DormPoMapper dormPoMapper;
    private final AlarmPoMapper alarmPoMapper;

    @Autowired
    public FormMessageListener(RabbitTemplate rabbitTemplate, DormPoMapper dormPoMapper, AlarmPoMapper alarmPoMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.dormPoMapper = dormPoMapper;
        this.alarmPoMapper = alarmPoMapper;
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("Form: " + mqttMessage.toString());
        try {
            SensorVo mqttVo = JsonUtil.objectMapper.readValue(new String(mqttMessage.getPayload()).
                    getBytes("UTF-8"), SensorVo.class);
            log.info("mqttVo{}", mqttVo);
            rabbitTemplate.convertAndSend(RabbitMQConfig.DORM_QUEUE_SENSOR, mqttVo);
            Optional<DormPo> dormPo = dormPoMapper.findByUserId(mqttVo.getDormId());
            log.info("update:{}", dormPo);
            if (dormPo.isPresent()) {
                DormPo po = dormPo.get();
                po.setFormadldehyde(mqttVo.getMessage());
                po.setId(dormPo.get().getId());
                dormPoMapper.save(po);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }

    }
}
