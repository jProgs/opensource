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

import com.cs440.twit2.core.Admin;
import com.cs440.twit2.dao.IAdminService;
import com.cs440.twit2.dao.AdminService;
import com.cs440.twit2.core.Feed;
import com.cs440.twit2.dao.IFeedService;
import com.cs440.twit2.dao.FeedService;

@Controller
public class AdminController {
	@Inject
	private static IAdminService service;
	{
		service = new AdminService();
	}
	
	@Inject
	private static IFeedService feedService;
	{
		feedService = new FeedService();
	}
	
	
	
	@RequestMapping("/adminStartPage")
	public ModelAndView adminLogInCredentials() {
		return new ModelAndView("adminViews/adminStartPage", "command", new Admin());
	}
	
	@RequestMapping(value = "/checkCredentials", method = RequestMethod.POST)
	public ModelAndView addAdminNow(@ModelAttribute("admin")Admin admin, Model model){
		ModelAndView mav = new ModelAndView("adminViews/adminStartPage", "command", new Admin());
		if(service.testPassword(admin))
		{
			ModelAndView mav1 = new ModelAndView("adminViews/adminPage", "command", new Feed());
			return mav1;
		}else{
			System.out.println("bad password");
			ModelAndView mav2 = new ModelAndView("adminViews/adminStartPage", "command", new Admin());
			return mav2;
		}					
	}
	
	@RequestMapping(value = "addFeed", method = RequestMethod.POST)
	public ModelAndView addFeed(@ModelAttribute("feed")Feed feed, Model model){
		feedService.add(feed);
		ModelAndView mav = new ModelAndView("adminViews/adminPage", "command", new Feed());
		return mav;
	}
	
	
	@RequestMapping("/adminPage")
	public ModelAndView goToAdminMainPage(){
		System.out.println("at /adminPage request mapping");
		return new ModelAndView("adminViews/adminPage", "command", new Admin());
	}
	
	
	
	
	
	
	
}
