<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
</div>
</div>
</div>	

<%@ include file="/WEB-INF/views/modules/group/group_dialogue.jsp"%>

<%@ include file="/WEB-INF/views/commons/_pagination.jsp"%>
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>


<input type="hidden" id="presentationId" name="presentationId" value="${presentationId}">
<input type="hidden" id="isSlideShow" name="isSlideShow" value="${presentationModelSync.isSlideShow}">
</body>
</html>