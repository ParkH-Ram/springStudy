class Solution {
    public int solution(int[] dot) {
        int answer = 0;
        
        if (dot[0] >= 1 && dot[1] >= 1 ) answer = 1;
        else if (dot[0] <= 1 && dot[1] >= 1 ) answer = 2;
        else if (dot[0] <= 1 && dot[1] <= 1 ) answer = 3;
        else if (dot[0] >= 1 && dot[1] <= 1 ) answer = 4;
        
        return answer;
    }
}