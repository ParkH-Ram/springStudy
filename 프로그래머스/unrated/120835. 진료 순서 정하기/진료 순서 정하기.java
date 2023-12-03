class Solution {
    public int[] solution(int[] emergency) {
        int[] answer = new int [emergency.length];
        int value = 0;
        
        //  값 비교 위해 이중 포문
        for(int i=0; i<emergency.length; i++){
            for(int j=0; j<emergency.length; j++){
                if(emergency[i] < emergency[j]) answer[i] ++;
                
            }
            answer[i]++;   // 순번 1 부터 시작하기 위한 초기 세팅
            
        }
        
        return answer;
    }
}