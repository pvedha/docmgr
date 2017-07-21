package com.blog.dto;

import java.util.ArrayList;

public class PostDto {

	private int postId;
	private String title;
	private String message;	
	private String userId;
	private String userName;
	private String postedOn;
	private String tags;
	private String category;
	private ArrayList<CommentDto> comments = new ArrayList<>();
	
	public PostDto() {
	}
	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPostedBy() {
		return userId;
	}

	public void setPostedBy(String postedBy) {
		this.userId = postedBy;
	}

	public String getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(String postedOn) {
		this.postedOn = postedOn;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ArrayList<CommentDto> getComments() {
		return comments;
	}

	public void setComments(ArrayList<CommentDto> comments) {
		this.comments = comments;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	

}
