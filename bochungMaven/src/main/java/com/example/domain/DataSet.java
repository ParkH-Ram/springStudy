package com.example.domain;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
public class DataSet {
    String username;
    String password;


    @Override
    public String toString(){
        return "DataSet [username=" + username + ", password=" + password + "]";
    }

}
