package com.example.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.aop.model.Employee;
import com.example.aop.service.EmployeeService;

@RestController
public class EmployeeController
{
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService)
	{
		this.employeeService = employeeService;
	}

	@GetMapping("/add/employee")
	public Employee addEmployee(@RequestParam String name, @RequestParam String empId)
	{
		return employeeService.createEmployee(name, empId);
	}

	@GetMapping("/remove/employee")
	public String removeEmployee(@RequestParam String empId)
	{
		employeeService.deleteEmployee(empId);

		return "Employee removed";
	}
}
