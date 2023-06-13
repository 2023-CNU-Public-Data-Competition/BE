package com.example.cnualarm.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "scrap")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScrapEntity {
    @Id
    @GeneratedValue
    @Column(name = "scrap_no", columnDefinition = "int")
    private int scrapNo;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @ManyToOne(targetEntity = PostEntity.class)
    @JoinColumn(name = "article_no", referencedColumnName = "article_no")
    private PostEntity post;

    public ScrapEntity(String userId, int articleNo) {
        this.user = new UserEntity(userId);
        this.post = new PostEntity(articleNo);
    }
}
