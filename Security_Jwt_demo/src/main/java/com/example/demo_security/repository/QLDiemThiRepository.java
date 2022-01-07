package com.example.demo_security.repository;

import com.example.demo_security.models.DiemThi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QLDiemThiRepository extends JpaRepository<DiemThi, Long> {
    @Query("select coalesce(max(d.lanThi), 0) from DiemThi d where d.monHoc.mSMH=:maMh and d.sinhVien.mSSV=:maSv")
    Optional<Integer> findLanThiMaxByMaSVandMaMH(@Param("maSv") Long maSV,
                                                 @Param("maMh") Long maMH);

    @Query("select coalesce(max(d.diem), 0) from DiemThi d where d.monHoc.mSMH=:maMh and d.sinhVien.mSSV=:maSv " +
            "and (select max(d1.lanThi) from DiemThi d1 where d1.monHoc.mSMH=:maMh and d1.sinhVien.mSSV=:maSv)=d.lanThi")
    Optional<Double> findDiemThiByMaSVandMaMH(@Param("maSv") Long maSV,
                                              @Param("maMh") Long maMH);

    Optional<DiemThi> findFirstByMonHoc_mSMHAndSinhVien_mSSVOrderByIdDesc(Long maMH, Long maSV);

    @Query("from DiemThi d where d.id in (select max(d1.id) from DiemThi d1 where d1.sinhVien.mSSV=:maSv group by d1.monHoc.mSMH)")
    List<DiemThi> findAllBySinhVienIsTrue(@Param("maSv") Long maSv);
}
