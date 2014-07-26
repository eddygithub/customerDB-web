package com.prudentcpa.customerDB.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prudent.domain.Customer;
import com.prudent.domain.User;
import com.prudent.domain.util.CalendarUtil;
import com.prudentcpa.customerDB.repository.CustomerRepository;
import com.prudentcpa.customerDB.repository.UserRepository;
import com.prudentcpa.customerDB.web.domain.ProfilePhoto;

@Service
@Transactional
public class GemfireCrmService implements CrmService {
	
	private CustomerRepository customerRepository;
	private UserRepository userRepository;
	
	@Autowired
	PhotoFileSerivce photoFileSerivce;
	
	@Autowired
	public GemfireCrmService(CustomerRepository customerRepository, UserRepository userRepository) {
		this.customerRepository = customerRepository;
		this.userRepository = userRepository;
	}
	
	@Override
	public User createNewUser(User user) {
		userRepository.save(user);
		return user;
	}
	
	@Override
	public User findUserById(Long user) {
		return userRepository.findOne(user);
	}
	
	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public User deleteUser(Long user) {
		User reUser = userRepository.findOne(user);
		reUser.setActive(false);
		reUser.setDeleteDate(CalendarUtil.today());
		return reUser;
	}
	
	@Autowired
	public List<User> getAllUsers() {
		return userRepository.getAllActiveUsers();
	}
	
	@Override
	public void writeUserProfilePhoto(Long userId, MediaType mediaType,
			byte[] photoBytes) {
		User user = findUserById(userId);
		user.setProfilePhotoMediaType(mediaType.toString());
		user.setProfilePhotoImported(true);
		userRepository.save(user);
		photoFileSerivce.uploadFile(userId, photoBytes);
	}

	//customer CRUD
	@Override
	public Customer createNewCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}
	
	@Override
	public Customer findCustomerById(Long customerId) {
		return customerRepository.findOne(customerId);
	}
	
	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	@Override
	public Customer deleteCustomer(Long customerId) {
		Customer customer = customerRepository.findByCustomerID(customerId);
		customer.setDeleteDate(CalendarUtil.today());
		customer.setActive(false);
		return customer;
	}
	
	@Override
	public List<Customer> findCustomersByUser(Long user) {
		return customerRepository.findCustomersByUserID(user);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer assignCustomerToUser(Long userID, Long customerId) {
		User user = userRepository.findOne(userID);
		Customer customer = customerRepository.findOne(customerId);
		if(null != user){
			customer.setUserID(user.getId());
		}
		customerRepository.save(customer);
		return customer;
	}

	@Override
	public List<Customer> assignCustomersToUser(Long user, List<Long> customerIds) {
		User retrievedUser = userRepository.findOne(user);
		List<Customer> customers = customerRepository.findCustomersByIDs(customerIds);
		for(Customer customer : customers){
			customer.setUserID(retrievedUser.getId());
		}
		customerRepository.save(customers);
		return customers;
	}
	
	@Override
	public ProfilePhoto getUserProfilePhoto(Long user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void writeCustomerProfilePhoto(Long customerId, MediaType mediaType,
			byte[] photoBytes) {
		// TODO Auto-generated method stub
		Customer customer = findCustomerById(customerId);
		customer.setProfilePhotoMediaType(mediaType.toString());
		customer.setProfilePhotoImported(true);
		customerRepository.save(customer);
		photoFileSerivce.uploadFile(customerId, photoBytes);
	}
}
