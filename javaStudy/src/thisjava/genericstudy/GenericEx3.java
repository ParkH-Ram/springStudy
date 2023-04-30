package thisjava.genericstudy;

public class GenericEx3 {
            // T는 타입 파라미터라고 지정
            // 제네릭 선언 전에 앞에 <T> 를 해주면 << <T>가 타입 파라미터라고 알려주는 것
    public static <T> GenericEx3_Method_Box<T> boxing(T t){
        GenericEx3_Method_Box<T> box = new GenericEx3_Method_Box<>();
        box.set(t);
        return  box;
    }

    public static void main(String[] args) {
        //제네릭 메서드 호출
        GenericEx3_Method_Box<Integer> box1 = boxing(100); // T 를 Integer로 대체하고 값을 100을 넣어줌
        int value = box1.get();
        System.out.println(value);

        //제네릭 메서드 호출
        GenericEx3_Method_Box<String> box2 = boxing("만나서 반갑소");
        String value2= box2.get();
        System.out.println(value2);

    }
}
