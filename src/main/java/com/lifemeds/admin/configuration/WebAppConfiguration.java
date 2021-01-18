package com.lifemeds.admin.configuration;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfiguration implements WebMvcConfigurer {
	@Value("${upload.folder}")
    private String uploadFolder;
	
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    //Meaning: When /images is read in the url, it will automatically parse /images into home folder C:\Users\anjup"
	    registry.addResourceHandler("/images/**").addResourceLocations("file:"+ System.getProperty("user.home")+ File.separator + uploadFolder + File.separator);

	}
}

