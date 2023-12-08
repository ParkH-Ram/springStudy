class Solution {
    public int solution(int n) {
        int answer = 0;
        
        for(int i = 1; i<=n; i++){
            int value =  n%i;   // 수를 i로 나눈 나머지 
            if(value * i == 0) answer ++;
            
        }
        return answer;
    }
}