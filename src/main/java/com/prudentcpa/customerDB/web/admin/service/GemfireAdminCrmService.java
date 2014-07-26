package com.prudentcpa.customerDB.web.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prudent.domain.User;
import com.prudentcpa.customerDB.repository.UserRepository;

@Service
public class GemfireAdminCrmService implements AdminCrmService {

	UserRepository userRepository;
	
	@Autowired
	public GemfireAdminCrmService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User activateUser(Long userId) {
		User user = userRepository.findOne(userId);
		user.setActive(true);
		userRepository.save(user);
		return user;
	}
}
