package com.cs440.twit2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.cs440.twit2.core.Feed;


//****************************************************************************************
//NEED TO ADJUST DIFFERENT ASPECTS OF THIS TO THE ACTUAL TABLE NAME AND THE TABLE COLUMNS
//****************************************************************************************

public class FeedRowMapper implements RowMapper {
	public Feed mapRow(ResultSet rs, int rowNum) throws SQLException {
		Feed fd = new Feed();
		fd.setName(rs.getString("name"));
		fd.setFavoritedCount(Integer.parseInt(rs.getString("favoritedCount")));
		
		
		return fd;
	}
}
