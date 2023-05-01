package thisjava.collection;

import java.util.HashSet;
import java.util.Set;

public class CollectionEx2_Set {
    public static void main(String[] args) {
        //HashSet 컬렉션 생성
        Set<String> set = new HashSet<>();

        //객체 저장'
        set.add("Java");
        set.add("JDBC");
        set.add("JPA");
        set.add("Java");
        set.add("java");
        set.add("Spring");

        int size = set.size();
        System.out.println("size = " + size);



        for(String set23 :  set){
            System.out.println(set23);

        }


    }
}
