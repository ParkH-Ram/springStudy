package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repo = new MemoryMemberRepository();
    
    @AfterEach            // 테스트가 끝날 때마다 레포지토리를 지워 주는 메서드
    public void afterEach(){  // 콜백 메서드
        repo.clearStore();   // 테스트가 끝나고 실행 될때마다 저장소를 한번씩 비움
        
    }

    @Test
    public void save() {
        Member member1 = new Member();
        member1.setName("spring1");
        repo.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repo.save(member2);

        Member result = repo.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

//        repo.save(member);

        // 값이 똑같으면 true 리턴
 /*       Member result = repo.findById(member1.getId()).get();
//        System.out.println("result = " + (result == member));
//        Assertions.assertEquals(member, result);    // 기대하는 것?

        assertThat(member1).isEqualTo(result);  // 멤버가 리절트랑 똑같애~  그냥 읽히죠 ~
        // 실무에서는 빌드툴이랑 엮어서 오류 통과 못하면 다음 단계 x*/


    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repo.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repo.save(member2);

        List<Member> result = repo.findAll();
        assertThat(result.size()).isEqualTo(2);
    }


}
