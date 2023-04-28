package thisjava.throwstudy;

public class ThrowException_ClassforName {

    public static void main(String[]args){

        try {
            Class.forName("java.lang.String"); // ClassNotFoundException 일반예외 처리를 해줘야 하낟
            System.out.println("클래스 있슈");
        } catch (ClassNotFoundException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.out.println("클래스 없슈");
            }

        try{
            Class.forName("java.lang.String2"); // String의 풀네임 = java.lang.String
            System.out.println("클래스 있슈");
        } catch (ClassNotFoundException e){
            System.err.println(e.getMessage());
            System.out.println("클래스 없슈");
            e.printStackTrace();
            }

        }

}
