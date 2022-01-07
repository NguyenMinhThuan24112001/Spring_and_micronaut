package com.example.demo_security.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class SinhVien extends AbstractEntity {
    @Id// đánh dấu trường sẽ là khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)// giá trị sẽ tự sinh ra
    private Long mSSV;

    @NotBlank
    private String ho;

    @NotBlank
    private String ten;

    @NotBlank
    private String lop;

    private boolean gioiTinh;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime ngaySinh;

    public SinhVien() {

    }
}
