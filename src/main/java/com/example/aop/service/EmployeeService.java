package com.example.aop.service;

import org.springframework.stereotype.Service;

import com.example.aop.model.Employee;

@Service
public class EmployeeService
{
	public Employee createEmployee(String name, String empId)
	{
		return Employee.builder()
				.name(name)
				.empId(empId)
				.build();
	}

	public void deleteEmployee(String empId)
	{
	}
}