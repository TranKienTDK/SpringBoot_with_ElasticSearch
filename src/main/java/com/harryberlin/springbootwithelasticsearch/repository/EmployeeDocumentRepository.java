package com.harryberlin.springbootwithelasticsearch.repository;


import com.harryberlin.springbootwithelasticsearch.domain.document.EmployeeDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EmployeeDocumentRepository extends ElasticsearchRepository<EmployeeDocument, String> {

}
