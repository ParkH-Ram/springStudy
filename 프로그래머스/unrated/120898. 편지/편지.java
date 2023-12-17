class Solution {
    public int solution(String message) {
        int answer = 0;
        
        if (message.length() * 2 <= 22) answer = 22;
        else answer = message.length() * 2;
        
        return answer;
    }
}