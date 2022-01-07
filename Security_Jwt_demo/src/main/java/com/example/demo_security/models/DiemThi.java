package com.example.demo_security.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "diemThi")
public class DiemThi extends AbstractEntity {
    @Id// đánh dấu trường sẽ là khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)// giá trị sẽ tự sinh ra
    private Long id;

    @ColumnDefault("1")
    private Long lanThi;

    @Check(constraints = "0<=diem<=10")
    @Column(name = "diem")
    private Double diem;

    @ManyToOne
    @JoinColumn(name = "MSSV_id")
    private SinhVien sinhVien;

    @ManyToOne
    @JoinColumn(name = "MSMH_id")
    private MonHoc monHoc;

}
