package com.example.demo_security.repository;


import com.example.demo_security.models.SinhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QLSinhVienRepository extends JpaRepository <SinhVien,Long> {


}
