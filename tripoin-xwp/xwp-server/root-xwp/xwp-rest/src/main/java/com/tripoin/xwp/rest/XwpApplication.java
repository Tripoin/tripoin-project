package com.tripoin.xwp.rest;

import com.tripoin.xwp.rest.constant.ApplicationConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
@ComponentScan(ApplicationConstant.COMPONENT_SCAN)
@EnableTransactionManagement
@EntityScan(basePackages = ApplicationConstant.BASE_PACKAGES)
@EnableJpaRepositories(basePackages = ApplicationConstant.BASE_PACKAGES)
public class XwpApplication extends ServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(XwpApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(XwpApplication.class);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
	}
}
