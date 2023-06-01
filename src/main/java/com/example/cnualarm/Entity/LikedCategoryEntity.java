package com.example.cnualarm.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "liked_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikedCategoryEntity {

    @Id
    @GeneratedValue
    @Column(name = "liked_category_no", columnDefinition = "int")
    private int likedCategoryNo;

    @JsonIgnore
    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @ManyToOne(targetEntity = CategoryEntity.class)
    @JoinColumn(name = "category_no", referencedColumnName = "category_no")
    private CategoryEntity category;

    public LikedCategoryEntity(UserEntity user, CategoryEntity categoryFromDto) {
        this.user = user;
        this.category = categoryFromDto;
        user.getLikedCategoryEntities().add(this);
    }
}
