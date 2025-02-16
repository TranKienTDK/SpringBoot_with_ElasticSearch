package com.harryberlin.springbootwithelasticsearch.domain.entity;

import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class EmployeeFakeGenerator {
    private static final Faker faker = new Faker();

    public static Employee generateEmployee() {
        return Employee.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .address(faker.address().streetAddress())
                .avatar(faker.internet().image())
                .build();
    }

    public static List<Employee> generateEmployees(int count) {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            employees.add(generateEmployee());
        }
        return employees;
    }
}
