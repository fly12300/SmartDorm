package cn.edu.xmu.dorm.service;


import cn.edu.xmu.dorm.controller.vo.LoginVo;
import cn.edu.xmu.dorm.entity.User;
import cn.edu.xmu.dorm.utils.ReturnObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IDeviceService {
    ReturnObject getDeviceStatus(Long userId,String deviceName);
    ReturnObject putDeviceStatus(Long userId,String deviceName,Integer deviceStatus);
}
