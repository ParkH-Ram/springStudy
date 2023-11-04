package program.sixday;

public class 마지막두원소 {

    public static int[] solution(int[] numList) {

        int[] value = new int[numList.length+1];

        for (int i = 0; i < numList.length; i++) {
            value[i] += numList[i];
            if (i == numList.length -1 && numList.length != 1) {
                if(numList[i] <= numList[i-1]) value[i+1] = numList[i]*2;     // 앞에 값 크거나 같으면 뒷 값 두배
                else value[i+1] = numList[i] - numList[i-1];  // 앞에 값 작으면  뒤에서 앞에 값 빼기
            } else if(numList.length == 1) value[i+1] = numList[0]*2; // 이로직은 쓸모 없음.. 문제를 다시 읽어 보니 길이는 최소 2임...
        }
        return value;
    }
}

/**
 *     int[] value = new int[numList.length + 1];
 *
 *     for (int i = 0; i < numList.length; i++) {
 *         value[i] = numList[i];
 *     }
 *
 *     // 마지막 원소에 대한 처리 로직을 for문 밖으로 빼냄
 *     if(numList[numList.length-1] < numList[numList.length-2]) {
 *         value[numList.length] = numList[numList.length-1]*2;
 *     } else {
 *         value[numList.length] = numList[numList.length-1] - numList[numList.length-2];
 *     }
 *
 *     return value;
 * ***/
