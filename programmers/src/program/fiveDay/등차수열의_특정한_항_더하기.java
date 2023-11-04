package program.fiveDay;

/**
 * 두 정수 a, d와 길이가 n인 boolean 배열 included가 주어집니다.
 * 첫째항이 a, 공차가 d인 등차수열에서 included[i]가 i + 1항을 의미할 때,
 * 이 등차수열의 1항부터 n항까지 included가 true인 항들만 더한 값을 return 하는 solution 함수를 작성해 주세요.
 *
 * */

public class 등차수열의_특정한_항_더하기 {
    public int solution(int a, int b, boolean[] included){
        int sum = 0;

    /*
            if(included[i]){
                System.out.println("배열 값" + included[i]);
                total += sum;  //  true인 값 더하기
                System.out.println(total);
            }

            sum += b; // a항의 값에 b 항을 더해서 수를  늘리기
            System.out.println("if문 밖" + sum);*/

        // 수학 알면 코드 단축 가능
        for (int i=0; i<included.length; i++){
            if (included[i])    sum += a + b*i;
        }
        return sum;
    }

    public static void main(String []args){
        등차수열의_특정한_항_더하기 an = new 등차수열의_특정한_항_더하기();

        boolean []type = {true, false, false, true, true};
        System.out.println(an.solution(3,4 ,type));


    }
}
