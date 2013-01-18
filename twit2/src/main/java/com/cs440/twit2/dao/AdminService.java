package com.cs440.twit2.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import com.cs440.twit2.core.Admin;
import com.cs440.twit2.dao.AdminDAOImpl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AdminService implements IAdminService {
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/Spring-Module.xml");
	private AdminDAO dao = (AdminDAO) context.getBean("adminDAO");
	
	private static List<Admin>admins;
	{
		admins = new ArrayList<Admin>();
	}

	public List<Admin> listAdmins() {
		List<Admin> listAdmins = new ArrayList<Admin>();
		listAdmins = dao.getAdminsList();
		return listAdmins;
	}

	public void add(Admin admin) {
		dao.addAdmin(admin);
	}

	public void removeAdmin(Admin admin) {
		dao.deleteAdmin(admin.getUserName());

	}
	
	public boolean testPassword(Admin admin){
		return dao.testPassword(admin);
	}
	

}
