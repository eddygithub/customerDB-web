package com.prudentcpa.customerDB.web.service;

public interface PhotoFileSerivce {

	String updateFile();

	void uploadFile(Long userId, byte[] photoBytes);
}
