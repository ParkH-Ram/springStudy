package thisjava.genericstudy;

public class GenericEx2_Rentable_CarAgency implements GenericEx2_Rentable<GenericEx2_Rentable_Car>{

    @Override
    public GenericEx2_Rentable_Car rent(){
        return new GenericEx2_Rentable_Car();
    }
}
