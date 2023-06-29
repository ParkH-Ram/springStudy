package com.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDto<E> {

    private int page;
    private int size;
    private int total;

    // 시작 번호
    private int start;

    // 끝 페이지 번호
    private int end;

    // 이전 페이지 존재 여부
    private boolean prev;

    // 다음 페이지 존재 여부
    private boolean next;

    private List<E> DtoList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDto(PageRequestDto pageRequestDto, List<E> DtoList, int total){
        if (total <= 0){
            return;
        }

        this.page = pageRequestDto.getPage();
        this.size = pageRequestDto.getSize();

        this.total = total;
        this.DtoList = DtoList;

        this.end = (int)(Math.ceil(this.page / 10.0 )) * 10;   // 화면에서 마지막 번호

        this.start = this.end - 9; // 화면에서 시작 번호

        int last = (int)(Math.ceil((total/(double)size))); // 데이터의 개수를 계산한 마지막 페이지 번호

        // 마지막  번호가 라스트보다 작으면 라스트가 마지막
        this.end = end > last ? last: end;

        this.prev = this.start > 1;

        this.next = total > this.end * this.size;
    }


}
