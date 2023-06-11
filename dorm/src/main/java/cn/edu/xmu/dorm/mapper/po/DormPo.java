package cn.edu.xmu.dorm.mapper.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Objects;

@Entity
@Table(name = "t_dorm")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DormPo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "user_id")
    private Long userId;
    @Basic
    @Column(name = "temperature")
    private Integer temperature;
    @Basic
    @Column(name = "message")
    private String message;
    @Basic
    @Column(name = "humidity")
    private Integer humidity;
    @Basic
    @Column(name = "formadldehyde")
    private Integer formadldehyde;
    @Basic
    @Column(name = "water_level")
    private Integer waterLevel;
    @Basic
    @Column(name = "electrical_power")
    private Integer electricalPower;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humdity) {
        this.humidity = humidity;
    }

    public Integer getFormadldehyde() {
        return formadldehyde;
    }

    public void setFormadldehyde(Integer formadldehyde) {
        this.formadldehyde = formadldehyde;
    }

    public Integer getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(Integer waterLevel) {
        this.waterLevel = waterLevel;
    }

    public Integer getElectricalPower() {
        return electricalPower;
    }

    public void setElectricalPower(Integer electricalPower) {
        this.electricalPower = electricalPower;
    }



    @Override
    public int hashCode() {
        return Objects.hash(id, userId, temperature, message, humidity, formadldehyde, waterLevel, electricalPower);
    }
}
