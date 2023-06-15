package com.example.cnualarm.Utils;

import com.example.cnualarm.Dto.*;
import com.example.cnualarm.Entity.*;
import org.springframework.stereotype.Component;

@Component
public class EntityConverter {
    public PostDto postToDto(PostEntity entity){
        return PostDto.builder()
                .categoryDto(categoryToDto(entity.getCategoryEntity()))
                .tag(entity.getTag().getKorean())
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

    public static ScrapDto scrapToDto(ScrapEntity entity) {
        return ScrapDto.builder()
                .scrapNo(entity.getScrapNo())
                .categoryNo(entity.getPost().getCategoryNo())
                .categoryName(entity.getPost().getCategoryEntity().getCategoryName())
                .articleNo(entity.getPost().getArticleNo())
                .articleTitle(entity.getPost().getArticleTitle())
                .writerName(entity.getPost().getWriterName())
                .tag(entity.getPost().getTag().getKorean())
                .updateDate(entity.getPost().getUpdateDate())
                .build();
    }

    public static CategoryEntity categoryFromDto(CategoryDto dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setCategoryNo(dto.getCategoryNo());
        entity.setCategoryType(dto.getCategoryType());
        entity.setCategoryName(dto.getCategoryName());
        return entity;
    }

    public static AlarmsDTO alarmsToDTO(AlarmEntity alarmEntity) {
        return AlarmsDTO.builder()
                .alarmType(alarmEntity.getAlarmType())
                .alarmName(alarmEntity.getAlarmName())
                .articleNo(alarmEntity.getArticleNo())
                .articleTitle(alarmEntity.getArticleTitle())
                .updateDate(alarmEntity.getUpdateDate()).build();
    }
}
