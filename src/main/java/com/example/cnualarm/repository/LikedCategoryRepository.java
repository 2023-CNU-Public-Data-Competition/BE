package com.example.cnualarm.repository;

import com.example.cnualarm.Entity.LikedCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikedCategoryRepository extends JpaRepository<LikedCategoryEntity, String> {

//    @Modifying
//    @Transactional
//    @Query(value = "delete from liked_category where user_id = :userId", nativeQuery = true)
//    void deleteByUserId(@Param("userId") String userId);

    void deleteAllByUser_UserId(String user_userId);

    List<LikedCategoryEntity> findAllByUser_UserId(String user_userId);
}
