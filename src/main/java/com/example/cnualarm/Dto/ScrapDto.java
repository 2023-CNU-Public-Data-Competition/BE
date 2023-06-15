package com.example.cnualarm.Dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScrapDto {
    private int scrapNo;
    private int categoryNo;
    private String categoryName;
    private int articleNo;
    private String articleTitle;
    private String writerName;
    private Date updateDate;
    private String tag;
}
