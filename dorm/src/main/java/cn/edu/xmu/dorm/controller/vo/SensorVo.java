package cn.edu.xmu.dorm.controller.vo;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SensorVo implements Serializable {
    private static Map<String,Long> typeToLong = new HashMap<>();
    static {
        typeToLong.put("temp_sensor", 1L);
        typeToLong.put("humidity_sensor", 2L);
        typeToLong.put("form_sensor", 3L);
        typeToLong.put("water_sensor", 4L);
    }
    private Long sensorId;
    private String type;
    private Long dormId;
    private String sensorName;
    private Integer message;
    private Date msgTime;

    public Long getLongType() {
        return typeToLong.getOrDefault(type, -1L);
    }
}
