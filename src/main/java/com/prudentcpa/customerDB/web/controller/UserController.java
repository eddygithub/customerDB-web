package com.prudentcpa.customerDB.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prudent.domain.Customer;
import com.prudent.domain.User;
import com.prudentcpa.customerDB.web.service.CrmService;

@RestController
@RequestMapping("/users")
public class UserController{
	
	CrmService crmService;
	
	@Autowired
	public UserController(CrmService crmService) {
		this.crmService = crmService;
	}
	
	@RequestMapping
	public List<User> getUsers(){
		return crmService.getAllUsers();
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{user}")
	public ResponseEntity<User> deleteUser(@PathVariable Long user){
		return new ResponseEntity<User>(crmService.deleteUser(user), HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{user}")
	public ResponseEntity<User> findUser(@PathVariable Long user){
		User retrievedUser = crmService.findUserById(user);
		if(null == retrievedUser){
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(retrievedUser, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{user}/customers")
	public List<Customer> findCustomersByUser(@PathVariable Long user){
		return crmService.findCustomersByUser(user);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{user}/{customers}")
	public List<Customer> assignCustomers(@PathVariable Long user, @PathVariable List<Long> customers){
		return crmService.assignCustomersToUser(user, customers);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{user}")
	public ResponseEntity<User> createNewUser(@RequestBody User user){
		crmService.createNewUser(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
}
