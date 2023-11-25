class Solution {
    public int[] solution(int money) {
        int[] answer = new int[2];
        
        answer[0] = money/5500;  // 먹을 수 있는 잔
        answer[1] = money%5500;  // 남은 돈 

        return answer;
    }
}