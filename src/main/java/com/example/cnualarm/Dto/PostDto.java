package com.example.cnualarm.Dto;

import com.example.cnualarm.Utils.Tag;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
