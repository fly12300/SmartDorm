package cn.edu.xmu.dorm.utils;

import cn.edu.xmu.dorm.exception.DormException;
import cn.edu.xmu.dorm.mapper.AlarmPoMapper;
import cn.edu.xmu.dorm.mapper.DormPoMapper;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MqttUtils implements InitializingBean {
    @Value("${mqtt.host}")
    private String host;
    @Value("${mqtt.clientId}")
    private String clientId;
    private final RabbitTemplate rabbitTemplate;
    private final DormPoMapper dormPoMapper;
    private final AlarmPoMapper alarmPoMapper;
    @Value("${mqtt.topics.array}")
    private String[] topics;

    @Autowired
    public MqttUtils(RabbitTemplate rabbitTemplate, DormPoMapper dormPoMapper, AlarmPoMapper alarmPoMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.dormPoMapper = dormPoMapper;
        this.alarmPoMapper = alarmPoMapper;
    }

    MqttClient client;
    MqttConnectOptions options;

    /**
     * 订阅主题
     */
    public boolean subscribe(String theme) {
        try {
            if (theme.isEmpty()) {
                throw new DormException(ReturnNo.MQTT_THEME_NOT_EXIST);
            }
            client.setCallback(new PushCallback());
            client.connect(options);
            client.subscribe(theme, 1);
            return true;
        } catch (Exception e) {
            log.error("推送消息--异常\nclientId:{}\nexception:{}", clientId, e.toString());
            return false;
        }
    }

    /**
     * 推送消息
     */
    public boolean pushMsg(String clientId, String theme, String msg) {
        if (theme.isEmpty()) {
            throw new DormException(ReturnNo.MQTT_THEME_NOT_EXIST);
        }
        MqttTopic connect = this.getTopic(clientId, theme);
        MqttMessage message = new MqttMessage();
        //保证消息能到达一次
        message.setQos(1);
        message.setRetained(false);
        message.setPayload(msg.getBytes());
        try {
            this.publish(connect, message);
            return true;
        } catch (Exception e) {
            log.error("推送消息--异常\nclientId:{}\nexception:{}", clientId, e.toString());
            return false;
        }
    }

    /**
     * 获取订阅
     */
    private MqttTopic getTopic(String clientId, String theme) {
        MqttClient mqttClient = this.mqttClient(clientId);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
            mqttClient.setCallback(new PushCallback());
            mqttClient.connect(options);
            return mqttClient.getTopic(theme);
        } catch (Exception e) {
            log.error("获取订阅--异常\nclientId:{}\ntheme:{}\nexception:{}", clientId, theme, e.toString());
        }
        return null;
    }

    /**
     * 连接mqtt服务
     */
    private MqttClient mqttClient(String clientId) {
        MqttClient mqttClient = null;
        try {
            mqttClient = new MqttClient(host, clientId, new MemoryPersistence());
        } catch (MqttException e) {
            log.error("连接MQTT服务器--异常\nclientId:{}\nexception:{}", clientId, e.toString());
        }
        return mqttClient;
    }

    /**
     * 推送消息
     */
    private void publish(MqttTopic mqttTopic, MqttMessage message) throws Exception {
        if (mqttTopic != null) {
            MqttDeliveryToken token = mqttTopic.publish(message);
            token.waitForCompletion();
            if (!token.isComplete()) {
                log.error("推送消息--失败--msg：{}", message.toString());
            }
        } else {
            log.error("推送消息--失败--msg：{}", message.toString());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            //MQTT的连接设置
            client = new MqttClient(host, clientId, new MemoryPersistence());
            options = new MqttConnectOptions();
            //设置断开后重新连接
            // options.setAutomaticReconnect(true);
            client.connect(options);
            for (String topic : topics) {
                log.info("获得包名"+String.format("cn.edu.xmu.dorm.Listener.%sListener", topic));
                client.subscribe(topic, 1, (IMqttMessageListener) Class.forName
                        (String.format("cn.edu.xmu.dorm.Listener.%sListener", topic))
                        .getConstructor(RabbitTemplate.class, DormPoMapper.class, AlarmPoMapper.class)
                        .newInstance(rabbitTemplate,dormPoMapper,alarmPoMapper));
            }
        } catch (Exception e) {
            log.error("推送消息--异常\nclientId:{}\nexception:{}", clientId, e.toString());
        }
    }

    private class TemperatureMessageListener implements IMqttMessageListener {
        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            System.out.println("Temperature: " + message.toString());
            // 处理温度消息
        }
    }

    private class HumidityMessageListener implements IMqttMessageListener {
        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            System.out.println("Humidity: " + message.toString());
            // 处理湿度消息
        }
    }
}

