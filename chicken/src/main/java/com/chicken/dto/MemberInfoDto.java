package com.chicken.dto;

import com.chicken.constant.Role;
import com.chicken.entity.MemberInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

/**
 *  회원 관리 디티오
 * **/

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberInfoDto {

    private String memberId;

    private String memberPassword;

    private String memberName;

    private String memberEmail;

    private Double memberHeight;

    private Double memberWeight;

    private Long memberGender;

    private Role role;

    private static ModelMapper modelMapper = new ModelMapper();

    // Entity -> Dto
    public static MemberInfoDto of(MemberInfo memberInfo){
        return modelMapper.map(memberInfo, MemberInfoDto.class);
    }
}
