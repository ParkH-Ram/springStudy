class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        // 결과를 저장할 배열을 쿼리의 개수만큼 생성
        int[] answer = new int[queries.length];
        
        // 각 쿼리에 대해 반복
        for (int i = 0; i < queries.length; i++) {
            // 쿼리에서 s, e, k 값을 추출
            int s = queries[i][0];
            int e = queries[i][1];
            int k = queries[i][2];
            
            // 최소값을 저장할 변수 초기화 (최대값으로 초기화)
            int minValue = Integer.MAX_VALUE;
            // 찾는 값이 있는지 여부를 저장할 변수 초기화
            boolean found = false;
            
            // s부터 e까지의 배열 요소를 확인
            for (int j = s; j <= e; j++) {
                // 현재 요소가 k보다 크고, minValue보다 작으면
                if (arr[j] > k && arr[j] < minValue) {
                    // 최소값 갱신
                    minValue = arr[j];
                    // 값을 찾았음을 표시
                    found = true;
                }
            }
            
            // 찾은 값이 있으면 해당 값을, 없으면 -1을 결과 배열에 저장
            answer[i] = found ? minValue : -1;
        }
        
        // 결과 배열 반환
        return answer;
    }
}