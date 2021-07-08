package com.simplerestservice.service;

import com.simplerestservice.models.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();

    void save(Employee employee, MultipartFile multipartFile) throws IOException;

    Employee getById(Long id);

    void delete(Employee employee);

    void deleteById(Long id);
}
