class Solution {
    public int solution(int n) {
        
        int value = 1;  // 피자 한판
        
        while((6*value)%n !=0){   // 피자 한판을 사람 수로 나눠서 0이아니면 피자를 계속 증가
            
            value ++;
            
        }
            
        
        return value;
    }
}