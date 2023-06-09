package com.example.cnualarm.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmSetingDTO {
    private String id;
    private String alarmType;
    private List<String> list;
}
