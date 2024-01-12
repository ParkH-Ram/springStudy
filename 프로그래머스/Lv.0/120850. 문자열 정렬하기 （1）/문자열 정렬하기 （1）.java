import java.util.*;

class Solution {
    public int[] solution(String my_string) {
        
        char [] charArray = my_string.toCharArray();  
        List<Integer> intList = new ArrayList<>();
        
        for(char c : charArray){
            if(c >= '0' && c <= '9' ){
                intList.add(Character.getNumericValue(c));
            }
        }
        
         Collections.sort(intList);
        
        int [] intArray = new int[intList.size()];
        
        for(int i=0; i<intList.size(); i++){
            intArray[i] = intList.get(i).intValue();
        }        
        
        return intArray;
    }
}