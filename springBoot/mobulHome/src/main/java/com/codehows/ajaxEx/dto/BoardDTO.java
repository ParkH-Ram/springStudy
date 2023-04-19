package com.codehows.ajaxEx.dto;


import com.codehows.ajaxEx.entity.Board;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;

// DTO(Data Transfer Object), VO, Bean,         Entity
@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedTime;

    private MultipartFile boardFile; //save.html -> Controller 파일 담는 용도
    private String originalFileName; // 원본 파일 이름
    private  String storedFileName;
    private int fileAttached;   // 파일 첨부 여부 (첨부 1, 미첨부 0 반환)

    public BoardDTO(Long id, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreatedTime){
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardHits = boardHits;
        this.boardCreatedTime = boardCreatedTime;
    }

    public static BoardDTO toBoardDTO(Board board) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(board.getId());
        boardDTO.setBoardWriter(board.getBoardWriter());
        boardDTO.setBoardPass(board.getBoardPass());
        boardDTO.setBoardTitle(board.getBoardTitle());
        boardDTO.setBoardContents(board.getBoardContents());
        boardDTO.setBoardHits(board.getBoardHits());
        boardDTO.setBoardCreatedTime(board.getBoardCreatedTime());
        if(board.getFileAttached() == 0){
            boardDTO.setFileAttached(board.getFileAttached());  // 0
        } else{
            boardDTO.setFileAttached(board.getFileAttached()); // 1
            // 파일 이름을 가져가야 함
            // originalFileName, storedFileName :
            // board_file_table(BoardFile)
            // join
            // select * from board_table b, board_file_table bf where b.id=bf.board_id
            // and where b.id=?
            boardDTO.setOriginalFileName((board.getBoardFileEntityList()).get(0).getOriginalFileName());
            boardDTO.setStoredFileName(board.getBoardFileEntityList().get(0).getStoredFileName());
        }
        return boardDTO;
    }


}