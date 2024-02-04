package com.vovka.university.service.impl;

import com.vovka.university.exeption.AppException;
import com.vovka.university.exeption.EventType;
import com.vovka.university.model.Department;
import com.vovka.university.model.Lector;
import com.vovka.university.repository.DepartmentRepository;
import com.vovka.university.repository.LectorRepository;
import com.vovka.university.service.DepartmentService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public List<Lector> getLectorsForDepartment(Long department_id) {
        Department department = departmentRepository.findById(department_id)
                .orElseThrow(() -> new AppException(EventType.NOT_FOUND_EXCEPTION, "Department not found with id: " + department_id));
        return department.getLectors().stream().toList();
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}
