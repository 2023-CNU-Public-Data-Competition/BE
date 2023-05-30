package com.example.cnualarm.Dto;

import com.example.cnualarm.Entity.Tag;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostDto {
    private int articleNo;
//    private int categoryNo;
    private CategoryDto categoryDto;
    private String articleTitle;
    private String articleText;
    private String writerName;
    private int clickCnt;
    private Date updateDate;
    private Tag tag;
    private List<AttachmentDto> attachmentDtoList;
}
