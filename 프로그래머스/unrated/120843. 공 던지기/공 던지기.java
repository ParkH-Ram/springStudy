class Solution {
    public int solution(int[] numbers, int k) {
        int answer = 0;
        int size = numbers.length;
        answer += numbers[2 * (k-1) % size];
        return answer;
    }
}