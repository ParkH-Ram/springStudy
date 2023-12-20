class Solution {
    public int solution(int n) {
        int answer = 0;
        
        String value = String.valueOf(n);
        int [] array = new int[value.length()];
        
        for(int  i=0; i<value.length(); i++){
            
            answer += value.charAt(i) - '0';          
            
        }
        
        
        return answer;
    }
}