package com.example.cnualarm.Dto;

import lombok.*;
import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmsDTO {
    private int articleNo;
    private String articleTitle;
    private String alarmType;
    private String alarmName;
    private Date updateDate;
}
