package com.tweetapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@CrossOrigin(origins = "http://niktweet.s3-website-us-west-2.amazonaws.com")
public class TweetAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetAppApplication.class, args);
	}
	
	
	

}
