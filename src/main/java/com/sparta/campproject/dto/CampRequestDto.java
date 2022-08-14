package com.sparta.campproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CampRequestDto {
    private String title;
    private String location;
    private String review;
}
