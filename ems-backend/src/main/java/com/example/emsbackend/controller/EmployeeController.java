package com.example.emsbackend.controller;

import com.example.emsbackend.dto.DepartmentDto;
import com.example.emsbackend.dto.EmployeeDto;
import com.example.emsbackend.service.EmployeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeService employeService;


    @PostMapping("/employees")
    public ResponseEntity<EmployeeDto> createDepartment(@Valid @RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(employeService.createEmployee(employeeDto), HttpStatus.CREATED);
    }


    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        return new ResponseEntity(employeService.getAllEmployees(), HttpStatus.OK);
    }

    @PutMapping("/employees/{departmentId}")
    public ResponseEntity<EmployeeDto> getDepartmentById(@PathVariable(value = "departmentId") Long departmentId,
                                                           @RequestBody EmployeeDto employeeDto) {
        EmployeeDto updatedEmployeeDto = employeService.updateEmployee(employeeDto,departmentId);
        return new ResponseEntity<>(updatedEmployeeDto, HttpStatus.OK);
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String> deletePost(@PathVariable(value = "employeeId") Integer employeeId) {
        employeService.deleteEmployee(Long.valueOf(employeeId));
        return new ResponseEntity<>("Employee deleted succcessfully", HttpStatus.OK);
    }
}
