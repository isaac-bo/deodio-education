package com.deodio.elearning.persistence.mapper.customize;

import java.util.Map;

public interface UserCustomizeMapper {

	Map<String, Object> getAccoutInfo(String userName);
	Map<String, Object> getAccoutInfoByOwnerId(String ownerId);
	Map<String, Object> getUserInfoById(Map<String, Object> params);
	Map<String, Object> getUserInfo(Map<String, Object> params);
	Integer getclassficationNum(String accountId);
	Map<String, Object> userLogin(Map<String, Object> params);
	int updateUserEmailVerified(Map<String, Object> params);
	public Integer getAccountNum(String id);
	public Integer getJoinGroupNum(String userId);
	public Integer getCreateGroupNum(String userId);
}
