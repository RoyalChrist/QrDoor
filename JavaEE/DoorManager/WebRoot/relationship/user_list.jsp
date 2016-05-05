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
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/themes/material/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/demo.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/datagrid-detailview.js"></script>
	

  </head>
  
  <body>
			  <div id="tb_user_relationship_list">
			  <a href="javascript:save_user_relationship();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
				<a href="javascript:update_user_relationship();" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
				<a href="javascript:delete_user_relationship();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true">删除</a>
			</div>
  
    	<table id="table_user_relationship_list"></table>
    	<div id="div_user_relationship_temp"></div>
    	<script type="text/javascript">
    	//保存
    	function save_user_relationship()
    	{
    		$('#div_user_relationship_temp').window({  
    			title:'添加用户',
    			href:'<%=basePath%>user/save.jsp',
        	    width:400,    
        	    height:300,    
        	    modal:true   
        	}); 
    		
    	}
    	//更新
    	function update_user_relationship()
    	{
    		var row=$('#table_user_relationship_list').datagrid('getSelected');
    		if(row)
    		{
	    		$('#div_user_relationship_temp').window({  
	    			title:'修改用户信息',
	    			href:'<%=basePath%>user_findbyid.action?id='+row.id,
	        	    width:400,    
	        	    height:300,    
	        	    modal:true   
	        	}); 
    		}
    		else{
    			
    			$.messager.alert('警告','请选择操作行','warning'); 
    		}
    		
    	}
    	//删除方法
    	function delete_user_relationship()
    	{
    		var row=$('#table_user_relationship_list').datagrid('getSelected');
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
		    						timeout:3000,
		    						showType:'slide'
		    					});
		
		    					$('#table_user_relationship_list').datagrid('reload');
	    				}
	    			});
    	         
    			    }    
    			});  
    		}
    		else{
    			
    			$.messager.alert('警告','请选择操作行','warning'); 
    		}
    	}
    	
    	$('#table_user_relationship_list').datagrid({    
    	    url:'<%=basePath%>user_findall.action',
    	    loadMsg:'正在加载....',
    	    rownumbers:true,
    	    singleSelect:true,
    	    toolbar:'#tb_user_relationship_list',
    		
    	    columns:[[    
    	        {field:'ck',title:'选择',width:50,checkbox:true}, 
    	        {field:'loginName',title:'用户名',sortable:true,width:165,align:'center'},
    	    ]],
    	     onDblClickRow:function(rowIndex, rowData)
    	    {
    	    		$('#txt_user1').val(rowData.loginName);
        	    	$('#txt_relationship_user_id').val(rowData.id);
        	    	$('#div_relationship_user_list').window('close');
    	    }    
    	});  
    	</script>
    	
  </body>
</html>
