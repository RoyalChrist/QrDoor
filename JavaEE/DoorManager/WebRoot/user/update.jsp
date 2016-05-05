<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'update.jsp' starting page</title>
    
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
    		<form action="" method="post" id="form_user_update" enctype="multipart/form-data">
    			<input type="hidden" name="user.id" value="${user.id }">
    			<table style="width: 300px" cellpadding="5px">
    				<tr>
    					<td style="width: 135px">用户名:</td>
    					<td class="righttd"><input name="user.loginName" placeholder="用户名" value="${user.loginName }" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写用户名'" /></td>
    				</tr>
    				<tr>
    					<td>真实姓名:</td>
    					<td class="righttd"><input name="user.realName" placeholder="真实姓名" value="${user.realName }" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写真实姓名'" /></td>
    				</tr>
    				<tr>
    					<td>手机号码:</td>
    					<td class="righttd"><input name="user.mobilephone" placeholder="手机号码" class="easyui-validatebox" value="${user.mobilephone }" data-options="required:true,validType:'mobile',missingMessage:'请填写手机号码'"  /></td>
    				</tr>
    				<tr>
    					<td style="width: 135px">密码:</td>
    					<td class="righttd"><input name="user.password" placeholder="密码" class="easyui-validatebox" value="${user.password }" data-options="required:true,missingMessage:'请填写密码'" /></td>
    				</tr>
    				<tr>
    					<td style="width: 135px">IMEI:</td>
    					<td class="righttd"><input name="user.imei" placeholder="IMEI" class="easyui-validatebox" value="${user.imei }" data-options="required:true,missingMessage:'请填写IMEI'" /></td>
    				</tr>
    				
    				<tr>
    					<td colspan="2"><a id="btn_submit_update_user" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保 存</a></td>
    				</tr>
    			</table>
    		</form>
    	</div>	
    	
    	<script>
    	$.extend($.fn.validatebox.defaults.rules, {
	    	mobile : {// 验证手机号码 
	        validator : function(value) { 
	            return /^(13|15|18)\d{9}$/i.test(value); 
	        }, 
	        message : '手机号码格式不正确' 
	    }
	   });
    	</script>
    	
    	<script type="text/javascript">
    		$('#btn_submit_update_user').click(function(){
    			$.messager.progress();	// 显示进度条
    			$('#form_user_update').form('submit', {
    				url:'<%=basePath%>user_update.action',
    				onSubmit: function(){
    					var isValid = $(this).form('validate');
    					if (!isValid){
    						$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
    					}
    					return isValid;	// 返回false终止表单提交
    				},
    				success: function(){
    					$.messager.progress('close');	// 如果提交成功则隐藏进度条
    					$.messager.show({
    						title:'提示',
    						msg:'修改成功！！',
    						timeout:2000,
    						showType:'slide'
    					});
    					$('#div_user_temp').window('close');
    					$('#table_user_list').datagrid('reload');
    					
    				}
    			});


    			
    		});
    	
    	</script>
    	
  </body>
</html>
