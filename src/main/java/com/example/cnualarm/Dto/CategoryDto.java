package com.example.cnualarm.Dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private int categoryNo;
    private String categoryType;
    private String categoryName;
}
