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
			  <div id="tb_qrcode_list" style="height:30px; ">
			 	<a href="javascript:save_qrcode();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
				<a href="javascript:update_qrcode();" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
				<a href="javascript:delete_qrcode();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true">删除</a>
				
				
				<div style="float: right;margin-right: 300px;margin-top: 5px;">
				<a href="javascript:void(0);" title="门牌号，信息码，用户等." class="easyui-tooltip"><input id="ss" name="param" class="easyui-searchbox" style="width:200px;padding-top: 10px" data-options="searcher:qq,prompt:'请输入查询的内容',plain:true"></input></a>
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
				$('#table_qrcode_list').datagrid('load',{param:value,flag:'like'});
			} 
			
			</script> 
			
  
    	<table id="table_qrcode_list"></table>
    	<div id="div_qrcode_temp"></div>
    	<script type="text/javascript">
    	//保存
    	function save_qrcode()
    	{
    		$('#div_qrcode_temp').window({  
    			title:'添加二维码',
    			href:'<%=basePath%>qrcode/save.jsp',
        	    width:320,    
        	    height:200,    
        	    modal:true   
        	}); 
    		
    	}
    	//更新
    	function update_qrcode()
    	{
    		var row=$('#table_qrcode_list').datagrid('getSelected');
    		if(row)
    		{
	    		$('#div_qrcode_temp').window({  
	    			title:'修改二维码信息',
	    			href:'<%=basePath%>qrcode_findbyid.action?id='+row.id,
	        	    width:320,    
	        	    height:200,    
	        	    modal:true   
	        	}); 
    		}
    		else{
    			
    			$.messager.alert('警告','请选择操作行','warning'); 
    		}
    		
    	}
    	//删除方法
    	function delete_qrcode()
    	{
    		var row=$('#table_qrcode_list').datagrid('getSelected');
    		if(row)
    		{
    			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
    			    if (r){    	
		    			$.ajax({
		    				url:'<%=basePath%>qrcode_delete.action',
		    				type:'post',
		    				data:{id:row.id},
		    				success:function()
		    				{
		    					$.messager.show({
		    						title:'提示',
		    						msg:'删除成功！！',
		    						timeout:2000,
		    						showType:'slide'
		    					});
		
		    					$('#table_qrcode_list').datagrid('reload');
		    					
		    				}
		    				
		    			});
    			    }
    			});
    		}
    		else{
    			
    			$.messager.alert('警告','请选择操作行','warning'); 
    		}
    	}
    	
    	$('#table_qrcode_list').datagrid({    
    	    url:'<%=basePath%>qrcode_findall.action',
    	    loadMsg:'正在加载....',
    	    rownumbers:true,
    	    singleSelect:true,
    	    pagination:true,
    	    toolbar:'#tb_qrcode_list',
    		
    	    columns:[[    
    	        {field:'ck',title:'选择',width:50,checkbox:true}, 
    	         {field:'door',title:'门牌号',sortable:true,width:100,align:'center',
    	        	formatter: function(value,row,index){
    					if(row.door)
    					{
    						return row.door.doorNumber;
    					}
    					else
    						{
    						return '未知门牌号';
    						}
    	        	}
    	        },
    	        {field:'code',title:'信息码',width:150,sortable:true,align:'center'},
    	        {field:'randnumber',title:'随机数',width:200,align:'center'},
    	        {field:'createTime',title:'创建时间',width:160,sortable:true,align:'center'},
    	        {field:'expTime',title:'过期时间',width:160,sortable:true,align:'center'},
    	        {field:'isUsed',title:'是否过期',width:80,sortable:true,align:'center'},
    	        {field:'userScan',title:'扫描用户',width:100,sortable:true,align:'center'},
    	    ]]
    	});  
    	
    	
    	</script>
    	
  </body>
</html>
