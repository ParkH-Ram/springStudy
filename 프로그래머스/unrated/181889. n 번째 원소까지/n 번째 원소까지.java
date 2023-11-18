class Solution {
    public int[] solution(int[] num_list, int n) {
        
        // 정수의 길이 만큼 배열 생성
        int [] answer = new int [n];
        
        // 정수의 길이 만큼 배열 반복
        for(int i = 0; i < n; i++){
            
            // 리턴할 배열 인덱스에 입력받은 배열 인덱스에 맞춰 값들 넣어주기 
            answer[i] = num_list[i];
        }
        
        
        return answer;
    }
}