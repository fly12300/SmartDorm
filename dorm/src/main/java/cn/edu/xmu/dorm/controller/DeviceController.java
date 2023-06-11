package cn.edu.xmu.dorm.controller;

import cn.edu.xmu.dorm.config.annotation.userLoginCheck;
import cn.edu.xmu.dorm.controller.vo.RoomVo;
import cn.edu.xmu.dorm.controller.vo.UserVo;
import cn.edu.xmu.dorm.service.IDeviceService;
import cn.edu.xmu.dorm.utils.ReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/device")
public class DeviceController {

    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

   private final IDeviceService deviceService;

   @Autowired
    public DeviceController(IDeviceService deviceService) {
        this.deviceService = deviceService;
    }

    /**
     * 获取某个设别的状态
     * @param user
     * @param deviceName
     * @return
     */
    @GetMapping (value = "/{deviceName}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ReturnObject getDeviceStatus(@userLoginCheck UserVo user,@PathVariable String deviceName) {
        logger.info(deviceName);
        return deviceService.getDeviceStatus(user.getUserId(), deviceName);
    }

    /**
     * 更新设备状态
     * @param user
     * @param deviceName
     * @param deviceStatus
     * @return
     */
    @PutMapping(value = "/{deviceName}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ReturnObject updateDeviceStatus(@userLoginCheck UserVo user,
                                           @PathVariable String deviceName,
                                           @RequestParam(value = "deviceStatus") Integer deviceStatus) {
        logger.info(deviceStatus.toString());
        return deviceService.putDeviceStatus(user.getUserId(), deviceName, deviceStatus);
    }
}
