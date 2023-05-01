package thisjava.collection;

import java.util.HashSet;
import java.util.Set;

public class CollectionEx2_Set_HashSet {
    public static void main(String[] args) {
        //HashSet 컬렉션 생성
        Set<CollectionEx2_Set_Member> set = new HashSet<>();

        //CollectionEx2_Set_Member 객체 저장
        set.add(new CollectionEx2_Set_Member("홍길동", 30));
        set.add(new CollectionEx2_Set_Member("홍지로", 38));
        set.add(new CollectionEx2_Set_Member("홍라이", 43));
        set.add(new CollectionEx2_Set_Member("홍라이", 43));

        for(CollectionEx2_Set_Member set2 : set){
            System.out.println(set2);
        }
        int setSize = set.size();
        System.out.println("setSize = " + setSize);


    }
}
