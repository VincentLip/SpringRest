package com.example.emsbackend.service;

import com.example.emsbackend.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    DepartmentDto createDepartment(DepartmentDto departmentDto);

    List<DepartmentDto> getAllDepartments();

    DepartmentDto getDepartmentById(Long id);

    DepartmentDto updateDepartment(DepartmentDto departmentDto, Long id);

    void deleteDepartmentById(Long id);
}
