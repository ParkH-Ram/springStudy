package thisjava.lambdastudy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaEx5_Stream {
    public static void main(String[] args) {
        Supplier<Integer> s  = () ->(int)(Math.random()*100 +1); // 1~100 사이의 난수를 반환
        Consumer<Integer> c =  i -> System.out.print(i + ", ");   // 콘솔에 출력
        Predicate<Integer> p = i -> i%2==0;  // 항상 반환 타입이 Boolean 이라 생략  // 짝수면 트루
        Function<Integer, Integer> f= i -> i/10*10; //  i 의 1의 자리를 없앤다.   47/10 -> 4.7 * 10 47 // Integer 타입이라 소수점 자리는 자동으로 0이 된다.

        List<Integer> list = new ArrayList<>();
        makeRandomList(s, list);        //list를 random 값으로 채운다.
        System.out.println(list);       // 랜덤 난수 출력
        printEvenNum(p, c, list);       // 짝수 검사
        List<Integer> newList = doSomething(f, list); // 1의 자리를 없애는 리스트를 이용해서  새로운 리스트 반환
        System.out.println(newList); //

    }
    //Function<Integer, Integer> f= i -> i/10*10; //  i 의 1의 자리를 없앤다.   47/10 -> 4.7 * 10 47 // Integer 타입이라 소수점 자리는 자동으로 0이 된다.
    static <T> List<T> doSomething(Function<T, T> f, List<T> list){  // 1의자리를 없애는 람다식을 받아서 list 에 적용
        List<T> newList = new ArrayList<T>(list.size()); // 리스트랑 똑같은 size의 새로운 리스트 생성

        for (T i : list){
            newList.add(f.apply(i));  //리스트에 있는 값들을 하나씩 읽어서 일의 자리를 없앤 뒤 newList 에 저장
        }
        return newList;
    }


   // Consumer<Integer> c =  i -> System.out.println(i + ", ");
    // Predicate<Integer> p = i -> i%2==0; // 짝수인지 검사
    static <T> void printEvenNum(Predicate<T> p, Consumer<T> c, List<T> list) { // 짝수 인지 검사하고 컨슈머로 출력
        System.out.print("[");
        for (T i : list){  // 리스트에 있는 모든 요소를 하나씩 꺼내어
            if(p.test(i)){      // 짝수 인지 검사. p.test
                c.accept(i);    // true 면 컨슈머로 처리 하는 메서드  i->System.out.println(i+", ") << 출력
             }

        }
        System.out.println("]");
    }


    static <T>  void makeRandomList(Supplier<T> s, List<T> list) {
        for(int i=0; i<10; i++){        // 람다 식을 주면 그 람다식에 있는걸 10번 돌면서 꺼낸다
            list.add(s.get());          // 서플라이로 부터 10번 받아서 list에 추가한다.
        }
    }
}
