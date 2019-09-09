package com.infy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.infy.model.Employee;
import com.infy.repository.EmployeeRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@RequestMapping(value = "/employee")
	public void getEmployee() {
		System.out.println("Hello");
	}

	@GetMapping(value = "/employees")

	public List<Employee> getAllEmployees() {

		return employeeRepository.findAllByOrderByIdAsc();
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/save")
	Employee createOrSaveEmployee(@RequestBody Employee newEmployee) {
		return employeeRepository.save(newEmployee);
	}

	@GetMapping("/employees/{id}")
	Employee getEmployeeById(@PathVariable Integer id) {
		return employeeRepository.findById(id).get();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/employee", method = RequestMethod.PUT)
	public @ResponseBody void updateStudent(@RequestBody Employee newEmployee) {

		Employee e = employeeRepository.findAll().stream().filter(p -> p.getId() == newEmployee.getId()).findFirst()
				.get();
		e.setName(newEmployee.getName());
		e.setAddress(newEmployee.getAddress());
		e.setId(newEmployee.getId());
		e.setSalary(newEmployee.getSalary());
		employeeRepository.save(e);
		//return "success";

	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/employees/{id}")
	void deleteEmployee(@PathVariable Integer id) {
		employeeRepository.deleteById(id);

	}

}
