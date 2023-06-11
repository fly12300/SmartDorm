package cn.edu.xmu.dorm.controller;

import cn.edu.xmu.dorm.utils.MqttUtils;
import cn.edu.xmu.dorm.utils.ReturnNo;
import cn.edu.xmu.dorm.utils.ReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mqtt")
public class MqttController {

    private static final Logger logger = LoggerFactory.getLogger(MqttController.class);
    @Autowired
    private MqttUtils mqttUtils;

    @GetMapping("/pushMsg")
    @ResponseBody
    public ReturnObject pushMsg(@RequestParam(value = "theme", required = false) String theme, @RequestParam(value = "msg") String msg) {
        logger.info("mqtt:{}",msg);
        return new ReturnObject(ReturnNo.SUCCESS,mqttUtils.pushMsg(String.valueOf(System.currentTimeMillis()), theme, msg));
    }

    @GetMapping("/subscribe")
    @ResponseBody
    public ReturnObject subscribe(@RequestParam(value = "theme", required = false) String theme) {
         return new ReturnObject(ReturnNo.SUCCESS,mqttUtils.subscribe(theme));
    }

}
