package com.example.emsbackend.service.impl;

import com.example.emsbackend.dto.DepartmentDto;
import com.example.emsbackend.dto.EmployeeDto;
import com.example.emsbackend.entity.Department;
import com.example.emsbackend.entity.Employee;
import com.example.emsbackend.exception.BlogApiException;
import com.example.emsbackend.exception.ResourceNotFoundException;
import com.example.emsbackend.repository.DepartmentRepository;
import com.example.emsbackend.repository.EmployeeRepository;
import com.example.emsbackend.service.EmployeService;
import com.example.emsbackend.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private Mapper mapper;



    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = mapper.mapToEntity(employeeDto);
        Employee newEmployee = employeeRepository.save(employee);
        EmployeeDto employeeResponse = mapper.mapToDto(newEmployee);

        return employeeResponse;
    }


    @Override
    public List<EmployeeDto> getEmployeesByDepartmentId(Long departmentId) {
        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);
        return employees.stream().map(employee -> mapper.mapToDto(employee)).collect(Collectors.toList());

    }

    @Override
    public EmployeeDto getEmployeeById(Long departmentId, Long employeeId) {
        Employee employee = employeeById(departmentId,employeeId);
        return mapper.mapToDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        List<EmployeeDto> employeeDtoList = employees.stream().map(employee->mapper.mapToDto(employee)).collect(Collectors.toList());
        return employeeDtoList;
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id) {
        Employee employee = getEmployeeByIdFromDatabase(id);
        Department department = retrieveDepartmentEntityById(employeeDto.getDepartmentId());

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartment(department);

        Employee updateEmployee = employeeRepository.save(employee);

        return mapper.mapToDto(updateEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeByIdFromDatabase(id);
        employeeRepository.deleteById(Math.toIntExact(id));
    }

//

    private Department retrieveDepartmentEntityById(Long departmentId) {

        return departmentRepository.findById(Math.toIntExact(departmentId)).orElseThrow(
                () -> new ResourceNotFoundException("Department", "id", departmentId));
    }

    private Employee retrieveEmployeeById(Long employeeId) {

        return employeeRepository.findById(Math.toIntExact(employeeId)).orElseThrow(
                () -> new ResourceNotFoundException("Employe", "postId", employeeId));
    }

    private Employee employeeById(Long departmentId, Long employeeId) {

        Department department = retrieveDepartmentEntityById(departmentId);
        Employee employee = retrieveEmployeeById(employeeId);

        badRequestException(employee, department);

        return employee;
    }

    private void badRequestException(Employee employee, Department department) {

        if (((employee.getDepartment().getId()) != (department.getId()))) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Employee does not belong to department");
        }

    }

    private Employee getEmployeeByIdFromDatabase(Long id) {

        return employeeRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
    }
}
