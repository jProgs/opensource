package com.cs440.twit2.dao;

import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;
import com.cs440.twit2.core.Feed;

//****************************************************************************************
//NEED TO ADJUST DIFFERENT ASPECTS OF THIS TO THE ACTUAL TABLE NAME AND THE TABLE COLUMNS
//****************************************************************************************

public interface FeedDAO {
	
	public void setDataSource (DataSource dataSource);
	
	public HashMap<String, Feed> getFeeds();
	
	public Feed getFeed(String id);
	
	public void deleteFeed(String id);
	
	public void updateFeed(Feed feed);
	
	public void addFeed(Feed feed);
	
	public int getFeedCount();
	
	public int getFeedCount(Feed feed);
	
	public HashMap<String, Feed> getFeedsByCategory(String category);
	
	public List<Feed> getFeedsByCategoryList(String category);
	
	public void addOneToFavorite(String name);

	public List<Feed> getAllStars();

	
}
