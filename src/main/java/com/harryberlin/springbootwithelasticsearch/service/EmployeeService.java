package com.harryberlin.springbootwithelasticsearch.service;

import com.harryberlin.springbootwithelasticsearch.domain.document.EmployeeDocument;
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
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeService {
    EmployeeRepository employeeRepository;
    EmployeeDocumentRepository employeeDocumentRepository;
    EmployeeMapper employeeMapper;
    EmployeeDocumentMapper employeeDocumentMapper;
    RedisTemplate<String, Object> redisTemplate;


    public EmployeeResponse createEmployee(EmployeeCreateRequest request) {
        log.info("Create employee");
        Employee employee = employeeMapper.toEntity(request);
        return employeeMapper.toResponse(employeeRepository.save(employee));
    }

    public List<EmployeeResponse> getAllEmployees() {
        log.info("Get all employees");
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public EmployeeResponse getEmployeeById(String id) {
        log.info("Get employee by id: {}", id);
        Employee employee = employeeRepository.findById(id).orElse(null);
        return employeeMapper.toResponse(employee);
    }

    public EmployeeResponse updateEmployee(EmployeeUpdateRequest request) {
        log.info("Update Employee");
        Employee employee = employeeRepository.findById(request.getId()).orElse(null);

        return employeeMapper.toResponse(employeeRepository.save(employeeMapper.updateEntity(request, employee)));
    }

    public void deleteEmployeeById(List<String> listId) {
        employeeRepository.deleteAllById(listId);
    }

    public List<EmployeeResponse> findEmployeeByFirstName(String firstName) {
        log.info("Cache data from ElasticSearch");
        return employeeDocumentMapper.toResponses(employeeDocumentRepository.findByFirstName(firstName));
    }

    // FAKE DATA GENERATE
    public List<EmployeeResponse> generateRandomEmployees(int count) {
        log.info("Generate random employees");
        List<Employee> employees = EmployeeFakeGenerator.generateEmployees(count);
        return employeeMapper.toResponses(employeeRepository.saveAll(employees));
    }

    // REDIS
    public List<EmployeeResponse> findEmployeeByFirstNameWithCache(String firstName) {
        String cacheKey = "employee_by_firstName: " + firstName;

        List<EmployeeDocument> cachedEmployeeDocuments = (List<EmployeeDocument>) redisTemplate.opsForValue().get(cacheKey);
        if (cachedEmployeeDocuments != null) {
            log.info("Cache data from redis");
            return employeeDocumentMapper.toResponses(cachedEmployeeDocuments);
        }

        List<EmployeeDocument> employeeDocuments = employeeDocumentRepository.findByFirstName(firstName);
        redisTemplate.opsForValue().set(cacheKey, employeeDocuments, Duration.ofMinutes(10));

        log.info("Save data to redis");
        return employeeDocumentMapper.toResponses(employeeDocuments);
    }
}
