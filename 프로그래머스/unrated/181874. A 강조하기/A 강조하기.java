import java.util.*;

class Solution {
    public String solution(String myString) {
        String answer = myString.toLowerCase();
        String value = answer.replace("a", "A");
  
        return value;
    }
}