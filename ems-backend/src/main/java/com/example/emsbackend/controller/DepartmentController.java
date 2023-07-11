package com.example.emsbackend.controller;

import com.example.emsbackend.dto.DepartmentDto;
import com.example.emsbackend.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/departments")
    public ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestBody DepartmentDto departmentDto){
        return new ResponseEntity<>(departmentService.createDepartment(departmentDto), HttpStatus.CREATED);
    }

    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
        return new ResponseEntity(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @PutMapping("/departments/{departmentId}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable(value = "departmentId") Integer departmentId,
                                               @RequestBody DepartmentDto departmentDto) {
        DepartmentDto updatedDepartmentDto = departmentService.updateDepartment(departmentDto, Long.valueOf(departmentId));
        return new ResponseEntity<>(updatedDepartmentDto, HttpStatus.OK);
    }

    @DeleteMapping("/departments/{departmentId}")
    public ResponseEntity<String> deletePost(@PathVariable(value = "departmentId") Integer departmentId) {
        departmentService.deleteDepartmentById(Long.valueOf(departmentId));
        return new ResponseEntity<>("Department deleted succcessfully", HttpStatus.OK);
    }
}
