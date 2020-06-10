package com.ganzux.client;

import com.ganzux.client.service.ServiceEmployeeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 * @author Alvaro Alcedo Moreno - aalcedo
 * @version 1.0
 * @since 2020-06-10
 */
@SpringBootApplication
public class Client {

    @Autowired
    ServiceEmployeeFactory serviceEmployeeGet;

    public static void main(String[] args) {
        SpringApplication.run(Client.class, args);
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> serviceEmployeeGet.getService().getEmployees();
    }
}