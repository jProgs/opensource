package com.cs440.twit2.dao;


import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.cs440.twit2.core.Admin;
import com.cs440.twit2.dao.AdminRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class AdminDAOImpl extends JdbcDaoSupport implements AdminDAO {

	private static final String SELECT_ALL_ADMINS = "Select userName, password FROM cs440.Admin";
	
	
	public HashMap<String, Admin> getAdmins() {
		HashMap<String, Admin> li = new HashMap<String, Admin>();		
		List<Admin> admins = getJdbcTemplate().query(SELECT_ALL_ADMINS, new AdminRowMapper());
		for (Admin ad: admins){
			li.put(ad.getUserName(), ad);
		}
		return null;
	}

	public List<Admin> getAdminsList() {
		List<Admin> adminsList = getJdbcTemplate().query(SELECT_ALL_ADMINS, new AdminRowMapper());
		return adminsList;
	}

	public Admin getAdmin(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteAdmin(String userName) {
		String sql = "DELETE FROM cs440.Admin WHERE userName='" + userName + "'";
		getJdbcTemplate().execute(sql);
	}

	public void updateAdmin(Admin admin) {
		// TODO Auto-generated method stub

	}

	public int getFeedCount() {
		String sql = "SELECT count(*) from cs440.Admin";
		return getJdbcTemplate().queryForInt(sql);
	}
	
	public int getFeedCount(Admin admin){
		String sql = "SELECT count(*) from cs440.Admin WHERE userName='";
		sql += admin.getUserName();
		sql += "' AND password='";
		sql += admin.getPassword();
		sql += "'";		
		int i = getJdbcTemplate().queryForInt(sql);
		System.out.println(sql);
		return i;
	}

	public boolean testPassword(Admin admin) {
		String sql = "";
		sql = "SELECT count(*) FROM cs440.admin WHERE userName='" + admin.getUserName() + "' AND password='" + admin.getPassword() + "'";
		int i = 0;
		i = getJdbcTemplate().queryForInt(sql);
		if(i == 1)
			return true;
		else
			return false;		
	}

	public void addAdmin(Admin admin){
		String sql = "INSERT INTO cs440.admin VALUES ('" + admin.getUserName() + "','" + admin.getPassword() + "')";
		getJdbcTemplate().execute(sql);
	}
}
