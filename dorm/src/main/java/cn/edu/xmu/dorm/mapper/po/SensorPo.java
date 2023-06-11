package cn.edu.xmu.dorm.mapper.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "t_sensor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorPo implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "sensor_id")
    private Long sensorId;
    @Basic
    @Column(name = "sensor_type")
    private Integer sensorType;
    @Basic
    @Column(name = "dorm_id")
    private Long dormId;
    @Basic
    @Column(name = "sensor_name")
    private String sensorName;
    @Basic
    @Column(name = "sensor_data")
    private Integer sensorData;
    @Basic
    @Column(name = "receive_time")
    private Date receiveTime;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSensorId() {
        return sensorId;
    }

    public void setSensorId(long sensorId) {
        this.sensorId = sensorId;
    }

    public int getSensorType() {
        return sensorType;
    }

    public void setSensorType(Integer sensorType) {
        this.sensorType = sensorType;
    }

    public long getDormId() {
        return dormId;
    }

    public void setDormId(long dormId) {
        this.dormId = dormId;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public Integer getSensorData() {
        return sensorData;
    }

    public void setSensorData(Integer sensorData) {
        this.sensorData = sensorData;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Timestamp receiveTime) {
        this.receiveTime = receiveTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorPo that = (SensorPo) o;
        return id == that.id && sensorId == that.sensorId && sensorType == that.sensorType && dormId == that.dormId && sensorData == that.sensorData && Objects.equals(sensorName, that.sensorName) && Objects.equals(receiveTime, that.receiveTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sensorId, sensorType, dormId, sensorName, sensorData, receiveTime);
    }
    @Override
    public String toString() {
        return "SensorPo{" +
                "sensorId=" + sensorId +
                ", sensorType=" + sensorType +
                ", dormId=" + dormId +
                ", sensorName='" + sensorName + '\'' +
                ", sensorData=" + sensorData +
                ", receiveTime=" + receiveTime +
                '}';
    }
}
