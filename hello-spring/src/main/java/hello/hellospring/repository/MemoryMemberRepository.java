package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;   // 실무에서는 동시성 문제 때문에 long 말고 다른것~

    @Override
    public Member save(Member member) {
        member.setId(++sequence);       //멤버를 생성할 때 마다 시퀀스 값을 하나씩 올려줌
        store.put(member.getId(), member);  //스토어에다가 넣기전에 멤버에 아이디 값을 세팅해주고 세이브하기 전에 이름은 넘어 온 상태
        return member;
    }

    @Override
    public Optional<Member> frinById(Long id) {

        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {

        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {

        return null;
    }
}
