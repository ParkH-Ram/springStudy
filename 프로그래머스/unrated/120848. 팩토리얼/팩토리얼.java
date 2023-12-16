class Solution {
    public int solution(int n) {
        
        int sum = 1;
        int max = 0;
        
        for(int i=1; i<=10; i++){
        
            sum *= i;
    
            if(sum == n) max = i;
            else if(sum < n) max = i;
               
        }

        return max;
    }
}