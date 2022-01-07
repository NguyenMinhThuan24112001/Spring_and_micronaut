package com.example.demo_security.controllers;

import com.example.demo_security.models.MonHoc;
import com.example.demo_security.service.MonHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/apis")
public class MonHocController {
    @Autowired
    MonHocService monHocService;

    // Tao mot note moi
    @PostMapping("/monhoc")
    public MonHoc createMonHoc(@Valid @RequestBody MonHoc monHoc) {

        return monHocService.createMonHoc(monHoc);
    }

    // lay ra mot note bat ki
    @GetMapping("/monhoc/{id}")
    public MonHoc getMonHocById(@PathVariable(value = "id") Long monHocId) {
        return monHocService.getMonHocById(monHocId);
    }

    // cap nhat thay doi mot note
    @PutMapping("/monhoc/{id}")
    public MonHoc updateMonHoc(@PathVariable(value = "id") Long monHocId,
                           @Valid @RequestBody MonHoc monHocDetails) {
        return monHocService.updateMonHoc(monHocId, monHocDetails);
    }

    // xoa mot node
    @DeleteMapping("/monhoc/{id}")
    public ResponseEntity<?> deleteMonHoc(@PathVariable(value = "id") Long monHocId) {
        return monHocService.deleteMonHoc(monHocId);
    }

}
