package com.example.cnualarm.repository;

import com.example.cnualarm.Entity.AlarmEntity;
import com.example.cnualarm.Entity.AlarmSettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmSettingRepository extends JpaRepository<AlarmSettingEntity, Integer> {
    List<AlarmSettingEntity> findByUserIdAndAlarmType(String id, String alarm_type);
}
