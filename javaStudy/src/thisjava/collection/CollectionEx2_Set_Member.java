package thisjava.collection;

public class CollectionEx2_Set_Member {
    //필드 선언
    public String name;
    public int age;

    //생성자

    public CollectionEx2_Set_Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //hashCode 재정의
    @Override
    public int hashCode(){
        return name.hashCode() + age;       // name과 age 값이 같으면 동일한 hashCode가 리턴
    }

    @Override
    public String toString() {
        return "CollectionEx2_Set_Member{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    //equals 재정의
    @Override
    public boolean equals(Object obj){
        if(obj instanceof  CollectionEx2_Set_Member){   // CollectionEx2_Set_Member target <<  자바 버전 16+ 부터 사용가능
            CollectionEx2_Set_Member target = (CollectionEx2_Set_Member) obj;
            return target.name.equals(name) && (target.age == age);
        } else{
            return false;
        }
    }
}
