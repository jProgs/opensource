package com.cs440.twit2.dao;

import java.util.List;


import com.cs440.twit2.core.Feed;

public interface IFeedService {
	public List<Feed> listFeeds();
	public void add(Feed feed);
	void removeFeed(Feed feed);
	public List listFeedsByCategory(String categoryToChoose);
	public String convertListToQuery(List<Feed> ourList);
	public void addOneToFavoritedCount(Feed feed);
	public List<Feed> getAllStars();
}
