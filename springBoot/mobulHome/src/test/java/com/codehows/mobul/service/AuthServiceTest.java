package com.codehows.mobul.service;

import com.codehows.mobul.dto.UsersDTO;
import com.codehows.mobul.entity.Users;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class AuthServiceTest {

    @Autowired
    AuthService authService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Users createUser(){
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setUserId("유저아이디");
        usersDTO.setUserPassword("유저비밀번호");
        usersDTO.setUserPhone("유저폰");

        return Users.authSignUp(usersDTO, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void 멤버저장테스트(){
        Users users1 = createUser();
        Users saverUser = authService.saveUser(users1);

        assertEquals(users1.getUserId(), saverUser.getUserId());
        assertEquals(users1.getUserPassword(), saverUser.getUserPassword());
        assertEquals(users1.getUserPhone(), saverUser.getUserPhone());
        assertEquals(users1.getRole(), saverUser.getRole());

        System.out.println("saverUser = " + saverUser);
            

    }

}