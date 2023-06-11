package cn.edu.xmu.dorm.controller.vo;

import lombok.*;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DevicePowerVo {

    private Long deviceId;
    private Integer powerConsume;

}
