package com.board.service;

import com.board.domain.Reply;
import com.board.dto.PageRequestDto;
import com.board.dto.PageResponseDto;
import com.board.dto.ReplyDto;
import com.board.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class ReplyServiceImpl implements ReplyService{
    // repo랑 modelmapper 주입 받아  작성

    private final  ReplyRepository replyRepository;
    private final ModelMapper modelMapper;


    @Override
    public Long register(ReplyDto replyDto) {  // 작성
        Reply reply = modelMapper.map(replyDto, Reply.class);

        Long rno = replyRepository.save(reply).getRno();

        return rno;
    }


    // 댓글 읽기
    @Override
    public ReplyDto read(Long rno) {
        Optional<Reply> replyOptional = replyRepository.findById(rno);

        Reply reply = replyOptional.orElseThrow();

        return modelMapper.map(reply, ReplyDto.class);
    }

    // 댓글 수정
    @Override
    public void modify(ReplyDto replyDto) {

        Optional<Reply> replyOptional = replyRepository.findById(replyDto.getRno());

        Reply reply = replyOptional.orElseThrow();

        reply.changeText(replyDto.getReplyText()); // 댓글의 내용만 수정 가능

    }


    //댓글 삭제
    @Override
    public void remove(Long rno) {

        replyRepository.deleteById(rno);

    }

    //특정 게시물 댓글 목록 처리
    // 실제 반환 타입은 Reply가 아니라 ReplyDto 타입이므로 ReplyServiceImpl에서 이를 변환
    @Override
    public PageResponseDto<ReplyDto> getListOfBoard(Long bno, PageRequestDto pageRequestDto) {

        Pageable pageable = PageRequest.of(pageRequestDto.getPage() <=0? 0: pageRequestDto.getPage() -1,
                pageRequestDto.getSize(),
                Sort.by("rno").ascending());

        Page<Reply> result = replyRepository.listOfBoard(bno, pageable);

        List<ReplyDto> dtoList =
                result.getContent().stream().map(reply -> modelMapper.map(reply, ReplyDto.class))
                        .collect(Collectors.toList());

        return PageResponseDto.<ReplyDto>withAll()
                .pageRequestDto(pageRequestDto)
                .DtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
