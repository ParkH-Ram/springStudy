package com.bochung.service;

import com.bochung.dto.ReplyDto;
import com.bochung.entity.Board;
import com.bochung.entity.Members;
import com.bochung.entity.Reply;
import com.bochung.repository.BoardRepository;
import com.bochung.repository.MemberRepository;
import com.bochung.repository.ReplyRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final DupReplyService dupReplyService;

    /**
     * 23 - 8 - 11
     * **/
    public List<ReplyDto> getReplyList(Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(EntityExistsException::new);

        List<ReplyDto> replyDtos = new ArrayList<>();

        for(Reply reply : replyRepository.findByBoard(board)){
            ReplyDto replyDto = ReplyDto.of(reply);
            replyDto.setDupReplyDtoList(dupReplyService.getDupReplys(reply));
            replyDtos.add(replyDto);
        }
        return replyDtos;

    }

    public void saveReply(Long boardId, String content, String userEmail) {

        Board board = boardRepository.findById(boardId).orElseThrow(EntityExistsException::new);
        Members members = memberRepository.findByEmail(userEmail);

        Reply reply = Reply.builder()
            .content(content)
            .members(members)
            .board(board)
            .writer(members.getName())
            .build();

        replyRepository.save(reply);

    }

    /**
     * 23-9-14 딜리트 로직 변경
     *
     * **/
     // 이 동작은 리턴 값이 필요 없을 때  사용
    //    public void deleteReply(Long replyId) {
//        replyRepository.deleteById(replyId);
//    }
    // 리턴 값이 필요해서 사용. 딜리트만 정확하게 하기 위한 코드
    public Long deleteReply(Long replyId){

        // 객체를 만들어서 그 객체의 아이디로  찾아서 값을 넣어 주고
        Reply reply = replyRepository.findById(replyId).orElseThrow(EntityExistsException::new); // 엔티티 존재  오류

        //디비에 딜리트 쿼리를 날린다
        replyRepository.delete(reply);

        // 댓글 객체의 보드 부모를 찾아서 그 아이디를 리턴
        return reply.getBoard().getId();
    }


    public String getContent(Long replyId){
        return replyRepository.findById(replyId).orElseThrow(EntityExistsException::new)
            .getContent();

    }

    /** 23 - 8 - 17  댓글 갈아끼우고 **/
    public Long updateReply(Long replyId, String content) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(EntityExistsException::new);

        reply.updateReply(content);

        return reply.getBoard().getId();
    }



}
