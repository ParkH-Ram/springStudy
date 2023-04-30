package thisjava.genericstudy;

// 제네릭 타입으로 선언  T라는 타입 파라미터
public class GenericEx3_Method_Box<T>{
    //필드
    private T t;

    //Getter 메소드
    public T get(){
        return t;
    }

    //Setter 메소드
    public void set(T t){
        this.t = t;
    }
}
