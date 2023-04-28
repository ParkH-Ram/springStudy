package thisjava.throwstudy;

public class ThrowException_MultipleHandlers {
    public static void main (String[]args){
        String [] array = {"안녕하세요 좋은 아침이네요?", "100", null, "1500"};

        for(int i=0; i<=array.length; i++){
            try{
                System.out.println(array[i].length()); // 인덱스 안에 있는 문자의 길이 출력
                int value = Integer.parseInt(array[i]);  // 인덱스 번호를 value에 int로 바꿔 입력
                System.out.println(value);
            } catch (NumberFormatException e){  // 숫자가 아닐 때 예외
                System.out.println("숫자로 변할 수 없는 항목 존재. 확인바람");
                System.err.println(e.getMessage());
                
                // catch 에 두가지 오류를 같이 확인 하고 싶을 때 오류 종류  | <-- 이걸 쓰면 동시에 확인 가능 
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e1){  // 인덱스 초과
                System.out.println("인덱스 확인 및 널 값이 입력됨 확인 바람");
                System.err.println(e1.getMessage());
/*            } catch (NullPointerException e2){          // 널 존재
                System.out.println("널 값이 입력 됨 확인 바람");
                System.err.println(e2.getMessage());*/
            } catch (Exception e){
                System.out.println("그 외 예외 발생 됨. 오류 로고 확인 바람");
                System.err.println(e.getMessage());
                System.out.println(e.toString());
                e.printStackTrace();

            }
        }
    }
}
