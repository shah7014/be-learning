package com.example.learning.service;

import com.example.learning.entity.Department;
import com.example.learning.exception.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);

    List<Department> getAllDepartments();

    Department getDepartmentById(Long id) throws DepartmentNotFoundException;

    void deleteDepartmentById(Long departmentId);

    Department updateDepartment(Long departmentId, Department newDepartment);

    Department getDepartmentByName(String departmentName);
}
