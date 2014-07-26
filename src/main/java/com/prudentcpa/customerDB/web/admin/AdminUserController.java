package com.prudentcpa.customerDB.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prudent.domain.User;
import com.prudentcpa.customerDB.web.admin.service.AdminCrmService;

@RestController
@RequestMapping("/admin/users")
public class AdminUserController {

	private AdminCrmService crmService;
	
	@Autowired
	public AdminUserController(AdminCrmService crmService) {
		this.crmService = crmService;
	}
	
	@RequestMapping(value ="/user/{userId}", method = RequestMethod.POST)
	public User activateUser(@PathVariable Long userId){
		return crmService.activateUser(userId);
	}
}
