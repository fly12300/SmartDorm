package cn.edu.xmu.dorm.service;


import cn.edu.xmu.dorm.utils.ReturnObject;
import cn.edu.xmu.dorm.controller.vo.RoomVo;

public interface IRoomService {

    ReturnObject getRoomStatus(Long userId);
    ReturnObject updateDevStatus(Long userId,RoomVo roomVo);
    ReturnObject getTempChartData(Long userId);
    ReturnObject getRoomAlarm(Long userId);
}
