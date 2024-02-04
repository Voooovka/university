package com.vovka.university.service;

import com.vovka.university.model.Department;
import com.vovka.university.model.Lector;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {

    List<Lector> getLectorsForDepartment(Long department_id);
    List<Department> getAllDepartments();
}
