package com.example.demo_security.service;


import com.example.demo_security.exception.ResourceNotFoundException;
import com.example.demo_security.models.SinhVien;
import com.example.demo_security.repository.QLSinhVienRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SinhVienService {

    @Autowired
    QLSinhVienRepository qlSinhVienRepository;

    // lay tat cac notes
    public List<SinhVien> getAllSinhVien() {
        return qlSinhVienRepository.findAll();
    }

    // Tao mot note moi
    public SinhVien createSinhVien(SinhVien sinhVien) {
        return qlSinhVienRepository.save(sinhVien);
    }

    // lay ra mot note bat ki
    public SinhVien getSinhVienById(Long sinhVienId) {
        return qlSinhVienRepository.findById(sinhVienId)
                .orElseThrow(() -> new ResourceNotFoundException("SinhVien", "id", sinhVienId));
    }

    // cap nhat thay doi mot note
    public SinhVien updateSinhVien(Long sinhVienId, SinhVien sinhVienDetails) {

        SinhVien sinhVien = qlSinhVienRepository.findById(sinhVienId)
                .orElseThrow(() -> new ResourceNotFoundException("SinhVien", "id", sinhVienId));

        BeanUtils.copyProperties(sinhVienDetails, sinhVien);
        SinhVien updatedSinhVien = qlSinhVienRepository.save(sinhVien);
        return updatedSinhVien;
    }

    // xoa mot node
    public ResponseEntity<?> deleteSinhVien(Long sinhVienId) {
        SinhVien sinhVien = qlSinhVienRepository.findById(sinhVienId)
                .orElseThrow(() -> new ResourceNotFoundException("SinhVien", "id", sinhVienId));

        qlSinhVienRepository.delete(sinhVien);
        return ResponseEntity.ok().build();
    }

}
