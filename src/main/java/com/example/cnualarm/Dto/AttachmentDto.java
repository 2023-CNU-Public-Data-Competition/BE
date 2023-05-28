package com.example.cnualarm.Dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentDto {
    private int attachNo;
    private int articleNo;
    private String fileNm;
    private Date createDt;
    private String attachAddr;
}
