package com.prudentcpa.customerDB.web.service;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prudent.domain.Customer;
import com.prudent.domain.User;
import com.prudentcpa.customerDB.repository.CustomerRepository;
import com.prudentcpa.customerDB.repository.UserRepository;
import com.prudentcpa.customerDB.web.UserProfilePhotoException;
import com.prudentcpa.customerDB.web.domain.ProfilePhoto;

@Service
@Transactional
public class GemfireCrmService implements CrmService {
	
	private CustomerRepository customerRepository;
	private UserRepository userRepository;
	
	@Autowired
	public GemfireCrmService(CustomerRepository customerRepository, UserRepository userRepository) {
		this.customerRepository = customerRepository;
		this.userRepository = userRepository;
	}
	
	
	@Override
	public User remove(Long user) {
		User reUser = userRepository.findOne(user);
		userRepository.delete(user);
		return reUser;
	}

	@Override
	public User findById(Long user) {
		return userRepository.findOne(user);
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
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepository.save(customer);
		return null;
	}

	@Override
	public User assignCustomerToUser(Long userID, Customer customer) {
		User user = userRepository.findOne(userID);
		if(null != user){
			customer.setUserID(user.getID());
		}
		return user;
	}

	@Override
	public void writeUserProfilePhoto(Long userId, MediaType mediaType,
			byte[] photoBytes) {
		   User user = findById(userId);
	        user.setProfilePhotoMediaType(mediaType.toString());
	        user.setProfilePhotoImported(true);
	        userRepository.save(user);

	        ByteArrayInputStream byteArrayInputStream = null;
	        FileOutputStream fileOutputStream = null;
	        try {
	            fileOutputStream = new FileOutputStream(fileForPhoto(userId));
	            byteArrayInputStream = new ByteArrayInputStream(photoBytes);
	            IOUtils.copy(byteArrayInputStream, fileOutputStream);
	        } catch (IOException e) {
	            throw new UserProfilePhotoException(e);
	        } finally {
	            IOUtils.closeQuietly(fileOutputStream);
	            IOUtils.closeQuietly(byteArrayInputStream);
	        }

	}

	private String fileForPhoto(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ProfilePhoto getUserProfilePhoto(Long user) {
		// TODO Auto-generated method stub
		return null;
	}

}
