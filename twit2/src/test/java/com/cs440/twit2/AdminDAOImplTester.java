package com.cs440.twit2;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.cs440.twit2.core.Admin;
import com.cs440.twit2.dao.AdminDAO;
import com.cs440.twit2.dao.AdminDAOImpl;


import org.junit.Test;

public class AdminDAOImplTester {

	@Test
	public void testAdminRetrievals() {
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/Spring-Module.xml");
		AdminDAO dao = (AdminDAO) context.getBean("adminDAO");
		List<Admin> adminList = new ArrayList<Admin>();
		adminList = dao.getAdminsList();
		Assert.assertEquals(true, adminList.size() >= 1);
	}
	
	@Test
	public void checkPassword(){
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/Spring-Module.xml");
		AdminDAO dao = (AdminDAO) context.getBean("adminDAO");
		Admin ad = new Admin();
		ad.setPassword("root");
		ad.setUserName("root");
		Assert.assertEquals(true, dao.testPassword(ad) == true);
	}
	
	@Test public void checkAdminCredentialRetriever(){
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/Spring-Module.xml");
		AdminDAO dao = (AdminDAO) context.getBean("adminDAO");
		Admin ad = new Admin();
		ad.setPassword("root");
		ad.setUserName("root");
		int i = dao.getFeedCount(ad);
		System.out.println(i + "aaaaaaaaaaaaaaaaaaaa number of these admins");
		Assert.assertEquals(true, i == 1);
	}

}
