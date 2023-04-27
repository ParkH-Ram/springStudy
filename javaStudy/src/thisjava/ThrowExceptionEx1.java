package thisjava;

public class ThrowExceptionEx1 {
    // 메서드 선언
    public static void printLength(String data){
        try{
        int result = data.length(); // date의 길이를 파악 null일경우 NullPointException발생 시키기위한
        System.out.println("문자 수 " + result);
        } catch(NullPointerException e){
            System.out.println(e.getMessage());
        }finally {
            System.out.println("시스템 종료");
        }
    }
    public static void main(String[]args){
        System.out.println("시작");
        printLength("ThisIsJava");
        printLength(null);
    }

}
