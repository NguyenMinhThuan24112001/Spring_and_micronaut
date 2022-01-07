package com.example.demo_security.response;


import lombok.Data;

@Data
public class NoteSinhVienReponse {
    private String name;
    private Long maSv;
    private Long maMh;
    private Long lanThi;
    private Double DiemThi;
}
