package thisjava.lambdastudy;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaEx2 {
    public static void main(String[] args) {
//        Object obj = (a, b) -> a > b ? a : b; // 람다식. 익명 객체  참조 변수로 받아야 한다
//        Myfunction2 f = new Myfunction2(){
//            public int max(int a, int b){       // 오버라이딩 - 접근제어자는 좁게 못 바꾼다.
//                return a > b ? a : b ;
//            }
//        };   // 문장의 끝이니 세미콜론 있어야 한다.

        //람다식 (익명 객체)을 다루기 위한 참조변수의 타입은 함수형 인터페이스로 한다.
        Myfunction2 f = (a, b) -> a > b ? a : b ;  // 람다식 익명객체

        int value = f.max(3,5);  // 함수형 인터페이스
        System.out.println("value = " + value);

//        List<String> list = Arrays.asList("abc", "addd", "bbb", "aaa","abc");

        /*Collections.sort(list, new Comparator<String>() {  // 리스트를 정렬할 때 Comparator를 넣어 줘야 했는데
            @Override
            public int compare(String o1, String o2) { // 문자열 비교
                return o1.compareTo(o2);
            }
        });
        */
        // -->> 람다식으로 변환
        List<String> list = Arrays.asList("abc", "addd", "bbb", "aaa","abc");
        Collections.sort(list, (o1, o2)-> o2.compareTo(o1)); // Comparator 를 매개변수로 받는다

        //sort(List list, Comparator comparator)을 매개변수로 받는데...
        //Comparator C = (o1, o2) -> o2.compareTo(o1);
        // ^ 함수형 인터페이스

        System.out.println(list);
    }
}

@FunctionalInterface // 함수형 인터페이스는 단 하나의 추상 메서드만 가져야 함
interface Myfunction2{
    int max(int a, int b) ;
}




