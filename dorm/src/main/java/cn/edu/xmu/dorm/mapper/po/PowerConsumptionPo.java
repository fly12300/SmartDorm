package cn.edu.xmu.dorm.mapper.po;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_power_consumption")
@AllArgsConstructor
@NoArgsConstructor
public class PowerConsumptionPo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "device_id")
    private long deviceId;
    @Basic
    @Column(name = "power_consume")
    private int powerConsume;
    @Basic
    @Column(name = "count_time")
    private Timestamp countTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public int getPowerConsume() {
        return powerConsume;
    }

    public void setPowerConsume(int powerConsume) {
        this.powerConsume = powerConsume;
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
        PowerConsumptionPo that = (PowerConsumptionPo) o;
        return id == that.id && deviceId == that.deviceId && powerConsume == that.powerConsume && Objects.equals(countTime, that.countTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deviceId, powerConsume, countTime);
    }

    @Override
    public String toString() {
        return "PowerConsumptionPo{" +
                "id=" + id +
                ", deviceId=" + deviceId +
                ", powerConsume=" + powerConsume +
                ", countTime=" + countTime +
                '}';
    }
}
