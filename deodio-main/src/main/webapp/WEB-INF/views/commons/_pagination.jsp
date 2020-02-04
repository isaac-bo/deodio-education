<%@ page language="java" pageEncoding="utf-8"%>
<script id="table_pages" type="text/x-dot-template">	
	<div class="pull-center" style="text-align:center;" >
		<ul class="pagination"> 
			{{? it.noDate >0 }}

			  {{? it.pre != -1 }}
				 <li>  <a href="javascript:{{=it.dataFun}}('{{=it.pre}}');">&laquo;</a></li>
				  {{??}}
				   <li>  <a href="javascript:void(0);">&laquo;</a></li>
			 {{?}} 

			 {{~it.pageNo :p:index}}
				
				{{? p == it.current }}
					 <li class="active currPage"><a href="javascript:void(0);" crpageno="{{=p}}">{{=p}}</a></li>
				  {{??}}
					 <li><a href="javascript:{{=it.dataFun}}('{{=p}}');">{{=p}}</a></li>				
				 {{?}}
			
			 {{~}}
			  
				{{? it.next != -1 }}
			   	  <li><a href="javascript:{{=it.dataFun}}('{{=it.next}}');">&raquo;</a></li>
			      	{{??}}
			      <li><a href="javascript:void(0)">&raquo;</a></li>	
			   	 {{?}} 

		  {{?}}  	
		</ul>		  
	</div>	  
 </script>

