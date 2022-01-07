package com.example.demo_security.response;

import com.example.demo_security.models.User;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NoteResponse {
    private Long id;


    private String title;


    private String content;


    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;


    private String username;


    private String email;


}
