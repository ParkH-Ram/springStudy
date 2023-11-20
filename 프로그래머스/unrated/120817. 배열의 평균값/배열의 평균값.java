import java.util.*;

class Solution {
    public double solution(int[] numbers) {
   
              // 스트림 -> 스트림(배열).(연산할 것 넣고).(0이 아니면 값을, 0이면 0 리턴 )
        return Arrays.stream(numbers).average().orElse(0);
    }
}