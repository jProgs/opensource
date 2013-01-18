package com.cs440.twit2.presentation;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cs440.twit2.core.Feed;
import com.cs440.twit2.dao.IFeedService;
import com.cs440.twit2.dao.FeedService;

@Controller
public class FeedController  {
	
	@Inject
	private static IFeedService service;
	{
		service = new FeedService();
	}
	
	
	@RequestMapping("/home")
	public ModelAndView addNewArmor() {
		return new ModelAndView("feedsViews/home", "command", new Feed());
	}
	
		
	
	@RequestMapping("/listFeeds")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		String categoryToChoose = request.getParameter("category");
		System.out.println(categoryToChoose);
		List catList = new ArrayList<Feed>();
		catList = service.listFeedsByCategory(categoryToChoose);
		String myQuery = "";
		myQuery = service.convertListToQuery(catList);
		
		// WE NOW HAVE A LIST OF THE FEEDS WE WANT TO DO. HERE WE NEED TO DO
		// OUR CALL TO THE TWITTER API AND ADD IN THE LAST TWEET, PICTURE, ETC
		ModelAndView mav = new ModelAndView("feedsViews/listFeeds", "command", new Feed());
				
		mav.addObject("catList", catList);
		return mav;
		
	}
	
	@RequestMapping(value = "newFavorite", method = RequestMethod.POST)
	public ModelAndView addFeed(@ModelAttribute("feed")Feed feed, Model model){
		service.addOneToFavoritedCount(feed);
		ModelAndView mav = new ModelAndView("feedsViews/home", "command", new Feed());
		return mav;
	}
	
	@RequestMapping(value = "allStar")
	public ModelAndView goToAllStar(){		
		List<Feed> allStarList = new ArrayList<Feed>();
		allStarList = service.getAllStars();
		ModelAndView mav = new ModelAndView("feedsViews/allStar");
		mav.addObject("allStarList", allStarList);
		return mav;
	}
	
	

}
