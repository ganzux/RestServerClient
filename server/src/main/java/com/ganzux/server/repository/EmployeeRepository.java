package com.ganzux.server.repository;

import com.ganzux.server.dto.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alvaro Alcedo Moreno - aalcedo
 * @version 1.0
 * @since 2020-06-10
 */

@Component
public class EmployeeRepository implements IEmployeeRepository {

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(1L, "Alvaro", "Engineer"));
        employees.add(new Employee(2L, "Borja", "Tester"));

        return employees;
    }

}
