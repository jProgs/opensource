package com.cs440.twit2.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import com.cs440.twit2.core.Feed;
import com.cs440.twit2.dao.FeedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


/*
Feed

idNumber
name
lastTweet
favoritedCount
picture
category
*/
 


public class FeedDAOImpl extends JdbcDaoSupport implements FeedDAO {

	private static final String SELECT_FEED_BY_NAME = "SELECT name, favoritedCount FROM Feed WHERE name= ?";
	private static final String SELECT_ALL_FEEDS = "SELECT name, favoritedCount FROM Feed";
	private static final String SELECT_FEEDS_BY_CATEGORY = "SELECT name, favoritedCount FROM cs440.Feed WHERE category=";
	
	public HashMap<String, Feed> getFeeds() {		
		HashMap<String, Feed> li = new HashMap<String, Feed>();
		List<Feed> feeds = getJdbcTemplate().query(
				SELECT_ALL_FEEDS, new FeedRowMapper());		
		for (Feed fd: feeds){
			li.put(fd.getName(), fd);
		}
		
		return li;
	}

	public Feed getFeed(String name) {
		return (Feed)getJdbcTemplate().queryForObject(SELECT_FEED_BY_NAME, new Object[]{name}, new FeedRowMapper());
	}

	public void deleteFeed(String name) {
		String sql = "DELETE FROM cs440.feed WHERE name='" + name + "'";
		getJdbcTemplate().execute(sql);
		
	}

	public void updateFeed(Feed feed) {
		// TODO Auto-generated method stub
		
	}

	public int getFeedCount() {
		System.out.println("at getFeedCount in FeedDAOImpl");
		String sql = "SELECT count(*) from cs440.Feed";
		return getJdbcTemplate().queryForInt(sql);	
	}
	
	public int getFeedCount(Feed feed){
		String sql = "SELECT count(*) from cs440.Feed WHERE name='";
		sql += feed.getName();
		sql += "'";
		System.out.println(sql);
		return getJdbcTemplate().queryForInt(sql);
	}

	public HashMap<String, Feed> getFeedsByCategory(String category) {
		HashMap<String, Feed> li = new HashMap<String, Feed>();
		String s = SELECT_FEEDS_BY_CATEGORY + "'" +category + "'";
		List<Feed> feeds = getJdbcTemplate().query(
				s, new FeedRowMapper());
		for (Feed fd: feeds){
			li.put(fd.getName(), fd);
		}
		return li;
	}
	
	public List<Feed> getFeedsByCategoryList(String category) {
		System.out.println("in FeedDAOImpl -- getfeedsbycategorylist");
		HashMap<String, Feed> li = new HashMap<String, Feed>();
		String s = SELECT_FEEDS_BY_CATEGORY + "'" +category + "'";
		System.out.println("point 2");
		
		List<Feed> returnedFeed = new ArrayList<Feed>();
		
		List<Feed> feeds = getJdbcTemplate().query(
				s, new FeedRowMapper());
		for (Feed fd: feeds){
			li.put(fd.getName(), fd);
			}
		for (Feed thing : li.values()) {
		    returnedFeed.add(thing);
		}
		return returnedFeed;
		
	}
	
	public void addFeed(Feed feed)
	{
		int fav = 0;
		String cat = feed.getCategory();
		String name = feed.getName();
		String sql;
		sql = "INSERT INTO cs440.feed VALUES ('" + name + "'," + fav + ", '" + name + "')";
		getJdbcTemplate().execute(sql);
	
	}
	
	public void addOneToFavorite(String name){
		String sql;
		sql = "UPDATE cs440.feed SET favoritedCount = favoritedCount + 1 WHERE name='" + name + "'";
		getJdbcTemplate().execute(sql);
	}

	@Override
	public List<Feed> getAllStars() {
		FeedRowMapper frm = new FeedRowMapper();
		List<Feed> allStars = new ArrayList<Feed>();
					
		String sql = "SELECT max(favoritedCount), name, category from cs440.feed where category='games'";
		String sq2 = "SELECT max(favoritedCount), name, category from cs440.feed where category='comedy';";
		String sq3 = "SELECT max(favoritedCount), name, category from cs440.feed where category='soccer';";
		String sq4 = "SELECT max(favoritedCount), name, category from cs440.feed where category='technology';";
		String sq5 = "SELECT max(favoritedCount), name, category from cs440.feed where category='news';";
		
		
		List<Feed> feed1 = getJdbcTemplate().query(sql, new FeedRowNameMapper());
		List<Feed> feed2 = getJdbcTemplate().query(sq2, new FeedRowNameMapper());
		List<Feed> feed3 = getJdbcTemplate().query(sq3, new FeedRowNameMapper());
		List<Feed> feed4 = getJdbcTemplate().query(sq4, new FeedRowNameMapper());
		List<Feed> feed5 = getJdbcTemplate().query(sq5, new FeedRowNameMapper());
		
		allStars.add(feed1.get(0));
		allStars.add(feed2.get(0));
		allStars.add(feed3.get(0));
		allStars.add(feed4.get(0));
		allStars.add(feed5.get(0));
		
		return allStars;
	}

}