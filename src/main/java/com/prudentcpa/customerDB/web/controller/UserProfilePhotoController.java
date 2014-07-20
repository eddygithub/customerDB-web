package com.prudentcpa.customerDB.web.controller;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prudentcpa.customerDB.web.domain.ProfilePhoto;
import com.prudentcpa.customerDB.web.service.CrmService;

@RestController
@RequestMapping(value= "/users/{user}/photo")
public class UserProfilePhotoController {

	CrmService crmService;
	
	@Autowired
	public UserProfilePhotoController(CrmService crmService) {
		this.crmService = crmService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	HttpEntity<Void> writeUserProfile(@PathVariable Long user, @RequestParam MultipartFile file) throws IOException{
		byte bytesForProfilePhoto[] = FileCopyUtils.copyToByteArray(file.getInputStream());
		crmService.writeUserProfilePhoto(user, MediaType.parseMediaType(file.getContentType()), bytesForProfilePhoto);
		HttpHeaders httpHeaders = new HttpHeaders();
		URI uriOfPhoto = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("users/{user}/photo")
				.buildAndExpand(user).toUri();
		
		httpHeaders.setLocation(uriOfPhoto);
		
		return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	HttpEntity<byte[]> getUserProfilePhoto(@PathVariable Long user){
		ProfilePhoto profilePhoto = crmService.getUserProfilePhoto(user);
		if(null != profilePhoto){
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(profilePhoto.getMediaType());
			return new ResponseEntity<byte[]>(profilePhoto.getPhoto(), httpHeaders, HttpStatus.OK);
		}
		throw new IllegalArgumentException();
	}
}
