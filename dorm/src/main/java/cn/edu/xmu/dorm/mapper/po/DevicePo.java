package cn.edu.xmu.dorm.mapper.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "t_device")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DevicePo implements Serializable {
    @Basic
    @Column(name = "device_status")
    private Integer deviceStatus;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "device_id")
    private long deviceId;
    @Basic
    @Column(name = "device_type")
    private Integer deviceType;
    @Basic
    @Column(name = "user_id")
    private long userId;
    @Basic
    @Column(name = "device_name")
    private String deviceName;


    public Integer getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(Integer deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "DevicePo{" +
                "deviceStatus=" + deviceStatus +
                ", deviceId=" + deviceId +
                ", deviceType=" + deviceType +
                ", userId=" + userId +
                ", deviceName='" + deviceName + '\'' +
                '}';
    }
}
