class Solution {
    public int solution(int n) {
        
        int sum = 1;
        int max = 0;
        int total = 0;
        
        for(int i=1; i<=10; i++){
        
            sum *= i;
            
            
            System.out.println(sum);
            System.out.println(total);
            if(sum == n) max = i;
            else if(sum < n) max = i;
            
            
            
        }
        System.out.println(n);

        return max;
    }
}