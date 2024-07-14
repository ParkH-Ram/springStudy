class Solution {
    public String solution(int[] numLog) {
        String sum = ""; 
        
        for(int i=1; i<numLog.length; i++){
            // 앞인덱스 빼기
            int front =  numLog[i] - numLog[i-1]; 
            
            if(front == 1) sum += 'w';
            else if(front == -1) sum += 's';
            else if(front == 10) sum += 'd';
            else if(front == -10) sum += 'a';   
        }

        return sum;
    }
}