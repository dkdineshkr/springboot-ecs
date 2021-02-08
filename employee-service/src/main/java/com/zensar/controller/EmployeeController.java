package com.zensar.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.zensar.entity.Employee;
import com.zensar.repositories.EmployeeRepository;

@RestController
@RequestMapping("/empmgmt")
public class EmployeeController {

	private static final Log logger = LogFactory.getLog(EmployeeController.class);
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	Environment environment;
	
	@GetMapping("/employees")
	@ResponseStatus(HttpStatus.OK)
	public List<Employee> listAllUsers() {
		logger.info("Starting REST Client for listAllUsers start : "+new Date());
		List<Employee> empList = employeeRepository.findAll();
		empList.stream().forEach(e -> {
			e.setPort(environment.getProperty("local.server.port"));
			try {
				e.setHostName(InetAddress.getLocalHost().getHostName());
				e.setHostAddress(InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		return empList;
	}

	@GetMapping("/username/{username}")
	@ResponseStatus(HttpStatus.OK)
	public Employee getUserByUsername(@PathVariable("username") String userName) {
		return employeeRepository.findById(userName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("User with user name %s doesn't exist!", userName)));
	}

	@GetMapping("/health-status")
	public String healthStatus() {
		logger.info("Starting REST Client for health start : "+new Date());
		return "Employee Management Service UP and RUNNING";

	}

	@PostMapping("/employee")
	@ResponseStatus(HttpStatus.OK)
	public void createUser(@RequestBody @Valid Employee employee) {

		logger.info("Starting REST Client for createUser start : "+new Date());
		if (employeeRepository.findById(employee.getEmployeename()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					String.format("User with user name %s already exists!", employee.getEmployeename()));
		}
		if (employeeRepository.countByEmail(employee.getEmail()) != 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					String.format("User with email %s already exists!", employee.getEmail()));
		}
		employeeRepository.save(employee);
		// sendAccountActivationNotifications(user);
	}

	@PutMapping("/employee")
	@ResponseStatus(HttpStatus.OK)
	public void updateUser(@RequestBody @Valid Employee employee) {

		logger.info("Starting REST Client for updateUser start : "+new Date());
		Employee e = employeeRepository.findById(employee.getEmployeename())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("User with user name %s doesn't exist!", employee.getEmployeename())));

		// check email unique.
		if (e.getEmail().trim().equalsIgnoreCase(employee.getEmail().trim()) == false
				&& employeeRepository.countByEmail(employee.getEmail()) != 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					String.format("User with email %s already exists!", employee.getEmail()));
		}

		e.setEmail(employee.getEmail());
		e.setFirstname(employee.getFirstname());
		e.setLastname(employee.getLastname());
		employeeRepository.save(e);
	}

	@DeleteMapping("/employee/{employeename}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@PathVariable("employeename") String userName) {

		if (employeeRepository.findById(userName).isPresent()) {
			employeeRepository.deleteById(userName);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("User with user name %s doesn't exist!", userName));
		}
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
	
	public static void main(String args[]) throws UnknownHostException {
		InetAddress IP = InetAddress.getLocalHost();
        System.out.println("IP of my system is := "+IP.getHostAddress());
        System.out.println("IP of my system is := "+IP.getHostName());
	}

}
