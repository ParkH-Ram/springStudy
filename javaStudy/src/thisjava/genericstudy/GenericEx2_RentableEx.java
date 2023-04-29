package thisjava.genericstudy;

public class GenericEx2_RentableEx {
    public static void main(String[] args) {
        GenericEx2_Rentable_CarAgency carAgency = new GenericEx2_Rentable_CarAgency();
        GenericEx2_Rentable_HomeAgency homeAgency = new GenericEx2_Rentable_HomeAgency();


        GenericEx2_Rentable_Home home = homeAgency.rent();
        GenericEx2_Rentable_Car car = carAgency.rent();
        home.turnOnLight();
        car.run();
    }
}
