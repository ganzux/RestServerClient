package com.ganzux.client.service;

import com.ganzux.client.dto.Employee;

import java.util.List;

/**
 * @author Alvaro Alcedo Moreno - aalcedo
 * @version 1.0
 * @since 2020-06-10
 */
public interface IServiceEmployeeGet {

    List<Employee> getEmployees();

}
