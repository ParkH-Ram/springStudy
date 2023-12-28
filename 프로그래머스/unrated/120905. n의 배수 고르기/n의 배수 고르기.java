class Solution {
    public int[] solution(int n, int[] numlist) {
        int[] answer = new int [numlist.length];
        
         int check =0;
        
        for(int i=0; i<numlist.length; i++){
            
            if(numlist[i] % n == 0){
                answer[check] = numlist[i];         
                check ++;
            }
          
                        
        }
        System.out.println(check);
        int [] checkList = new int[check];
        for (int i=0; i<checkList.length; i++){
            
            checkList[i] = answer[i];
            
            
        }
        
        return checkList;
    }
}