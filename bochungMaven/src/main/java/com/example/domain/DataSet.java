package com.example.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DataSet {
    String userName;
    String password;

    @Override
    public String toString(){
        return "DataSet [username=" + userName + ", password=" + password + "]";
    }

}
