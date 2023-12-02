class Solution {
    public String solution(String my_string, String letter) {
        String answer = "";
        for(char c : letter.toCharArray()){
            my_string = my_string.replace(String.valueOf(c), ""); // c를 뺀 문자열  반환
        }
    
        return my_string;
    }
}