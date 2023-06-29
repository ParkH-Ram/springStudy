package com.board.service;

import com.board.domain.Board;
import com.board.dto.BoardDto;
import com.board.dto.BoardListReplyCountDto;
import com.board.dto.PageRequestDto;
import com.board.dto.PageResponseDto;
import com.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    private final ModelMapper modelMapper;

    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDto boardDto){
        Board board = modelMapper.map(boardDto, Board.class);

        Long bno = boardRepository.save(board).getBno();

        return bno;
    }

    @Override
    public BoardDto readOne(Long bno){
        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        BoardDto boardDto = modelMapper.map(board, BoardDto.class);

        return boardDto;
    }

    @Override
    public void modify(BoardDto boardDto){
        Optional<Board> result = boardRepository.findById(boardDto.getBno());

        Board board = result.orElseThrow();
        board.change(boardDto.getTitle(), boardDto.getContent());
        boardRepository.save(board);
    }


    @Override
    public void remove(Long bno){
        boardRepository.deleteById(bno);;
    }

    @Override
    public PageResponseDto<BoardDto> list(PageRequestDto pageRequestDto){

        String [] types = pageRequestDto.getTypes();
        String keyword = pageRequestDto.getKeyword();
        Pageable pageable = pageRequestDto.getPageable("bno");


        // 검색
        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        List<BoardDto> DtoList = result.getContent().stream()
                .map(board -> modelMapper.map(board, BoardDto.class))
                .collect(Collectors.toList());

        return PageResponseDto.<BoardDto>withAll()
                .pageRequestDto(pageRequestDto)
                .DtoList(DtoList)
                .total((int)result.getTotalElements())
                .build();

    }

    // 댓글 카운트 처리
    @Override
    public PageResponseDto<BoardListReplyCountDto> listWithReplyCount(PageRequestDto pageRequestDto) {
        String [] types = pageRequestDto.getTypes();
        String keyword = pageRequestDto.getKeyword();
        Pageable pageable = pageRequestDto.getPageable("bno");

        Page<BoardListReplyCountDto> result = boardRepository.searchWithReplyCount(types, keyword, pageable);

        return PageResponseDto.<BoardListReplyCountDto>withAll()
                .pageRequestDto(pageRequestDto)
                .DtoList(result.getContent())
                .total((int)result.getTotalElements())
                .build();
    }


}
