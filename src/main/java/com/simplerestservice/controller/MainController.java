package com.simplerestservice.controller;

import com.simplerestservice.models.Employee;
import com.simplerestservice.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class MainController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @RequestMapping
    public String main() throws IOException {
        return "index";
    }

    @PostMapping("/gethead")
    public ModelAndView edit(Long pid) {
        ModelAndView model = new ModelAndView("fragments/bootstrap");
        return model;
    }

    @PostMapping(value = "/save", consumes = {"multipart/form-data"})
    @ResponseBody
    public void save(Employee employee, @RequestParam("maintenanceFile") MultipartFile maintenanceFile) throws IOException {
        System.out.println(employee);
        employeeService.save(employee, maintenanceFile);
    }

    @RequestMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(Long pid) {
        Employee employee = employeeService.getById(pid);
        byte[] imageContent = null;
        imageContent = employee.getAvatar();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

    @PostMapping("/getallemployees")
    @ResponseBody
    public Employee[]  getAllEmployees() {
        return employeeService.getAll().toArray(new Employee[0]);
    }

    @GetMapping("/getmodal")
    public ModelAndView getModal() {
        ModelAndView model = new ModelAndView("fragments/addModal");
        return model;
    }



}
