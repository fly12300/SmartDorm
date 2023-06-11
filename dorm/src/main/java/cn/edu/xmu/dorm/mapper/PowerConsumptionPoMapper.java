package cn.edu.xmu.dorm.mapper;

import cn.edu.xmu.dorm.mapper.po.AlarmPo;
import cn.edu.xmu.dorm.mapper.po.PowerConsumptionPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PowerConsumptionPoMapper extends JpaRepository<PowerConsumptionPo,Long> {
    @Query(value = "select * from t_power_consumption where TO_DAYS(count_time) = TO_DAYS(?2) and device_id = ?1",nativeQuery = true)
    Optional<PowerConsumptionPo> findByDeviceIdAndAndCountTime(Long deviceId, LocalDate date);
}
