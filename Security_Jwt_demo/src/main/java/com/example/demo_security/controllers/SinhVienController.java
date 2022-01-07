package com.example.demo_security.controllers;

import com.example.demo_security.models.DiemThi;
import com.example.demo_security.models.MonHoc;
import com.example.demo_security.models.SinhVien;
import com.example.demo_security.request.DiemThiRequest;
import com.example.demo_security.response.DiemThiCuoiReponse;
import com.example.demo_security.response.NoteSinhVienReponse;
import com.example.demo_security.response.SinhVienReponse;
import com.example.demo_security.service.DiemThiService;
import com.example.demo_security.service.MonHocService;
import com.example.demo_security.service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SinhVienController {

    //Sinh Vien
    @Autowired
    SinhVienService sinhVienService;

    public List<SinhVien> getAllSinhVien() {
        return sinhVienService.getAllSinhVien();
    }

    // Tao mot note moi
    @PostMapping("/sinhvien")
    public SinhVien createSinhVien(@Valid @RequestBody SinhVien sinhVien) {

        return sinhVienService.createSinhVien(sinhVien);
    }

    // lay ra mot note bat ki
    @GetMapping("/sinhvien/{id}")
    public SinhVien getSinhVienById(@PathVariable(value = "id") Long sinhVienId) {
        return sinhVienService.getSinhVienById(sinhVienId);
    }

    // cap nhat thay doi mot note
    @PutMapping("/sinhvien/{id}")
    public SinhVien updateSinhVien(@PathVariable(value = "id") Long sinhVienId,
                                   @Valid @RequestBody SinhVien sinhVienDetails) {
        return sinhVienService.updateSinhVien(sinhVienId, sinhVienDetails);
    }

    // xoa mot node
    @DeleteMapping("/sinhvien/{id}")
    public ResponseEntity<?> deleteSinhVien(@PathVariable(value = "id") Long sinhVienId) {
        return sinhVienService.deleteSinhVien(sinhVienId);
    }


    //Mon  Hoc
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

    @GetMapping("/monhocs/{id}")
    public Long getMonHocsById(@PathVariable(value = "id") Long monHocId) {
        return monHocService.geMonHocById(monHocId);
    }


    //diem thi

    @Autowired
    DiemThiService diemThiService;

    // Tao mot note moi
    @PostMapping("/diemthi")
    public DiemThi createDiemThi(@Valid @RequestBody DiemThiRequest diemThi) {

        return diemThiService.createDiemThi(diemThi);
    }

    // lay ra mot note bat ki
    @GetMapping("/diemthi/{id}")
    public DiemThi getDiemThiById(@PathVariable(value = "id") Long diemThiId) {
        return diemThiService.getDiemThiById(diemThiId);
    }

    // cap nhat thay doi mot note
    @PutMapping("/diemthi/{id}")
    public DiemThi updateDiemThi(@PathVariable(value = "id") Long diemThiId,
                                 @Valid @RequestBody DiemThi diemThiDetails) {
        return diemThiService.updateDiemThi(diemThiId, diemThiDetails);
    }

    // xoa mot node
    @DeleteMapping("/diemthi/{id}")
    public ResponseEntity<?> deleteDiemThi(@PathVariable(value = "id") Long diemThiId) {
        return diemThiService.deleteDiemThi(diemThiId);
    }

    @GetMapping("/lanthimax")
    public SinhVienReponse getLanThiMax(@RequestParam(name = "maSV", required = false) Long maSV,
                                        @RequestParam(name = "maMH", required = false) Long maMH) {
        return diemThiService.findLanThiMax(maSV,maMH);
    }
    @GetMapping("/diemthilancuoi")
    public DiemThiCuoiReponse getDiemThiLanCuoi(@RequestParam(name = "maSV", required = false) Long maSV,
                                           @RequestParam(name = "maMH", required = false) Long maMH) {
        return diemThiService.findDiemThi(maSV,maMH);
    }
    @GetMapping("/diemthilancuoipart2")
    public DiemThi getDiemThiLanCuoiPart2(@RequestParam(name = "maMH", required = false) Long maMH,
                                                @RequestParam(name = "maSV", required = false) Long maSV) {
        return diemThiService.findDiemThiCuoi(maMH,maSV);
    }
    @GetMapping("/sinhvienhoc")
    public List<NoteSinhVienReponse> getSinhVienHoc( @RequestParam(name = "maSV", required = false) Long maSv){
        return diemThiService.findSinhVien(maSv);
    }

}
