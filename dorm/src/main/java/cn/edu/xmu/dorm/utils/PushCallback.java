package cn.edu.xmu.dorm.utils;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class PushCallback implements MqttCallback {
    private static final Logger logger = LoggerFactory.getLogger(MqttUtils.class);

    @Override
    public void connectionLost(Throwable cause) {
        logger.info("PushCallback--连接断开" + cause.toString());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        if (!token.isComplete()) {
            logger.info("PushCallback：" + token.toString());
        }
    }

    @Override
    public void messageArrived(String theme, MqttMessage message) {
        try {
            logger.info("【订阅回调】\ttheme：{}\tqos：{}\npayload：{}", theme, message.getQos(), new String(message.getPayload(),StandardCharsets.UTF_8));
            sentMsg(message);
        } catch (Exception e) {
            logger.error("【订阅回调】--异常\ttheme：{}\nmessage：{}", theme, message.toString());
        }
    }
    private void sentMsg(MqttMessage message){
        String payload = new String(message.getPayload());
        //mqSensorSender.send(payload);
    }
}
