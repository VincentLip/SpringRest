package com.example.emsbackend.service.impl;

import com.example.emsbackend.dto.DepartmentDto;
import com.example.emsbackend.entity.Department;
import com.example.emsbackend.exception.ResourceNotFoundException;
import com.example.emsbackend.repository.DepartmentRepository;
import com.example.emsbackend.service.DepartmentService;
import com.example.emsbackend.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private Mapper mapper;


    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = mapper.mapToEntity(departmentDto);
        Department newDepartment = departmentRepository.save(department);
        DepartmentDto departmentResponse = mapper.mapToDto(newDepartment);

        return departmentResponse;
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = (List<Department>) departmentRepository.findAll();
        List<DepartmentDto> departmentDtoList = departments.stream().map(department->mapper.mapToDto(department)).collect(Collectors.toList());
        return departmentDtoList;
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Department department = getDepartmentByIdFromDatabase(id);
        return mapper.mapToDto(department);
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto, Long id) {
        Department department = getDepartmentByIdFromDatabase(id);

        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());

        Department updateDepartment = departmentRepository.save(department);

        return mapper.mapToDto(updateDepartment);
    }

    @Override
    public void deleteDepartmentById(Long id) {
        Department department = getDepartmentByIdFromDatabase(id);
        departmentRepository.deleteById(Math.toIntExact(id));
    }


    private Department getDepartmentByIdFromDatabase(Long id) {

        return departmentRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
    }
}
