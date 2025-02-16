package com.harryberlin.springbootwithelasticsearch.domain.entity;

import com.harryberlin.springbootwithelasticsearch.domain.listener.EmployeeEntityListener;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
@EntityListeners(EmployeeEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "address")
    String address;

    @Column(name = "avatar")
    String avatar;
}
