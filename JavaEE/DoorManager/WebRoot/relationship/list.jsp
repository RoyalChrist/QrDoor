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
			  <div id="tb_relationship_list" style="height:60px; ">
			 	<a href="javascript:save_relationship();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
				<a href="javascript:update_relationship();" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
				<a href="javascript:delete_relationship();" class="easyui-linkbutton" data-options="iconCls:'icon-cut',plain:true">删除</a>
				
				
				<div style="float: right;margin-right: 300px;margin-top: 5px;">
				<a href="javascript:void(0);" title="门牌号，用户名等." class="easyui-tooltip"><input id="ss" name="param" class="easyui-searchbox" style="width:200px;padding-top: 10px" data-options="searcher:qq,prompt:'请输入要查询的内容',plain:true"></input></a>
						
				</div>
				<div>
					<div style="float: left;">
						<a href="javascript:void(0)" id="a_menu_search" class="easyui-menubutton"     
							        data-options="menu:'#mm',iconCls:'icon-filter'">用户名</a>   
							<div id="mm" style="width:150px;"  data-options="onClick:menuHandler">   
							    <div data-options="name:'1',iconCls:'icon-filter'">用户名</div>   
							    <div data-options="name:'2',iconCls:'icon-filter'">门牌号</div>   
							</div>
					</div>
					<div id="div_search_user" style="float: left; margin-left: 50px">
						<input id="txt_search_user" placeholder="请选择用户名" data-options="plain:true"  />
					</div>
					<div id="div_search_door" style="float: left; margin-left: 50px">
						<input id="txt_search_door" placeholder="请选择门牌号" data-options="plain:true"  />
					</div>
					</div>
					
				</div>
				
			<div id="div_relationship_search_user_list"></div>
			<div id="div_relationship_search_door_list"></div>
			<script type="text/javascript"> 
			var flag="search";
			hideallsearchdiv();
			$('#div_search_user').show();
			//下拉菜单选项
			function menuHandler(item){
				var temp=parseInt(item.name);
				switch(temp)
				{
				case 1:hideallsearchdiv();
						$('#div_search_user').show();
						$('#a_menu_search').menubutton( {    
							  text:'用户名',
							  iconCls:'icon-filter'
							}); 
						break;
				case 2:hideallsearchdiv();
						$('#div_search_door').show();
						$('#a_menu_search').menubutton( {    
							  text:'门牌号',
							  iconCls:'icon-filter'
							});
						break;
				}
			}
			//隐藏所有的查询div
			function hideallsearchdiv()
			{	
				$('#div_search_user').hide();
				$('#div_search_door').hide();
			}
			///////
			$('#txt_search_user').click(function(){
	    		
	    		$('#div_relationship_search_user_list').window({  
	    			title:'选择用户',
	    			href:'<%=basePath%>relationship/user_list.jsp',
	        	    width:240,    
	        	    height:300,    
	        	    modal:true   
	        	}); 
	    		
	    	});
			$('#txt_search_door').click(function(){
			    		
			    $('#div_relationship_search_door_list').window({  
			    	title:'选择门禁',
			    	href:'<%=basePath%>relationship/door_list.jsp',
			        width:240,    
			       	height:300,    
			       	modal:true   
			     }); 
			    		
			});
			function qq(value,name){ 
				if(value=="")
				{
					$.messager.alert('警告','请输入查询内容','warning');
					return;
				}
				$('#table_relationship_list').datagrid('load',{param:value,flag:'like'});
			} 
			
			</script> 
			
  
    	<table id="table_relationship_list"></table>
    	<div id="div_relationship_temp"></div>
    	<script type="text/javascript">
    	//保存
    	function save_relationship()
    	{
    		$('#div_relationship_temp').window({  
    			title:'添加权限',
    			href:'<%=basePath%>relationship/save.jsp',
        	    width:360,    
        	    height:300,    
        	    modal:true   
        	}); 
    		
    	}
    	//更新
    	function update_relationship()
    	{
    		var row=$('#table_relationship_list').datagrid('getSelected');
    		if(row)
    		{
	    		$('#div_relationship_temp').window({  
	    			title:'修改权限',
	    			href:'<%=basePath%>relationship_findbyid.action?id='+row.id,
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
    	function delete_relationship()
    	{
    		var row=$('#table_relationship_list').datagrid('getSelected');
    		if(row)
    		{
    			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
    			    if (r){    	
		    			$.ajax({
		    				url:'<%=basePath%>relationship_delete.action',
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
		
		    					$('#table_relationship_list').datagrid('reload');
		    					
		    				}
		    				
		    			});
    			    }
    			});
    		}
    		else{
    			
    			$.messager.alert('警告','请选择操作行','warning'); 
    		}
    	}
    	
    	$('#table_relationship_list').datagrid({    
    	    url:'<%=basePath%>relationship_findall.action',
    	    loadMsg:'正在加载....',
    	    rownumbers:true,
    	    singleSelect:true,
    	    pagination:true,
    	    toolbar:'#tb_relationship_list',
    		
    	    columns:[[    
    	        {field:'ck',title:'选择',width:50,checkbox:true}, 
    	        {field:'loginName',title:'用户名',sortable:true,width:150,align:'center',
    	        	formatter:function(value,row,index)
    	        	{
    	        		if(row.user)
    	        			{
    	        			return row.user.loginName;
    	        			}
    	        	}
    	        },
    	        {field:'doorNumber',title:'门牌号',width:100,align:'center',
    	        	formatter: function(value,row,index){
    					if(row.door)
    					{
    						return row.door.doorNumber;
    					}
    	        	}
    	        },
    	        {field:'info',title:'详情',width:300,align:'center'},
    	    ]]
    	   
    	});  
    	</script>
  </body>
</html>
