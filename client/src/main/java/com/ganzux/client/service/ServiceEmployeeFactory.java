package com.ganzux.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alvaro Alcedo Moreno - aalcedo
 * @version 1.0
 * @since 2020-06-10
 */
@Component
public class ServiceEmployeeFactory {

    private static final String REST_TEMPLATE_KEY = "resttemplate";
    private static final String WEB_CLIENT_KEY = "webclient";


    private String employeeServiceActive;

    private static final Map<String, IServiceEmployeeGet> servicesMap = new HashMap<>();

    public ServiceEmployeeFactory(@Value("${service.type.implementation}") String employeeServiceActive,
                                  @Autowired ServiceEmployeeRestTemplateGet restTemplateEmployeeService,
                                  @Autowired ServiceEmployeeWebClientGet webClientEmployeeService){

        this.employeeServiceActive = employeeServiceActive;

        servicesMap.put(REST_TEMPLATE_KEY, restTemplateEmployeeService);
        servicesMap.put(WEB_CLIENT_KEY, webClientEmployeeService);
    }


    public IServiceEmployeeGet getService() {
        if (servicesMap.containsKey(employeeServiceActive)) {
            return servicesMap.get(employeeServiceActive.toLowerCase());
        }

        return servicesMap.get(REST_TEMPLATE_KEY);
    }

}
