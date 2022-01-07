package com.example.demo_security.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DiemThiRequest {

    @JsonProperty("maSv")
    private Long mSSV;

    @JsonProperty("maMh")
    private Long mSMH;

    private Double diemThi;

}
