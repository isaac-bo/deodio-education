package com.deodio.elearning.persistence.model.customize;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.deodio.core.utils.StringUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.persistence.model.GroupRoleUserRel;

public class GroupRoleRelBo extends GroupRoleUserRel {

	private String groupRoleName;
	
	private String groupName;
	
	private String accountId;
	
	private short hasLeader;
	
	private String groupDescription;	

	public String getGroupRoleName() {
		return groupRoleName;
	}

	public void setGroupRoleName(String groupRoleName) {
		this.groupRoleName = groupRoleName;
	}
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public short getHasLeader() {
		return hasLeader;
	}

	public void setHasLeader(short hasLeader) {
		this.hasLeader = hasLeader;
	}
	

	public String getGroupDescription() {
		return StringUtils.removeHtmlTags(groupDescription);
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(this.getGroupRoleId()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		if(!(obj instanceof GroupRoleRelBo)){
			return false;
		}
		GroupRoleRelBo bo = (GroupRoleRelBo)obj;
		
		return this.getGroupRoleId().equals(bo.getGroupRoleId());
	}

	@Override
	public String toString() {
		return this.getGroupRoleId() + DConstants.DELIMITER_COMMA + this.getGroupRoleName();
	}
	
} 
