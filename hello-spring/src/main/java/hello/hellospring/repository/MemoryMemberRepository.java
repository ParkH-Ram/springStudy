package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();  // 어딘가 저장..? 맵 ..
    private static long sequence = 0L;   // 실무에서는 동시성 문제 때문에 long 말고 다른것~

    @Override
    public Member save(Member member) {
        member.setId(++sequence);       //멤버를 생성할 때 마다 시퀀스 값을 하나씩 올려줌
        store.put(member.getId(), member);  //스토어에다가 넣기전에 멤버에 아이디 값을 세팅해주고 세이브하기 전에 이름은 넘어 온 상태
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(store.get(id));  // null 반환될 가능성이 있으면 Optional.ofNullable()로 감싸준다
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()  // 루프로 돌리겠다
                .filter(member -> member.getName().equals(name))  // 멤버에서 멤버.겟네임이 name 파라미터로 넘어온 값하고 비교
                .findAny();                                     // 하나라도 참으면 반환 (결과가 옵셔널로 반환) , 끝까지 돌렸는데 없으면 null 포함 되어 반환

    }

    @Override
    public List<Member> findAll() {

        return new ArrayList<>(store.values());  // store.values()  == members
    }
    public void clearStore(){
        store.clear();   // 스토어를 싹 비우거든요~
    }
}
