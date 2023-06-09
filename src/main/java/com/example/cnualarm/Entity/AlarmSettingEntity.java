package com.example.cnualarm.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alarm_setting")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlarmSettingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarmsetting_id", columnDefinition = "INT")
    private int alarmSettingId;

    @Column(name = "user_id", columnDefinition = "VARCHAR(30)")
    private String userId;

    @Column(name = "alarm_type", columnDefinition = "VARCHAR(10)")
    private String alarmType;

    @Column(name = "alarm_nm", columnDefinition = "VARCHAR(30)")
    private String alarmName;
}
