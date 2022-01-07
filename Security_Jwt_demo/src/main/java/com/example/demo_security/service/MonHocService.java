package com.example.demo_security.service;

import com.example.demo_security.exception.ResourceNotFoundException;
import com.example.demo_security.models.MonHoc;
import com.example.demo_security.repository.QLMonHoc;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MonHocService {
    @Autowired
    QLMonHoc qlMonHoc;

    // lay tat cac notes
    public List<MonHoc> getAllMonHoc() {
        return qlMonHoc.findAll();
    }

    // Tao mot note moi
    public MonHoc createMonHoc(MonHoc monHoc) {
        return qlMonHoc.save(monHoc);
    }

    // lay ra mot note bat ki
    public MonHoc getMonHocById(Long monHocId) {
        return qlMonHoc.findById(monHocId)
                .orElseThrow(() -> new ResourceNotFoundException("MonHoc", "id", monHocId));
    }
    public Long geMonHocById(Long monHocId) {
        return qlMonHoc.findById(monHocId).get().getMSMH();
                //.orElseThrow(() -> new ResourceNotFoundException("MonHoc", "id", monHocId));
    }

    // cap nhat thay doi mot note
    public MonHoc updateMonHoc(Long monHocId, MonHoc monHocDetails) {

        MonHoc monHoc = qlMonHoc.findById(monHocId)
                .orElseThrow(() -> new ResourceNotFoundException("MonHoc", "id", monHocId));

        BeanUtils.copyProperties(monHocDetails, monHoc);
        MonHoc updatedMonHoc = qlMonHoc.save(monHoc);
        return updatedMonHoc;
    }

    // xoa mot node
    public ResponseEntity<?> deleteMonHoc(Long monHocId) {
        MonHoc monHoc = qlMonHoc.findById(monHocId)
                .orElseThrow(() -> new ResourceNotFoundException("MonHoc", "id", monHocId));

        qlMonHoc.delete(monHoc);
        return ResponseEntity.ok().build();
    }

}
