package com.codehows.mobul.entity;

import com.codehows.mobul.dto.UsersDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
//import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
@ToString
public class Users {

    @Id
    @Column(name = "user_id", nullable = false, length = 20)
    private String userId;

    @Column(name = "user_password", nullable = false, length = 20)
    private String userPassword;

    @Column(name = "user_phone", length = 20)
    private String userPhone;

//    @OneToMany(mappedBy = "users")
//    private List<Boards> board_writer;
    
    
    // 유저생성 메서드  // PasswordEncoder가 해시코드로 저장 되게 하는 것
  /*  public static Users createUsers(UsersDTO usersDTO, PasswordEncoder passwordEncoder){
        Users users = new Users();
        users.setUserId(usersDTO.getUserId());
        String password = passwordEncoder.encode(usersDTO.getUserPassword());
        users.setUserPassword(password);
        users.setUserPhone(users.getUserPhone());
        return  users;
    }*/

    // 유저 생성 메서드
    public static Users createUsers(UsersDTO usersDTO){
        Users users = new Users();
        users.setUserId(usersDTO.getUserId());
        users.setUserPassword(usersDTO.getUserPassword());
        users.setUserPhone(users.getUserPhone());
        return  users;
    }



}
