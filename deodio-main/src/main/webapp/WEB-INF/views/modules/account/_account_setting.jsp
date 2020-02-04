<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <ul class="nav nav-tabs set_tab" role="tablist" style= "background-color:#FFFFFF ">
        <li role="presentation" class="active"><a href="#name" role="tab" data-toggle="tab">域名</a></li>
        <li role="presentation"><a href="#logo" role="tab" data-toggle="tab">标志</a></li>
       <!--  <li role="presentation"><a href="#header" role="tab" data-toggle="tab">头部</a></li> -->
        <!-- <li role="presentation"><a href="#banner" role="tab" data-toggle="tab">广告</a></li> -->
        <li role="presentation"><a href="#footer" role="tab" data-toggle="tab">底部</a></li>
        <li role="presentation"><a href="#layout" role="tab" data-toggle="tab">布局</a></li>
        <li role="presentation"><a href="#theme" role="tab" data-toggle="tab">主题</a></li>
    </ul>
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="name">
        	<form id="domainForm">
	            <div class="form_bg ">
	                <div class="row" style="min-height:450px;">
	                    <div class="col-md-2 text-right"><span class="title">域名：http://</span></div>
	                    <div class="col-md-3">
	                    		<input type="text" value="" class="form-control" id="subdomain" check-type="char" 
									data-callback="validateHostExists()" call-message="当前域名已被注册" required-message="请输入正确英文字符"
									placeholder="请输入二级域名" class="form-control pull-left h40">
	                    </div>
	                    <div class="col-md-2 text-left"><span class="title">.deodio.com.cn</span></div>
	                    <span class="error-custom"></span> 
	                </div>
	                <div class="row">
	                    <div class="col-md-10 col-md-offset-2">
	                        <button class="btn submit btn_160_36" btn-type="true" type="button">保存</button>
	                    </div>
	                </div>
	            </div>
            </form>
        </div>
        <div role="tabpanel" class="tab-pane" id="logo">
            <div class="form_bg ">
            	<form id="logoForm">
	                <div class="row">
	                    <div class="col-md-6">
	                        <div class="text-center logo_upload" id="sign01">
	                        	<!-- <button class="btn upload_btn" type="button"><span class="glyphicon glyphicon-plus"></span>选择图片</button><br/><br/> -->
<!-- 	                            <div id="logo_upload_div" style="position: absolute; top: 50%; margin-top: -20px; left: 38%;"> -->
<!-- 									<input id="logoUploadFile" name="uploadFile" type="file"> -->
<!-- 								</div> -->
								
								
								<div  id="logo_upload_div"  style="width:130px;height:34px;background:#3eaad0;position:relative;text-align:center;overflow:hidden;line-height: 34px;margin-top: 163px;margin-left: 109px;margin-bottom: -200px;">
                                    <span style="display:block;color:#fff;cursor:pointer;">选择图片</span>
                                   <input accept="image/png, image/gif, image/jpg, image/jpeg" id="logoUploadFile" type="file" name="uploadFile" multiple="" style="position:absolute;opacity:0;width:400px;hright:34px;right:0;top:0;cursor:pointer;">
                                </div>
								
								
								
								
								<span style="font-size: 12px; position: relative; top: 100px;">只支持JPG、PNG、GIF，大小不超过5M</span>
								
								<div id="logo_img_div" style="width: 360px; height: 360px; position: absolute; overflow: hidden; top: 5px; left: 50px; display: none;">
									<img id="logo_img" style="" alt="" src="">
								</div>
	                        </div>
	                    </div>
	                    <div class="col-md-6">
	                    	<div class="form-inline " id="sign02" style="width: 400px;">
								<input type="text" placeholder="请输入http://www.网址.com"  id="logoLinkUrl"  check-type="url" class="form-control" style="width:400px !important;"> 
								<span class="error-custom"></span> 
							</div>
	                        <div class="logo_pic"><img id="logo_img_snap" width="240" height="78" src="${ctx}/resources/img/account/uplogo.png" alt=""></div>
	                       <!--  <div class="text-center">
	                            <button class="btn btn-default set_btn" btn-type="true" type="button">保存</button>&emsp;&emsp;
	                            <button class="btn cancel set_btn" type="button" onclick="deleteStyleUploadFun();">重置</button>
	                        </div> -->
	                    </div>
	                    <div class="col-md-12">
	                        <div class="text-center">
	                            <button class="btn btn-default set_btn" btn-type="true" type="button">保存</button>&emsp;&emsp;
	                            <button class="btn cancel set_btn" type="button" onclick="deleteStyleUploadFun();">重置</button>
	                        </div>
	                    </div>
	                </div>
                </form>
            </div>
        </div>
        <div role="tabpanel" class="tab-pane" id="header">
            <div class="form_bg ">
            	<form id="headerForm">
	                <div class="text-center logo_upload2">
	                	<div id="header_upload_div" style="position: relative; left: 50%; top: 50%; margin-top: -20px; margin-left: -50px;">
							<input id="headerUploadFile" name="uploadFile" type="file">
						</div>
						<span style="font-size: 12px; position: relative; top: 10px;">只支持JPG、PNG、GIF，大小不超过5M</span>
						<div id="header_img_div" style="width: 360px; height: 360px; position: relative; overflow: hidden; top: 0px; left: 0px; display: none;">
							<img id="header_img" style="" alt="" src="">
						</div>
	                </div>
	                <div class="form-inline ">
						<input type="text" placeholder="请输入http://www.网址.com"  id="headerLinkUrl"  check-type="url" class="form-control" style="width:400px !important;"> 
						<span class="error-custom"></span> 
					</div>
	                <div class="logo_pic2"><img id="header_img_snap" src="${ctx}/resources/img/account/banner_pic.png" alt=""></div>
	                <div class="text-center">
	                    <button class="btn btn-default set_btn" btn-type="true" type="button">保存</button>&emsp;&emsp;
	                            <button class="btn cancel set_btn" type="button" onclick="deleteStyleUploadFun();">重置</button>
	                </div>
                </form>
            </div>
        </div>
        
        <div role="tabpanel" class="tab-pane" id="banner">
        	<div class="form_bg ">
            	<form id="bannerForm">
	                <div class="text-center logo_upload2">
	                	<div id="banner_upload_div" style="position: relative; left: 50%; top: 50%; margin-top: -20px; margin-left: -50px;">
							<input id="bannerUploadFile" name="uploadFile" type="file">
						</div>
						<span style="font-size: 12px; position: relative; top: 10px;">只支持JPG、PNG、GIF，大小不超过5M</span>
						<div id="banner_img_div" style="width: 360px; height: 360px; position: relative; overflow: hidden; top: 0px; left: 0px; display: none;">
							<img id="banner_img" style="" alt="" src="">
						</div>
	                </div>
	                <div class="form-inline ">
						<input type="text" placeholder="请输入http://www.网址.com"  id="bannerLinkUrl"  check-type="url" class="form-control" style="width:400px !important;"> 
						<span class="error-custom"></span> 
					</div>
	                <div class="logo_pic2"><img id="banner_img_snap" src="${ctx}/resources/img/account/banner_pic.png" alt=""></div>
	                <div class="text-center">
	                    <button class="btn btn-default set_btn" btn-type="true" type="button">保存</button>&emsp;&emsp;
	                            <button class="btn cancel set_btn" type="button" onclick="deleteStyleUploadFun();">重置</button>
	                </div>
                </form>
            </div>
        </div>
        <div role="tabpanel" class="tab-pane" id="footer">
        	<div class="form_bg ">
            	<form id="footerForm">
	                <div class="text-center logo_upload2" id="foot01">
<!-- 	                	<div id="footer_upload_div" style="position: relative; left: 50%; top: 50%; margin-top: -20px; margin-left: -50px;"> -->
<!-- 							<input id="footerUploadFile" name="uploadFile" type="file"> -->
<!-- 						</div> -->
						
							<div  id="footer_upload_div"  style="width:130px;height:34px;background:#3eaad0;position:relative;text-align:center;overflow:hidden;line-height: 34px;margin-top: 50px;margin-left: 370px;margin-bottom: -70px;">
                                    <span style="display:block;color:#fff;cursor:pointer;">选择图片</span>
                                   <input accept="image/png, image/gif, image/jpg, image/jpeg" id="footerUploadFile" type="file" name="uploadFile" multiple="" style="position:absolute;opacity:0;width:400px;hright:34px;right:0;top:0;cursor:pointer;">
                                </div>
						
						<span style="font-size: 12px; position: relative; top: 10px;">只支持JPG、PNG、GIF，大小不超过5M</span>
						<div id="footer_img_div" style="width: 360px; height: 160px; position: relative; overflow: hidden; top: -16px; left: 4px; display: none;">
							<img id="footer_img" width="360" height="160" style="" alt="" src="">
						</div>
	                </div>
	                <div class="form-inline " id="foot02">
						<input type="text" placeholder="请输入http://www.网址.com"  id="footerLinkUrl"  check-type="url" class="form-control" style="width:400px !important;"> 
						<span class="error-custom"></span> 
					</div>
	                <div class="logo_pic2"><img id="footer_img_snap" src="${ctx}/resources/img/account/banner_pic.png" alt=""></div>
	                <div class="text-center">
	                    <button class="btn btn-default set_btn" btn-type="true" type="button">保存</button>&emsp;&emsp;
	                            <button class="btn cancel set_btn" type="button" onclick="deleteStyleUploadFun();">重置</button>
	                </div>
                </form>
            </div>
        </div>
        <div role="tabpanel" class="tab-pane" id="layout">
        	<div class="form_bg ">
        		<form id="layoutForm">
	                <ul class="themelist">
	                    <li class="layout-img layout-list-1"></li>
			            <li class="layout-img layout-list-2 active"></li>
			            <li class="layout-img layout-list-3"></li>
			            <li class="layout-img layout-list-4"></li>
			            <li class="layout-img layout-list-5"></li>
			            <li class="layout-img layout-list-6"></li>
			            <li class="layout-img layout-list-7"></li>
			            <li class="layout-img layout-list-8"></li>
	                </ul>
	                <div class="text-center">
	                    <button class="btn btn-default set_btn" btn-type="true" type="button">保存</button>&emsp;&emsp;
		            </div>
		          </form>
            </div>
        </div>
        <div role="tabpanel" class="tab-pane" id="theme">
            <div class="form_bg ">
            	<form id="themeForm">
	                <ul class="themelist">
	                    <li ><img src="${ctx}/resources/img/account/theme1.png" alt=""></li>
	                    <li ><img src="${ctx}/resources/img/account/theme2.png" alt=""></li>
	                    <li><img src="${ctx}/resources/img/account/theme3.png" alt=""></li>
	                    <li><img src="${ctx}/resources/img/account/theme4.png" alt=""></li>
	                    <li><img src="${ctx}/resources/img/account/theme5.png" alt=""></li>
	                    <li><img src="${ctx}/resources/img/account/theme6.png" alt=""></li>
	                    <li><img src="${ctx}/resources/img/account/theme7.png" alt=""></li>
	                    <li><img src="${ctx}/resources/img/account/theme8.png" alt=""></li>
	                </ul>
	                <div class="text-center">
	                    <button class="btn btn-default set_btn" btn-type="true" type="button">保存</button>&emsp;&emsp;
		            </div>
		         </form>
            </div>
        </div>
    </div>
    
    <input type="hidden" id="hidAttDir"> 
    <input type="hidden" id="hidAttUrl">
    <input type="hidden" value="1" id="hidInputType">
    <input type="hidden" id="x1">
	<input type="hidden" id="y1">
	<input type="hidden" id="w">
	<input type="hidden" id="h">
	<script type="text/javascript">
		require(['modules/account/_account.setting'],function(){
		}); 
	</script>