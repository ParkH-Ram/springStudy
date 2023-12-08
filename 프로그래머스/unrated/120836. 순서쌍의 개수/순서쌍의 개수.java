class Solution {
    public int solution(int n) {
        int answer = 0;
        
        for(int i = 1; i<=n; i++){
            int value =  n/i;   
            if(value * i == n) answer ++;
            
        }
        return answer;
    }
}