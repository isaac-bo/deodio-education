package com.deodio.elearning.persistence.model.customize;

import com.deodio.elearning.persistence.model.PresentationDiscussionReply;

public class PresentationDiscussionReplyDto extends PresentationDiscussionReply {
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
