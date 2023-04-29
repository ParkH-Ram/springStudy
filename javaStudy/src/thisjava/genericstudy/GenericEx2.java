package thisjava.genericstudy;

public class GenericEx2 {
    public static void main(String[] args) {
        // 클래스 안에 클래스
        // K는 GenericEx2_Tv,  M은 String으로 대체
        GenericEx2_Product<GenericEx2_Tv, String> product1 = new GenericEx2_Product<>();

        //Setter 매개값은 반드시 Tv와 String을 제공
        product1.setKind(new GenericEx2_Tv());
        product1.setModel("텔레비전");

        //Getter 리턴값은 GenericEx2_Tv와 String이 됨
        GenericEx2_Tv tv = product1.getKind();
        String tvModel = product1.getModel();
        System.out.println(tv + tvModel);

        // K는 Generic_Car, M은 String으로 대체
        GenericEx2_Product<GenericEx2_Car, String> product2 = new GenericEx2_Product<>();

        //Setter 값은 Car 타입과 String 타입으로 대체
        product2.setKind(new GenericEx2_Car());
        product2.setModel("자동차가 굴러가유~");

        //Getter  리턴 값은 Car와 String
        GenericEx2_Car car = product2.getKind();
        System.out.println("car = " + car);
        String carModel = product2.getModel();  // 자동차가 굴러가유가 된다

        
        System.out.println("carModel = " + carModel);






    }
}
