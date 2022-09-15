package com.tweetapp.request;

import com.tweetapp.dto.TweetsDto;


public class TweetRequest {

	public TweetsDto getTweet() {
		return tweet;
	}

	public void setTweet(TweetsDto tweet) {
		this.tweet = tweet;
	}

	private TweetsDto tweet;
}
