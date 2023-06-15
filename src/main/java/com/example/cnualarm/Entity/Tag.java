package com.example.cnualarm.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

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

    public static Tag get(String korean) throws Exception {
        return Arrays.stream(Tag.values())
                .filter(tag -> tag.getKorean().equals(korean))
                .findFirst().orElseThrow(() -> new Exception("tag not defined."));
    }
}
