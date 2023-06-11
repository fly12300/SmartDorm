package cn.edu.xmu.dorm.service;


import cn.edu.xmu.dorm.controller.vo.LoginVo;
import cn.edu.xmu.dorm.entity.User;
import cn.edu.xmu.dorm.utils.ReturnObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IUserService {


    /**
     * 登录功能
     *
     * @param loginVo
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    ReturnObject doLogin(LoginVo loginVo, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);


    /**
     *
     * @param token
     * @return
     */
    User getUserByCookie(String token);
}
