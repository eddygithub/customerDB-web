package com.prudentcpa.customerDB.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.prudentcpa.customerDB.web.config.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class ApplicationTests {

	@Autowired
	private WebApplicationContext wac;
	
	@Before
	public void setup(){
	}
	
	@Test
	public void contextLoads() {
	}

}
