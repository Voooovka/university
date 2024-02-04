package com.vovka.university.controller;

import com.vovka.university.model.Department;
import com.vovka.university.model.Lector;
import com.vovka.university.service.DepartmentService;
import com.vovka.university.service.LectorService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UniversityController {

    private final LectorService lectorService;
    private final DepartmentService departmentService;

    @GetMapping("/department/{department_id}/statistics")
    public List<Lector> getLectorsForDepartment(@PathVariable Long department_id){
        return departmentService.getLectorsForDepartment(department_id);
    }

    @GetMapping("/departments")
    public List<Department> getDepartments(){
        return departmentService.getAllDepartments();
    }

    @PutMapping("/update-name")
    public Lector updateLectorName(@RequestParam(name = "lectorId") Long lectorId, @RequestParam(name = "newName") String newName){
        return lectorService.updateLectorName(lectorId, newName);
    }

    @GetMapping("/search")
    public List<Lector> searchLector(@RequestParam String user_query){
        return lectorService.getLectorsByUserQuery(user_query);
    }

    @PutMapping("/lector/{lector_id}/promote")
    public ResponseEntity<String> promoteLector(@PathVariable Long lector_id){
        if (lectorService.promoteLector(lector_id)) {
            return new ResponseEntity<>("Lector promoted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Lector cannot be promoted", HttpStatus.BAD_REQUEST);
        }
    }

}
