class Solution {
    public int solution(int hp) {
        int answer = 0;
        int valueA = hp % 5;
        int valueB = hp % 3;
        
        int a = hp / 5; // 제일 먼저 장군으로 피를 깎고
        int b = valueA / 3;  // 피를 깎고 남은 hp를  병정으로 깎고 
        int c = b / 1  ;  // 마지막 남은 hp를 일개미가 깎는다 
        
        System.out.println(valueA);
        
        if(valueA % 3 == 0) c  = 0;
        else if(valueA == 1 ) c = 1;
        else if(valueA == 2 ) c = 2;
        
        
                
        
          
        return a+b+c;
    }
}