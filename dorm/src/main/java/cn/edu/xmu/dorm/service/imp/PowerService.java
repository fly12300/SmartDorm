package cn.edu.xmu.dorm.service.imp;


import cn.edu.xmu.dorm.controller.vo.PowerDayVo;
import cn.edu.xmu.dorm.controller.vo.DevicePowerVo;
import cn.edu.xmu.dorm.mapper.DevicePoMapper;
import cn.edu.xmu.dorm.mapper.PowerConsumptionPoMapper;
import cn.edu.xmu.dorm.mapper.TotalPowerPoMapper;
import cn.edu.xmu.dorm.mapper.po.DevicePo;
import cn.edu.xmu.dorm.mapper.po.PowerConsumptionPo;
import cn.edu.xmu.dorm.mapper.po.TotalPowerPo;
import cn.edu.xmu.dorm.service.IPowerService;
import cn.edu.xmu.dorm.utils.ReturnNo;
import cn.edu.xmu.dorm.utils.ReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PowerService implements IPowerService {

    private static final Logger logger = LoggerFactory.getLogger(PowerService.class);
    private final DevicePoMapper devicePoMapper;
    private final PowerConsumptionPoMapper powerConsumptionPoMapper;
    private final TotalPowerPoMapper totalPowerPoMapper;
    @Autowired
    public PowerService(DevicePoMapper devicePoMapper, PowerConsumptionPoMapper powerConsumptionPoMapper, TotalPowerPoMapper totalPowerPoMapper) {
        this.devicePoMapper = devicePoMapper;
        this.powerConsumptionPoMapper = powerConsumptionPoMapper;
        this.totalPowerPoMapper = totalPowerPoMapper;
    }


    @Override
    public ReturnObject getPowerConsumeByDay(Long userId, LocalDate date) {
        List<DevicePo> pos = devicePoMapper.findByUserId(userId);
        logger.info("devices:{}", pos.toString());
        List<DevicePowerVo> devicePowerVos = pos.stream()
                .map(po -> powerConsumptionPoMapper.findByDeviceIdAndAndCountTime(po.getDeviceId(), date))
                .filter(Optional::isPresent)
                .map(po -> this.getPowerVo(po.get())).collect(Collectors.toList());
        return new ReturnObject(ReturnNo.SUCCESS, devicePowerVos);
    }

    @Override
    public ReturnObject getOneDevicePowerConsumeByMonth(Long userId, LocalDate date) {
        List<PowerDayVo> pos = totalPowerPoMapper.findByDormIdAndAndCountTime(userId,
                        String.format("%d-%02d", date.getYear(), date.getMonthValue())).stream()
                .sorted((o1, o2) -> o1.getCountTime().before(o2.getCountTime()) ? 0 : 1)
                .map(this::getPowerDayVo)
                .collect(Collectors.toList());
        return new ReturnObject(ReturnNo.SUCCESS,pos);
    }

    private DevicePowerVo getPowerVo(PowerConsumptionPo po) {
        return new DevicePowerVo(po.getDeviceId(), po.getPowerConsume());
    }

    private PowerDayVo getPowerDayVo(TotalPowerPo po) {
        return new PowerDayVo(po.getTotalPower(),po.getCountTime().toString());
    }
}
