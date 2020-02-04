package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;

import com.deodio.elearning.persistence.model.AttachmentSlidesItem;

public interface PresentationSyncCustomizeMapper {
	
	public List<AttachmentSlidesItem> getAttachmentSlidesItemByPresentationId(String presentationId);
	
}
