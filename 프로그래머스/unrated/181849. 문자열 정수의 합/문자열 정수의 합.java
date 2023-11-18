class Solution {
    public int solution(String num_str) {
        int value = 0;
       
        for(int i=0; i<num_str.length(); i++){
            
            value += Integer.parseInt(num_str.substring(i, i+1));
        }
        
        return value;
    }
}