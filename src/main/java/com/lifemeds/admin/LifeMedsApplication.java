package com.lifemeds.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import com.lifemeds.admin.listener.EventListener;
import com.lifemeds.admin.listener.SessionListener;

@ServletComponentScan
@SpringBootApplication
public class LifeMedsApplication {

	public static void main(String[] args) {
		SpringApplication sa = new SpringApplication();
		//sa.getRunListeners(new SessionListener());
		sa.run(LifeMedsApplication.class, args);

	}

}
