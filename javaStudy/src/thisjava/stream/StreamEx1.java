package thisjava.stream;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class StreamEx1 {
    public static void main(String[]args){
        // Set  컬렉션 생성 셋은 하나씩 불러 올 수 없다.
        Set<String> str = new HashSet<>();
        str.add("홍길동");
        str.add("임꺽정");
        str.add("박찬호");

        //Stream 을 이용한 요소 반복 처리

        Stream<String> stream = str.stream();   //스트림 받고
        stream.forEach(name-> System.out.println(name)); //   스트림에 저장된 값을 하나씩 돌면서 출력 

    }
}
