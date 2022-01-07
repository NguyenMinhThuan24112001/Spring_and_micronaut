package com.example.demo_security.repository;

import com.example.demo_security.models.Note;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

   @Query("from Note n where n.user.username like %:username%")
   Page<Note> findAllUsersWithPagination(@Param("username") String username, Pageable pageable);//null không có câu

    @Query("from Note n where n.user.email like %:email%")
    Page<Note> findAllEmailWithPagination(@Param("email") String email, Pageable pageable);

    @Query("from Note n where n.createdAt>:date1 and n.createdAt<:date2 and n.user.username like %:username%")
    Page<Note> findDateWithPagination(@Param("username") String username,
                                      @Param("date1") LocalDateTime date1,
                                      @Param("date2") LocalDateTime date2,
                                      Pageable pageable);
    @Query("from Note n where n.user.username=:username")
    Page<Note> findUserLogin(@Param("username") String username, Pageable pageable);

    Page<Note> findByTitleContaining(String title, Pageable pageable);

    List<Note> findByTitleContaining(String title, Sort sort);

}
