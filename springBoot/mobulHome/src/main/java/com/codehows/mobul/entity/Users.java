package com.codehows.mobul.entity;

import com.codehows.mobul.constant.Role;
import com.codehows.mobul.dto.UsersDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter @Setter @ToString
public class Users {

    @Id
    @Column(name = "user_id", length = 20)
    private String userId;

    @Column(name = "user_password", length = 100)
    private String userPassword;

    @Column(name = "user_phone", length = 20)
    private String userPhone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "user_one", length = 20)
    private String userOne;

    @Column(name = "user_two", length = 20)
    private String userTwo;

    @Column(name = "user_three", length = 20)
    private String userThree;


    // 유저 생성 메서드  dto -> entity
    public static Users authSignUp(UsersDTO usersDTO, PasswordEncoder passwordEncoder){
        Users users = new Users();
        users.setUserId(usersDTO.getUserId());
        users.setUserPassword(usersDTO.getUserPassword());
        users.setUserPhone(usersDTO.getUserPhone());
        users.setRole(Role.ADMIN);
        return  users;
    }
}