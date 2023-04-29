package thisjava.genericstudy;

// 인터페이스를
public class GenericEx2_Rentable_HomeAgency implements GenericEx2_Rentable<GenericEx2_Rentable_Home> {
    @Override
    public GenericEx2_Rentable_Home rent(){
        return new GenericEx2_Rentable_Home();
    }
}
