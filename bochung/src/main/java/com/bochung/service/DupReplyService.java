package com.bochung.service;

import com.bochung.dto.DupReplyDto;
import com.bochung.entity.DupReply;
import com.bochung.entity.Members;
import com.bochung.entity.Reply;
import com.bochung.repository.DupReplyRepository;
import com.bochung.repository.MemberRepository;
import com.bochung.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DupReplyService {

    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;
    private final DupReplyRepository dupReplyRepository;

    public Long saveDupReply(Long replyId, String content, String userEmail){

        Reply reply = replyRepository.findById(replyId).orElseThrow(EntityExistsException::new);

        Members members = memberRepository.findByEmail(userEmail);

        DupReply dupReply = DupReply.builder()
            .reply(reply)
            .members(members)
            .writer(members.getName())
            .content(content)
            .build();
        dupReplyRepository.save(dupReply);
        return reply.getBoard().getId();

    }

    public List<DupReplyDto> getDupReplys(Long replyId){
        Reply reply = replyRepository.findById(replyId).orElseThrow(EntityExistsException::new);

        List<DupReplyDto> dupReplyDtoList = new ArrayList<>();

        for(DupReply dupReply : dupReplyRepository.findByReply(reply)){
            dupReplyDtoList.add(DupReplyDto.of(dupReply));
        }

        return dupReplyDtoList;
    }

    public void deleteDupReply(Long dupReplyId) {
        dupReplyRepository.deleteById(dupReplyId);
    }

    public Long updateDupReply(Long dupReplyId, String content) {
        DupReply dupReply = dupReplyRepository.findById(dupReplyId)
            .orElseThrow(EntityExistsException::new);
        dupReply.updateDupReply(content);
        return dupReply.getReply().getBoard().getId();
    }
}
