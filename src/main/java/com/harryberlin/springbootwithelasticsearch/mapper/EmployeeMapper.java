package com.harryberlin.springbootwithelasticsearch.mapper;

import com.harryberlin.springbootwithelasticsearch.domain.entity.Employee;
import com.harryberlin.springbootwithelasticsearch.dto.request.EmployeeCreateRequest;
import com.harryberlin.springbootwithelasticsearch.dto.request.EmployeeUpdateRequest;
import com.harryberlin.springbootwithelasticsearch.dto.response.EmployeeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(EmployeeCreateRequest request);

    @Mapping(target = "id", ignore = true)
    Employee updateEntity(EmployeeUpdateRequest request, @MappingTarget Employee employee);
    EmployeeResponse toResponse(Employee employee);

    List<EmployeeResponse> toResponses(List<Employee> employees);
}
