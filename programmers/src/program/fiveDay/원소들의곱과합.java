package program.fiveDay;

public class 원소들의곱과합 {
    public int solution(int[] numList){
        int multi = 1;
        int square = 0;
        for(int num : numList){
            multi *= num;
            System.out.println(multi);
            square += num;
        }
        System.out.println(multi + "멀티");
        System.out.println(square*square + "스퀘어");
        return multi > (square*square) ? 0 : 1;
    }
}
