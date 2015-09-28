package com.tripoin.core.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripoin.core.pojo.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/WEB-INF/config/core-integration-config.xml" })
public class GenerateGSON implements ApplicationContextAware{

private ApplicationContext applicationContext;
	
	public <T> T getBean(Class<T> beanType) {
		return applicationContext.getBean(beanType);
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void runTes() {

		List<Employee> employees = new ArrayList<Employee>();
		Employee employee1 = new Employee();
		employee1.setName("A");
		
		Employee employee2 = new Employee();
		employee2.setName("B");
		
		employees.add(employee1);
		employees.add(employee2);
		
		ObjectMapper mapper = new ObjectMapper();
		String objResult = null;
		try {
			objResult = mapper.writeValueAsString(employees);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(objResult);
	}

}
