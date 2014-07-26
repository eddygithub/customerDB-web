package com.prudentcpa.customerDB.web.service;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.prudentcpa.customerDB.web.exception.UserProfilePhotoException;

public class FileSystemPhotoFileService implements PhotoFileSerivce {
	
	private String fileDirectory;
	
	public FileSystemPhotoFileService(String fileDirectory) {
		this.fileDirectory = fileDirectory;
	}
	
	
	@Override
	public String updateFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void uploadFile(Long userId, byte[] photoBytes) {
		 ByteArrayInputStream byteArrayInputStream = null;
	        FileOutputStream fileOutputStream = null;
	        try {
	            fileOutputStream = new FileOutputStream(fileDirectory+userId);
	            byteArrayInputStream = new ByteArrayInputStream(photoBytes);
	            IOUtils.copy(byteArrayInputStream, fileOutputStream);
	        } catch (IOException e) {
	            throw new UserProfilePhotoException(e);
	        } finally {
	            IOUtils.closeQuietly(fileOutputStream);
	            IOUtils.closeQuietly(byteArrayInputStream);
	        }


	}

}
