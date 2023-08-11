package com.bochung.service;

import com.bochung.dto.BoardDto;
import com.bochung.entity.Board;
import com.bochung.entity.Members;
import com.bochung.repository.BoardRepository;
import com.bochung.repository.MemberRepository;
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
    private final MemberRepository memberRepository;
    public Board saveBoard(BoardDto boardDto, String email) {
        /** 23-8-9 작성자 추가 하기 위한
         *  이메일을 찾아서 members 객체안에 닮은다
         * **/
        System.out.println("i");
        Members members = memberRepository.findByEmail(email);
        boardDto.setWriter(members.getName());
        System.out.println("in2");
        return boardRepository.save(Board.createBoard(boardDto, members));
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
    // 23-8-3 삭제 구현
    // 삭제는 레포지토리에서 바로 삭제하는거라 dto로 변환하지 않는다.
    public void deleteBoard(Long boardId) {

        boardRepository.deleteById(boardId);
    }

    public void updateBoard(BoardDto boardDto) {
        Board board = boardRepository.findById(boardDto.getId())
            .orElseThrow(EntityExistsException::new);
        board.updateBoard(boardDto);

    }

}
