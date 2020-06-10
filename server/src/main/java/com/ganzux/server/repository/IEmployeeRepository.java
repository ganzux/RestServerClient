package com.ganzux.server.repository;

import com.ganzux.server.dto.Employee;

import java.util.List;

/**
 * @author Alvaro Alcedo Moreno - aalcedo
 * @version 1.0
 * @since 2020-06-10
 */
public interface IEmployeeRepository {

    List<Employee> findAll();

}
