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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/data")
public class chartDataController {

    private static final Logger logger = LoggerFactory.getLogger(chartDataController.class);

    private final IRoomService roomService;

    @Autowired
    public chartDataController(IRoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping (value = "/temp", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ReturnObject getTempData(@userLoginCheck UserVo user) {
        logger.info(user.toString());
        return roomService.getTempChartData(user.getUserId());
    }


}
