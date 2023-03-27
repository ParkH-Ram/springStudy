package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
   // 실무에서는 동시성 문제가 있을 수 있어서 공유되는 변수일 때는 컨커런트 해쉬맵을 쓴다
   private static Map<Long, Member> store = new HashMap<>();
   //시퀀스는 0 1 2 이런 키 값을 생성해준다
   private static long sequence = 0L;

   @Override
   public Member save(Member member) {
      member.setId(++sequence);
      store.put(member.getId(), member);
      return member;
   }

   @Override
   public Optional<Member> findById(Long id) {
      // 널이 반환 될 가능성이 있으면 Optional로 감싼다
      // 클라이언트에서 어떤 동작이 가능하게 하기 위해
      return Optional.ofNullable(store.get(id));
   }

   @Override
   public Optional<Member> findByname(String name) {
     return store.values().stream()
                  .filter(member -> member.getName().equals(name))
                  .findAny();
         // member.getName 이 파라미터로 넘어온 name 과 값이 같은지 확인
         // 같은 경우만 리턴이 된다
         // findAny()는 하나라도 찾는 것.
         // 끝까지 돌렸는데 없으면 Optional에 null이 포함 돼서 반환

   }

   @Override
   public List<Member> findAll() {
      // sotre.values 가 member 들이 반환 된다
      return new ArrayList<>(store.values());
   }
   public void clearStore(){
      store.clear();
   }
}
