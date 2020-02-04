<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/account/account.info.css">

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
	<div class="content">
		<%@include file="/WEB-INF/views/modules/account/account_top_menu.jsp"%>


		<div class="content-item bradius2">
			<div class="nav-list">
				<a href="#" class="nav-list-btn on">我的在线课程</a> 
				<a href="#" class="nav-list-btn">我的线下课程</a> 
				<a href="#" class="nav-list-btn">我的在线考试</a>
				<a href="#" class="nav-list-btn">我的调查问卷</a>
			</div>
			<div class="control-bar">
				<a href="" class="table-item on">
					<div class="" style="margin-top: 2px;"></div>
					<div class=""></div>
					<div class=""></div>
					<div class=""></div>
				</a> <a href="" class="pic-item">
					<div class=""></div>
					<div class=""></div>
					<div class=""></div>
					<div class=""></div>
				</a>
			</div>
			<ul class="course-list">
				<li>
					<div class="course-title-img">
						<img src="${ctx}/resources/img/account/course-title-img-1.jpg" width="278">
					</div>
					<div class="course-text">
						<title>课程名字文字</title>
						<p>课程描述文字课程描述文字课程描述文字课程描述文字课程描述文字课程描述文字课程描述文字课程描述文字</p>
						<div>
							<h5>来自：<span class="teacher-name-color">Lsaac</span></h5>
							<h5>有效期：<span>2015-12-1</span>—<span>2015-12-2</span></h5>
						</div>
						<div>
							<h5>时长：<span>00:12:23</span></h5>
							<div class="progress">
								<div class="progress-bar" role="progressbar" aria-valuenow="60"
									aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
								</div>
							</div>
						</div>
					</div>
					<div class="pull-right">
			          <div class="text-center">
			            <span class="icons-img icons-16px player-1"></span><h6>章节数：12</h6>
			          </div>
			          <div class="text-center">
			            <span class="icons-img icons-16px pencil-1"></span><h6>测验数：12</h6>
			          </div>
			          <div>
			            <button type="button" class="icons-img icons-81-28 tag-1 cfff">书签</button>
			            <button type="button" class="tag-2">Active</button>
			          </div>
			        </div>
				</li>
				<li>
					<div class="course-title-img">
						<img src="${ctx}/resources/img/account/course-title-img-1.jpg" width="278">
					</div>
					<div class="course-text">
						<title>课程名字文字</title>
						<p>课程描述文字课程描述文字课程描述文字课程描述文字课程描述文字课程描述文字课程描述文字课程描述文字</p>
						<div>
							<h5>来自：<span class="teacher-name-color">Lsaac</span></h5>
							<h5>有效期：<span>2015-12-1</span>~<span>2015-12-2</span></h5>
						</div>
						<div>
							<h5>时长：<span>00:12:23</span></h5>
							<div class="progress">
								<div class="progress-bar" role="progressbar" aria-valuenow="60"
									aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
								</div>
							</div>
						</div>
					</div>
					<div class="pull-right">
			          <div>
			            <button type="button" class="icons-img icons-81-28 tag-1 cfff">书签</button>
			            <button type="button" class="tag-2">Active</button>
			          </div>
			        </div>
				</li>
			</ul>
			<table class="table table-striped td_h60">
				<thead>
					<tr>
						<th width="18%">课程名称</th>
						<th width="18%">进度</th>
						<th width="10%">创建者</th>
						<th width="10%">起始时间</th>
						<th width="10%">结束时间</th>
						<th width="10%">章节数</th>
						<th width="10%">测验数</th>
						<th width="10%">状态</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Mariah Maclachlan</td>
						<td>
							<div class="progress">
								<div class="progress-bar" role="progressbar" aria-valuenow="60"
									aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
									<span class="sr-only">60%</span>
								</div>
							</div>
						</td>
						<td><span class="teacher-name-color">Lsaac</span></td>
						<td>2015-12-1</td>
						<td>2015-12-1</td>
						<td>12</td>
						<td>35</td>
						<td><button type="button" class="tag-2">Active</button></td>
					</tr>
					<tr>
						<td>Mariah Maclachlan</td>
						<td>
							<div class="progress">
								<div class="progress-bar" role="progressbar" aria-valuenow="60"
									aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
									<span class="sr-only">60%</span>
								</div>
							</div>
						</td>
						<td><span class="teacher-name-color">Lsaac</span></td>
						<td>2015-12-1</td>
						<td>2015-12-1</td>
						<td>12</td>
						<td>35</td>
						<td><button type="button" class="tag-2">Active</button></td>
					</tr>
					<tr>
						<td>Mariah Maclachlan</td>
						<td>
							<div class="progress">
								<div class="progress-bar" role="progressbar" aria-valuenow="60"
									aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
									<span class="sr-only">60%</span>
								</div>
							</div>
						</td>
						<td><span class="teacher-name-color">Lsaac</span></td>
						<td>2015-12-1</td>
						<td>2015-12-1</td>
						<td>12</td>
						<td>35</td>
						<td><button type="button" class="tag-2">Active</button></td>
					</tr>
					<tr>
						<td>Mariah Maclachlan</td>
						<td>
							<div class="progress">
								<div class="progress-bar" role="progressbar" aria-valuenow="60"
									aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
									<span class="sr-only">60%</span>
								</div>
							</div>
						</td>
						<td><span class="teacher-name-color">Lsaac</span></td>
						<td>2015-12-1</td>
						<td>2015-12-1</td>
						<td>12</td>
						<td>35</td>
						<td><button type="button" class="tag-2">Active</button></td>
					</tr>
					<tr>
						<td>Mariah Maclachlan</td>
						<td>
							<div class="progress">
								<div class="progress-bar" role="progressbar" aria-valuenow="60"
									aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
									<span class="sr-only">60%</span>
								</div>
							</div>
						</td>
						<td><span class="teacher-name-color">Lsaac</span></td>
						<td>2015-12-1</td>
						<td>2015-12-1</td>
						<td>12</td>
						<td>35</td>
						<td><button type="button" class="tag-2">Active</button></td>
					</tr>
				</tbody>

			</table>

		</div>
	</div>

	<input type="hidden" value="${uKeyId}" id="uKeyId">
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	<script type="text/javascript">
		require(['modules/account/account','modules/account/account_activity']);
	</script>

</body>
</html>