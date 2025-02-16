package com.harryberlin.springbootwithelasticsearch.mapper;

import com.harryberlin.springbootwithelasticsearch.domain.document.EmployeeDocument;
import com.harryberlin.springbootwithelasticsearch.dto.response.EmployeeResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeDocumentMapper {
    List<EmployeeResponse> toResponses(List<EmployeeDocument> employeeDocuments);
}
