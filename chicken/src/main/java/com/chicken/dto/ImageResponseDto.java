package com.chicken.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageResponseDto {

    private String imageFileUrl;

    @Builder
    public ImageResponseDto(String imageFileUrl){
        this.imageFileUrl = imageFileUrl;
    }
}
