package com.test.controller;

import java.util.ArrayList;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.Employee;
import com.test.repository.EmployeeRepository;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class EmployeeController {

  @Autowired
  EmployeeRepository empRepository;
  
  
  @RequestMapping(value = "/emp", method = RequestMethod.GET)
  public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(required = false) String name) {
    try {
      List<Employee> emp = new ArrayList<Employee>();

      if (name == null)
        empRepository.findAll().forEach(emp::add);
      else
        empRepository.findByNameContaining(name).forEach(emp::add);

      if (emp.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(emp, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/emp/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
    Optional<Employee> empData = empRepository.findById(id);

    if (empData.isPresent()) {
      return new ResponseEntity<>(empData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/emp")
  public ResponseEntity<Employee> createEmployee(@RequestBody Employee emp) {
    try {
      Employee _emp = empRepository
          .save(new Employee(emp.getName(),emp.getSalary(),emp.getDesignation()));
      return new ResponseEntity<>(_emp, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /* 
    if (empData.isPresent()) {
        Employee _emp = empData.get();
        _emp.setName(emp.getName());
        _emp.setDesignation(emp.getDesignation());
        return new ResponseEntity<>(empRepository.update(_emp), HttpStatus.OK);
      } else {
      	 return new ResponseEntity<>(empRepository.save(_emp), HttpStatus.OK); 
      }*/

  }

  @PutMapping("/emp/{id}")
  public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee emp) {
    Optional<Employee> empData = empRepository.findById(id);

    if (empData.isPresent()) {
      Employee _emp = empData.get();
      _emp.setName(emp.getName());
      _emp.setSalary(emp.getSalary());
      _emp.setDesignation(emp.getDesignation());
      return new ResponseEntity<>(empRepository.save(_emp), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  
 
  @DeleteMapping("/emp/{id}")
  public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long id) {
    try {
      empRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/emp")
  public ResponseEntity<HttpStatus> deleteAllTutorials() {
    try {
      empRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }



}