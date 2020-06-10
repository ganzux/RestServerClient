package com.ganzux.client.service;

import com.ganzux.client.ClientConfiguration;
import com.ganzux.client.dto.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Alvaro Alcedo Moreno - aalcedo
 * @version 1.0
 * @since 2020-06-10
 */
@Service
public class ServiceEmployeeRestTemplateGet implements IServiceEmployeeGet {

    private static final Logger log = LoggerFactory.getLogger(ServiceEmployeeRestTemplateGet.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ClientConfiguration clientConfiguration;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Override
    public List<Employee> getEmployees(){

        List<Employee> employees = restTemplate.getForObject(
                clientConfiguration.getServerEndPointEmployeesGetDirection(), List.class);

        log.debug(employees.toString());

        return employees;
    }

}
