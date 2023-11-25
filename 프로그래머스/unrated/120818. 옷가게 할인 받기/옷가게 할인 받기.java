class Solution {
    public int solution(int price) {
        
        if(price >= 500000) price = (int)price * 80/100;
        
        else if(price >= 300000 && price < 500000) price = price * 90/100;
        
        else if(price >= 100000) price = price * 95/100;
        
        return price;
    }
}