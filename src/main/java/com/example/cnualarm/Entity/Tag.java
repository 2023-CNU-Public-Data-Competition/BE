package com.example.cnualarm.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Tag {
    ALL("전체"),
    CONTEST("대회"),
    INTERN_JOB("인턴/취업"),
    SCHOLARSHIP("장학"),
    SCHEDULE("학사일정"),
    GRADUATION("졸업"),
    LECTURE("특강"),
    NOTICE("기타 공지");

    private final String korean;
}
