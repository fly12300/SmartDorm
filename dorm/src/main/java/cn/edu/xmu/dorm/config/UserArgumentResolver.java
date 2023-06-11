package cn.edu.xmu.dorm.config;

import cn.edu.xmu.dorm.exception.DormException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import cn.edu.xmu.dorm.config.annotation.userLoginCheck;
import cn.edu.xmu.dorm.controller.vo.UserVo;
import cn.edu.xmu.dorm.utils.CookieUtil;
import cn.edu.xmu.dorm.utils.RedisUtil;
import cn.edu.xmu.dorm.utils.ReturnNo;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    private final RedisUtil redisUtil;

    @Autowired
    public UserArgumentResolver(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(userLoginCheck.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        if (null == httpServletRequest)
            throw new DormException(ReturnNo.ERROR);
        String token = CookieUtil.getCookieValue(httpServletRequest, "token");
        log.info("token = {}", token);
        if (null == token || token.isEmpty()) {
            throw new DormException(ReturnNo.LOGIN_NON);
        }
        UserVo user = (UserVo) redisUtil.getValueByKey(UserVo.RedisKey(token));
        if (null == user) {
            throw new DormException(ReturnNo.LOGIN_NON);
        }
        return user;
    }
}
