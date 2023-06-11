package cn.edu.xmu.dorm.Listener;

import cn.edu.xmu.dorm.config.RabbitMQConfig;
import cn.edu.xmu.dorm.controller.vo.DeviceVo;
import cn.edu.xmu.dorm.mapper.AlarmPoMapper;
import cn.edu.xmu.dorm.mapper.DormPoMapper;
import cn.edu.xmu.dorm.mapper.po.DevicePo;
import cn.edu.xmu.dorm.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class DeviceMessageListener implements IMqttMessageListener {



    private final RabbitTemplate rabbitTemplate;
    private final DormPoMapper dormPoMapper;
    private final AlarmPoMapper alarmPoMapper;

    @Autowired
    public DeviceMessageListener(RabbitTemplate rabbitTemplate, DormPoMapper dormPoMapper, AlarmPoMapper alarmPoMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.dormPoMapper = dormPoMapper;
        this.alarmPoMapper = alarmPoMapper;
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
            log.info("Device: " + mqttMessage.toString());
            // 处理设备消息消息
        try {
            DeviceVo vo = JsonUtil.objectMapper.readValue(new String(mqttMessage.getPayload()).
                    getBytes(StandardCharsets.UTF_8), DeviceVo.class);
            DevicePo po = DevicePo.builder().deviceName(vo.getDeviceName()).deviceStatus(vo.getDeviceStatus())
                    .deviceType(vo.getDeviceType()).deviceId(vo.getDeviceId())
                    .userId(vo.getUserId()).build();
            log.info("DevicePo:{}",po);
            rabbitTemplate.convertAndSend(RabbitMQConfig.DORM_QUEUE_DEVICE, po);
        } catch (Exception e) {
            log.info("出现异常");
            log.info(e.getMessage());
        }
    }
}
