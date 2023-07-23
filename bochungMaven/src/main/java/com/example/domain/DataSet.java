package com.example.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DataSet {
    String username;
    String password;


    @Override
    public String toString(){
        return "DataSet [username=" + username + ", password=" + password + "]";
    }

}
