package thisjava.throwstudy;

public class Q7_WrongPasswordException extends Exception{   // 일반 예외로 선언
    public Q7_WrongPasswordException(){ }
    
    public Q7_WrongPasswordException(String message) {   // 예외 메시지를 입력 받는 생성자
        super(message); 
    }
}
