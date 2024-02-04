package com.vovka.university.repository;

import com.vovka.university.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.lectors WHERE d.id = :departmentId")
    Department findByIdWithLectors(Long departmentId);
}
