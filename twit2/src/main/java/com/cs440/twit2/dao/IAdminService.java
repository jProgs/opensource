package com.cs440.twit2.dao;

import com.cs440.twit2.core.Admin;
import java.util.List;

public interface IAdminService {
	public List<Admin> listAdmins();
	public void add(Admin admin);
	void removeAdmin (Admin admin);
	public boolean testPassword(Admin admin);
	
}
