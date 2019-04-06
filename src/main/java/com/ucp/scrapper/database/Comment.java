package com.ucp.scrapper.database;

import java.sql.Date;

public class Comment {
	private String id;
	private String text;
	private Date date;
	private User user;
	private String mark;
	private String idRecipe;
	
	public Comment(String text, Date date, User user, String mark, String idRecipe) {
		this.idRecipe = idRecipe;
		this.text = text;
		this.date = date;
		this.user = user;
		this.mark = mark;
	}
	
	public String getId() {
		return id;
	}
	
	public String getText() {
		return text;
	}

	public Date getDate() {
		return date;
	}

	public User getUser() {
		return user;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String getMark() {
		return mark;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setMark(String mark) {
		this.mark = mark;
	}
		
	@Override
	public String toString() {
		return "Comments [id=" + id + ", text=" + text + ", date=" + date + ", user=" + user + ", mark=" + mark
				+ "]";
	}

	public String getIdRecipe() {
		return idRecipe;
	}

	public void setIdRecipe(String idRecipe) {
		this.idRecipe = idRecipe;
	}
}
