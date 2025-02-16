package com.harryberlin.springbootwithelasticsearch.service;

import com.harryberlin.springbootwithelasticsearch.domain.entity.Employee;
import com.harryberlin.springbootwithelasticsearch.domain.entity.EmployeeFakeGenerator;
import com.harryberlin.springbootwithelasticsearch.dto.request.EmployeeCreateRequest;
import com.harryberlin.springbootwithelasticsearch.dto.request.EmployeeUpdateRequest;
import com.harryberlin.springbootwithelasticsearch.dto.response.EmployeeResponse;
import com.harryberlin.springbootwithelasticsearch.mapper.EmployeeDocumentMapper;
import com.harryberlin.springbootwithelasticsearch.mapper.EmployeeMapper;
import com.harryberlin.springbootwithelasticsearch.repository.EmployeeDocumentRepository;
import com.harryberlin.springbootwithelasticsearch.repository.EmployeeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeService {
    EmployeeRepository employeeRepository;
    EmployeeDocumentRepository employeeDocumentRepository;
    EmployeeMapper employeeMapper;
    private final EmployeeDocumentMapper employeeDocumentMapper;

    public EmployeeResponse createEmployee(EmployeeCreateRequest request) {
        Employee employee = employeeMapper.toEntity(request);
        return employeeMapper.toResponse(employeeRepository.save(employee));
    }

    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public EmployeeResponse getEmployeeById(String id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        return employeeMapper.toResponse(employee);
    }

    public EmployeeResponse updateEmployee(EmployeeUpdateRequest request) {
        Employee employee = employeeRepository.findById(request.getId()).orElse(null);

        return employeeMapper.toResponse(employeeRepository.save(employeeMapper.updateEntity(request, employee)));
    }

    public void deleteEmployeeById(List<String> listId) {
        employeeRepository.deleteAllById(listId);
    }

    public List<EmployeeResponse> findEmployeeByFirstName(String firstName) {
        return employeeDocumentMapper.toResponses(employeeDocumentRepository.findByFirstName(firstName));
    }

    // FAKE DATA GENERATE
    public List<EmployeeResponse> generateRandomEmployees(int count) {
        List<Employee> employees = EmployeeFakeGenerator.generateEmployees(count);
        return employeeMapper.toResponses(employeeRepository.saveAll(employees));
    }
}
