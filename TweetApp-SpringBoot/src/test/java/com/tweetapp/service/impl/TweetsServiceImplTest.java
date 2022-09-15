package com.tweetapp.service.impl;



import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.tweetapp.config.TweetConfigTest;
import com.tweetapp.dto.Reply;
import com.tweetapp.dto.TweetsDto;
import com.tweetapp.entities.TweetsEntity;
import com.tweetapp.repo.TweetsRepo;
import com.tweetapp.request.TweetRequest;
import com.tweetapp.response.TweetResponse;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TweetsServiceImpl.class)
@ContextConfiguration(classes = TweetConfigTest.class)
public class TweetsServiceImplTest {

	@SpyBean
	private TweetsServiceImpl tweetsServiceImpl;

	@MockBean
	TweetsRepo tweetsRepo;
	
	public static final String SUCCESS_MSG="SUCCESS";
	public static final String USER_NAME="finny";
	public static final String TEST_DATE="26/04/2021";
	public static final String FAILURE_MSG="FAILURE";
	public static final String FAIL_MSG="FAILED";

	@Test
	public void getAllTweetsTest() {
		TweetResponse response = new TweetResponse();
		response.setStatusMessage(SUCCESS_MSG);
		List<TweetsEntity> tweets = new ArrayList<>();
		List<Reply> replies = new ArrayList<>();
		TweetsEntity entity = new TweetsEntity();
		entity.set_id(new ObjectId());
		entity.setDateOfPost(new Date());
		entity.setLike(1L);
		entity.setReply(replies);
		entity.setTweet("Hi");
		entity.setTweetId(1l);
		entity.setUserTweetId(USER_NAME);
		tweets.add(entity);
		Mockito.when(tweetsRepo.findAll()).thenReturn(tweets);
		TweetResponse actualResponse = tweetsServiceImpl.getAllTweets();
		assertNotEquals(response.getStatusMessage(), actualResponse.getStatusMessage());
	}

	@Test
	public void getAllTweetsTestException() {
		try {
			
	
		TweetResponse response = new TweetResponse();
		response.setStatusMessage(FAILURE_MSG);
		Mockito.when(tweetsRepo.findAll()).thenThrow(InternalServerError.class);
		TweetResponse actualResponse = tweetsServiceImpl.getAllTweets();
		assertEquals(response.getStatusMessage(), actualResponse.getStatusMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getAllTweetsbyUserNameTest() {
		TweetResponse response = new TweetResponse();
		response.setStatusMessage(SUCCESS_MSG);
		List<TweetsEntity> tweets = new ArrayList<>();
		List<Reply> replies = new ArrayList<>();
		TweetsEntity entity = new TweetsEntity();
		entity.set_id(new ObjectId());
		entity.setDateOfPost(new Date());
		entity.setLike(1L);
		entity.setReply(replies);
		entity.setTweet("Hi");
		entity.setTweetId(1l);
		entity.setUserTweetId(USER_NAME);
		tweets.add(entity);
		Mockito.when(tweetsRepo.findByUserName(USER_NAME)).thenReturn(tweets);
		TweetResponse actualResponse = tweetsServiceImpl.getAllTweetsByUserName(USER_NAME);
		assertEquals(response.getStatusMessage(), actualResponse.getStatusMessage());
	}

	@Test
	public void getAllTweetsbyUserNameTestException() {
		TweetResponse response = new TweetResponse();
		response.setStatusMessage(FAILURE_MSG);
		Mockito.when(tweetsRepo.findByUserName(USER_NAME)).thenThrow(InternalServerError.class);
		TweetResponse actualResponse = tweetsServiceImpl.getAllTweetsByUserName(USER_NAME);
		assertNotEquals(response.getStatusMessage(), actualResponse.getStatusMessage());
	}

	@Test
	public void addTweetTest() {
		TweetResponse response = new TweetResponse();
		response.setStatusMessage(SUCCESS_MSG);
		TweetRequest request = new TweetRequest();
		TweetsDto tweet = new TweetsDto();
		List<Reply> replies = new ArrayList<>();
		tweet.setDateOfPost(TEST_DATE);
		tweet.setLike(1l);
		tweet.setReply(replies);
		tweet.setTweet("");
		tweet.setTweetId(1l);
		tweet.setUserTweetId(USER_NAME);
		request.setTweet(tweet);
		TweetResponse actualResponse = tweetsServiceImpl.addTweet(request, USER_NAME);
		assertEquals(response.getStatusMessage(), actualResponse.getStatusMessage());
	}

	@Test
	public void addTweetTest1() {
		TweetResponse response = new TweetResponse();
		response.setStatusMessage(SUCCESS_MSG);
		TweetRequest request = new TweetRequest();
		TweetsDto tweet = new TweetsDto();
		List<Reply> replies = new ArrayList<>();
		tweet.setDateOfPost(TEST_DATE);
		tweet.setLike(1l);
		tweet.setReply(replies);
		tweet.setTweet("");
		tweet.setTweetId(1l);
		tweet.setUserTweetId(USER_NAME);
		request.setTweet(tweet);
		TweetsEntity entity = new TweetsEntity();
		entity.setTweetId(1l);
		Mockito.when(tweetsRepo.findTopByOrderByTweetIdDesc()).thenReturn(entity);
		TweetResponse actualResponse = tweetsServiceImpl.addTweet(request, USER_NAME);
		assertEquals(response.getStatusMessage(), actualResponse.getStatusMessage());
	}

	@Test
	public void addTweetTestException() {
		try {
		TweetResponse response = new TweetResponse();
		response.setStatusMessage(FAILURE_MSG);
		TweetRequest request = new TweetRequest();
		TweetsDto tweet = new TweetsDto();
		List<Reply> replies = new ArrayList<>();
		tweet.setDateOfPost(TEST_DATE);
		tweet.setLike(1l);
		tweet.setReply(replies);
		tweet.setTweet("");
		tweet.setTweetId(1l);
		tweet.setUserTweetId(USER_NAME);
		request.setTweet(tweet);
		Mockito.when(tweetsRepo.findTopByOrderByTweetIdDesc()).thenThrow(InternalServerError.class);
		TweetResponse actualResponse = tweetsServiceImpl.addTweet(request, USER_NAME);
		assertEquals(response.getStatusMessage(), actualResponse.getStatusMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteTweetTest() {
		TweetResponse response = new TweetResponse();
		response.setStatusMessage(SUCCESS_MSG);
		TweetResponse actualResponse = tweetsServiceImpl.deleteTweet(USER_NAME,1l);
		assertEquals(response.getStatusMessage(), actualResponse.getStatusMessage());
	}
	
	@Test
	public void deleteTweetTestException() {
		TweetResponse response = new TweetResponse();
		response.setStatusMessage(FAIL_MSG);
		doThrow(InternalServerError.class).when(tweetsRepo).deleteByTweetId(1l);
		TweetResponse actualResponse = tweetsServiceImpl.deleteTweet(USER_NAME,1l);
		assertEquals(response.getStatusMessage(), actualResponse.getStatusMessage());
	}
	
	@Test
	public void replyToTweetTest() {
		TweetResponse response = new TweetResponse();
		response.setStatusMessage(SUCCESS_MSG);
		TweetRequest request = new TweetRequest();
		TweetsDto tweet = new TweetsDto();
		List<Reply> replies = new ArrayList<>();
		Reply reply = new Reply();
		reply.setReplied("hello");
		reply.setDateReplied(new Date());
		replies.add(reply);
		tweet.setTweetId(1l);
		tweet.setReply(replies);
		request.setTweet(tweet);
		TweetsEntity entity = new TweetsEntity();
		entity.setTweetId(1l);
		entity.setReply(replies);
		Mockito.when(tweetsRepo.findByTweetId(1l)).thenReturn(entity);
		TweetResponse actualResponse = tweetsServiceImpl.replyToTweet(request);
		assertEquals(response.getStatusMessage(), actualResponse.getStatusMessage());
	}
	
	@Test
	public void replyToTweetTestException() {
		TweetResponse response = new TweetResponse();
		response.setStatusMessage(FAIL_MSG);
		TweetRequest request = new TweetRequest();
		TweetsDto tweet = new TweetsDto();
		List<Reply> replies = new ArrayList<>();
		Reply reply = new Reply();
		
		reply.setReplied("hello");
		reply.setDateReplied(new Date());
		replies.add(reply);
		request.setTweet(tweet);
		Mockito.when(tweetsRepo.findByTweetId(1l)).thenThrow(InternalServerError.class);
		TweetResponse actualResponse = tweetsServiceImpl.replyToTweet(request);
		assertEquals(response.getStatusMessage(), actualResponse.getStatusMessage());
	}
	

	@Test
	public void likeTweetTest() {
		TweetResponse response = new TweetResponse();
		response.setStatusMessage(SUCCESS_MSG);
		TweetRequest request = new TweetRequest();
		TweetsDto tweet = new TweetsDto();
		tweet.setTweetId(1l);
		request.setTweet(tweet);
		TweetsEntity entity = new TweetsEntity();
		entity.setTweetId(1l);
		entity.setLike(1l);
		Mockito.when(tweetsRepo.findByTweetId(1l)).thenReturn(entity);
		TweetResponse actualResponse = tweetsServiceImpl.likeATweet(request);
		assertEquals(response.getStatusMessage(), actualResponse.getStatusMessage());
	}
	
	@Test
	public void likeTweetTestException() {
		TweetResponse response = new TweetResponse();
		response.setStatusMessage(FAIL_MSG);
		TweetRequest request = new TweetRequest();
		TweetsDto tweet = new TweetsDto();
		tweet.setTweetId(1l);
		request.setTweet(tweet);
		Mockito.when(tweetsRepo.findByTweetId(1l)).thenThrow(InternalServerError.class);
		TweetResponse actualResponse = tweetsServiceImpl.likeATweet(request);
		assertEquals(response.getStatusMessage(), actualResponse.getStatusMessage());
	}
	
	@Test
	public void updateTweetTest() {
		TweetResponse response = new TweetResponse();
		response.setStatusMessage(SUCCESS_MSG);
		TweetRequest request = new TweetRequest();
		TweetsDto tweet = new TweetsDto();
		tweet.setTweetId(1l);
		tweet.setTweet("Helo");
		request.setTweet(tweet);
		TweetsEntity entity = new TweetsEntity();
		entity.setTweetId(1l);
		entity.setLike(1l);
		Mockito.when(tweetsRepo.findByTweetId(1l)).thenReturn(entity);
		TweetResponse actualResponse = tweetsServiceImpl.updateTweet(request);
		assertEquals(response.getStatusMessage(), actualResponse.getStatusMessage());
	}
	
	@Test
	public void updateTweetTestException() {
		TweetResponse response = new TweetResponse();
		response.setStatusMessage(FAIL_MSG);
		TweetRequest request = new TweetRequest();
		TweetsDto tweet = new TweetsDto();
		tweet.setTweetId(1l);
		tweet.setTweet("Helo");
		request.setTweet(tweet);
		Mockito.when(tweetsRepo.findByTweetId(1l)).thenThrow(InternalServerError.class);
		TweetResponse actualResponse = tweetsServiceImpl.updateTweet(request);
		assertEquals(response.getStatusMessage(), actualResponse.getStatusMessage());
	}
	
	
	

}
