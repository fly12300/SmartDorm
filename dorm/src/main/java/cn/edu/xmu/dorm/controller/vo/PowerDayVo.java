package cn.edu.xmu.dorm.controller.vo;

import lombok.*;

import java.util.Date;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PowerDayVo {

    private Integer powerConsume;
    private String time;
}
