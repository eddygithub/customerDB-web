package com.prudentcpa.customerDB.web.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prudent.domain.Customer;
import com.prudentcpa.customerDB.web.service.CrmService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	CrmService crmService;
	
	@Autowired
	public CustomerController(CrmService crmService) {
		this.crmService = crmService;
	}
	
	@RequestMapping
	public ResponseEntity<List<Customer>> getAllCustomers(){
		//TODO remove when UI is done
		List<Customer> customerList = new ArrayList<Customer>();
		//return crmService.getAllCustomers();
		
		Customer customer = new Customer("John", "", "Chan", "123");
		
		customerList.add(customer);
		return new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/customer")
	ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){

		Customer addedCustomer = crmService.createNewCustomer(customer);
		
		URI uriOfNewCustomer = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/customers/{customer}")
				.buildAndExpand(addedCustomer.getCustomerID()).toUri();
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(uriOfNewCustomer);
		
		return new ResponseEntity<Customer>(addedCustomer, httpHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/customer")
	ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
		crmService.updateCustomer(customer);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);

	}
}
