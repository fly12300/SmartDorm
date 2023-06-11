package cn.edu.xmu.dorm.controller;

import cn.edu.xmu.dorm.config.annotation.userLoginCheck;
import cn.edu.xmu.dorm.controller.vo.UserVo;
import cn.edu.xmu.dorm.entity.Time;
import cn.edu.xmu.dorm.service.IPowerService;
import cn.edu.xmu.dorm.utils.ReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/power")
public class PowerController {

    private static final Logger logger = LoggerFactory.getLogger(PowerController.class);

    private final IPowerService powerService;

    @Autowired
    public PowerController(IPowerService powerService) {
        this.powerService = powerService;
    }

    /**
     * 某个宿舍某天所有设备耗电量
     * @param user
     * @return
     */
    @GetMapping (value = "/day", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ReturnObject getPowerByDay(@userLoginCheck UserVo user, @RequestBody @Valid Time time) {
        logger.info("查询日期{}",LocalDate.parse(time.getTime()));
        return powerService.getPowerConsumeByDay(user.getUserId(), LocalDate.parse(time.getTime()));
    }

    /**
     * 查询某月的
     * @param user
     * @param time
     * @return
     */
    @GetMapping (value = "/month", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ReturnObject getPowerByMonth(@userLoginCheck UserVo user, @RequestBody @Valid Time time) {
        logger.info("查询日期{}",LocalDate.parse(time.getTime()));
        return powerService.getPowerConsumeByDay(user.getUserId(), LocalDate.parse(time.getTime()));
    }

}
