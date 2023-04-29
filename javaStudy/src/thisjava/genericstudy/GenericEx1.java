package thisjava.genericstudy;

public class GenericEx1 {
    public static void main(String[] args) {
        GenericBox<String> genericBox = new GenericBox<>(); // T대신 String으로 값을 받겠다
        genericBox.content = "만나서 반갑소";
        String genericContent = genericBox.content; // genericBox.content에 문자열만 대입 가능 이유는 T를 스트링으로 지정 했기 때문
        System.out.println(genericContent);

        GenericBox<Integer> genericBoxInteger = new GenericBox<>(); // T 자리에 기본타입은 올 수 없다.
        genericBoxInteger.content = 15000*50;
        int integerContent = genericBoxInteger.content; // 인티저 객체 안에 있는 인트값을 꺼내서 integerContent에 저장한다
        System.out.println(integerContent);
    }

}
