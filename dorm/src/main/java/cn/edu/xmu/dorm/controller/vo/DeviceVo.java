package cn.edu.xmu.dorm.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceVo implements Serializable {

    private Integer deviceStatus;
    private long deviceId;
    private Integer deviceType;
    private long userId;
    private String deviceName;
}
