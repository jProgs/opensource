package com.cs440.twit2.dao;

import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;
import com.cs440.twit2.core.Admin;


public interface AdminDAO {

	public void setDataSource (DataSource dataSource);
	
	public HashMap<String, Admin> getAdmins();
	
	public List<Admin> getAdminsList();
	
	public Admin getAdmin(String userName);
	
	public void deleteAdmin(String userName);
	
	public void updateAdmin(Admin admin);
	
	public int getFeedCount();
	
	public int getFeedCount(Admin admin);
	
	public boolean testPassword(Admin admin);
	
	public void addAdmin(Admin admin);
		
	
}
