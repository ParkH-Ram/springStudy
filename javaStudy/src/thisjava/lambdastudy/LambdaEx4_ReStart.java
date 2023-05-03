package thisjava.lambdastudy;

import java.util.function.Predicate;

public class LambdaEx4_ReStart {
    public static void main(String[] args) {
        // 람다식을 쓰려면 함수형 인터페이스로 사용해야 한다.
        /*`MyFunction3 mf = new MyFunction3() {
            @Override
            public int max(int a, int b) {
                return  a>b ? a: b;
            }
        };*/
        // ---> 이걸 간단하게
        // 람다식 ( 익명객체) 를 다루기 위한 참조변수의 타입은 함수형 인터페이스로 한다.
        MyFunction3 f = (a, b) -> a > b ? a: b;

        int value = f.max(3, 5);  // 함수형 인터페이스
        System.out.println(value);

        // Predicate<T> 조건식을 표현하는데 사용됨.
        // 매개 변수는 하나. 반환 타입은 Boolean 
        Predicate<String> isEmptyStr = s -> s.length() ==0;  // s.length() == 0 << 이 이름이 .test()

        //test(문자열 입력)      // 문자가 없으면 True 리턴
        if (isEmptyStr.test("")) // if (s.length() == 0 ) 같은 뜻
            System.out.println("This is an empty String.");
    }
}

//함수형 인터페이스 선언
@FunctionalInterface            // 함수형 인터페이스는 하나의 추상메서드만 가져야 한다. 그걸 확인해주는 어노테이션
interface MyFunction3 {
    int max(int a, int b);

}
