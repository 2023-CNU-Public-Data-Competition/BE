package com.example.cnualarm.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "attachment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentEntity {
    @Id
    @Column(name = "attach_no", columnDefinition = "INT")
    private int attachNo;
    @ManyToOne(targetEntity = PostEntity.class)
    @JoinColumn(name = "article_no", referencedColumnName = "article_no")
    private PostEntity postEntity;
    @Column(name = "file_nm", columnDefinition = "TEXT")
    private String fileNm;
    @Column(name = "create_dt", columnDefinition = "DATE")
    private Date createDt;
    @Column(name = "attach_addr", columnDefinition = "TEXT")
    private String attachAddr;
}
