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
			  <div id="tb_user_list" style="height:30px; ">
			 	<a href="javascript:save_user();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
				<a href="javascript:update_user();" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
				<a href="javascript:delete_user();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true">删除</a>
				
				
				<div style="float: right;margin-right: 300px;margin-top: 5px;">
				<a href="javascript:void(0);" title="用户名,真实姓名,手机号等." class="easyui-tooltip"><input id="ss" name="param" class="easyui-searchbox" style="width:200px;padding-top: 10px" data-options="searcher:qq,prompt:'请输入查询的内容',plain:true"></input></a>
						
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
				$('#table_user_list').datagrid('load',{param:value,flag:'like'});
			} 
			
			</script> 
			
  
    	<table id="table_user_list"></table>
    	<div id="div_user_temp"></div>
    	<script type="text/javascript">
    	//保存
    	function save_user()
    	{
    		$('#div_user_temp').window({  
    			title:'添加用户',
    			href:'<%=basePath%>user/save.jsp',
        	    width:400,    
        	    height:400,    
        	    modal:true   
        	}); 
    		
    	}
    	//更新
    	function update_user()
    	{
    		var row=$('#table_user_list').datagrid('getSelected');
    		if(row)
    		{
	    		$('#div_user_temp').window({  
	    			title:'修改用户信息',
	    			href:'<%=basePath%>user_findbyid.action?id='+row.id,
	        	    width:360,    
	        	    height:400,    
	        	    modal:true   
	        	}); 
    		}
    		else{
    			
    			$.messager.alert('警告','请选择操作行','warning'); 
    		}
    		
    	}
    	//删除方法
    	function delete_user()
    	{
    		var row=$('#table_user_list').datagrid('getSelected');
    		if(row)
    		{
    			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
    			    if (r){    	
		    			$.ajax({
		    				url:'<%=basePath%>user_delete.action',
		    				type:'post',
		    				data:{id:row.id},
		    				success:function(data)
		    				{
		    					$.messager.show({
		    						title:'提示',
		    						msg:'删除成功！！',
		    						timeout:2000,
		    						showType:'slide'
		    					});
		
		    					$('#table_user_list').datagrid('reload');
		    				}
		    				
		    			});
    			    }
    			});
    		}
    		else{
    			
    			$.messager.alert('警告','请选择操作行','warning'); 
    		}
    	}
    	
    	$('#table_user_list').datagrid({    
    	    url:'<%=basePath%>user_findall.action',
    	    loadMsg:'正在加载....',
    	    rownumbers:true,
    	    singleSelect:true,
    	    pagination:true,
    	    toolbar:'#tb_user_list',
    		
    	    columns:[[    
    	        {field:'ck',title:'选择',width:50,checkbox:true}, 
    	        {field:'loginName',title:'用户名',width:150,sortable:true,align:'center'},
    	        {field:'realName',title:'真实姓名',sortable:true,width:150,align:'center'},
    	        {field:'mobilephone',title:'手机号码',width:200,align:'center'},
    	        {field:'password',title:'密码',width:150,align:'center'},
    	        {field:'imei',title:'IMEI',width:300,align:'center'},
    	    ]]
    	});  
    	
    	
    	</script>
    	
  </body>
</html>
