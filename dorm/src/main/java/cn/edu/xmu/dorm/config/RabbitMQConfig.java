package cn.edu.xmu.dorm.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String DORM_QUEUE_SENSOR = "DORM_QUEUE_SNESOR";
    public static final String DORM_QUEUE_DEVICE = "DORM_QUEUE_DEVICE";
    public static final String DORM_EXCHANGE = "DORM_SWITCH";
    public static final String DORM_KEY = "dorm.mqtt";
    @Bean
    public Queue queue() {
        return new Queue(DORM_QUEUE_SENSOR, true);
    }
    @Bean
    public Queue queueDevice() {
        return new Queue(DORM_QUEUE_DEVICE, true);
    }
    @Bean
    public TopicExchange exchange() {return new TopicExchange(DORM_EXCHANGE);}
    @Bean
    public Binding binding1() {return BindingBuilder.bind(queue()).to(exchange()).with(DORM_KEY);}
    @Bean
    public Binding binding2() {return BindingBuilder.bind(queueDevice()).to(exchange()).with(DORM_KEY);}
}
