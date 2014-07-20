package com.prudentcpa.customerDB.web.service;

import java.util.List;

import org.springframework.http.MediaType;

import com.prudent.domain.Customer;
import com.prudent.domain.User;
import com.prudentcpa.customerDB.web.domain.ProfilePhoto;


public interface CrmService {
	
	User remove(Long user);
	
	User findById(Long user);
	
	List<Customer> findCustomersByUser(Long user);
	
	List<Customer> getAllCustomers();
	
	Customer addCustomer(Customer customer);
	
	User assignCustomerToUser(Long user, Customer customer);
	
	void writeUserProfilePhoto(Long user, MediaType mediaType, byte[] photoBytes);

	ProfilePhoto getUserProfilePhoto(Long user);
}
