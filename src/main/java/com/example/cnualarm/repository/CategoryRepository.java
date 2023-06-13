package com.example.cnualarm.repository;

import com.example.cnualarm.Entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    List<CategoryEntity> findByCategoryTypeOrderByCategoryName(String categoryType);

    @Query(value = "select category_type from category group by category_type order by category_type", nativeQuery = true)
    List<String> getCategoryTypes();

}
