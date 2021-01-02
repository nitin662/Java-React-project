package com.nitin.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitin.project.exception.ResourceNotFoundException;
import com.nitin.project.model.Node;
import com.nitin.project.repository.NodeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	private NodeRepository employeeRepository;
	//get all employee list
	@GetMapping("/employees")
	public List<Node> getAllEmployees(){
		return employeeRepository.findAll();
	}
	//create employee rest api
	@PostMapping("/employees")
	public Node createEmployee(@RequestBody Node employee) {
		return employeeRepository.save(employee);
	}
	//get Employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Node> getEmployeeById(@PathVariable Long id){
		Node employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+id));
		return ResponseEntity.ok(employee);
	}
	//update Employee rest api
	@PutMapping("/employees/{id}")
	public ResponseEntity<Node> updateEmployee(@PathVariable Long id, @RequestBody Node emaployeeDetail){
		Node employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+id));
		employee.setFirstName(emaployeeDetail.getFirstName());
		employee.setLastName(emaployeeDetail.getLastName());
		employee.setEmailID(emaployeeDetail.getEmailID());
		
		Node updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);

	}
	//delete Employee rest API
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Node employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+id));
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
