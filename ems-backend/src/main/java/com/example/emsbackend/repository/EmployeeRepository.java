package com.example.emsbackend.repository;

import com.example.emsbackend.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee,Integer> {


    List<Employee> findByDepartmentId(Long id);
}
