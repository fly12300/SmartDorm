package cn.edu.xmu.dorm.mapper;

import cn.edu.xmu.dorm.mapper.po.SensorPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SensorPoMapper extends JpaRepository<SensorPo,Long> {
    List<SensorPo> findByDormIdAndReceiveTime(Long dormId, Date receiveTime);
    @Query(value = "select * from sensor where TO_DAYS(receive_time) = TO_DAYS(NOW())",nativeQuery = true)
    List<SensorPo> findAllByDay();
    @Query(value = "select * from sensor where TO_DAYS(receive_time) = TO_DAYS(NOW()) and dorm_id = ?1",nativeQuery = true)
    List<SensorPo> findAllByDayAndDormId(Long dormId);
}
