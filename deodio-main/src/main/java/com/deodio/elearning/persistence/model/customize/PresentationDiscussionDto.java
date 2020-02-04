package com.deodio.elearning.persistence.model.customize;


import java.util.List;

import com.deodio.elearning.persistence.model.PresentationDiscussion;


public class PresentationDiscussionDto extends PresentationDiscussion{
	private String userName;
	private List<PresentationDiscussionReplyDto> pdrlist;

	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<PresentationDiscussionReplyDto> getPdrlist() {
		return pdrlist;
	}

	public void setPdrlist(List<PresentationDiscussionReplyDto> pdrlist) {
		this.pdrlist = pdrlist;
	}

	
	
}
