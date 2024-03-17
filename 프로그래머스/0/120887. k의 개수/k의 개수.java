public class Solution {
    public int solution(int i, int j, int k) {
        int count = 0; // k가 등장하는 횟수를 세는 변수

        // i부터 j까지 반복
        for (int num = i; num <= j; num++) {
            // 현재 숫자를 문자열로 변환
            String numStr = String.valueOf(num);
            // k를 문자열로 변환
            String kStr = String.valueOf(k);
            // 현재 숫자 문자열에서 k 문자열이 등장하는 횟수를 센다
            for (int index = 0; index < numStr.length(); index++) {
                // 현재 숫자 문자열의 부분 문자열이 k 문자열과 같은지 확인
                if (numStr.substring(index, index + 1).equals(kStr)) {
                    count++; // 등장 횟수 증가
                }
            }
        }
        // k가 등장하는 횟수 반환
        return count;
    }
}