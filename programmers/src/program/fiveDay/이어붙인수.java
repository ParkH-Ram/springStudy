package program.fiveDay;

public class 이어붙인수 {

    public static int solution(int[] numList){
        String odd ="";
        String even ="";

        for(int i : numList){
            if(i % 2 != 0) odd  += i+"";
            else even += i+"";
        }
        return Integer.parseInt(odd) + Integer.parseInt(even);
    }

}
