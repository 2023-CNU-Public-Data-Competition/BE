package com.example.cnualarm.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

import java.sql.Date;

@Entity
@Table(name = "alarm")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlarmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id", columnDefinition = "INT")
    private int alarmId;

    @Column(name = "alarm_type", columnDefinition = "VARCHAR(10)")
    private String alarmType;

    @Column(name = "alarm_nm", columnDefinition = "VARCHAR(30)")
    private String alarmName;

    @Column(name = "user_id", columnDefinition = "VARCHAR(30)")
    private String userId;

    @Column(name = "article_no", columnDefinition = "INT")
    private int articleNo;

    @Column(name = "article_title", columnDefinition = "TEXT")
    private String articleTitle;

    @Column(name = "update_dt", columnDefinition = "DATE")
    private Date updateDate;
}
