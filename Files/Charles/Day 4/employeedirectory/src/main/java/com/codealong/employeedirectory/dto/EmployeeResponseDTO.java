package com.codealong.employeedirectory.dto;

import lombok.Data;

@Data
public class EmployeeResponseDTO {
    // We send the ID back so the client can reference it.
    private Long id; 
    private String name;
    private String email;
    
    // NOTE: No password or salary fields here!
    // Even if we wanted to, we physically cannot send them.
}
