package com.prudentcpa.customerDB.web.service;

import java.util.List;

import org.springframework.http.MediaType;

import com.prudent.domain.Customer;
import com.prudent.domain.User;
import com.prudentcpa.customerDB.web.domain.ProfilePhoto;

public interface CrmService {
	// User CRUD
	User createNewUser(User user);

	User findUserById(Long user);

	/**
	 * Get all active users
	 * @return
	 */
	List<User> getAllUsers();

	User deleteUser(Long user);

	User updateUser(User user);

	// User other methods
	ProfilePhoto getUserProfilePhoto(Long user);

	Customer assignCustomerToUser(Long user, Long customerId);

	List<Customer> assignCustomersToUser(Long user, List<Long> customerIds);

	void writeUserProfilePhoto(Long user, MediaType mediaType, byte[] photoBytes);

	// Customer CRUD
	Customer createNewCustomer(Customer customer);

	Customer findCustomerById(Long customerId);

	Customer updateCustomer(Customer customer);

	Customer deleteCustomer(Long customerId);

	// Customer other methods
	List<Customer> findCustomersByUser(Long user);

	List<Customer> getAllCustomers();

	void writeCustomerProfilePhoto(Long customerId, MediaType mediaType,
			byte[] photoBytes);
}
