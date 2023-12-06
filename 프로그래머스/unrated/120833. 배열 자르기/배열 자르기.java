import java.util.*;

class Solution {
    public int[] solution(int[] numbers, int num1, int num2) {
        // +1 하는 이유는  copyOfRange는 시작 인덱스 부터 끝 인덱스 까지 배열을 복사
        //  즉 num1 = 1, num2 = 3 이면 1~3  3번 앞 인덱스 까지 인데 
        //  문제에서 요구하는 것은 3번 인덱스 까지 출력 해야 하기 때문에 
        return Arrays.copyOfRange(numbers, num1, num2+1);
    }
}