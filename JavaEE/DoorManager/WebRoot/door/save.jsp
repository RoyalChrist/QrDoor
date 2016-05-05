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
    		<form action="" method="post" id="form_door_save" enctype="multipart/form-data">
    			<table style="width: 300px"  cellpadding="5px">
    				<tr>
    					<td style="width: 135px">门牌号:</td>
    					<td class="righttd"><input name="door.doorNumber" placeholder="门牌号" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写门牌号'" /></td>
    				</tr>
    				<tr>
    					<td>开门指令:</td>
    					<td class="righttd"><input name="door.openCode" placeholder="开门指令" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写开门指令'"  /></td>
    				</tr>
    				<tr>
    				<tr>
    					<td>节点编号:</td>
    					<td class="righttd"><input name="door.nodeNumber" placeholder="节点编号" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写节点编号'"  /></td>
    				</tr>
    				<tr>
    					<td>IP地址:</td>
    					<td class="righttd"><input name="door.ip" placeholder="IP地址" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写IP地址',validType:'ip',invalidMessage:'请输入正确的ip地址'"  /></td>
    				</tr>
    				<tr>
    					<td>端口号:</td>
    					<td class="righttd"><input name="door.port" placeholder="端口号" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写端口号',validType:'port',invalidMessage:'请输入正确的端口号'"  /></td>
    				</tr>
    				<tr>
    					<td style="width: 135px">节点信息:</td>
    					<td class="righttd"><textarea name="door.info" placeholder="节点信息" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写节点信息'" ></textarea></td>
    				</tr>
    				
    				<tr>
    					<td colspan="2"><a id="btn_submit_save_door" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保 存</a></td>
    				</tr>
    			</table>
    		</form>
    	</div>	
    	<div id="div_door_user_list"></div>
    	
    	<script>
    	$.extend($.fn.validatebox.defaults.rules, {
	    	ip : {// 验证IP地址 
        validator : function(value) { 
            return /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/i.test(value); 
        }, 
        message : 'IP地址格式不正确' 
        },
        port : {// 验证端口号
        validator : function(value) { 
            return /^([0-9]|[1-9]\d|[1-9]\d{2}|[1-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$/i.test(value); 
        }, 
        message : '端口号必须是0到65535之间的整数' 
    }
	   });
    	</script>
    	
    	<script type="text/javascript">
    	flag="save";
    		$('#btn_submit_save_door').click(function(){
    			$.messager.progress();	// 显示进度条
    			$('#form_door_save').form('submit', {
    				url:'<%=basePath%>door_save.action',
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
    					$('#div_door_temp').window('close');
    					$('#table_door_list').datagrid('reload');
    					
    				}
    			});


    			
    		});
    	
    	</script>
    	
  </body>
</html>
