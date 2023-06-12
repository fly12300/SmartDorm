package cn.edu.xmu.dorm.controller.vo;

import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.util.Date;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlarmVo {
    private String alarmMsg;
    private String alarmTime;

}
