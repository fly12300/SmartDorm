package cn.edu.xmu.dorm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import cn.edu.xmu.dorm.config.annotation.userLoginCheck;
import cn.edu.xmu.dorm.controller.vo.RoomVo;
import cn.edu.xmu.dorm.controller.vo.UserVo;
import cn.edu.xmu.dorm.service.IRoomService;
import cn.edu.xmu.dorm.utils.ReturnObject;

import javax.validation.Valid;

@Controller
@RequestMapping("/room")
public class RoomController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    private final IRoomService roomService;

    @Autowired
    public RoomController(IRoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * 查询宿舍具体信息
     * @param user
     * @return
     */
    @GetMapping (value = "/status", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ReturnObject getRoomInfo(@userLoginCheck UserVo user) {
        logger.info(user.toString());
        return roomService.getRoomStatus(user.getUserId());
    }

    /**
     * 更新宿舍信息
     * @param user
     * @param roomVo
     * @return
     */
    @PutMapping(value = "", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ReturnObject updateRoomStatus(@userLoginCheck UserVo user, @Valid @RequestBody RoomVo roomVo) {
        logger.info(roomVo.toString());
        return roomService.updateDevStatus(user.getUserId(),roomVo);
    }
}
