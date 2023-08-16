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

    /**
     * 23 - 8 - 11
     * **/
    public List<ReplyDto> getReplyList(Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(EntityExistsException::new);

        List<ReplyDto> replyDtos = new ArrayList<>();

        for(Reply reply : replyRepository.findByBoard(board)){
            replyDtos.add(ReplyDto.of(reply));
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

    public void deleteReply(Long replyId) {
        replyRepository.deleteById(replyId);
    }

    public String getContent(Long replyId){
        return replyRepository.findById(replyId).orElseThrow(EntityExistsException::new)
            .getContent();


    }
}
