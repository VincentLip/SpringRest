package com.example.emsbackend.repository;

import com.example.emsbackend.entity.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department,Integer> {
}
