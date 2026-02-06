package com.codealong.employeedirectory.dto;

import lombok.Data;

@Data
public class EmployeeRequestDTO {
    // We don't need ID here, the DB generates it.
    private String name;
    private String email;
    private String password; // Client sends this to us
    private Double salary;   // Client sends this to us
}
