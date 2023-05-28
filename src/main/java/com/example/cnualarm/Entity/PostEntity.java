package com.example.cnualarm.Entity;

import com.example.cnualarm.Utils.Tag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {

    @Id
    @Column(name = "article_no", columnDefinition = "int")
    private int articleNo;

    @Column(name = "category_no", insertable=false, updatable=false)
    private int categoryNo;

    @ManyToOne(targetEntity = CategoryEntity.class)
    @JoinColumn(name = "category_no", referencedColumnName = "category_no")
    private CategoryEntity categoryEntity;

    @Column(name = "board_no", columnDefinition = "INT")
    private int boardNo;

    @Column(name = "article_title", columnDefinition = "TEXT")
    private String articleTitle;

    @Column(name = "article_text", columnDefinition = "TEXT")
    private String articleText;

    @Column(name = "writer_nm", columnDefinition = "TEXT")
    private String writerName;

    @Column(name = "click_cnt", columnDefinition = "INT")
    private int clickCnt;

    @Column(name = "attach_cnt", columnDefinition = "INT ")
    private int attachCnt;

    @Column(name = "update_dt", columnDefinition = "DATE")
    private Date updateDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "tag", columnDefinition = "VARCHAR[30]")
    private Tag tag;

    @OneToMany(mappedBy = "postEntity")
    private List<AttachmentEntity> attachmentEntityList;
}
