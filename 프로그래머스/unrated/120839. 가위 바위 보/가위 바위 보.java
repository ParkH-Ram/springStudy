class Solution {
    public String solution(String rsp) {
        String answer = "";
        
        for(int i=0 ; i < rsp.length() ; i++) {
            if(String.valueOf(rsp.charAt(i)).equals("2"))      answer += "0";
            else if(String.valueOf(rsp.charAt(i)).equals("0")) answer += "5";
            else if(String.valueOf(rsp.charAt(i)).equals("5")) answer += "2";
        }
        
        return answer;
    }
}