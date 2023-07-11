package com.example.emsbackend.utils;

import com.example.emsbackend.dto.DepartmentDto;
import com.example.emsbackend.dto.EmployeeDto;
import com.example.emsbackend.entity.Department;
import com.example.emsbackend.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public EmployeeDto mapToDto(Employee employee) {

        ModelMapper mapper = new ModelMapper();
        EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
        return employeeDto;

    }

    public Employee mapToEntity( EmployeeDto employeeDto) {
        ModelMapper mapper = new ModelMapper();
        Employee employee = mapper.map(employeeDto, Employee.class);

        return employee;
    }


    public DepartmentDto mapToDto(Department department) {

        ModelMapper mapper = new ModelMapper();
        DepartmentDto departmentDto = mapper.map(department, DepartmentDto.class);
        return departmentDto;

    }

    public Department mapToEntity(DepartmentDto departmentDto) {
        ModelMapper mapper = new ModelMapper();
        Department department = mapper.map(departmentDto, Department.class);

        return department;
    }


}
