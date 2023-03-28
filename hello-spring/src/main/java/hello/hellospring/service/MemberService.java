package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
   private final MemberRepository memberRepository = new MemoryMemberRepository();

   @Autowired
   public MemberService(MemoryMemberRepository memberRepository) {
   }

   //회원 가입

   public Long join(Member member){
      // 같은 이름이 있는 중복 회원 x
      // 옵셔널을 바로 반환하는건 배드코드
//      Optional<Member> result = memberRepository.findByname(member.getName())
//      // 만약 값이 있으면
//      result.ifPresent(m -> {
//         throw new IllegalStateException("이미 존재 하는 회원");
//      });

      
      //findByName을 해 그 결과는 옵셔널 멤버니까 옵셔널 멤버 . ifPresent 해서 값을 하나씩 비교~ 
      // 값이 존재 하면 이미 존재하는 회원 출력 
      validateDuplacateMember(member);       // 중복 회원 검증
      memberRepository.save(member);
      return member.getId();
   }

   private void validateDuplacateMember(Member member) {
      memberRepository.findByname(member.getName())
         .ifPresent(m -> {
            throw new IllegalStateException("이미 존재 하는 회원");
      });
   }

   // 전체 회원 조회
   public List<Member> findMembers(){
      return memberRepository.findAll();
   }

   public Optional<Member> findOne(Long memberId){
      return memberRepository.findById(memberId);
   }
}
