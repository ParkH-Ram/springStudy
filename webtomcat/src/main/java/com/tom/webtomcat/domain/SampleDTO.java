package com.tom.webtomcat.domain;


import lombok.Data;

@Data // Data를 사용하면 getter/setter, equals(). toString() 등의 메서드 자동생성
public class SampleDTO {

    private String name;
    private int age;
}
