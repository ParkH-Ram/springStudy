package thisjava.throwstudy;

public class Q7_NotExistIDException extends  Exception{  // 일반 예외로 선언
    public Q7_NotExistIDException(){}                   // 기본 생성자
    public Q7_NotExistIDException(String message){      // 예외 메시지를 입력받는 생성자
        super(message);
    }
}
