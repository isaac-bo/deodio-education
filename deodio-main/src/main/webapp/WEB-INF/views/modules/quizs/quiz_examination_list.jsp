<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/quizs/quizs.css">
<body>
    <div class="content">
    <%@ include file="/WEB-INF/views/modules/quizs/quiz_top_menu.jsp"%>
        <div id="myTabContent" class="content tab-content">
           <div class="pre_center" style="position:absolute;width:975px;">
                <div class="con-mask"></div>
                <div class="con" style="min-height: 20px;">
                    <div id="div_tables_bar" class="pre_left_top_fl" style="width:945px;">
                        <div class="control-bar pull-left">
                            <a onclick="mediumIconShow(1);" href="javascript:void(0)" class="pic-item on">
                                <div class=""></div>
                                <div class="on"></div>
                                <div class=""></div>
                                <div class=""></div>
                            </a>
                            
                            <a onclick="smallIconShow(1)" href="javascript:void(0)" class="table-item">
                                <div class="" style="margin-top: 2px;"></div>
                                <div class=""></div>
                                <div class=""></div>
                                <div class=""></div>
                            </a>
                            
                        </div>
                        <div class="control-bar pull-right" id="smallIconConditionBar">
                            <span>试卷类型</span>
                            <div class="w240 mr20">
                                <select name="quizCategory" id="quizCategory" class="selectpicker">
                                    <option value="0">请选择</option>
                                    <option value="1">测验</option>
                                    <option value="2">考试</option>
                                    <option value="3">测验和考试</option>
                                </select>
                            </div>
                            <span>试卷名称</span>
                            <div class="w240">
                                <button type="button" class="btn-success btn36" onClick="searchQuizExaminationList();">&nbsp;</button>
                                <div class="search_input"><input type="text" class="form-control" id="keywords_small"></div>    
                            </div>  
                            <button type="button" value="上传试题" onclick="onCreateExamination();" class="btn btn_green ml20 btn36">新建试卷</button>  
                        </div>
                    </div>
                    <!--pre_left_top_fl end-->
                    <div class="clearfix"></div>
                    <div class="con-corner"></div>
                </div>
           </div>
        
        
            <div class="tab-pane active" id="shijuan">
                <div id="smallIcon" style="margin-top: 60px;padding: 20px;display:none;">
                    <table cellpadding="0" cellspacing="0" class="table table-striped table-hover td_h60 mt20">
                        <thead>
                            <tr>
                                <th class="text-center" style="width:30%;">试卷名称</th>
                                <th class="text-center">问题总数</th>
                                <th class="text-center">试卷总分</th>
                                <th class="text-center">创建人</th>
                                <th class="text-center">创建时间</th>
                                <th class="text-center">状态</th>
                                <th class="text-center">操作</th>
                            </tr>
                        </thead>
                        <tbody id="table_panle">
                        </tbody>
                    </table>
                </div>
                <div id="mediumIcon" style="margin-top: 75px;">
                    <!-- <div class="pre_right p10" id="mediumLeftBar">
                        <div>
                            <button type="button" class="btn-success btn36" onClick="searchQuizExaminationList();">&nbsp;</button>
                            <div class="search_input"><input type="text" class="form-control" id="keywords_medium"></div>
                        </div>
                        <ul class="mt20 pre_t_btn">
                            <li><button type="submit" class="add" onclick="editQuizExamination()"></button></li>
                            <li><button type="submit" class="del" onclick="deleteQuizExamination()"></button></li>
                            <li><button type="submit" class="share"></button></li>
                            <li><button type="submit" class="copy"></button></li>
                            <li><button type="submit"class="menu"></button></li>
                        </ul>
                        <div class="pre_t_txt mt20 pt10">文字提示内容文字提示内容</div>
                    </div> -->
                    <div class="pre_left p20 pt0" style="padding:20px;">
                        <div class="pre_left_top_fl" style="margin:0 auto;border-bottom:0;" id="classificationContent">
                            <ul class="group_list neirong" id="content">
                            </ul>
                        </div>
                    </div>
                    <div class="pre_right mr20 mt20" style="margin-top: -75px !important;">
                        <h3>点击以下按钮操作</h3>
                        <div class="wb100 search">
                            <button type="button" class="btn-success btn36" onClick="searchQuizExaminationList()">&nbsp;</button>
                            <div class="search_input">
                                <input type="text" class="form-control" id="keywords_medium">
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <ul class="p10 pre_t_btn" id="ul_li_focus_events">
                            
                        </ul>
                        <div class="text" id="text_tips"></div>
                    </div>
                    <div class="clearfix"></div>    
                    <input type="hidden" id="hid_default_page" value="1">
                </div>  
                <!-- 分页 -->
                <div class="mt20 text-center" id="data_page_Panel">
                    
                </div>
        </div>
    </div>
</div>

<script id="table_data_template" type="text/x-dot-template">
 {{~it.data:v:index}}
        <tr>
            <td>{{=isNullFormat(v.quizName)}}</td>
            <td class="text-center">{{=isNullFormat(v.quizNum)}}</td>
            <td class="text-center">{{=isNullFormat(v.score)}}</td>                                 
            <td class="text-center">{{=isNullFormat(v.userName)}}</td>
            <td class="text-center">{{=dateFormat(v.createTime)}}</td>
            <td class="text-center">
                <button type="button" {{? v.isPublish == 1}}class="btn btn_green">已发布{{?}}
                {{? v.isPublish != 1}}class="btn btn_red">可编辑{{?}}</button>
            </td>
            <td class="text-right" style="padding-top:12px;">
                {{? v.isPublish != 1}}
                <span class="icon-edit f16 mr5" style="color:rgb(201,201,201);" onclick="onEditExamination('{{=isNullFormat(v.id)}}','{{=v.createType}}','1');"></span>
                {{?}}
                {{? (v.quizOwner == v.createId && v.isEdit == 0)}}
                <span class="icon-trash f16 mr5" style="color:rgb(201,201,201);" onclick="onDeleteExamination('{{=isNullFormat(v.id)}}');"></span>
                {{?}}
                <span class="icon-eye-open f16 mr5" style="color:rgb(201,201,201);" onclick="onViewExamination('{{=isNullFormat(v.id)}}');"></span>
                {{? v.isPublish == 1 && v.createId == v.quizOwner}}
                <span class="icon-paper-clip f16 mr5" style="color:rgb(201,201,201);" onclick="onCancelPublish('{{=isNullFormat(v.id)}}');"></span>
                {{?}}
            </td>
        </tr>
		<input type="hidden" id="_item_id" name="_item_id" value="{{=isNullFormat(v.id)}}"/>
 {{~}}  
</script>

<script id="data_template" type="text/x-dot-template">
    {{~it.data:v:index}}
        <li id="{{=isNullFormat(v.id)}}" {{? index == 0}} class="item_select"{{?}} 
            onclick="javascript:onSelectExamination('{{=isNullFormat(v.id)}}',
            '{{=isNullFormat(v.quizName)}}','{{=isNullFormat(v.isPublish)}}',
            '{{=isNullFormat(v.quizOwner)}}','{{=isNullFormat(v.isEdit)}}',
            '{{=isNullFormat(v.createId)}}','{{=isNullFormat(v.groupId)}}','{{=isNullFormat(v.quoteCount)}}')">
        <div class="pic">
            {{? isNullFormat(v.attachUrl) == ''}}
            <img src="${ctx}/resources/img/account/course-title-img-1.jpg" alt="">
            {{??}}
            <img src="${imgStatic}/{{=isNullFormat(v.attachUrl)}}{{=isNullFormat(v.generateName)}}" alt="">
            {{?}}
        </div>
	<div class="mt10 wid-100">
			<dl class="mt10">
				<dt>试卷名称：</dt>
				<dd class="w200" title="{{=isNullFormat(v.quizName)}}">{{=subString(isNullFormat(v.quizName),14)}}</dd>
			</dl>
		</div>
     	<div class="mt10 wid-100">

			<dl>
				<dt style="vertical-align: top;">简介：</dt>
				<dd class="neirong-max-height f12 w200" title="{{=removeHtmlTags(isNullFormat(v.quizDesc))}}">{{=subString(removeHtmlTags(isNullFormat(v.quizDesc)),30)}}</dd>
			</dl>
		</div>
      
	<div class="mt10 f12">
			<dl>
				<dt>问题数目：</dt>
				<dd>{{=isNullFormat(v.quizNum)}}</dd>
			</dl>
			<dl>
				<dt>试卷总分：</dt>
				<dd>{{=isNullFormat(v.score)}}</dd>
			</dl>
		</div>
     <div class="mt10 f12">
			<dl>
				<dt>创建人：</dt>
				<dd title="{{=isNullFormat(v.userName)}}">{{=subString(isNullFormat(v.userName),4)}}</dd>
			</dl>
			<dl>
				<dt>引用数：</dt>
				<dd id="quoteCount">{{=isNullFormat(v.quoteCount)}}</dd>
			</dl>
		</div>
       	<div class="mt10 f12">

			<dl>
				<dt>创建时间：</dt>
				<dd>{{=dateFormat(v.createTime)}}</dd>
			</dl>
			<dl>
				<dt>修改时间：</dt>
				<dd>{{=dateFormat(v.updateTime)}}</dd>
			</dl>
		</div>
       
        <div class="neirong-bottom">
			{{? v.quizOwner==v.createId&&v.isPublish != 1}}
				<button type="button" class="btn btn_unlock_icon">
					<span class="icon-unlock"></span>
				</button>
			{{?}}
			{{? v.quizOwner!=v.createId&&v.isPublish != 1}}
				<button type="button" class="btn btn_lock_icon" 
					onclick="onCancelShare('{{=isNullFormat(v.id)}}')">
					<span class="icon-lock"></span>
				</button>
			{{?}}
            {{? v.isPublish != 1}}<button type="button" class="btn btn_red mr10">可编辑</button>{{?}}
            {{? v.isPublish == 1}}<button type="button" class="btn btn_green">已发布</button>{{?}}
            <input type="hidden" id="examination_create_type_{{=isNullFormat(v.id)}}" 
                name="examination_create_type_{{=isNullFormat(v.id)}}" value="{{=isNullFormat(v.createType)}}"/>
        </div> <!--neirong-bottom end-->
    </li>
    {{~}}   

    {{~it.data:v:index}}
        {{? index == 0}}
            <input type="hidden" id="_item_id" name="_item_id" value="{{=isNullFormat(v.id)}}"/>
            <input type="hidden" id="_item_name" name="_item_name" value="{{=isNullFormat(v.quizName)}}"/>
            <input type="hidden" id="_item_isPublish" name="_item_isPublish" value="{{=isNullFormat(v.isPublish)}}"/>   
            <input type="hidden" id="_item_owner" name="_item_owner" value="{{=isNullFormat(v.quizOwner)}}"/>   
            <input type="hidden" id="_item_edit" name="_item_edit" value="{{=isNullFormat(v.isEdit)}}"/>    
            <input type="hidden" id="_item_createId" name="_item_createId" value="{{=isNullFormat(v.createId)}}"/>  
            <input type="hidden" id="_item_groupId" name="_item_groupId" value="{{=isNullFormat(v.groupId)}}"/> 
            <input type="hidden" id="_item_quoteCount" name="_item_quoteCount" value="{{=isNullFormat(v.quoteCount)}}"/>
        {{?}}   
    {{~}}

</script>

<%@ include file="/WEB-INF/views/commons/_dialogue.jsp" %>
<%@ include file="/WEB-INF/views/commons/_pagination.jsp"%>
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
<%@ include file="/WEB-INF/views/modules/quizs/quiz_dialogue.jsp" %>
<%@ include file="/WEB-INF/views/modules/quizs/quiz_view_dialogue.jsp" %>
<%@ include file="/WEB-INF/views/modules/quizs/quiz_copy_dialogue.jsp"%>
<%@ include file="/WEB-INF/views/modules/quizs/quiz_quoted_dialogue.jsp"%>
<%@ include file="/WEB-INF/views/modules/quizs/quiz_share_dialogue.jsp"%>
<%@ include file="/WEB-INF/views/modules/quizs/quiz_preview_dialogue.jsp"%>
    <script type="text/javascript">
        require(['modules/quizs/quiz','modules/quizs/quiz_examination_list']);
    </script>
</body>
</html>