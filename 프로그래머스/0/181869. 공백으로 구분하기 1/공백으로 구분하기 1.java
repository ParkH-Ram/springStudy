public class Solution {
    public String[] solution(String my_string) {
        // 문자열을 공백으로 분리하여 배열에 저장
        String[] words = my_string.split(" ");
        
        // 분리된 단어들이 담긴 배열 반환
        return words;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // 테스트를 위한 문자열
        String my_string = "This is a test string";
        
        // solution 함수 호출 및 결과 출력
        String[] result = sol.solution(my_string);
        for(String word : result) {
            System.out.println(word);
        }
    }
}