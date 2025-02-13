package com.harryberlin.springbootwithelasticsearch.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeCreateRequest {
    String firstName;
    String lastName;
    String address;
    String avatar;
}
