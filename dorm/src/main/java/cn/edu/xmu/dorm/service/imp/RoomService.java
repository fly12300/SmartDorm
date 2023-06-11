package cn.edu.xmu.dorm.service.imp;


import cn.edu.xmu.dorm.controller.vo.RoomVo;
import cn.edu.xmu.dorm.controller.vo.ChartDataVo;
import cn.edu.xmu.dorm.exception.DormException;
import cn.edu.xmu.dorm.mapper.AlarmPoMapper;
import cn.edu.xmu.dorm.mapper.SensorPoMapper;
import cn.edu.xmu.dorm.mapper.po.AlarmPo;
import cn.edu.xmu.dorm.mapper.po.DormPo;
import cn.edu.xmu.dorm.mapper.DormPoMapper;
import cn.edu.xmu.dorm.mapper.po.SensorPo;
import cn.edu.xmu.dorm.service.IRoomService;
import cn.edu.xmu.dorm.utils.ReturnNo;
import cn.edu.xmu.dorm.utils.ReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService implements IRoomService {

    private static final Logger logger = LoggerFactory.getLogger(RoomService.class);
    private final DormPoMapper dormPoMapper;
    private final SensorPoMapper sensorPoMapper;
    private final AlarmPoMapper alarmPoMapper;

    @Autowired
    RoomService(DormPoMapper dormPoMapper, SensorPoMapper sensorPoMapper, AlarmPoMapper alarmPoMapper) {
        this.dormPoMapper = dormPoMapper;
        this.sensorPoMapper = sensorPoMapper;
        this.alarmPoMapper = alarmPoMapper;
    }

    private ChartDataVo getVo(SensorPo po) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String format = formatter.format(po.getReceiveTime());
        ChartDataVo chartDataVo = new ChartDataVo(format, po.getSensorData());
        return chartDataVo;
    }

    @Override
    public ReturnObject getRoomStatus(Long userId) {
        Optional<DormPo> dormPo = dormPoMapper.findByUserId(userId);
        if (dormPo.isEmpty()) {
            throw new DormException(ReturnNo.DORM_ROOM_NOT_EXIST, "房间不存在");
        }
        return new ReturnObject(ReturnNo.SUCCESS, dormPo.get());
    }

    @Override
    public ReturnObject updateDevStatus(Long userId, RoomVo roomVo) {
        Optional<DormPo> dormPo = dormPoMapper.findByUserId(userId);
        if (dormPo.isEmpty()) {
            throw new DormException(ReturnNo.DORM_ROOM_NOT_EXIST, "房间不存在");
        }
        DormPo po = dormPo.get();
        dormPoMapper.save(po);
        return new ReturnObject(ReturnNo.SUCCESS);
    }

    @Override
    public ReturnObject getTempChartData(Long userId) {
        List<SensorPo> list = sensorPoMapper.findAllByDayAndDormId(userId);
        List<ChartDataVo> dataVos = list.stream()
                .sorted((o1, o2) -> o1.getReceiveTime().before(o2.getReceiveTime()) ? 0 : 1)
                .map(this::getVo)
                .collect(Collectors.toList());
        return new ReturnObject(ReturnNo.SUCCESS, dataVos);
    }

    @Override
    public ReturnObject getRoomAlarm(Long userId) {
        List<AlarmPo> alarmPos = alarmPoMapper.findAllByDormId(userId);
        return new ReturnObject(ReturnNo.SUCCESS,alarmPos);
    }
}
