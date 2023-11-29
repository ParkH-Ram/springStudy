class Solution {
    public int solution(int n, int k) {
        int sum = 2000*(n/10);
        
        return n>=10 ? (n*12000) + (k*2000) - sum : (n*12000) + (k*2000) ;
    }
}