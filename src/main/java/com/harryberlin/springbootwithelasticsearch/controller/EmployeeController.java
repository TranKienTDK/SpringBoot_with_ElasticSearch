package com.harryberlin.springbootwithelasticsearch.controller;

import com.harryberlin.springbootwithelasticsearch.dto.request.EmployeeCreateRequest;
import com.harryberlin.springbootwithelasticsearch.dto.request.EmployeeDeleteRequest;
import com.harryberlin.springbootwithelasticsearch.dto.request.EmployeeUpdateRequest;
import com.harryberlin.springbootwithelasticsearch.dto.response.EmployeeResponse;
import com.harryberlin.springbootwithelasticsearch.service.EmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {
    EmployeeService employeeService;

    @PostMapping
    ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeService.createEmployee(request));
    }

    @GetMapping
    ResponseEntity<List<EmployeeResponse>> getEmployees() {
        return ResponseEntity.ok()
                .body(employeeService.getAllEmployees());
    }

    @GetMapping("/{employeeId}")
    ResponseEntity<EmployeeResponse> getEmployee(@PathVariable("employeeId") String employeeId) {
        return ResponseEntity.ok()
                .body(employeeService.getEmployeeById(employeeId));
    }

    @PutMapping
    ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody EmployeeUpdateRequest request) {
        return ResponseEntity.ok()
                .body(employeeService.updateEmployee(request));
    }

    @DeleteMapping
    ResponseEntity<Void> deleteEmployee(@RequestBody EmployeeDeleteRequest listId) {
        employeeService.deleteEmployeeById(listId.getListId());
        return ResponseEntity.noContent().build();
    }

    // ELASTIC SEARCH
    @GetMapping("/search")
    ResponseEntity<List<EmployeeResponse>> getEmployeeByFirstName(
            @RequestParam(name = "firstName") String firstName) {
        return ResponseEntity.ok()
                .body(employeeService.findEmployeeByFirstName(firstName));
    }

    // FAKER DATA
    @PostMapping("/generate")
    ResponseEntity<List<EmployeeResponse>> generateEmployees(@RequestParam int count) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeService.generateRandomEmployees(count));
    }
}
