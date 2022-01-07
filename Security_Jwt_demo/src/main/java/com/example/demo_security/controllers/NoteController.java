package com.example.demo_security.controllers;

import com.example.demo_security.response.NoteResponse;
import com.example.demo_security.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.time.LocalDate;


@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;


    @GetMapping("/allnotes")
    public Page<NoteResponse> getNotes(Pageable pageable) {
        return noteService.getAllNoteResponse(pageable);
    }


    @GetMapping("/users")
    public Page<NoteResponse> getNoteUserName(@RequestParam(name = "username", required = false) String username,
                                              @RequestParam(name = "date1", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date1,
                                              @RequestParam(name = "date2", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date2,
                                              Pageable pageable) {
        if (username == null) {
            return noteService.getAllNoteResponse(pageable);
        } else {
            return noteService.findDate(username, date1, date2, pageable);
        }
    }

    @GetMapping("/email")
    public Page<NoteResponse> getNoteEmail(@RequestParam(name = "email", required = false) String email, Pageable pageable) {
        if (email == null) {
            return noteService.getAllNoteResponse(pageable);
        } else {
            return noteService.findEmail(email, pageable);
        }
    }
    @GetMapping("/usernull")
    public Page<NoteResponse> getNoteOrNull(@RequestParam(name = "username",required = false) String username,Pageable pageable){
        return noteService.findByUserNull(username,pageable);
    }
    @GetMapping("/resource")
    public Page<NoteResponse> getNoteUserLogin(Pageable pageable,Principal principal){
        return noteService.findUserLogin1(pageable,principal);
    }
}
