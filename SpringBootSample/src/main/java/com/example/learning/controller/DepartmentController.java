package com.example.learning.controller;

import com.example.learning.entity.Department;
import com.example.learning.exception.DepartmentNotFoundException;
import com.example.learning.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@Slf4j
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping
    public Department saveDepartment(@RequestBody @Valid Department department) {
        log.info("INSIDE saveDepartment for DepartmentController");
        return departmentService.saveDepartment(department);
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId, @RequestBody Department newDepartment) {
        return departmentService.updateDepartment(departmentId, newDepartment);
    }

    @DeleteMapping("/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return "Department deleted successfully";
    }

    @GetMapping("/name/{name}")
    public Department getDepartmentByName(@PathVariable("name") String departmentName) {
        return departmentService.getDepartmentByName(departmentName);
    }
}
