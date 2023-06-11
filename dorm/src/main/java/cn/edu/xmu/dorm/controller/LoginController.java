package cn.edu.xmu.dorm.controller;

import cn.edu.xmu.dorm.aop.MqttConvert;
import cn.edu.xmu.dorm.controller.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.edu.xmu.dorm.service.IUserService;
import cn.edu.xmu.dorm.utils.ReturnObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final IUserService userService;

    /**
     * 登录
     * @param userService
     */
    @Autowired
    public LoginController(IUserService userService) {
        this.userService = userService;
    }
    @PostMapping(value = "/dologin", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ReturnObject doLogin(@Valid @RequestBody LoginVo loginVo,
                                HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse) {
        logger.info(loginVo.toString());
        return userService.doLogin(loginVo, httpServletRequest, httpServletResponse);
    }
}
