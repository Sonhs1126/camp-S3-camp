package com.sparta.campproject.dto;

import com.sparta.campproject.entity.Camp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class CampDto {
    private String title;
    private String review;
    private String nickname;

    public CampDto(Camp camp) {
        this.title = camp.getTitle();
        this.review = camp.getReview();
        this.nickname = camp.getNickname();
    }
}