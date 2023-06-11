package cn.edu.xmu.dorm.mapper.po;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_alarm")
@AllArgsConstructor
@NoArgsConstructor
public class AlarmPo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "dorm_id")
    private long dormId;
    @Basic
    @Column(name = "alarm_msg")
    private String alarmMsg;
    @Basic
    @Column(name = "alarm_time")
    private Timestamp alarmTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDormId() {
        return dormId;
    }

    public void setDormId(long dormId) {
        this.dormId = dormId;
    }

    public String getAlarmMsg() {
        return alarmMsg;
    }

    public void setAlarmMsg(String alarmMsg) {
        this.alarmMsg = alarmMsg;
    }

    public Timestamp getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Timestamp alarmTime) {
        this.alarmTime = alarmTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlarmPo that = (AlarmPo) o;
        return id == that.id && dormId == that.dormId && Objects.equals(alarmMsg, that.alarmMsg) && Objects.equals(alarmTime, that.alarmTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dormId, alarmMsg, alarmTime);
    }
}
