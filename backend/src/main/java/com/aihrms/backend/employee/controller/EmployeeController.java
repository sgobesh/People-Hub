package com.aihrms.backend.employee.controller;

import com.aihrms.backend.employee.dto.EmployeeRequestDTO;
import com.aihrms.backend.employee.dto.EmployeeResponseDTO;
import com.aihrms.backend.employee.service.EmployeeService;
import org.springframework.data.domain.Page;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

import com.aihrms.backend.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {


    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }



    // CREATE EMPLOYEE

  @PostMapping
public ResponseEntity<ApiResponse<EmployeeResponseDTO>> createEmployee(
        @Valid @RequestBody EmployeeRequestDTO dto) {

    EmployeeResponseDTO employee = employeeService.createEmployee(dto);

    ApiResponse<EmployeeResponseDTO> response =
            new ApiResponse<>(
                    true,
                    "Employee created successfully",
                    employee
            );

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
}



    // GET ALL EMPLOYEES

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees(){


        return ResponseEntity.ok(
                employeeService.getAllEmployees()
        );

    }





    // GET EMPLOYEE BY ID

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(
            @PathVariable Long id){


        return ResponseEntity.ok(
                employeeService.getEmployeeById(id)
        );

    }

@GetMapping("/search")
public ResponseEntity<List<EmployeeResponseDTO>> searchEmployees(
        @RequestParam String keyword) {

    return ResponseEntity.ok(
            employeeService.searchEmployees(keyword)
    );
}



    // UPDATE EMPLOYEE

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable Long id,
           @Valid @RequestBody EmployeeRequestDTO dto){


        return ResponseEntity.ok(
                employeeService.updateEmployee(id,dto)
        );

    }

@GetMapping("/page")
public ResponseEntity<Page<EmployeeResponseDTO>> getEmployeesWithPagination(

        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {

    return ResponseEntity.ok(
            employeeService.getEmployeesWithPagination(
                    page,
                    size,
                    sortBy,
                    direction
            )
    );
}



    // DELETE EMPLOYEE

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable Long id){


        employeeService.deleteEmployee(id);


        return ResponseEntity.ok(
                "Employee deleted successfully"
        );

    }

}