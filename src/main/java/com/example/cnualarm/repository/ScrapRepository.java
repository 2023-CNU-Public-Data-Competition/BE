package com.example.cnualarm.repository;

import com.example.cnualarm.Entity.ScrapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapRepository extends JpaRepository<ScrapEntity, Integer> {
    List<ScrapEntity> findByUser_UserId(String userId);
}
