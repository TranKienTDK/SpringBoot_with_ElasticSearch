package com.harryberlin.springbootwithelasticsearch.domain.listener;

import com.harryberlin.springbootwithelasticsearch.domain.document.EmployeeDocument;
import com.harryberlin.springbootwithelasticsearch.domain.entity.Employee;
import com.harryberlin.springbootwithelasticsearch.repository.EmployeeDocumentRepository;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeEntityListener {
    @Autowired
    private EmployeeDocumentRepository employeeDocumentRepository;

    @PostPersist
    @PostUpdate
    public void afterSave(Employee employee) {
        EmployeeDocument employeeDocument = new EmployeeDocument();
        employeeDocument.setId(employee.getId());
        employeeDocument.setFirstName(employee.getFirstName());
        employeeDocument.setLastName(employee.getLastName());
        employeeDocument.setAddress(employee.getAddress());
        employeeDocument.setAvatar(employee.getAvatar());

        employeeDocumentRepository.save(employeeDocument);
    }

    @PostRemove
    public void afterDelete(Employee employee) {
        employeeDocumentRepository.deleteById(employee.getId());
    }

}
