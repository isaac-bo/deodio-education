package com.deodio.elearning.persistence.model.customize;

import java.util.List;

import com.deodio.elearning.persistence.model.DiscussionModel;

public class DiscussionModelBo extends  DiscussionModel{

	private String nickName;
	private String userIcon;
	private String attachUrl;
	
	private List<DiscussionModelBo> replys;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public String getAttachUrl() {
		return attachUrl;
	}

	public void setAttachUrl(String attachUrl) {
		this.attachUrl = attachUrl;
	}

	public List<DiscussionModelBo> getReplys() {
		return replys;
	}

	public void setReplys(List<DiscussionModelBo> replys) {
		this.replys = replys;
	}
	
}