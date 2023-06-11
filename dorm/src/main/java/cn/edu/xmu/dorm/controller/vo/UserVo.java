package cn.edu.xmu.dorm.controller.vo;

import lombok.*;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVo {
    public static String RedisKey(String token) {
        return String.format("U:%s", token);
    }
    private Long userId;
    private String nickname;
    private String head;
}
