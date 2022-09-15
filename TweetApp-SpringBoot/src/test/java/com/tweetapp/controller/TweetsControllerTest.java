package com.tweetapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tweetapp.config.TweetConfigTest;
import com.tweetapp.kafka.Producer;
import com.tweetapp.request.TweetRequest;
import com.tweetapp.response.TweetResponse;
import com.tweetapp.service.TweetsService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TweetController.class)
@ContextConfiguration(classes = TweetConfigTest.class)
public class TweetsControllerTest {

	@SpyBean
	TweetController tweetController;

	@MockBean
	TweetsService tweetsService;
	
	@MockBean
	Producer producer;
	
	public static final String SUCCESS_MSG="SUCCESS";
	public static final String USER_NAME="finny";

	@Test
	public void getAllTweetsTest() {
		TweetResponse tweetResponse = new TweetResponse();
		tweetResponse.setStatusMessage(SUCCESS_MSG);
		Mockito.when(tweetsService.getAllTweets()).thenReturn(tweetResponse);
		TweetResponse actualResponse = tweetController.getAllTweets();
		assertEquals(tweetResponse.getStatusMessage(),actualResponse.getStatusMessage());
	}
	
	@Test
	public void getAllTweetsUser() {
		TweetResponse tweetResponse = new TweetResponse();
		tweetResponse.setStatusMessage(SUCCESS_MSG);
		Mockito.when(tweetsService.getAllTweetsByUserName(USER_NAME)).thenReturn(tweetResponse);
		TweetResponse actualResponse = tweetController.getAllTweetsUser(USER_NAME);
		assertEquals(tweetResponse.getStatusMessage(),actualResponse.getStatusMessage());
	}
	
	@Test
	public void addTweet() {
		TweetResponse tweetResponse = new TweetResponse();
		TweetResponse actualResponse = new TweetResponse();
		TweetRequest request = new TweetRequest();
		tweetResponse.setStatusMessage(SUCCESS_MSG);
		actualResponse.setStatusMessage(SUCCESS_MSG);
		Mockito.when(tweetsService.addTweet(request,USER_NAME)).thenReturn(tweetResponse);
		
		Mockito.doNothing().when(producer).sendMessage(Mockito.anyString());

		assertEquals(tweetResponse.getStatusMessage(),actualResponse.getStatusMessage());
	}
	
	@Test
	public void deleteTweet() {
		TweetResponse tweetResponse = new TweetResponse();
		tweetResponse.setStatusMessage(SUCCESS_MSG);
		Mockito.when(tweetsService.deleteTweet(USER_NAME,1l)).thenReturn(tweetResponse);
		TweetResponse actualResponse = tweetController.deleteTweet(USER_NAME,1l);
		assertEquals(tweetResponse.getStatusMessage(),actualResponse.getStatusMessage());
	}
	
	@Test
	public void replyToTweet() {
		TweetResponse tweetResponse = new TweetResponse();
		TweetResponse actualResponse = new TweetResponse();
		TweetRequest request = new TweetRequest();
		tweetResponse.setStatusMessage(SUCCESS_MSG);
		actualResponse.setStatusMessage(SUCCESS_MSG);
		Mockito.when(tweetsService.replyToTweet(request)).thenReturn(tweetResponse);
		Mockito.doNothing().when(producer).sendMessage(Mockito.anyString());
		assertEquals(tweetResponse.getStatusMessage(),actualResponse.getStatusMessage());
	}
	
	@Test
	public void likeATweet() {
		TweetResponse tweetResponse = new TweetResponse();
		TweetRequest request = new TweetRequest();
		tweetResponse.setStatusMessage(SUCCESS_MSG);
		Mockito.when(tweetsService.likeATweet(request)).thenReturn(tweetResponse);
		TweetResponse actualResponse = tweetController.likeATweet(request);
		assertEquals(tweetResponse.getStatusMessage(),actualResponse.getStatusMessage());
	}
	
	@Test
	public void updateTweet() {
		TweetResponse tweetResponse = new TweetResponse();
		TweetResponse actualResponse = new TweetResponse();
		TweetRequest request = new TweetRequest();
		tweetResponse.setStatusMessage(SUCCESS_MSG);
		actualResponse.setStatusMessage(SUCCESS_MSG);
		Mockito.when(tweetsService.updateTweet(request)).thenReturn(tweetResponse);
		Mockito.doNothing().when(producer).sendMessage(Mockito.anyString());
		assertEquals(tweetResponse.getStatusMessage(),actualResponse.getStatusMessage());
	}

}
