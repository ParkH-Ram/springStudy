package thisjava.throwstudy;

public class MyResources implements AutoCloseable {

    private String name;   // 리소스의 이름을 지정하기 위해

    public MyResources(String name){
        this.name = name;
        System.out.println("myResources = " + name);
    }
    public String read1(){
        System.out.println("myResources = " + name + "읽기");
        return "100";
    }

    public String read2(){
        System.out.println("myResources = " + name + "읽기");
        return "ABC";
    }

    @Override
    public void close() throws Exception {
        System.out.println("myResources = " + name + "닫기");
    }
}
