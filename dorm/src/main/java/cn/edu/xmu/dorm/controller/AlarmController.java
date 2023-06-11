package cn.edu.xmu.dorm.controller;

import cn.edu.xmu.dorm.config.annotation.userLoginCheck;
import cn.edu.xmu.dorm.controller.vo.RoomVo;
import cn.edu.xmu.dorm.controller.vo.UserVo;
import cn.edu.xmu.dorm.service.IRoomService;
import cn.edu.xmu.dorm.utils.ReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/alarm")
public class AlarmController {

    private static final Logger logger = LoggerFactory.getLogger(AlarmController.class);

    private final IRoomService roomService;

    @Autowired
    public AlarmController(IRoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping (value = "", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ReturnObject getRoomAlarm(@userLoginCheck UserVo user) {
        logger.info(user.toString());
        return roomService.getRoomAlarm(user.getUserId());
    }

}
