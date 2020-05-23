package com.capg.pbms.accountmanagement.controller;


import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.capg.pbms.accountmanagement.model.Customer;
import com.capg.pbms.accountmanagement.service.AccountManagementService;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	AccountManagementService service;
	
//	Customer customer=new Customer(accountId,"123456", "1", "savings", "Active", 2000.00, 3.00,null, "`123456789012", "asdfg", "1234567890", "wer3456", "1234567890", "MALE", 	null, null);
	@PostMapping("/add")
	public ResponseEntity<Customer> addAccount(@RequestBody Customer customer){
		return new ResponseEntity<Customer>(service.addAccount(customer),HttpStatus.CREATED);
	}
	
	@GetMapping("/id/{accountId}")
	public ResponseEntity<Customer> getAccount(@PathVariable("accountId") long accountId)
	{	
		return new ResponseEntity<Customer>(service.getAccount(accountId),HttpStatus.OK); 		
    }
	
	@PutMapping("/updateName/id/{accountId}")
	public ResponseEntity<Customer> updateCustomerName(@PathVariable("accountId") long accountId,@RequestBody Customer customer)
	{
	  return new ResponseEntity<Customer>(service.updateCustomerName(accountId,customer),HttpStatus.OK);
	}
	
	@PutMapping("/updateContact/id/{accountId}")
	public ResponseEntity<Customer> updateCustomerContact(@PathVariable("accountId") long accountId,@RequestBody Customer customer)
	{
	  return new ResponseEntity<Customer>(service.updateCustomerContact(accountId,customer),HttpStatus.OK);
	}
	
	@PutMapping("/updateAddress/id/{accountId}")
	public ResponseEntity<Customer> updateCustomerAddress(@PathVariable("accountId") long accountId,@RequestBody Customer customer)
	{
	  return new ResponseEntity<Customer>(service.updateCustomerAddress(accountId,customer),HttpStatus.OK);
	}

  
	@DeleteMapping("/delete/id/{accountId}")
    public boolean deleteAccount(@PathVariable("accountId") long accountId)
    {
		return service.deleteAccount(accountId);
    }
	@GetMapping("/all")
	public ResponseEntity<List<Customer>> getAllAccounts()
	{
		return new ResponseEntity<List<Customer>>(service.getAllAccounts(),HttpStatus.OK);

}}
	

