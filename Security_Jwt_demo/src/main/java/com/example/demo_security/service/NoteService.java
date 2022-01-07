package com.example.demo_security.service;

import com.example.demo_security.models.Note;
import com.example.demo_security.repository.CreateNoteRepository;
import com.example.demo_security.repository.NoteRepository;
import com.example.demo_security.response.NoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;

@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepository;// repository chỉ làm việc với entity
    @Autowired
    CreateNoteRepository createNoteRepository;

    public Page<NoteResponse> getAllNoteResponse(Pageable pageable) {
        return noteRepository.findAll(pageable).map(note -> {//chuyển từ entity thành response
            NoteResponse noteResponse = new NoteResponse();
            noteResponse.setContent(note.getContent());
            noteResponse.setId(note.getId());
            noteResponse.setCreatedAt(note.getCreatedAt());
            noteResponse.setTitle(note.getTitle());
            noteResponse.setUsername(note.getUser().getUsername());
            noteResponse.setEmail(note.getUser().getEmail());

            return noteResponse;
        });
    }
    public  Page<NoteResponse> findUserName(String username,Pageable pageable){
        return noteRepository.findAllUsersWithPagination(username,pageable).map(
                note -> {//chuyển từ entity thành response
                    NoteResponse noteResponse = new NoteResponse();
                    noteResponse.setContent(note.getContent());
                    noteResponse.setId(note.getId());
                    noteResponse.setCreatedAt(note.getCreatedAt());
                    noteResponse.setTitle(note.getTitle());
                    noteResponse.setUsername(note.getUser().getUsername());
                    noteResponse.setEmail(note.getUser().getEmail());

                    return noteResponse;
                }
        );
    }
    public  Page<NoteResponse> findEmail(String email,Pageable pageable) {
        return noteRepository.findAllEmailWithPagination(email, pageable).map(
                note -> {//chuyển từ entity thành response
                    NoteResponse noteResponse = new NoteResponse();
                    noteResponse.setContent(note.getContent());
                    noteResponse.setId(note.getId());
                    noteResponse.setCreatedAt(note.getCreatedAt());
                    noteResponse.setTitle(note.getTitle());
                    noteResponse.setUsername(note.getUser().getUsername());
                    noteResponse.setEmail(note.getUser().getEmail());

                    return noteResponse;
                }
        );
    }
    public  Page<NoteResponse> findDate(String username, LocalDate date1, LocalDate date2, Pageable pageable) {
        return noteRepository.findDateWithPagination(username,date1.atStartOfDay(), date2.plusDays(1).atStartOfDay(), pageable).map(
                note -> {//chuyển từ entity thành response
                    NoteResponse noteResponse = new NoteResponse();
                    noteResponse.setContent(note.getContent());
                    noteResponse.setId(note.getId());
                    noteResponse.setCreatedAt(note.getCreatedAt());
                    noteResponse.setTitle(note.getTitle());
                    noteResponse.setUsername(note.getUser().getUsername());
                    noteResponse.setEmail(note.getUser().getEmail());
                    return noteResponse;
                }
        );
    }
    public Page<NoteResponse> findByUserNull(String username,Pageable pageable){
        PageImpl<Note> notes=new PageImpl<Note>(createNoteRepository.noteUserNull(username,pageable),pageable,createNoteRepository.noteUserTotals(username,pageable));
        return notes.map(
                note -> {//chuyển từ entity thành response
                    NoteResponse noteResponse = new NoteResponse();
                    noteResponse.setContent(note.getContent());
                    noteResponse.setId(note.getId());
                    noteResponse.setCreatedAt(note.getCreatedAt());
                    noteResponse.setTitle(note.getTitle());
                    noteResponse.setUsername(note.getUser().getUsername());
                    noteResponse.setEmail(note.getUser().getEmail());

                    return noteResponse;
                }
        );
    }
    public  Page<NoteResponse> findUserLogin1(Pageable pageable,Principal principal) {
        return noteRepository.findUserLogin(principal.getName(), pageable).map(
                note -> {//chuyển từ entity thành response
                    NoteResponse noteResponse = new NoteResponse();
                    noteResponse.setContent(note.getContent());
                    noteResponse.setId(note.getId());
                    noteResponse.setCreatedAt(note.getCreatedAt());
                    noteResponse.setTitle(note.getTitle());
                    noteResponse.setUsername(note.getUser().getUsername());
                    noteResponse.setEmail(note.getUser().getEmail());

                    return noteResponse;
                }
        );
    }

}
