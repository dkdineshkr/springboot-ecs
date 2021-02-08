package com.zensar.controller;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.zensar.entity.Employee;

@RestController
@RequestMapping("/companymgmt")
public class CompanyController {

	private static final Log logger = LogFactory.getLog(CompanyController.class);

	@Autowired
	private Environment env;

	
	@GetMapping("/health-status")
	public String healthStatus() {
		logger.debug("Starting REST Client for checking health!!!!");
		logger.info("Starting REST Client for health start : "+new Date());
		return "Company Management Service UP and RUNNING";

	}
	@GetMapping("/health-status2")
	public String healthStatus2() {
		logger.debug("Starting REST Client for checking health!!!!");
		logger.info("Starting REST Client for health start : "+new Date());
		return "Company Management Service UP and RUNNING with health status 2";

	}

	@PostMapping("/employee")
	@ResponseStatus(HttpStatus.OK)
	public void createEmployee(@RequestBody Employee employee) {

		logger.debug("Starting REST Client!!!!");
		logger.info("Starting REST Client for create employee start : "+new Date());
		final String uri = env.getProperty("employee.service.createemployee.url");
		logger.info("Create employee Service URL: " + env.getProperty("employee.service.createemployee.url"));

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForEntity(uri, employee, Employee.class);

		//System.out.println(result);

	}

	@GetMapping("/employees")
	@ResponseStatus(HttpStatus.OK)
	public List<Object> listAllUsers() {

		final String uri = env.getProperty("employee.service.getemployees.url");
		logger.info("Get employees Service URL: " + env.getProperty("employee.service.getemployees.url"));
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(uri, Object[].class);
		Object[] objects = responseEntity.getBody();
		//MediaType contentType = responseEntity.getHeaders().getContentType();
		//HttpStatus statusCode = responseEntity.getStatusCode();

		// Employee[] result = restTemplate.getForObject(uri, Employee[].class);
		List<Object> employees = Arrays.asList(objects);
		System.out.println(employees);
		return employees;

	}
	@RequestMapping(value = "/exception")
	public String exception() {
		String response = "";
		try {
			throw new Exception("Exception has occured....");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String stackTrace = sw.toString();
			logger.error("Exception - " + stackTrace);
			response = stackTrace;
		}

		return response;
	}
	
	
}
