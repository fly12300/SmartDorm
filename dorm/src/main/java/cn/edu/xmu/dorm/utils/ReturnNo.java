package cn.edu.xmu.dorm.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ReturnNo {
    SUCCESS(200, "成功"),
    ERROR(500, "服务端异常"),
    LOGIN_ERROR(901, "手机号或密码不正确"),
    LOGIN_NON(902, "用户未登录"),
    DORM_ROOM_NOT_EXIST(704, "房间不存在"),
    MQTT_MQTTVO_NOT_ILLEGAL(702,"传入格式错误"),
    MQTT_MQTTVO_FOMAT_ERROR(700,"传感器数据错误"),
    MQTT_THEME_NOT_EXIST(703,"订阅为空");
    private final Integer code;

    private final String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
