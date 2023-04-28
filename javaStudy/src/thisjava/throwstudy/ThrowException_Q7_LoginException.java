package thisjava.throwstudy;

public class ThrowException_Q7_LoginException {
    public static void main(String[] args) {
        try{
            login("white","12345");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        try{
            login("blue", "54321");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void login(String id, String password)throws Q7_NotExistIDException, Q7_WrongPasswordException {
        // id가 blue가 아니라면 NotExistIDException을 발생시킴
        if(!id.equals("blue")){
            throw new Q7_NotExistIDException("아이디 확인 바람");
        }
        //비밀번호가 12345가 아니라면 WrongPasswordException을 발생시킴
        if(!password.equals("12345")){
            throw new Q7_WrongPasswordException("비밀번호 확인 바람");
        }
    }
}
