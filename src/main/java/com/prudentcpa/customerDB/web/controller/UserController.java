package com.prudentcpa.customerDB.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prudent.domain.Customer;
import com.prudent.domain.User;
import com.prudentcpa.customerDB.web.service.CrmService;

@RestController
@RequestMapping(value="/users")
public class UserController{
	
	CrmService crmService;
	
	@Autowired
	public UserController(CrmService crmService) {
		this.crmService = crmService;
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{user}")
	ResponseEntity<User> deleteUser(@PathVariable Long user){
		return new ResponseEntity<User>(crmService.remove(user), HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{user}")
	ResponseEntity<User> findUser(@PathVariable Long user){
		User retrievedUser = crmService.findById(user);
		if(null == retrievedUser){
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(retrievedUser, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{user}")
	List<Customer> findUserCustomers(@PathVariable Long user){
		return crmService.findCustomersByUser(user);
	}
}
