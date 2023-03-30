package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

   MemoryMemberRepository repo = new MemoryMemberRepository();

   //Test 끝난 후 클리어 해주는 메서드
   // 콜백 메서드
   @AfterEach
   public void afterEach(){
      repo.clearStore();
   }

   @Test
   public void save(){
      Member member = new Member();
      member.setName("summer");

      repo.save(member);

      //Optional 에서 값을 꺼낼 때는 ... .get()
      Member result = repo.findById(member.getId()).get();
//      Assertions.assertEquals(member, null);
      // 조금 더 편하게 쓰게 해주는

      //member 가 result랑 똑같아?
      assertThat(member).isEqualTo(result);
   }

   @Test
   public void findByName(){
      Member member1 = new Member();
      member1.setName("summer1");
      repo.save(member1);

      Member member2 = new Member();
      member2.setName("summer2");
      repo.save(member2);

      Member result = repo.findByName("summer1").get();
      assertThat(result).isEqualTo(member1);

      List<Member> result1 = repo.findAll();

      assertThat(result1.size()).isEqualTo(2);


   }

}
