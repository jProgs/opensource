package com.cs440.twit2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.cs440.twit2.core.Feed;

public class FeedRowNameMapper implements RowMapper{
	public Feed mapRow(ResultSet rs, int rowNum) throws SQLException {
		Feed fd = new Feed();
		fd.setName(rs.getString("name"));
		fd.setCategory(rs.getString("category"));		
		
		return fd;
	}
}
