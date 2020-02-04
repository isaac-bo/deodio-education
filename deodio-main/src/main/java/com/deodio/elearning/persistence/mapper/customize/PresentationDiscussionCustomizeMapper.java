package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;

import com.deodio.elearning.persistence.model.PresentationDiscussionReply;
import com.deodio.elearning.persistence.model.customize.PresentationDiscussionDto;
import com.deodio.elearning.persistence.model.customize.PresentationDiscussionReplyDto;

public interface PresentationDiscussionCustomizeMapper {
	public List<PresentationDiscussionDto> getPresentationDiscussion(String presentationId);
	
	
	public List<PresentationDiscussionReplyDto> getPresentationDiscussionReply(String presentationDisId);
}
