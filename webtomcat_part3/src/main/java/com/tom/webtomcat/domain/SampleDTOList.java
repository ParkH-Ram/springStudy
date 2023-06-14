package com.tom.webtomcat.domain;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SampleDTOList {

    private List<String> sampleDTOList;

    public SampleDTOList(){
        sampleDTOList = new ArrayList<>();
    }
}
