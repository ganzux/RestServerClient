package com.ganzux.server.controller;

import com.ganzux.server.dto.Employee;
import com.ganzux.server.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Alvaro Alcedo Moreno - aalcedo
 * @version 1.0
 * @since 2020-06-10
 */

@RestController
public class EmployeeController {

    @Autowired
    IEmployeeRepository repository;

    @GetMapping("/employees")
    List<Employee> all() {
        return repository.findAll();
    }

}
