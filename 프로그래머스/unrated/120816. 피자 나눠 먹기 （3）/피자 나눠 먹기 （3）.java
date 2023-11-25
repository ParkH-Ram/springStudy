class Solution {
    public int solution(int slice, int n) {
        
        int value = 1;
        
        // 한조각씩 먹으면  
        if( n % slice == 0 )  value = n / slice;

        else value = n / slice + 1;

        return value;
    }
}