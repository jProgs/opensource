package com.cs440.twit2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cs440.twit2.core.Feed;
import com.cs440.twit2.dao.FeedDAO;
import com.cs440.twit2.dao.FeedDAOImpl;

public class FeedDAOImplTester {

	@Test
	public void testRetrievingFeeds() {
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/Spring-Module.xml");		
		FeedDAO dao = (FeedDAO) context.getBean("feedDAO");
		HashMap<String,Feed> feeds = dao.getFeeds();
		System.out.println(feeds.size() + " in the hashmap in test");
		Assert.assertEquals(true, feeds.size()>=1);
	}
	
	@Test
	public void testCategory(){
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/Spring-Module.xml");
		FeedDAO dao = (FeedDAO) context.getBean("feedDAO");
		HashMap<String,Feed> feedsCat = dao.getFeedsByCategory("soccer");		
		Assert.assertEquals(true,  feedsCat.size()>=1);
	}
	
	@Test
	public void testTechnology(){
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/Spring-Module.xml");
		FeedDAO dao = (FeedDAO) context.getBean("feedDAO");
		HashMap<String,Feed> feedsCat = dao.getFeedsByCategory("technology");
		Assert.assertEquals(true,  feedsCat.size()>=1);
	}
	
	@Test
	public void testCategoryList(){
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/Spring-Module.xml");
		FeedDAO dao = (FeedDAO) context.getBean("feedDAO");
		List<Feed> myList = new ArrayList<Feed>();
		myList = dao.getFeedsByCategoryList("soccer");
		
		for (Feed fd: myList){
			System.out.println(fd.getName());
		}
		
		
		Assert.assertEquals(true,  myList.size() >= 1);
		
	}
	
	@Test
	public void testCount(){
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/Spring-Module.xml");
		FeedDAO dao = (FeedDAO) context.getBean("feedDAO");
		int i = dao.getFeedCount();
		System.out.println(i + "   *** number of feeds total");
		Assert.assertEquals(true,  i >= 1);
	}
	/*
	@Test
	public void addFeed(){
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/Spring-Module.xml");
		FeedDAO dao = (FeedDAO) context.getBean("feedDAO");
		Feed f = new Feed();
		f.setCategory("soccer");
		f.setName("soccerFeed2");
		dao.addFeed(f);
		Assert.assertEquals(true, dao.getFeedCount(f) == 1);
	}
	*/
	
	@Test
	public void testGetFeed(){
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/Spring-Module.xml");
		FeedDAO dao = (FeedDAO) context.getBean("feedDAO");
		Feed f = new Feed();
		System.out.println("********");
		System.out.println(f.getFavoritedCount());
		f = dao.getFeed("Microsoft");
		System.out.println(f.getFavoritedCount());
		Assert.assertEquals(true, f.getFavoritedCount() > 0);
	}
	
	@Test
	public void addOneToFavoriteTest(){
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/Spring-Module.xml");
		FeedDAO dao = (FeedDAO) context.getBean("feedDAO");
		Feed f = new Feed();
		f = dao.getFeed("Microsoft");
		int i = 0;
		i = f.getFavoritedCount();		
		dao.addOneToFavorite("Microsoft");
		f = dao.getFeed("Microsoft");
		Assert.assertEquals(true,  (f.getFavoritedCount() - i) == 1);
	}
	
	@Test
	public void allStarTester(){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/Spring-Module.xml");
		FeedDAO dao = (FeedDAO) context.getBean("feedDAO");
		List<Feed> allStars = new ArrayList<Feed>();
		allStars = dao.getAllStars();
		System.out.println(allStars.size());
		for(Feed f: allStars){
			System.out.println(f.getName());
		}
		
		Assert.assertEquals(true,  allStars.size() == 5);			
	}
	
	
}
