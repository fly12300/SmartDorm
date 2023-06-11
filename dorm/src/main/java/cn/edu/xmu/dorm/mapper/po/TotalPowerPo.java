package cn.edu.xmu.dorm.mapper.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_total_power")
public class TotalPowerPo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "dorm_id")
    private long dormId;
    @Basic
    @Column(name = "total_power")
    private int totalPower;
    @Basic
    @Column(name = "count_time")
    private Timestamp countTime;

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

    public int getTotalPower() {
        return totalPower;
    }

    public void setTotalPower(int totalPower) {
        this.totalPower = totalPower;
    }

    public Timestamp getCountTime() {
        return countTime;
    }

    public void setCountTime(Timestamp countTime) {
        this.countTime = countTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TotalPowerPo that = (TotalPowerPo) o;
        return id == that.id && dormId == that.dormId && totalPower == that.totalPower && Objects.equals(countTime, that.countTime);
    }

    @Override
    public String toString() {
        return "TotalPowerPo{" +
                "id=" + id +
                ", dormId=" + dormId +
                ", totalPower=" + totalPower +
                ", countTime=" + countTime +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dormId, totalPower, countTime);
    }
}
