<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="modal fade" id="requiredQuizBanksModal" tabindex="-1" role="dialog"  aria-labelledby="requiredQuizBanksModalLabel" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-label="Close" data-dismiss="modal" class="close" type="button">
					<span aria-hidden="true">×</span>
				</button>
				<h4 id="myModalLabel" class="modal-title">出题范围</h4>
			</div>
			<div class="modal-body" style="height: 460px;">
				<div style="position: relative;" class="p20">
				    <span>难度</span>
					<div class="w240 mr20">
						<select id="subjectLevel" class="selectpicker">
							<option value="0">全部</option>
							<option value="1">容易</option>
							<option value="2">较易</option>
							<option value="3">中等</option>
							<option value="4">较难</option>
							<option value="5">难</option>
						</select>
					</div>
					<div class="w240 pull-right">
						<button class="btn-success btn36" onclick="loadDataList(1);"
							type="button">&nbsp;</button>
						<div class="search_input">
							<input id="subjectTile" type="text" class="form-control">
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="pl20 pr20">
					<table cellspacing="0" cellpadding="0"
						class="table table-striped table-hover td_h60">
						<thead>
							<tr>
								<th style="width: 8%" class="text-center">
									<div class="checkbox-group">
										<input type="checkbox" id="one2" name="one2"> <label
											for="one2" class="checkbox-2"></label>
									</div>
								</th>
								<th style="width: 18%;" class="text-center">题型</th>
								<th style="width: 28%;" class="text-center" title="{{=isNullFormat(item.quiz_bank_name)}}">题目</th>
								<th style="width: 7%;" class="text-center">难度</th>
								<th class="text-center">缺省分数</th>
								<th class="text-center">预览</th>
							</tr>
						</thead>
						<tbody id="table_panle">

						</tbody>
					</table>
				</div>
			</div>
			
			<div class="modal-footer" style="height:65px;">
				<button class="btn btn_gray pull-right" data-dismiss="modal" type="button">取消</button>
				<button class="btn btn_blue pull-right mr20" onclick="onAddRequiredSubject()"  type="button">添加到试卷</button>
				<div id="data_page_Panel" class="ml20"  style="margin-top: -40px;"></div>
				<div class="clearfix"></div>
			</div>
			
		</div>
	</div>
</div>
		
		
<div class="modal fade" id="quizBanksModal" tabindex="-1" role="dialog"  aria-labelledby="quizBanksModalLabel" aria-hidden="true" data-backdrop="static">
	<div style="width: 800px;" role="document" class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-label="Close" data-dismiss="modal" class="close" type="button">
					<span aria-hidden="true">×</span>
				</button>
				<h4 id="myModalLabel" class="modal-title">出题范围</h4>
			</div>
			<div style="height: 400px; overflow-y: hidden;" class="modal-body">
				<!-- 这里开始是弹出内容 -->
				<div style="position: relative; height: 56px;padding:10px;border:1px dashed #3faad0;border-radius:4px;">
					<div style="width: 768px; left: 0; top: 0; height: 38px;" class="show_more">
						<div class="div-tags" id="div-tags" style="width: 751px">
							<button type="button" class="btn_add" id="classifications"></button>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				<table cellspacing="0" cellpadding="0" class="table table-striped table-hover td_h60 mt20" >
					<thead>
						<tr>
							<th style="width: 8%" class="text-center">
								<div class="checkbox-group">
									<input type="checkbox" id="questionall" name="questionall"> <label
										for="questionall" class="checkbox-2"></label>
								</div>
							</th>
							<th style="width: 22%;" class="text-center">题库名称</th>
							<th class="text-center">题库描述</th>
						</tr>
					</thead>
				</table>
				<div style="height: 290px; overflow-y: auto; margin-top: -20px;">
					<table cellspacing="0" cellpadding="0" class="table table-striped table-hover td_h60">
						<tbody id="banks_panel">
						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer" style=" text-align: center;">
				<button class="btn btn_green btn_160_36" data-dismiss="modal" class="btn btn_gray" onclick="setBanksTag();" type="button">提交</button>
				<button data-dismiss="modal" class="btn btn_gray btn_160_36" type="button">取消</button>
			</div>
		</div>
	</div>
</div>


		<div class="modal fade" id="myModalPreviewQuiz" tabindex="-1" role="dialog" aria-labelledby="myModalPreviewQuizLabel" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:750px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myQuizPreviewType"></h4>
					</div>
					<div class="modal-body" style="height:450px;overflow-y:auto;position:relative;overflow-x: hidden;">	
						<!-- 这里开始是弹出内容 -->
						<div class="ti_form" style="position:absolute;">
							<div id="quizs_preview_contents"></div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>



		<div class="modal fade" id="myModalQuiz" tabindex="-1" role="dialog" aria-labelledby="myModalQuizLabel" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:750px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myQuizType"></h4>
					</div>
					<div class="modal-body" style="height:450px;overflow-y:auto;position:relative;overflow-x: hidden;">	
						<!-- 这里开始是弹出内容 -->
						<div class="ti_form" style="position:absolute;">
							<div id="toolsBar" class="biaoqian" onClick="javascript:toggleSettings()">查<br>看<br>详<br>细<br><span class="glyphicon glyphicon-menu-left mt10"></span></div>
							<div id="quizs_contents"></div>
						</div>

						<div id="settingContents" style="position:absolute;background-color:#FFF;width:695px;left:740px;">
							<input type="hidden" id="hiddenQuizSubjectId" name="hiddenQuizSubjectId" />
							<div class="form-group b_top pt10">
								<label class="input-title input-title-color">知识点</label>
								<div class="jj_bjq pull-left ml3">
									<select name="quiz_knowledge" id="quiz_knowledge" class="selectpicker">
										<option value="">知识点</option>
									</select>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="form-group">
								<label class="input-title input-title-color">难度</label>
								<div class="jj_bjq pull-left ml3">
									<div class="radio-group pull-left ml10">
										<input type="radio" name="quiz_difficult" id="man21" value="1" checked>
										<label class="radio-2" for="man21"></label>
									</div>
									<span class="pt8 ml10 pull-left">容易</span>
									<div class="radio-group pull-left ml10">
										<input type="radio" name="quiz_difficult" id="man211" value="2" >
										<label class="radio-2" for="man211"></label>
									</div>
									<span class="pt8 ml10 pull-left">较易</span>
									<div class="radio-group pull-left ml10">
										<input type="radio" name="quiz_difficult" id="man212" value="3" >
										<label class="radio-2" for="man212"></label>
									</div>
									<span class="pt8 ml10 pull-left">中等</span>
									<div class="radio-group pull-left ml10">
										<input type="radio" name="quiz_difficult" id="man23" value="4" >
										<label class="radio-2" for="man23"></label>
									</div>
									<span class="pt8 ml10 pull-left">较难</span>
									<div class="radio-group pull-left ml10">
										<input type="radio" name="quiz_difficult" id="man24" value="5" >
										<label class="radio-2" for="man24"></label>
									</div>
									<span class="pt8 ml10 pull-left">难</span>
								</div>
								<div class="clearfix"></div>
								<input type="hidden" name="quiz_type" id="quiz_type"/>
							</div>
							<div class="form-group">
								<label class="input-title input-title-color">缺省分数</label>
								<div class="jj_bjq pull-left ml3">
									<select name="quiz_score" id="quiz_score">
										<option value="1">1</option>
										<option value="5">5</option>
										<option value="10">10</option>
									</select>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="form-group" style="display:none;">
								<label class="input-title input-title-color">标签</label>
								<div class="jj_bjq pull-left ml3" style="width: 445px;">
									<div class="fenlei_1" style="min-width: 445px ! important;">
										<div class="clearfix" id="labelNodes"></div>
									    <div class="div-tags" id="tagsDiv" style="width: 443px;">
										<input id="inputTagator" name="inputTagator" style="width: 400px;" onclick="popTagsPicker()">
										<button type="button" class="btn_add" id="tagInput"></button>
									</div>
									<!-- 点加号弹出选择分类 -->
									<div class="up" id="hotTagsList"
										style="display: none; background-color: #f9f9f9; width: 100.3%;">
									</div>
		
									</div>
								</div>
								<div class="clearfix"></div>
							</div>					
							<div class="form-group">
								<label class="input-title input-title-color">过期时间</label>
								<div class="jj_bjq pull-left ml3">
									<input type="text" id="quiz_expire_date" class="form-control group_title date_btn form_datetime" style="margin-top: -1px;width: 180px;height: 38px;">
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="form-group">
								<label class="input-title input-title-color">可见性</label>
								<div class="jj_bjq pull-left ml3">
									<select name="quiz_visible" id="quiz_visible">
										<option value="0">考试和测验都可用</option>
										<option value="1">仅考试可用</option>
										<option value="2">仅测验可用</option>
										<option value="3">禁用</option>
									</select>
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn_green btn_160_36" onClick="submitQuiz()">提交</button>
						<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
		
<script type="text/javascript">
	require(['modules/quizs/quiz_dialogue'],function(){
		
	});
</script>


<script id="checkbox_template2"  type="text/x-dot-template">

<div class="c929292 f12 mb10">
<!-- <span class="glyphicon glyphicon-info-sign"></span>&nbsp;备注：输入文本，用方括号［...]定义一个或多个空。</div> -->
	<label class="quiz_title ml20 h36">{{=it.quizSubjectName}}</label>
	{{~ it.quizItems:v:index}}
		<div class="ti">
			<div class="checkbox-group pull-left">
				<input type="checkbox" id="quiz_{{=v.id}}" name="quiz" value="{{=v.quizItemName}}" {{? v.isCorrect == 1}}checked{{?}}>
				<label for="quiz_{{=v.id}}" class="checkbox-2"></label>
			</div>
			<div class="ti_input_w pull-left ml20">
				<label class="h36 br2">{{=v.quizItemName}}</label>
			</div>
		</div>
	{{~}}
</script>

<script id="radio_template2" type="text/x-dot-template">

<div class="c929292 f12 mb10">
<!-- <span class="glyphicon glyphicon-info-sign"></span>&nbsp;备注：输入文本，用方括号［...]定义一个或多个空。</div> -->
	<label class="quiz_title ml20 h36">{{=it.quizSubjectName}}</label>
	{{~ it.quizItems:v:index}}
	<div class="ti">
		<div class="radio-group pull-left">
			<input type="radio" value="{{=v.quizItemName}}" id="quiz_{{=v.id}}" name="quiz"  {{? v.isCorrect == 1}}checked{{?}}>
			<label for="quiz_{{=v.id}}" class="radio-2"></label>
		</div>
		<div class="ti_input_w pull-left ml20">
			<label class="h36 br2">{{=v.quizItemName}}</label>
		</div>
	</div>	
	{{~}}
</script>

<script id="alternative_template2" type="text/x-dot-template">
<div class="c929292 f12 mb10"><span class="glyphicon glyphicon-info-sign"></span>&nbsp;备注：输入文本，用方括号［...]定义一个或多个空。</div>

	<label class="quiz_title ml20 h36">{{=it.quizSubjectName}}</label>
	{{~ it.quizItems:v:index}}
	<div class="ti">
		<div class="radio-group pull-left">
			<input type="radio" value="{{=v.quizItemName}}" id="quiz_{{=v.id}}" name="quiz" {{? v.isCorrect == 1}}checked{{?}}>
			<label for="quiz_{{=v.id}}" class="radio-2"></label>
		</div>
		<div class="ti_input_w pull-left ml20">
			<label class="h36 br2">{{=v.quizItemName}}</label>
		</div>
	</div>	
	{{~}}
</script>

<script id="order_template2" type="text/x-dot-template">
<div class="c929292 f12 mb10">
<!-- <span class="glyphicon glyphicon-info-sign"></span>&nbsp;备注：输入文本，用方括号［...]定义一个或多个空。</div> -->
	<label class="quiz_title ml20 h36">{{=it.quizSubjectName}}</label>
	{{~ it.quizItems:v:index}}
	<div class="ti">
		<div class="pull-left pt8 _order-no">{{=index+1}}</div>
		<div class="ti_input_w pull-left ml20">
			<label class="h36 br2">{{=v.quizItemName}}</label>
		</div>
	</div>
	{{~}}
</script>

<script id="picture_template2" type="text/x-dot-template">
<div class="c929292 f12 mb10">
<!-- <span class="glyphicon glyphicon-info-sign"></span>&nbsp;备注：输入文本，用方括号［...]定义一个或多个空。</div> -->
<label class="quiz_title ml20 h36">{{=it.quizSubjectName}}</label>
<div class="mt20">
	{{~ it.quizItems:v:index}}
	<div class="l_pic" style="background-image:url({{=v.attachUrl}});background-position:center center;background-repeat:no-repeat;background-size:100%;">
	</div>
	
	<textarea class="form-control pic_textarea ml20 pull-left" rows="10" cols="30" id="tuxing" name="tuxing" style="width: 450px;"  disabled="disabled">{{=v.quizItemName}}</textarea>
	<div class="clearfix"></div>
	{{~}}
</div>
</script>

<script id="short_answer_template2" type="text/x-dot-template">

	<div class="c929292 f12 mb10">
<span class="glyphicon glyphicon-info-sign"></span>&nbsp;备注：输入文本，用方括号［...]定义一个或多个空。</div>
	<label class="quiz_title ml20 h36">{{=it.quizSubjectName}}</label>
	<div class="mt20">
		{{~ it.quizItems:v:index}}
		<textarea class="form-control textarea2" rows="10" cols="30" id="question_answer_id" name="" style="width: 680px;"  disabled="disabled">{{=v.quizItemName}}</textarea>
		{{~}}
		
	</div>
</script>


<script id="space_template2" type="text/x-dot-template">
	<div class="c929292 f12 mb10">
 <span class="glyphicon glyphicon-info-sign"></span>&nbsp;备注：输入文本，用方括号［...]定义一个或多个空。</div> 
	<label class="quiz_title ml20 h36">{{=it.quizSubjectName}}</label>
	<div class="mt20">
		{{~ it.quizItems:v:index}}
		<textarea class="form-control textarea2" rows="10" cols="30" id="question_answer_id" name="" style="width: 680px;"  disabled="disabled">{{=v.quizItemName}}</textarea>
		{{~}}
		
	</div>
</script>
		
<script id="checkbox_template"  type="text/x-dot-template">

<div class="c929292 f12 mb10">
<!-- <span class="glyphicon glyphicon-info-sign"></span>&nbsp;备注：输入文本，用方括号［...]定义一个或多个空。</div> -->
{{? it != 'abcdefghijklmnopqrstuvwxyz'}}
	<input type="text" value="{{=it.quizSubjectName}}" class="quiz_title form-control h36">
	{{~ it.quizItems:v:index}}
		<div class="ti">
			<div class="checkbox-group pull-left">
				<input type="checkbox" id="quiz_{{=v.id}}" name="quiz" value="{{=v.quizItemName}}" {{? v.isCorrect == 1}}checked{{?}}>
				<label for="quiz_{{=v.id}}" class="checkbox-2"></label>
			</div>
			<div class="ti_input_w pull-left ml20">
				<input type="text" value="{{=v.quizItemName}}" class="form-control h36 br2">
			</div>
			<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteBankOptions(this);"></button>
			<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addBankOptions(this,2);"></button>
		</div>
	{{~}}
{{??}}
<input type="text" value="标题" class="quiz_title form-control h36">
<div class="ti">
	<div class="checkbox-group pull-left">
		<input type="checkbox" id="quiz_1" name="quiz" value="1">
		<label for="quiz_1" class="checkbox-2"></label>
	</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" value="题目一" class="form-control h36 br2">
	</div>
	<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteBankOptions(this);"></button>
	<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addBankOptions(this,2);"></button>
</div>	
<div class="ti">
	<div class="checkbox-group pull-left">
		<input type="checkbox" id="quiz_2" name="quiz" value="2">
		<label for="quiz_2" class="checkbox-2"></label>
	</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" value="题目二" class="form-control h36 br2">
	</div>
	<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteBankOptions(this);"></button>
	<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addBankOptions(this,2);"></button>
</div>
<div class="ti">
	<div class="checkbox-group pull-left">
		<input type="checkbox" id="quiz_3" name="quiz" value="3">
		<label for="quiz_3" class="checkbox-2"></label>
	</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" value="题目三" class="form-control h36 br2">
	</div>
	<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteBankOptions(this);"></button>
	<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addBankOptions(this,2);"></button>
</div>
<div class="ti">
	<div class="checkbox-group pull-left">
		<input type="checkbox" id="quiz_4" name="quiz" value="4">
		<label for="quiz_4" class="checkbox-2"></label>
	</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" value="题目四" class="form-control h36 br2">
	</div>
	<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteBankOptions(this);"></button>
	<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addBankOptions(this,2);"></button>
</div>
	
{{?}}	
</script>

<script id="radio_template" type="text/x-dot-template">

<div class="c929292 f12 mb10">
<!-- <span class="glyphicon glyphicon-info-sign"></span>&nbsp;备注：输入文本，用方括号［...]定义一个或多个空。</div> -->
{{? it != 'abcdefghijklmnopqrstuvwxyz'}}
	<input type="text" value="{{=it.quizSubjectName}}" class="quiz_title form-control h36">
	{{~ it.quizItems:v:index}}
	<div class="ti">
		<div class="radio-group pull-left">
			<input type="radio" value="{{=v.quizItemName}}" id="quiz_{{=v.id}}" name="quiz"  {{? v.isCorrect == 1}}checked{{?}}>
			<label for="quiz_{{=v.id}}" class="radio-2"></label>
		</div>
		<div class="ti_input_w pull-left ml20">
			<input type="text" value="{{=v.quizItemName}}" class="form-control h36 br2">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteBankOptions(this);"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addBankOptions(this,1);"></button>
	</div>	
	{{~}}
{{??}}

<input type="text" value="标题" class="quiz_title form-control h36">
<div class="ti">
	<div class="radio-group pull-left">
		<input type="radio" value="1" id="quiz_1" name="quiz">
		<label for="quiz_1" class="radio-2"></label>
	</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" value="题目一" class="form-control h36 br2">
	</div>
	<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteBankOptions(this);"></button>
	<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addBankOptions(this,1);"></button>
</div>	
<div class="ti">
	<div class="radio-group pull-left">
		<input type="radio" value="2" id="quiz_2" name="quiz">
		<label for="quiz_2" class="radio-2"></label>
	</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" value="题目二" class="form-control h36 br2">
	</div>
	<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteBankOptions(this);"></button>
	<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addBankOptions(this,1);"></button>
</div>
<div class="ti">
	<div class="radio-group pull-left">
		<input type="radio" value="3" id="quiz_3" name="quiz">
		<label for="quiz_3" class="radio-2"></label>
	</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" value="题目三" class="form-control h36 br2">
	</div>
	<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteBankOptions(this);"></button>
	<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addBankOptions(this,1);"></button>
</div>
<div class="ti">
	<div class="radio-group pull-left">
		<input type="radio" value="4" id="quiz_4" name="quiz">
		<label for="quiz_4" class="radio-2"></label>
	</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" value="题目四" class="form-control h36 br2">
	</div>
	<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteBankOptions(this);"></button>
	<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addBankOptions(this,1);"></button>
</div>
{{?}}
</script>

<script id="alternative_template" type="text/x-dot-template">
<div class="c929292 f12 mb10">
<!-- <span class="glyphicon glyphicon-info-sign"></span>&nbsp;备注：输入文本，用方括号［...]定义一个或多个空。</div>  -->

{{? it != 'abcdefghijklmnopqrstuvwxyz'}}
	<input type="text" value="{{=it.quizSubjectName}}" class="quiz_title form-control h36">
	{{~ it.quizItems:v:index}}
	<div class="ti">
		<div class="radio-group pull-left">
			<input type="radio" value="{{=v.quizItemName}}" id="quiz_{{=v.id}}" name="quiz" {{? v.isCorrect == 1}}checked{{?}}>
			<label for="quiz_{{=v.id}}" class="radio-2"></label>
		</div>
		<div class="ti_input_w pull-left ml20">
			<input type="text" value="{{=v.quizItemName}}" class="form-control h36 br2" disabled="disabled">
		</div>
	</div>	
	{{~}}
{{??}}

<input type="text" value="标题" class="quiz_title form-control h36">
<div class="ti">
	<div class="radio-group pull-left">
		<input type="radio" value="1" id="quiz_1" name="quiz">
		<label for="quiz_1" class="radio-2"></label>
	</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" value="对" class="form-control h36 br2">
	</div>
</div>	
<div class="ti">
	<div class="radio-group pull-left">
		<input type="radio" value="2" id="quiz_2" name="quiz">
		<label for="quiz_2" class="radio-2"></label>
	</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" value="错" class="form-control h36 br2">
	</div>
</div>
{{?}}
</script>

<script id="order_template" type="text/x-dot-template">
<div class="c929292 f12 mb10">
<!-- <span class="glyphicon glyphicon-info-sign"></span>&nbsp;备注：输入文本，用方括号［...]定义一个或多个空。-->
</div>
{{? it != 'abcdefghijklmnopqrstuvwxyz'}}
	<input type="text" value="{{=it.quizSubjectName}}" class="quiz_title form-control h36">
	{{~ it.quizItems:v:index}}
	<div class="ti">
		<div class="pull-left pt8 _order-no">
<input type="text" style="width:36px ; height: 28px; ">
</div>
		<div class="ti_input_w pull-left ml20">
			<input type="text" value="{{=v.quizItemName}}" class="form-control h36 br2">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteBankOptions(this);"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addBankOptions(this,3);"></button>
	</div>
	{{~}}
{{??}}

<input type="text" value="标题" class="quiz_title form-control h36">
<div class="ti">
	<div class="pull-left pt8 _order-no">1</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" value="题目一" class="form-control h36 br2">
	</div>
	<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteBankOptions(this);"></button>
	<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addBankOptions(this,3);"></button>

</div>	
<div class="ti">
	<div class="pull-left pt8 _order-no">2</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" value="题目二" class="form-control h36 br2">
	</div>
	<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteBankOptions(this);"></button>
	<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addBankOptions(this,3);"></button>

</div>
<div class="ti">
	<div class="pull-left pt8 _order-no">3</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" value="题目三" class="form-control h36 br2">
	</div>
	<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteBankOptions(this);"></button>
	<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addBankOptions(this,3);"></button>

</div>
<div class="ti">
	<div class="pull-left pt8 _order-no">4</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" value="题目四" class="form-control h36 br2">
	</div>
	<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteBankOptions(this);"></button>
	<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addBankOptions(this,3);"></button>

</div>
{{?}}
</script>


<script id="picture_template" type="text/x-dot-template">
{{? it != 'abcdefghijklmnopqrstuvwxyz'}}
<div class="c929292 f12 mb10">
<!-- <span class="glyphicon glyphicon-info-sign"></span>&nbsp;备注：输入文本，用方括号［...]定义一个或多个空。</div> -->
<input type="text" value="{{=it.quizSubjectName}}" class="quiz_title form-control h36">
<div class="mt20">
	{{~ it.quizItems:v:index}}
	<div class="l_pic" style="background-image:url({{=v.attachUrl}});background-position:center center;background-repeat:no-repeat;background-size:100%;">
        <input type="file" name="uploadFile" class="btn up_color"  id="uploadFile_{{=v.id}}" style="width:50px" onchange="quizAttachUpload(this,'11')">
		<input type="hidden" name="filePath" id="uploadFilePath_{{=v.id}}" value="{{=v.attachUrl}}">
		
	</div>
	
	<textarea class="form-control pic_textarea ml20 pull-left" rows="10" cols="30" id="tuxing" name="tuxing" style="width: 450px;">{{=v.quizItemName}}</textarea>
	<div class="clearfix"></div>
	{{~}}
</div>
{{??}}

<div class="c929292 f12 mb10"><span class="glyphicon glyphicon-info-sign"></span>&nbsp;备注：输入文本，用方括号［...]定义一个或多个空。</div>
<input type="text" value="标题" class="quiz_title form-control h36">
<div class="mt20">
	<div class="l_pic">
		<!--<button class="hdp" type="button">选择需要上传的图片</button>-->
        <input type="file" name="uploadFile" class="btn up_color"  id="uploadFile_${items.id}" style="width:50px" onchange="quizAttachUpload(this,'11')">
		<input type="hidden" name="filePath" >
		
	</div>
	<textarea class="form-control pic_textarea ml20 pull-left" rows="10" cols="30" id="tuxing" name="tuxing" style="width: 450px;"></textarea>
	<div class="clearfix"></div>
</div>
{{?}}

</script>

<script id="short_answer_template" type="text/x-dot-template">

{{? it != 'abcdefghijklmnopqrstuvwxyz'}}
	<div class="c929292 f12 mb10">
<!-- <span class="glyphicon glyphicon-info-sign"></span>&nbsp;备注：输入文本，用方括号［...]定义一个或多个空。</div> -->
	<input type="text" value="{{=it.quizSubjectName}}" class="quiz_title form-control h36">
	<div class="mt20">
		{{~ it.quizItems:v:index}}
		<textarea class="form-control textarea2" rows="10" cols="30" id="question_answer_id" name="" style="width: 680px;">{{=v.quizItemName}}</textarea>
		{{~}}
		
	</div>
{{??}}
	<div class="c929292 f12 mb10"><span class="glyphicon glyphicon-info-sign"></span>&nbsp;备注：输入文本，用方括号［...]定义一个或多个空。</div>
	<input type="text" value="标题" class="quiz_title form-control h36">
	<div class="mt20">
		<textarea class="form-control textarea2" rows="10" cols="30" id="question_answer_id" name="" style="width: 680px;"></textarea>
	</div>
{{?}}
</script>

<script id="space_template" type="text/x-dot-template">
{{? it != 'abcdefghijklmnopqrstuvwxyz'}}
	<div class="c929292 f12 mb10">
 <span class="glyphicon glyphicon-info-sign"></span>&nbsp;备注：输入文本，用方括号［...]定义一个或多个空。</div> 
	<input type="text" value="{{=it.quizSubjectName}}" class="quiz_title form-control h36">
	<div class="mt20">
		{{~ it.quizItems:v:index}}
		<textarea class="form-control textarea2" rows="10" cols="30" id="question_answer_id" name="" style="width: 680px;">{{=v.quizItemName}}</textarea>
		{{~}}
		
	</div>
{{??}}
	<div class="c929292 f12 mb10"><span class="glyphicon glyphicon-info-sign"></span>&nbsp;备注：输入文本，用方括号［...]定义一个或多个空。</div>
	<input type="text" value="标题" class="quiz_title form-control h36">
	<div class="mt20">
		<textarea class="form-control textarea2" rows="10" cols="30" id="question_answer_id" name="" style="width: 680px;"></textarea>
	</div>
{{?}}

</script>

<script id="options_add_text_template" type="text/x-dot-template">
	<div class="ti">
		<div class="pull-left pt8 _order-no">{{=it.data}}</div>
		<div class="ti_input_w pull-left ml20">
			<input type="text" class="form-control h36 br2" value="题目一">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteBankOptions(this,1);"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addBankOptions(this,3);"></button>
	</div>
</script>

<script id="options_add_template" type="text/x-dot-template">
<div class="ti">
	<div class="{{=it.data.divClass}} pull-left">
		<input type="{{=it.data.inputType}}" name="quiz" id="{{=it.data.tiId}}" value="{{=it.data.values}}">
		<label class="{{=it.data.inputType}}-2" for="{{=it.data.tiId}}"></label>
	</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" class="form-control" value="">
	</div>
	<button type="button" onclick="deleteBankOptions(this);" class="btn_del_18 pull-left mt10 ml10"></button>
	{{? it.data.inputType == 'checkbox'}}
	<button type="button" onclick="addBankOptions(this,2);"class="btn_add_18 pull-left mt10 ml10"></button>
	{{??}}
	<button type="button" onclick="addBankOptions(this,1);"class="btn_add_18 pull-left mt10 ml10"></button>
	{{?}}
</div>
</script>



<script id="view_data_template" type="text/x-dot-template">
	
	<div class="mb20">
		<div id="draggableContent" class="ti_left" style="height: 100%; width: 100%;margin-right: 0px;">
		{{~ it.data:v:index}}
			{{? v.quiz_subject_type == 1}}
				
				<div class="chouti mt20">
					<div class="edit_title">
						<span class="pull-left _quiz-order">{{=v.quiz_subject_order + 1}}</span>
						<div style="background: #cdd5d9; border-radius: 0 2px 0 0;" class="pl10">{{=v.quiz_subject_name}}</div>
					</div>
					{{
						var items = v.item_options.split('#');
						var answs = v.option_answer.split('#');
					}}
					{{
						for(var jndex in items){
					}}

					{{
						for(var zndex in answs){
					
					}}
						
						{{? jndex == zndex}}
								<div class="ti">
									<div class="radio-group pull-left">
										<input type="radio"  {{? answs[zndex] == 1}} checked="checked"{{?}} name="quiz{{=v.id}}" id="{{=v.id}}_{{=jndex}}" value="{{=zndex}}"> 
										<label class="radio-2 {{?  answs[zndex] == 1}} checked{{?}}" for="{{=v.id}}_{{=jndex}}"></label>
									</div>
									<div class="ti_input_w pull-left ml20 pt8">
										{{=items[jndex]}}
									</div>
								</div>
							{{?}}

					{{
						}
					}}
					{{
						}
					}}


				</div>
											

			{{?}}


			{{? v.quiz_subject_type == 2}}
				
				<div class="chouti mt20">
					<div class="edit_title">
						<span class="pull-left _quiz-order">{{=v.quiz_subject_order + 1}}</span>
						<div style="background: #cdd5d9; border-radius: 0 2px 0 0;" class="pl10">{{=v.quiz_subject_name}}</div>
					</div>
					{{
						var items = v.item_options.split('#');
						var answs = v.option_answer.split('#');
					}}
					{{
						for(var jndex in items){
					}}

					{{
						for(var zndex in answs){
					
					}}
						
						{{? jndex == zndex}}
								<div class="ti">
									<div class="checkbox-group pull-left">
										<input type="checkbox"  {{? answs[zndex] == 1}} checked="checked"{{?}} name="quiz{{=v.id}}" id="{{=v.id}}_{{=jndex}}" value="{{=zndex}}"> 
										<label class="checkbox-2 {{?  answs[zndex] == 1}} checked{{?}}" for="{{=v.id}}_{{=jndex}}"></label>
									</div>
									<div class="ti_input_w pull-left ml20 pt8">
										{{=items[jndex]}}
									</div>
								</div>
							{{?}}

					{{
						}
					}}
					{{
						}
					}}


				</div>
											

			{{?}}


			{{? v.quiz_subject_type == 3}}
				
				<div class="chouti mt20">
					<div class="edit_title">
						<span class="pull-left _quiz-order">{{=v.quiz_subject_order + 1}}</span>
						<div style="background: #cdd5d9; border-radius: 0 2px 0 0;" class="pl10">{{=v.quiz_subject_name}}</div>
					</div>
					{{
						var items = v.item_options.split('#');
						var answs = v.option_answer.split('#');
					}}
					{{
						for(var jndex in items){
					}}

					{{
						for(var zndex in answs){
					
					}}
						
						{{? jndex == zndex}}
								<div class="ti">
									<div class="radio-group pull-left">
										<input type="radio"  {{? answs[zndex] == 1}} checked="checked"{{?}} name="quiz{{=v.id}}" id="{{=v.id}}_{{=jndex}}" value="{{=zndex}}"> 
										<label class="radio-2 {{?  answs[zndex] == 1}} checked{{?}}" for="{{=v.id}}_{{=jndex}}"></label>
									</div>
									<div class="ti_input_w pull-left ml20 pt8">
										{{=items[jndex]}}
									</div>
								</div>
							{{?}}

					{{
						}
					}}
					{{
						}
					}}


				</div>
											

			{{?}}


			{{? v.quiz_subject_type == 4}}
				
				<div class="chouti mt20">
					<div class="edit_title">
						<span class="pull-left _quiz-order">{{=v.quiz_subject_order + 1}}</span>
						<div style="background: #cdd5d9; border-radius: 0 2px 0 0;" class="pl10">{{=v.quiz_subject_name}}</div>
					</div>
					{{
						var items = v.item_options.split('#');
					}}
					{{
						for(var jndex in items){
					}}

						<div class="ti">
							<div class="pull-left pt8">
								<input type="text" class="form-control w50 text-center" id="{{=v.id}}_{{=jndex}}">
							</div>
							<div class="ti_input_w pull-left ml20 pt8">{{=items[jndex]}} </div>

						</div>
						

					{{
						}
					}}



				</div>
											

			{{?}}

			{{? v.quiz_subject_type == 5}}
				
				<div class="chouti mt20">
					<div class="edit_title">
						<span class="pull-left _quiz-order">{{=v.quiz_subject_order + 1}}</span>
						<div style="background: #cdd5d9; border-radius: 0 2px 0 0;" class="pl10">{{=v.quiz_subject_name}}</div>
					</div>

					<div class="tuxing p20">
						<div class="l_pic">
							<img src="{{=v.attach_url}}" id="userIdCardImg" width="160">
						</div>
						<textarea name="" id="" cols="30" rows="10" class="pic_textarea ml20 pull-left" style="width: 632px; ">{{=v.item_options}}</textarea>
						<div class="clearfix"></div>
					</div>

				</div>
											

			{{?}}

			{{? v.quiz_subject_type == 6}}
				
				<div class="chouti mt20">
					<div class="edit_title">
						<span class="pull-left _quiz-order">{{=v.quiz_subject_order + 1}}</span>
						<div style="background: #cdd5d9; border-radius: 0 2px 0 0;" class="pl10">{{=v.quiz_subject_name}}</div>
					</div>

					<div class="jianda mt10">
						<textarea name="" id="">{{=isNullFormat(v.item_options)}}</textarea>
					</div>

				</div>
											

			{{?}}


			{{? v.quiz_subject_type == 7}}
				
				<div class="chouti mt20">
					<div class="edit_title">
						<span class="pull-left _quiz-order">{{=v.quiz_subject_order + 1}}</span>
						<div style="background: #cdd5d9; border-radius: 0 2px 0 0;" class="pl10">{{=v.quiz_subject_name}}</div>
					</div>

					<div class="jianda mt10">
						<textarea name="" id="">{{=v.item_options}}</textarea>
					</div>

				</div>
											

			{{?}}
		{{~}}

</script>

<%@ include file="/WEB-INF/views/modules/classification/classification_dialogue.jsp"%>

<script id="options_knowledge_point" type="text/x-dot-template">
{{~it.data:v:index}}
	<option value="{{=v.id}}">{{=v.knowledge_point_name}}</option>
{{~}}
</script>
