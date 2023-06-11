package cn.edu.xmu.dorm.service.imp;

import cn.edu.xmu.dorm.aop.MqttConvert;
import cn.edu.xmu.dorm.exception.DormException;
import cn.edu.xmu.dorm.mapper.DevicePoMapper;
import cn.edu.xmu.dorm.mapper.po.DevicePo;
import cn.edu.xmu.dorm.service.IDeviceService;
import cn.edu.xmu.dorm.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceService implements IDeviceService {

    private static final Logger logger = LoggerFactory.getLogger(DeviceService.class);
    private final DevicePoMapper devicePoMapper;

    @Autowired
    public DeviceService(DevicePoMapper devicePoMapper) {
        this.devicePoMapper = devicePoMapper;
    }


    @Override
    public ReturnObject getDeviceStatus(Long userId, String deviceName) {
        Optional<DevicePo> po = devicePoMapper.findByUserIdAndAndDeviceName(userId, deviceName);
        if (po.isEmpty()){
            throw new DormException(ReturnNo.ERROR,"设备不存在");
        }
        return new ReturnObject(ReturnNo.SUCCESS,po.get().getDeviceStatus());
    }

    @Override
    public ReturnObject putDeviceStatus(Long userId, String deviceName, Integer deviceStatus) {
        Optional<DevicePo> po = devicePoMapper.findByUserIdAndAndDeviceName(userId, deviceName);
        if (po.isEmpty()){
            throw new DormException(ReturnNo.ERROR,"设备不存在");
        }
        DevicePo devicePo = po.get();
        devicePo.setDeviceStatus(deviceStatus);
        devicePoMapper.save(devicePo);
        return new ReturnObject(ReturnNo.SUCCESS);
    }

}
