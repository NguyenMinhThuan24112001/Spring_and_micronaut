package com.example.demo_security.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "monHoc")
public class MonHoc extends AbstractEntity {
    @Id// đánh dấu trường sẽ là khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)// giá trị sẽ tự sinh ra
    private Long mSMH;

    @NotBlank
    private String tenMH;


    public MonHoc() {

    }
}

