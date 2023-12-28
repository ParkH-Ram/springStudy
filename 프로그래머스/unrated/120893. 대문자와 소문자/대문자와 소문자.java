class Solution {
    public String solution(String my_string) {
        String answer = "";
        
        char [] charList = new char[my_string.length()];
        
        for(int i=0; i<my_string.length(); i++){
            charList[i] = my_string.charAt(i);
        }
        
        for(int i=0; i<charList.length; i++){
            if(charList[i] >= 'A' && charList[i] <= 'Z'){
                charList[i] = (char)(charList[i] + 32) ;
            } else if(charList[i] >= 'a' && charList[i] <= 'z'){
                charList[i] = (char)(charList[i] - 32) ;
            }
        }
        
        return answer.valueOf(charList);
    }
}