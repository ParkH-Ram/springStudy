package thisjava;

public class ThrowException {
    // 메서드 선언
    public static void printLength(String data){
        int result = data.length(); // date의 길이를 파악 null일경우 NullPointException발생 시키기위한
        System.out.println("문자 수 " + result);
    }
    //메인
    public static void main(String[]args){
        System.out.println("프로그램 시작");
        printLength("ThisIsJava");
        printLength(null);  // 매개 값을 null로 설정
        System.out.println("종료");
    }
}
