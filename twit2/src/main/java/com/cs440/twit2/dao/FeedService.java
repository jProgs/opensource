package com.cs440.twit2.dao;



import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.cs440.twit2.core.Feed;
import com.cs440.twit2.dao.FeedDAOImpl;

public class FeedService implements IFeedService {
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/Spring-Module.xml");
	private FeedDAO dao = (FeedDAO) context.getBean("feedDAO");
	
	private static List<Feed>feeds;
	{
		feeds = new ArrayList<Feed>();
			
		
	}

	public List<Feed> listFeeds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Feed> listFeedsByCategory(String category){
		
		List<Feed> catList = new ArrayList<Feed>();
		catList = dao.getFeedsByCategoryList(category);				
		return catList;
	}

	public void add(Feed feed) {
		// TODO Auto-generated method stub

	}

	public void removeFeed(Feed feed) {
		// TODO Auto-generated method stub

	}
	
	public String convertListToQuery(List<Feed> ourList){
		String query = "";
		
		for(Feed f: ourList){
			query += f.getName() + ",";
		}
		
		query = query.substring(0, query.length()-1);
		System.out.println(query);
		return query;
	}
	
	public void addOneToFavoritedCount(Feed feed){
		dao.addOneToFavorite(feed.getName());
	}

		
	public List<Feed> getAllStars() {
		List<Feed> allStars = new ArrayList<Feed>();
		allStars = dao.getAllStars();
		return allStars;		
	}
	

}
