package com.harryberlin.springbootwithelasticsearch.domain.document;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "employees")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDocument {
    String id;
    String firstName;
    String lastName;
    String address;
    String avatar;
}
