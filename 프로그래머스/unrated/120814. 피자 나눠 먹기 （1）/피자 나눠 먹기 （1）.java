class Solution {
    public int solution(int n) {
        int s = n/7;
        
        if(n%7 != 0){
            n = s+1;
        } else 
           n = s;
    
        
        return n;
    }
}