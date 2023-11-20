class Solution {
    public int[] solution(int[] num_list) {
        
        int []arr = new int [num_list.length];
        
        for(int i = 0; i<num_list.length; i++){
            // 넘리스트의 길이에 - i 를 빼주고 거기서 1을 빼면 맨 뒷 부터 불러옴
           arr[i] = num_list[num_list.length-i-1];
            System.out.println(i + " " + num_list[num_list.length-1]);
        }
        
        return arr;
    }
}