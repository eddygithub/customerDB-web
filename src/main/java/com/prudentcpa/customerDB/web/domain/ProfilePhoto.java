package com.prudentcpa.customerDB.web.domain;

import org.springframework.http.MediaType;

public class ProfilePhoto {
	public MediaType mediaType;
	public byte[] photo;
	public Long userId;
	
	public ProfilePhoto(long userId, byte[] data, MediaType mediaType) {
		this.mediaType = mediaType;
		this.photo = data;
		this.userId = userId;
	}

	public MediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
