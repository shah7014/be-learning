package com.example.learning.service;

import com.example.learning.entity.Department;
import com.example.learning.exception.DepartmentNotFoundException;
import com.example.learning.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements DepartmentService{


    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) throws DepartmentNotFoundException {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department is not present currently"));
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    private boolean validateIfNonEmptyAndNonNull(String str) {
        return Objects.nonNull(str) && !str.isBlank() && !str.isEmpty();
    }

    @Override
    public Department updateDepartment(Long departmentId, Department newDepartment) {
//         find it and then save it?
        Department department  = departmentRepository.findById(departmentId).get();
        if (validateIfNonEmptyAndNonNull(newDepartment.getDepartmentAddress())) department.setDepartmentAddress(newDepartment.getDepartmentAddress());
        if (validateIfNonEmptyAndNonNull(newDepartment.getDepartmentCode())) department.setDepartmentCode(newDepartment.getDepartmentCode());
        if (validateIfNonEmptyAndNonNull(newDepartment.getDepartmentName())) department.setDepartmentName(newDepartment.getDepartmentName());
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentName(departmentName);
    }
}
