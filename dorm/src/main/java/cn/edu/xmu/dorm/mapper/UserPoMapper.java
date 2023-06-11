package cn.edu.xmu.dorm.mapper;

import cn.edu.xmu.dorm.mapper.po.UserPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPoMapper extends JpaRepository<UserPo,Long> {
    @Override
    Optional<UserPo> findById(Long aLong);
}
