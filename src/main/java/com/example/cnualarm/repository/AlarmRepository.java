package com.example.cnualarm.repository;

import com.example.cnualarm.Entity.AlarmEntity;
import com.example.cnualarm.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AlarmRepository extends JpaRepository<AlarmEntity, Integer> {
    Collection<AlarmEntity> findByuserId(String userId);
}
