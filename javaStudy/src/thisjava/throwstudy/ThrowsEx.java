package thisjava.throwstudy;

public class ThrowsEx {
    public static void main(String[] args) {
        try {
            findClasS();
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();

            // 메인 메서드에서 마지막에 예외를 처리 한다.
        }
    }

    public  static  void findClasS() throws ClassNotFoundException{ //이 예외가 발생할 수 있다. 호출 하는 곳에서 예외 처리를 해야 한다.
        Class.forName("java.lang.String2");
    }
}
