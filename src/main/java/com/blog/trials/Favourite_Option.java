package com.blog.trials;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

//@Entity
public class Favourite_Option {
	@EmbeddedId
	private FavouriteEmbeddable favItem = new FavouriteEmbeddable();
	
	public Favourite_Option() {
	}

	public Favourite_Option(String userId, int postId){
		favItem.setUserId(userId);
		favItem.setPostId(postId);
	}
	
	public FavouriteEmbeddable getFavItem() {
		return favItem;
	}

	public void setFavItem(FavouriteEmbeddable favItem) {
		this.favItem = favItem;
	}	

}
