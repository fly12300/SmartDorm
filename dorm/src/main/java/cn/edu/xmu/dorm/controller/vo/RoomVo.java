package cn.edu.xmu.dorm.controller.vo;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RoomVo {
    private Integer temperature;
    private String message;
    private Integer humdity;
    private Integer formadldehyde;
    private Integer waterLevel;
    private Integer electricalPower;
    private Integer light;
    private Integer fan;
    private Integer airconditioner;
    private Integer door;
}
