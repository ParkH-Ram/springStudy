package thisjava.genericstudy;

//제네릭 타입
public class GenericEx2_Product<K, M> {// 타입 파라미터로 K, M 정의
    //필드
    private K kind;         // 타입 파라미터를 필드로 사용
    private M model;        // 어떤 타입이 들어올지 몰라서 제네릭 선언


    // 메서드   // 타입 파라미터를   리턴 타입과 매개변수 타입으로 사용
    public K getKind() {
        return kind;
    }

    public void setKind(K kind) {
        this.kind = kind;
    }

    public M getModel() {
        return model;
    }

    public void setModel(M model) {
        this.model = model;
    }
}
