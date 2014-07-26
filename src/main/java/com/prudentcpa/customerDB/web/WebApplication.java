package com.prudentcpa.customerDB.web;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import com.prudentcpa.customerDB.repository.PersistentApplication;
import com.prudentcpa.customerDB.web.service.FileSystemPhotoFileService;

@ComponentScan
@Configuration
@EnableAutoConfiguration
@EnableGemfireRepositories
@Import(value={PersistentApplication.class})
public class WebApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class);
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(WebApplication.class);
	}
	
//	use for file upload
	@Bean
	public MultipartConfigElement multipartConfigElement(){
		return new MultipartConfigElement("");
	}

	@Bean
	public FileSystemPhotoFileService fileSystemPhotoFileService(){
		return new FileSystemPhotoFileService("/Users/zhue/Desktop");
	}
}