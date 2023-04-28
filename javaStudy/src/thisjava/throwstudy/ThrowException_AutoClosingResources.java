package thisjava.throwstudy;

public class ThrowException_AutoClosingResources{

    public static void main(String[] args)  throws Exception {
        MyResources myResources1 = null;
        try {

          // 리소스 열기
          myResources1 = new MyResources("myResources1");
          // 리소스 읽기
          System.out.println(Integer.parseInt(myResources1.read1())); // 문자열 "100" 을 숫자로 바꿔서...
          System.out.println(Integer.parseInt(myResources1.read2())); // 문자열 "ABC"를 숫자로 못 바꿔서 에러 발생

      } catch (Exception e) {
          e.printStackTrace();
      } finally{
          // 리소스 닫기
          myResources1.close();  // try catch 사용하지 않으면 에러 발생시 close가 동작안한다 그렇기 때문에 bad code
                                 // try catch finally 를 사용해야 자동 리소스 닫기를 실행
      }

        try(MyResources myResources2 = new MyResources("myResources2")) {

            /*
            리소스 열기가 따로 필요 없음 트라이 문에서 리소스를 열어 줬기 때문

            myResources2 = new MyResources("myResources2");*/
            // 리소스 읽기
            System.out.println(Integer.parseInt(myResources2.read1())); // 문자열 "100" 을 숫자로 바꿔서...
            System.out.println(Integer.parseInt(myResources2.read2())); // 문자열 "ABC"를 숫자로 못 바꿔서 에러 발생

        } catch (Exception e) {
            e.printStackTrace();
        } /*finally{
            // 리소스 닫기   try 구문에 리소스 추가 하면 자동으로 close 실행 되기 때문에  필요 없음/
            myResources2.close();  // try catch 사용하지 않으면 에러 발생시 close가 동작안한다 그렇기 때문에 bad code
            // try catch finally 를 사용해야 자동 리소스 닫기를 실행*/

        MyResources myResources3 = new MyResources("myResources3");
        MyResources myResources4 = new MyResources("myResources4");
        MyResources myResources5 = new MyResources("myResources5");
        try(myResources3; myResources4; myResources5){ // try 안에 값 추가 할 때 ; 이걸로 처리
            System.out.println(Integer.parseInt(myResources3.read1()));
            System.out.println(Integer.parseInt(myResources3.read2()));
        } catch (Exception e){
            e.printStackTrace();
        }

    }


}
