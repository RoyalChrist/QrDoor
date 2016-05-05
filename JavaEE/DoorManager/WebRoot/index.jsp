<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>QrDoor门禁后台管理系统</title>
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
<style type="text/css">
a {
	text-decoration: none;
	font-size: 16px;
	font-weight: bold;
}
</style>

<script type="text/javascript">
	function openwindow(title, height, width, url) {
		$('#div_temp_window').window({
			href : url,
			title : title,
			width : width,
			height : height,
			modal : true
		});

	}
</script>

</head>

<body class="easyui-layout">
	<!-- 顶部 -->
	<div data-options="region:'north'" style="height:98px;background-image: url('<%=basePath%>img/main_bg.jpg');">
		<div style="text-align:center;margin-top:30px;">
			<h1 style="font-family:Microsoft YaHei;color: red">QrDoor 门 禁 后 台 管 理 系 统</h1>
		</div>
		<div style="float: right;margin-top:-10px;margin-right: 30px">
			Welcome, <b><font color="red" size="3px">${admin.loginName }</font></b>
			|<a href="<%=basePath%>admin_loginout.action" style="font-size: 12px;font-family:Microsoft YaHei;color:#00BBEE;">注销</a>
		</div>
	</div>
	<!-- 西侧 导航 -->
	<div data-options="region:'west',split:true,iconCls:'icon-tip'" title="操作菜单"  style="width:200px;">
		<div id="aa" class="easyui-accordion" data-options="fit:true">
			<div title="门禁管理" data-options="iconCls:'icon-more'"
				style="overflow:auto;padding:10px;">
				<div style="height: 20px;line-height: 20px; text-align: center;">
					<a style="color:red;"
						href="javascript:addtab('快速添加','<%=basePath%>door/save.jsp');">快速添加</a>
				</div>
				<hr style="height:1px;border:none;border-top:1px solid #6495ED;" />
				<div style="height: 20px;line-height: 20px; text-align: center;">
					<a style="color:#00BBEE;"
						href="javascript:addtab('门禁列表','<%=basePath%>door/list.jsp');">查看门禁列表</a>
				</div>
				<hr style="height:1px;border:none;border-top:1px solid #6495ED;" />
			</div>
			<div title="用户管理" data-options="iconCls:'icon-more'"
				style="overflow:auto;padding:10px;">
				<div style="height: 20px;line-height: 20px; text-align: center;">
					<a style="color:red;"
						href="javascript:addtab('添加用户','<%=basePath%>user/save.jsp');">快速添加</a>
				</div>
				<hr style="height:1px;border:none;border-top:1px solid #6495ED;" />
				<div style="height: 20px;line-height: 20px; text-align: center;">
					<a style="color:#00BBEE;"
						href="javascript:addtab('用户列表','<%=basePath%>user/list.jsp');">查看用户列表</a>
				</div>
				<hr style="height:1px;border:none;border-top:1px solid #6495ED;" />
			</div>
			<div title="权限管理" data-options="iconCls:'icon-more'"
				style="overflow:auto;padding:10px;">
				<div style="height: 20px;line-height: 20px; text-align: center;">
					<a style="color:#00BBEE;"
						href="javascript:addtab('权限管理','<%=basePath%>relationship/list.jsp');">权限管理</a>
				</div>
				<hr style="height:1px;border:none;border-top:1px solid #6495ED;" />
			</div>
			<div title="二维码管理" data-options="iconCls:'icon-more'"
				style="overflow:auto;padding:10px;">
				<div style="height: 20px;line-height: 20px; text-align: center;">
					<a style="color:red;"
						href="javascript:addtab('添加二维码','<%=basePath%>qrcode/save.jsp');">快速添加</a>
				</div>
				<hr style="height:1px;border:none;border-top:1px solid #6495ED;" />
				<div style="height: 20px;line-height: 20px; text-align: center;">
					<a style="color:#00BBEE;"
						href="javascript:addtab('二维码列表','<%=basePath%>qrcode/list.jsp');">查看二维码列表</a>
				</div>
				<hr style="height:1px;border:none;border-top:1px solid #6495ED;" />
			</div>
			<div title="日志记录" data-options="iconCls:'icon-more'"
				style="overflow:auto;padding:10px;">
				<div style="height: 20px;line-height: 20px; text-align: center;">
					<a style="color:#00BBEE;"
						href="javascript:addtab('日志列表','<%=basePath%>log/list.jsp');">查看用户日志</a>
				</div>
				<hr style="height:1px;border:none;border-top:1px solid #6495ED;" />
			</div>
		</div>


	</div>
	<!-- 主要内容 -->
	<div data-options="region:'center'"
		style="padding:5px;background-image: url('<%=basePath%>img/main_bg.jpg');background-repeat:no-repeat;background-size:1400px 800px;">
		<div id="tt" class="easyui-tabs" data-options="fit:true">
			<div title="主页" data-options="tools:'#p-tools',iconCls:'icon-man',closable:true" style="padding:20px;">
				<div style="text-align:center;margin-top:120px;">
					<h1 style="font-family:Microsoft YaHei;color: #00BBEE;">
						&nbsp;&nbsp;&nbsp;请点击左侧“操作菜单”<br>进入相应的操作窗口进行操作
					</h1>
				</div>
			</div>
		</div>


	</div>
	<div id="div_temp_window"></div>
	<script type="text/javascript">
		function addtab(title, url) {
			if ($('#tt').tabs('exists', title)) {
				$('#tt').tabs('select', title);

			} else {
				$('#tt').tabs('add', {
					title : title,
					href : url,
					closable : true,

				});
			}

		}
	</script>

</body>
</html>
