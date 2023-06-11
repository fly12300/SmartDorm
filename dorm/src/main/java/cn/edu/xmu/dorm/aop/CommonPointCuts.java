package cn.edu.xmu.dorm.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CommonPointCuts {

    @Pointcut("@annotation(cn.edu.xmu.dorm.aop.MqttConvert)")
    public void mqttConvertAnnotation() {
    }
}
