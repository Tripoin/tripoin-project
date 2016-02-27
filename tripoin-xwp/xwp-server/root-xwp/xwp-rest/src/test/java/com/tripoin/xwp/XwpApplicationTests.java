package com.tripoin.xwp;

import com.tripoin.xwp.rest.XwpApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = XwpApplication.class)
@WebAppConfiguration
public class XwpApplicationTests {

	@Test
	public void contextLoads() {
	}

}
