package com.example.demo_security.service;

import com.example.demo_security.exception.ResourceNotFoundException;
import com.example.demo_security.models.DiemThi;
import com.example.demo_security.models.MonHoc;
import com.example.demo_security.models.SinhVien;
import com.example.demo_security.repository.QLDiemThiRepository;
import com.example.demo_security.repository.QLMonHoc;
import com.example.demo_security.repository.QLSinhVienRepository;
import com.example.demo_security.request.DiemThiRequest;
import com.example.demo_security.response.DiemThiCuoiReponse;
import com.example.demo_security.response.NoteSinhVienReponse;
import com.example.demo_security.response.SinhVienReponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DiemThiService {

    @Autowired
    QLDiemThiRepository qlDiemThiRepository;

    @Autowired
    QLMonHoc qlMonHoc;


    @Autowired
    QLSinhVienRepository qlSinhVienRepository;


    // lay tat cac notes
    public List<DiemThi> getAllDiemThi() {
        return qlDiemThiRepository.findAll();
    }

    // Tao mot note moi
    public DiemThi createDiemThi(DiemThiRequest diemThiRequest) {
        DiemThi diemThi = new DiemThi();
        diemThi.setCreatedAt(LocalDateTime.now());
        SinhVien sv = qlSinhVienRepository.findById(diemThiRequest.getMSSV()).orElse(null);
        MonHoc mh = qlMonHoc.findById(diemThiRequest.getMSMH()).orElse(null);
        diemThi.setMonHoc(mh);
        diemThi.setSinhVien(sv);
        diemThi.setDiem(diemThiRequest.getDiemThi());
        return qlDiemThiRepository.save(diemThi);
    }

    // lay ra mot note bat ki
    public DiemThi getDiemThiById(Long diemThiId) {
        return qlDiemThiRepository.findById(diemThiId)
                .orElseThrow(() -> new ResourceNotFoundException("DiemThi", "id", diemThiId));
    }

    // cap nhat thay doi mot note
    public DiemThi updateDiemThi(Long diemThiId, DiemThi diemThiDetails) {

        DiemThi diemThi = qlDiemThiRepository.findById(diemThiId)
                .orElseThrow(() -> new ResourceNotFoundException("DiemThi", "id", diemThiId));

        BeanUtils.copyProperties(diemThiDetails, diemThi);
        DiemThi updatedDiemThi = qlDiemThiRepository.save(diemThi);
        return updatedDiemThi;
    }

    // xoa mot node
    public ResponseEntity<?> deleteDiemThi(Long diemThiId) {
        DiemThi diemThi = qlDiemThiRepository.findById(diemThiId)
                .orElseThrow(() -> new ResourceNotFoundException("DiemThi", "id", diemThiId));

        qlDiemThiRepository.delete(diemThi);
        return ResponseEntity.ok().build();
    }

    public SinhVienReponse findLanThiMax(Long maSv, Long maMh){
        SinhVienReponse sinhVienResonse=new SinhVienReponse();
        sinhVienResonse.setMaSv(maSv);
        sinhVienResonse.setMaMh(maMh);
        sinhVienResonse.setLanThiMax(qlDiemThiRepository.findLanThiMaxByMaSVandMaMH(maSv,maMh).orElse(null));
        return  sinhVienResonse;
    }

    public DiemThiCuoiReponse findDiemThi(Long maSv, Long maMh){
        DiemThiCuoiReponse diemThiCuoiReponse=new DiemThiCuoiReponse();
        diemThiCuoiReponse.setMaSv(maSv);
        diemThiCuoiReponse.setMaMh(maMh);
        diemThiCuoiReponse.setLanThi(qlDiemThiRepository.findLanThiMaxByMaSVandMaMH(maSv,maMh).orElse(null));
        diemThiCuoiReponse.setDiemThi(qlDiemThiRepository.findDiemThiByMaSVandMaMH(maSv,maMh).orElse(null));
        return  diemThiCuoiReponse;
    }

    public DiemThi findDiemThiCuoi(Long maMh, Long maSv){
       DiemThi diemThi1=qlDiemThiRepository.findFirstByMonHoc_mSMHAndSinhVien_mSSVOrderByIdDesc(maMh,maSv).orElse(null);
        return diemThi1;
    }

    public List<NoteSinhVienReponse> findSinhVien(Long maSv) {

        return qlDiemThiRepository.findAllBySinhVienIsTrue(maSv).stream().map(diemThi -> {
            NoteSinhVienReponse noteSinhVienReponse = new NoteSinhVienReponse();
            noteSinhVienReponse.setName(diemThi.getSinhVien().getTen());
            noteSinhVienReponse.setMaMh(diemThi.getMonHoc().getMSMH());
            noteSinhVienReponse.setMaSv(maSv);
            noteSinhVienReponse.setLanThi(diemThi.getLanThi());
            noteSinhVienReponse.setDiemThi(diemThi.getDiem());
            return noteSinhVienReponse;
        }).collect(Collectors.toList());
    }
}
