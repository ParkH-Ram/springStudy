class Solution {
    public String solution(int age) {
        String answer = Integer.toString(age);
        
        String value = "";
        
        for(int i=0; i<=answer.length()-1; i++){
            
            if(answer.charAt(i) == '0')  value += "a";
            else if(answer.charAt(i) == '1')  value +="b";
            else if(answer.charAt(i) == '2') value +="c";
            else if(answer.charAt(i) == '3') value +="d";
            else if(answer.charAt(i) == '4') value +="e";
            else if(answer.charAt(i) == '5') value +="f";
            else if(answer.charAt(i) == '6') value +="g";
            else if(answer.charAt(i) == '7') value +="h";
            else if(answer.charAt(i) == '8') value +="i";
            else if(answer.charAt(i) == '9')  value +="j";
         }
                
        System.out.println(answer);
        
        return value;
    }
}