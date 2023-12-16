class Solution {
    public int[] solution(String[] strlist) {
        int[] answer = new int[strlist.length];
        int idx = 0;
        
        for(int i=0; i<strlist.length; i++){
            for(int j=0; j<strlist[i].length(); j++){
                idx = j;
            }
            answer[i] = idx+1; // 인덱스 번호를  갯수로 바뀌기 위해 +1 
        }
        
        return answer;
    }
}