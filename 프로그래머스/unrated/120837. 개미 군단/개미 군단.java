class Solution {
    public int solution(int hp) {
        int valueA = hp % 5;
        
        int a = hp / 5; // 제일 먼저 장군으로 피를 깎고
        int b = valueA / 3;  // 피를 깎고 남은 hp를  병정으로 깎고 
        int c = valueA % 3;  // 마지막 남은 hp를 일개미가 깎는다 


        return a+b+c;
    }
}