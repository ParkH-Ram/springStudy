package thisjava.lambdastudy;

@FunctionalInterface
interface MyFunction {
    void run();  // public abstract void run();
}

public class LambdaEx3{
    static void execute(MyFunction f) { // 매개변수 타입이 MyFunction 인 메서드
        f.run();
    }

    static MyFunction getMyFunction(){ // 반환 타입이 MyFunction 인 메서드
        MyFunction f = () -> System.out.println("f3.run()");
        return f;
    }

    public static void main(String[] args) {
        MyFunction f1 = () -> System.out.println("f1.run()");

        MyFunction f2 = new MyFunction() { // 익명 클래스로 run()을 구현
            @Override
            public void run() {     //public을 반드시 붙여야 함
                System.out.println("f2.run()");
            }
        };

        MyFunction f3 = getMyFunction();
        f1.run();
        f2.run();
        f3.run();

        execute(f1);
        execute(() -> System.out.println("run()"));
    }

}
