package com.example.cnualarm.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @Column(name = "user_id", columnDefinition = "VARCHAR(30)")
    private String userId;

    @Column(name = "password", columnDefinition = "TEXT")
    private String password;

    private Role role;

    @OneToMany(mappedBy = "user")
    private List<LikedCategoryEntity> likedCategoryEntities = new ArrayList<>();

    public UserEntity(String id, String pw, Role role) {
        this.userId = id;
        this.password = pw;
        this.role = role;
    }

    public UserEntity(String userId) {
        this.userId = userId;
    }
}
