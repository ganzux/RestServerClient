package com.ganzux.server.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alvaro Alcedo Moreno - aalcedo
 * @version 1.0
 * @since 2020-06-10
 */
@RestController
@EnableAutoConfiguration
@RequestMapping(value = { "/" })
public class RootController {

	@Value("${application.version}")
	private String appVersion;

	@Value("${application.name}")
	private String appName;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String healthCheck()  {
		return "SERVER (" + appName + ") is up and running - V" + appVersion;
	} 
}  
