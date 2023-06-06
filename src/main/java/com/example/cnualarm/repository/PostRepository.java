package com.example.cnualarm.repository;

import com.example.cnualarm.Entity.PostEntity;
import com.example.cnualarm.Entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    List<PostEntity> findByCategoryNo(int categoryNo);
    List<PostEntity> findByCategoryNoIn(Collection<Integer> categoryNo);
    List<PostEntity> findByCategoryNoAndTag(int categoryNo, Tag tag);
    List<PostEntity> findByCategoryNoInAndTag(Collection<Integer> categoryNo, Tag tag);
}
