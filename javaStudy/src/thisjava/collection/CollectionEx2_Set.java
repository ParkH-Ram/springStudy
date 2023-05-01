package thisjava.collection;

import java.util.HashSet;
import java.util.Set;

public class CollectionEx2_Set {
    public static void main(String[] args) {
        //HashSet 컬렉션 생성  // Set 컬렉션 안에 스트링만 저장,  HashSet 이라는 구현 객체를 사용
        Set<String> set = new HashSet<>();

        //객체 저장'
        set.add("Java");
        set.add("JDBC");
        set.add("JPA");
        set.add("Java");    // 중복 객체는 같은 객체로 판단해서 저장 X HashCode 도 같고  Equals 도 동등
        set.add("java");
        set.add("Spring");

        int size = set.size();
        System.out.println("size = " + size);



        for(String set23 :  set){
            System.out.println(set23);

        }


    }
}
