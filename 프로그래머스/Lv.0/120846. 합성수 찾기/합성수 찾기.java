class Solution {
    public int solution(int n) {
        
        int check;
        int count=0;
        
        for(int i=1; i<=n; i++){
            check = 0;
    
            for(int j=1; j<=i; j++){
                if(i % j == 0) check++;  
                
            }
            if (check >= 3) count++;          
        }
        
        return count;
    }
}