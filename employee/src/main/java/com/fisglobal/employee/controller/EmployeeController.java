package com.fisglobal.employee.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fisglobal.employee.exception.ResourceNotFound;
import com.fisglobal.employee.model.Employee;
import com.fisglobal.employee.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllEmployee(){
		
		List<Employee> employees = employeeRepository.findAll();
		
		if(employees.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(employees);
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("NO DATA FOUND !!");
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") String id) throws ResourceNotFound{
		
		Employee empl = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFound("Employee not found with id : "+id));
		return ResponseEntity.ok().body(empl);
		
	}
	
	@PostMapping("/addDept")
	public ResponseEntity<?> createEmployee(@Valid @RequestBody Employee newEmployee){

		if(employeeRepository.existsById(newEmployee.getUserId())){
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Already Available");
		}
		
		Employee empl2 = employeeRepository.save(newEmployee);
		System.out.println(newEmployee);
		if(empl2 != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(newEmployee);
		}
	}
	@DeleteMapping("/deleteemployee/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable String id){
		Optional<Employee> empl = employeeRepository.findById(id);
		if(empl.isPresent()) {
			employeeRepository.delete(empl.get());
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@Transactional
	@PutMapping("/updateemployee/{id}")
}

