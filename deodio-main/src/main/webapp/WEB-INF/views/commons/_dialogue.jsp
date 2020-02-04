<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- alert -->

<div class="modal fade" id="alertModel" tabindex="-1" role="dialog" style="z-index: 1060"  aria-labelledby="alertModalLabel" aria-hidden="true"  data-backdrop="static">
   <div class="modal-dialog alert-modal-dialog">
      <div class="modal-content alert-modal-content">
         <!-- <div class="modal-header"  style="background: #fff none repeat scroll 0 0;color: #218b9c;font-size: 16px;">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" style="color:#218b9c;">&times;</span></button>
	        <h4 class="modal-title" id="alertModalLabel">提示</h4>
	      </div> -->
         <div class="modal-body">
         	<div class="modal-message" style="height: 50px;margin-left: 25; margin-right: 25px;">
         		<div class="verify-box-center">
					<img src="${ctx}/resources/img/right-pic.png"/><h1 class="text-center" style="color:#02b58d;" id="alertModalLabel">提示</h1>
				</div><!--verify-box-center end-->
           		<span id="alertModelBody" class="confirm-model-body">在这里添加一些文本</span>
            </div>
            <div class="modal-footer" style=" text-align: center;">
				<button id="colseSubmitButton" type="button" class="btn cancel btn_160_36" data-dismiss="modal">确定</button>
			</div>
         </div>
      </div>
	</div><!-- /.alert -->
</div>


<!-- alert -->
<div class="modal fade" id="alertConfirmModel" tabindex="-1" role="dialog" style="z-index: 1060" aria-labelledby="alertConfirmModalLabel" aria-hidden="true"  data-backdrop="static">
   <div class="modal-dialog confirm-modal-dialog">
      <div class="modal-content confirm-modal-content">
          <!-- <div class="modal-header"  style="background: #eb604d none repeat scroll 0 0;color: #fff;font-size: 16px;"">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" style="color:#eb604d;">&times;</span></button>
	        <h4 class="modal-title" id="alertConfirmModalLabel">警告提示</h4>
	      </div> -->
         <div class="modal-body">
         	<div class="modal-message" style="height: 50px;margin-left: 25; margin-right: 25px;">
         		<div class="verify-box-center">
					<%-- <img src="${ctx}/resources/img/wrong-pic.png"/><h1 class="text-center red" id="alertConfirmModalLabel">警告</h1> --%>
				</div><!--verify-box-center end-->
           		<span id="alertConfirmModelBody" class="confirm-model-body">在这里添加一些文本</span>
            </div>
             <div class="modal-footer" style=" text-align: center;">
         		<button id="confirmSubmitButton" type="button" class="btn btn_red btn_160_36" btn-type="true" data-dismiss="modal">确定</button>
				<button type="button" class="btn cancel btn_160_36" data-dismiss="modal">取消</button>
	         </div>
         </div>
      </div>
	</div><!-- /.alert -->
</div>

<div class="md-popup">
	<div class="modal fade" id="popupPreviewModel" tabindex="-1" role="dialog" aria-labelledby="popupPreviewModalLabel" aria-hidden="true" data-backdrop="static">
	  <div class="modal-dialog" id="modal_dialog">
	    <div class="modal-content" id="modal_content" style="overflow-y:hidden;top: 0px;overflow-x: hidden;">
	      <div class="modal-header"  style="background: #fff none repeat scroll 0 0;color: #41829a;border-bottom: 0;">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="popupPreviewModalLabel">Modal Title</h4>
	      </div>
	      <div id="popupPreviewModelBody" class="modal-body setup-popup-item1" style="!important; ">
	       
	      </div>
	       <div class="modal-footer" style=" text-align: right;">
				<button type="button" class="btn cancel btn_160_36" data-dismiss="modal" style="margin-right: 30px;">关闭</button>
	         </div>
	    </div>
	  </div>
	</div>		
</div>

<!-- popup window -->
<div class="md-popup">
	<div class="modal fade" id="popupModel" tabindex="-1" role="dialog" aria-labelledby="popupModalLabel" aria-hidden="true" data-backdrop="static">
	  <div class="modal-dialog" id="modal_dialog">
	    <div class="modal-content" id="modal_content" style="overflow-y:hidden;top: 0px;overflow-x: hidden;">
	      <div class="modal-header"  style="background: #41829a none repeat scroll 0 0;color: #fff;">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="popupModalLabel">Modal Title</h4>
	      </div>
	      <div id="popupModelBody" class="modal-body setup-popup-item1" style="overflow-y:hidden !important; ">
	       
	      </div>
	    </div>
	  </div>
	</div>		
</div>
<!-- popup window end -->



<!-- popup flash window -->
<div class="modal fade" id="popupSWFModel" tabindex="-1" role="dialog" aria-labelledby="popupSWFModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog" style="width:900px;height:509px;">
    <div class="modal-content" style="width:900px;height:509px;overflow-y:hidden;top: 0px;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="popupSWFModalLabel">Modal Title</h4>
      </div>
      <div id="popupSWFModelBody" class="modal-body" style="overflow-y:auto;padding:0px; ">
        <div id="swf-body">
        
        </div>
      </div>
    </div>
  </div>
</div>

<!-- popup window end -->
