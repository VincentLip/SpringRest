package com.example.emsbackend.service;

import com.example.emsbackend.dto.DepartmentDto;
import com.example.emsbackend.dto.EmployeeDto;

import java.util.List;

public interface EmployeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getEmployeesByDepartmentId(Long departmentId);

    EmployeeDto getEmployeeById(Long departmentId , Long employeeId);

    List<EmployeeDto> getAllEmployees();

//   EmployeeDto updateEmployee(Long departmentId , Long employeeId , EmployeeDto employeeRequest);
    EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id);

    void deleteEmployee( Long id);
}
