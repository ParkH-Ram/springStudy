package com.example.service;

import com.example.domain.Board;
import com.example.dto.BoardDto;
import com.example.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board saveBoard(BoardDto boardDto){
        return boardRepository.save(Board.createBoard(boardDto));

    }
    public List<BoardDto> getBoardList() {
        List<BoardDto> boardDto = new ArrayList<>();

        for(Board board : boardRepository.findAll()){
            boardDto.add(BoardDto.of(board));

        }
        return boardDto;
    }
}
