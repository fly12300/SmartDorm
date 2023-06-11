package cn.edu.xmu.dorm.rabbitMq;

import cn.edu.xmu.dorm.config.RabbitMQConfig;
import cn.edu.xmu.dorm.controller.vo.SensorVo;
import cn.edu.xmu.dorm.mapper.AlarmPoMapper;
import cn.edu.xmu.dorm.mapper.DevicePoMapper;
import cn.edu.xmu.dorm.mapper.DormPoMapper;
import cn.edu.xmu.dorm.mapper.SensorPoMapper;
import cn.edu.xmu.dorm.mapper.po.AlarmPo;
import cn.edu.xmu.dorm.mapper.po.DevicePo;
import cn.edu.xmu.dorm.mapper.po.DormPo;
import cn.edu.xmu.dorm.mapper.po.SensorPo;
import cn.edu.xmu.dorm.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class MQDeviceReceiver {

    @Autowired
    private DevicePoMapper devicePoMapper;

    @RabbitListener(queues = RabbitMQConfig.DORM_QUEUE_DEVICE)
    public void receive(DevicePo msg) throws IOException {
        log.info("收到消息{}",msg);
        devicePoMapper.save(msg);
    }

}
