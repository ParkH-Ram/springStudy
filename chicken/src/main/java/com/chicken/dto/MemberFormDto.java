package com.chicken.dto;

import com.chicken.entity.MemberInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;

@Builder
@ToString
@Setter
@Getter
public class MemberFormDto {

    private String memberId;

    private String memberPassword;

    private String memberName;

    private String memberEmail;

    private Long memberHeight;

    private Long memberWeight;




}
