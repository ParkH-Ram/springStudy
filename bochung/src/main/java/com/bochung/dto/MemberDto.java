package com.bochung.dto;

import com.bochung.constant.Role;
import com.bochung.entity.Members;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

/**
 * 회원을 관리할 Dto
 **/

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDto {

    private Long id;

    private String name;

    private String email;

    private String password;

    private Role role;

    private static ModelMapper modelMapper = new ModelMapper();

    public static MemberDto of(Members members) {
        return modelMapper.map(members, MemberDto.class);
    }


}
