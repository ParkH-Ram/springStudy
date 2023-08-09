package com.bochung.dto;

import com.bochung.entity.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReplyDto {

    private String content;
    private String writer;
    private String memberEmail;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;

    public static ModelMapper modelMapper = new ModelMapper();

    public static ReplyDto of(Reply reply){
        return modelMapper.map(reply, ReplyDto.class);
    }


}
