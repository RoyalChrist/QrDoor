<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

  </head>
  
  <body>
			  <div id="tb_log_list" style="height:30px; ">
				
				
				<div style="float: right;margin-right: 300px;margin-top: 5px;">
				<a href="javascript:void(0);" title="门牌号，用户名等." class="easyui-tooltip"><input id="ss" name="param" class="easyui-searchbox" style="width:200px;padding-top: 10px" data-options="searcher:qq,prompt:'请输入查询的内容',plain:true"></input></a>
						
				</div>
				<div>
					
				
					<div id="div_search_price" style="float: left; margin-left: 50px">
					
					</div>
					
				</div>
				
			</div>
			<script type="text/javascript"> 
			var flag="search";
			hideallsearchdiv();
			
			//隐藏所有的查询div
			function hideallsearchdiv()
			{	
			
			}
			function qq(value,name){ 
				if(value=="")
				{
					$.messager.alert('警告','请输入查询内容','warning');
					return;
				}
				$('#table_log_list').datagrid('load',{param:value,flag:'like'});
			} 
			
			</script> 
			
  
    	<table id="table_log_list"></table>
    	<script type="text/javascript">
    	
    	$('#table_log_list').datagrid({    
    	    url:'<%=basePath%>log_findall.action',
    	    loadMsg:'正在加载....',
    	    rownumbers:true,
    	    singleSelect:true,
    	    pagination:true,
    	    toolbar:'#tb_log_list',
    		
    	    columns:[[    
    	        {field:'ck',title:'选择',width:50,checkbox:true}, 
    	        {field:'doorNumber',title:'门牌号',width:150,sortable:true,align:'center'},    
    	        {field:'loginName',title:'用户名',width:150,sortable:true,align:'center'},    
    	        {field:'realName',title:'真实姓名',width:150,sortable:true,align:'center'},    
    	        {field:'record',title:'操作记录',width:100,align:'center'},
    	        {field:'time',title:'时间',width:500,align:'center'},
    	    ]]
    	});  
    	
    	
    	</script>
    	
  </body>
</html>
