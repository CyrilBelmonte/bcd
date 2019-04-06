package com.ucp.scrapper.database;

import java.sql.Date;

public class User {
	private String id;
	private String name;
	private String surname;
	private int like;
	private int share;
	private Date creationTime;

	public User(String name, String surname, int like, int share, Date creationTime) {
		this.name = name;
		this.surname = surname;
		this.like = like;
		this.share = share;
		this.creationTime = creationTime;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public int getLike() {
		return like;
	}

	public int getShare() {
		return share;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public void setShare(int share) {
		this.share = share;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", like=" + like + ", share=" + share
				+ ", creationTime=" + creationTime + "]";
	}
	

}
