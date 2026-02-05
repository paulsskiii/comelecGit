package com.codealong.employeedirectory.service;

import com.codealong.employeedirectory.dto.EmployeeRequestDTO;
import com.codealong.employeedirectory.dto.EmployeeResponseDTO;
import com.codealong.employeedirectory.entity.Employee;
import com.codealong.employeedirectory.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper; // Injecting the bean we created in Step 2

    // Scenario 1: Saving data (DTO -> Entity)
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO) {
        // 1. Convert DTO to Entity using ModelMapper
        // Matches fields: name->name, email->email, password->password
        Employee employee = modelMapper.map(requestDTO, Employee.class);

        // 2. Save Entity to Database
        Employee savedEmployee = employeeRepository.save(employee);

        // 3. Convert saved Entity back to ResponseDTO
        // Matches fields: name->name, id->id. IGNORES password/salary because ResponseDTO doesn't have them.
        return modelMapper.map(savedEmployee, EmployeeResponseDTO.class);
    }

    // Scenario 2: Reading data (Entity -> DTO)
    public EmployeeResponseDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Convert Entity -> ResponseDTO
        // This ensures the password and salary stay in the service layer and don't leak out.
        return modelMapper.map(employee, EmployeeResponseDTO.class);
    }
}
