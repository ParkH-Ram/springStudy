package com.bochung.service;

import com.bochung.dto.BoardDto;
import com.bochung.entity.Board;
import com.bochung.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class BoardService {

    private final BoardRepository boardRepository;

    public Board saveBoard(BoardDto boardDto) {
        return boardRepository.save(Board.createBoard(boardDto));
    }

    public List<BoardDto> getBoardList() {
        List<BoardDto> boardDtos = new ArrayList<>();

        for(Board board : boardRepository.findAll()) {
            boardDtos.add(BoardDto.of(board));
            log.info(BoardDto.of(board));
        }

//        List<Board> boards = boardRepository.findAll();
//        for(Board board : boards) {
//            boardDtos.add(BoardDto.of(board));
//        }

//        List<Board> boards = boardRepository.findAll();
//        for(int i=0; i<boards.size(); i++) {
//            boardDtos.add(BoardDto.of(boards.get(i)));
//        }

        return boardDtos;
    }

    // 23-8-2
    public BoardDto showDetail (Long boardId) {
        Board board = boardRepository.findById(boardId)
                // 널 검증
                .orElseThrow(EntityExistsException::new); // :: 자바  문법에서 메서드를 추출해오는 ...

        return BoardDto.of(board);

    }
}
