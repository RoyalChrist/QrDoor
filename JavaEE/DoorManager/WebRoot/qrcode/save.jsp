<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'save.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>

  <body>
  <style>
  	td{
  		text-align: right;
  	}
  .righttd{
  	text-align: left;
  }
  </style>
  		<div style="margin-top: 30px;height: 110px;text-align: center;">
    		<form action="" method="post" id="form_qrcode_save" enctype="multipart/form-data">
    			<table style="width: 300px" cellpadding="5px;">
    				<tr>
    					<td>门牌号:</td>
    					<td class="righttd"><input id="txt_door" placeholder="请选择门牌号" class="easyui-validatebox"  data-options="required:true,missingMessage:'请选择门牌号'" />
    						<input type="hidden" id="txt_qrcode_door_id" name="qrcode.door.id">
    					</td>
    				</tr>
    				<tr>
    					<td>信息码:</td>
    					<td class="righttd"><input name="qrcode.code" placeholder="信息码" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写二维码的信息码'"  /></td>
    				</tr>
    				<tr>
    					<td colspan="2"><a id="btn_submit_save_qrcode" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保 存</a></td>
    				</tr>
    			</table>
    		</form>
    	</div>	
    	<div id="div_qrcode_door_list"></div>
    	
    	<script type="text/javascript">
    	flag="save";
    	$('#txt_door').click(function(){
    			$('#div_qrcode_door_list').window({  
    			title:'选择门牌号',
    			href:'<%=basePath%>qrcode/door_list.jsp',
        	    width:240,    
        	    height:300,    
        	    modal:true   
        	}); 
    	});
    	
    		$('#btn_submit_save_qrcode').click(function(){
    			$.messager.progress();	// 显示进度条
    			$('#form_qrcode_save').form('submit', {
    				url:'<%=basePath%>qrcode_save.action',
    				onSubmit: function(){
    					var isValid = $(this).form('validate');
    					if (!isValid){
    						$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
    					}
    					return isValid;	// 返回false终止表单提交
    				},
    				success: function(){
    					$.messager.progress('close');	// 如果提交成功则隐藏进度条
    					//把开关 改为 搜索
    					flag="search";
    					$.messager.show({
    						title:'提示',
    						msg:'添加成功！！',
    						timeout:3000,
    						showType:'slide'
    					});
    					$('#div_qrcode_temp').window('close');
    					$('#table_qrcode_list').datagrid('reload');
    					
    				}
    			});


    			
    		});
    	
    	</script>
    	
  </body>
</html>
