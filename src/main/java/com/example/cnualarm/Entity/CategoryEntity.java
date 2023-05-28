package com.example.cnualarm.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    @Id
    @Column(name = "category_no", columnDefinition = "int")
    private int categoryNo;
    @Column(name = "category_type", columnDefinition = "VARCHAR(30)")
    private String categoryType;
    @Column(name = "category_name", columnDefinition = "VARCHAR(30)")
    private String categoryName;
}
