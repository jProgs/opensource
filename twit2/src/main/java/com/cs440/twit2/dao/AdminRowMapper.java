package com.cs440.twit2.dao;



import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.cs440.twit2.core.Admin;

public class AdminRowMapper implements RowMapper{
	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		Admin ad = new Admin();
		ad.setUserName(rs.getString("userName"));
		ad.setPassword(rs.getString("password"));
		
		return ad;
	}
}
