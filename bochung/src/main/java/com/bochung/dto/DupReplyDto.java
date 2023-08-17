package com.bochung.dto;


import com.bochung.entity.DupReply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class DupReplyDto {

    private Long id;
    private String content;
    private String writer;
    private String memberEmail;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;

    private static ModelMapper modelMapper = new ModelMapper();

    public static DupReplyDto of(DupReply dupReply){
        return modelMapper.map(dupReply, DupReplyDto.class);
    }
}
