package com.avg.platform.test;


import org.testng.annotations.Test;

import com.avg.platform.web.pages.AdminPage;
import com.avg.platform.test.BaseTest;


public class FirstTest extends BaseTest   {

	@Test
	public void testMethod(){
			new AdminPage(driver)
			.loadAsAnonymousUser()
			.loginAs("qa-admin", "12345");
			
			
      try {Thread.sleep(2000);}catch(Exception e){}
	
	}
	

	
}
