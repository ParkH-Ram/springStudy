package study.home.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class HomeDTO {

    private String name;

    private String email;


    public HomeDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
