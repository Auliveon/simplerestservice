package com.simplerestservice.service;

import com.simplerestservice.models.Employee;
import com.simplerestservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository er;

    @Override
    public List<Employee> getAll() {
        return er.findAll();
    }

    @Override
    public void save(Employee employee, MultipartFile multipartFile) throws IOException {
        employee.setAvatar(multipartFile.getBytes());
        er.save(employee);
    }

    @Override
    public Employee getById(Long id) {
        return er.getById(id);
    }

    @Override
    public void delete(Employee employee) {
        er.delete(employee);
    }

    @Override
    public void deleteById(Long id) {
        er.deleteById(id);
    }


}
