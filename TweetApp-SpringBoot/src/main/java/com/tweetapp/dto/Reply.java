package com.tweetapp.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class Reply {

	public String getReplied() {
		return replied;
	}

	public void setReplied(String replied) {
		this.replied = replied;
	}

	public Date getDateReplied() {
		return dateReplied;
	}

	public void setDateReplied(Date dateReplied) {
		this.dateReplied = dateReplied;
	}

	private String replied;

	private Date dateReplied;
}
