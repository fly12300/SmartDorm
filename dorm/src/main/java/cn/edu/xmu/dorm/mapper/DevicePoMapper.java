package cn.edu.xmu.dorm.mapper;

import cn.edu.xmu.dorm.mapper.po.AlarmPo;
import cn.edu.xmu.dorm.mapper.po.DevicePo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DevicePoMapper extends JpaRepository<DevicePo,Long> {
    List<DevicePo> findByUserId(Long userId);
    Optional<DevicePo> findByUserIdAndAndDeviceName(Long userId,String deviceName);
}
