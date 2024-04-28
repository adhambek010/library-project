package com.example.library.weblibrary.user.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String details;

}
