package com.example.demo_security;

import com.example.demo_security.models.DiemThi;
import com.example.demo_security.repository.QLDiemThiRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class DemoSecurityApplicationTests {

    @Autowired
    QLDiemThiRepository qlDiemThiRepository;

    @Test
    void contextLoads() {
//        Optional<DiemThi> max = qlDiemThiRepository.findFirstByMonHoc_mSMHAndSinhVien_mSSVOrderByIdDesc(1L, 2L);
//        System.out.println(max);
//        Optional<Integer> max2 = qlDiemThiRepository.findLanThiMaxByMaSVandMaMH(1L, 2L);
//        System.out.println(max);
//        Optional<Integer> max3 = qlDiemThiRepository.findLanThiMaxByMaSVandMaMH(1L, 2L);
//        System.out.println(max);
    }

}
