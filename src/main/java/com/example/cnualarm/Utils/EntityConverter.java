package com.example.cnualarm.Utils;

import com.example.cnualarm.Dto.AttachmentDto;
import com.example.cnualarm.Dto.CategoryDto;
import com.example.cnualarm.Dto.PostDto;
import com.example.cnualarm.Entity.AttachmentEntity;
import com.example.cnualarm.Entity.CategoryEntity;
import com.example.cnualarm.Entity.PostEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityConverter {
    public PostDto postToDto(PostEntity entity){
        return PostDto.builder()
                .categoryDto(categoryToDto(entity.getCategoryEntity()))
                .tag(entity.getTag())
                .articleTitle(entity.getArticleTitle())
                .writerName(entity.getWriterName())
                .updateDate(entity.getUpdateDate())
                .clickCnt(entity.getClickCnt())
                .articleText(entity.getArticleText())
                .articleNo(entity.getArticleNo())
                .attachmentDtoList(entity.getAttachmentEntityList().stream().map(this::attachmentToDto).toList())
                .build();
    }

    public AttachmentDto attachmentToDto(AttachmentEntity entity){
        return AttachmentDto.builder()
                .attachNo(entity.getAttachNo())
                .articleNo(entity.getPostEntity().getArticleNo())
                .fileNm(entity.getFileNm())
                .attachAddr(entity.getAttachAddr())
                .createDt(entity.getCreateDt())
                .build();
    }

    public static CategoryDto categoryToDto(CategoryEntity entity){
        return CategoryDto.builder()
                .categoryNo(entity.getCategoryNo())
                .categoryType(entity.getCategoryType())
                .categoryName(entity.getCategoryName())
                .build();
    }

    public static CategoryEntity categoryFromDto(CategoryDto dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setCategoryNo(dto.getCategoryNo());
        entity.setCategoryType(dto.getCategoryType());
        entity.setCategoryName(dto.getCategoryName());
        return entity;
    }
}
