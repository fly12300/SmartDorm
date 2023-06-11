package cn.edu.xmu.dorm.service;


import cn.edu.xmu.dorm.controller.vo.LoginVo;
import cn.edu.xmu.dorm.entity.User;
import cn.edu.xmu.dorm.utils.ReturnObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Date;

public interface IPowerService {
    ReturnObject getPowerConsumeByDay(Long userId, LocalDate date);
    ReturnObject getOneDevicePowerConsumeByMonth(Long deviceId,LocalDate date);
}
