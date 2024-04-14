public class Solution {
    public int[] solution(int[] array) {
        // 가장 큰 수의 초기값을 배열의 첫 번째 요소로 설정
        int maxNumber = array[0];
        // 가장 큰 수의 인덱스 초기값 설정
        int maxIndex = 0;
        
        // 배열을 순회하며 가장 큰 수와 그 수의 인덱스 찾기
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maxNumber) {
                maxNumber = array[i];
                maxIndex = i;
            }
        }
        
        // 결과를 담을 배열 생성
        int[] result = {maxNumber, maxIndex};
        
        return result;
    }
}