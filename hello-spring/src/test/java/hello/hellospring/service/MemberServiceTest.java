package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
   MemberService memberService;
   MemoryMemberRepository memberRepository;

   @BeforeEach
   public void beforEatch(){
      memberRepository = new MemoryMemberRepository();

      //외부에서  memberRepository를 넣어주죠...
      //디펜더씨 인젝션
      memberService = new MemberService(memberRepository);
   }


   @AfterEach
   public void afterEach(){
      memberRepository.clearStore();
   }
   @Test
   void 회원가입() {  // test는 한글로 바꿔도 된다~
      //given
      Member member = new Member();
      member.setName("spring");

      //when
      Long saveId = memberService.join(member);

      //then
      Member findMember = memberService.findOne(saveId).get();
      assertThat(member.getName()).isEqualTo(findMember.getName());
   }

   @Test
   void 중복_회원_예외() {
      //given
      Member member1 = new Member();
      member1.setName("spring");

      Member member2 = new Member();
      member2.setName("spring");

      //when
      memberService.join(member1);
      IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 좋은 코드

      assertThat(e.getMessage()).isEqualTo("이미 존재 하는 회원");

      // 델리게이트 에서 걸려서 예외가 터진다 예외처리로 잡아 준다
   // 이거 때문에 try catch를 넣는게 별로다
/*     try{
         memberService.join(member2);
         fail();   
      }catch (IllegalStateException e){
         //정상작동
         assertThat(e.getMessage()).isEqualTo("이미 존재해유");
      }
*/


      //then
   }

   @Test
   void findOne() {
   }
}