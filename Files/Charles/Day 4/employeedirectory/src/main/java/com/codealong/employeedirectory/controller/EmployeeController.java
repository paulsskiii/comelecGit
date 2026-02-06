package com.codealong.employeedirectory.controller;

import com.codealong.employeedirectory.dto.EmployeeRequestDTO;
import com.codealong.employeedirectory.dto.EmployeeResponseDTO;
import com.codealong.employeedirectory.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody EmployeeRequestDTO requestDTO) {
        // We receive data WITH password/salary, but return data WITHOUT them
        return ResponseEntity.ok(employeeService.createEmployee(requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }
}
