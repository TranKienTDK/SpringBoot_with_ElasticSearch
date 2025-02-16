package com.harryberlin.springbootwithelasticsearch.repository;


import com.harryberlin.springbootwithelasticsearch.domain.document.EmployeeDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EmployeeDocumentRepository extends ElasticsearchRepository<EmployeeDocument, String> {

    @Query("{\"match\": {\"firstName\": {\"query\": \"?0\", \"fuzziness\": \"AUTO\"}}}")
    List<EmployeeDocument> findByFirstName(String firstName);
}
