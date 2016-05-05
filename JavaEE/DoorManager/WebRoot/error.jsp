<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<title>登录失败</title>
<meta content="text/html; charset=utf-8" http-equiv=Content-Type>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/datepicker3.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
</head>
<body>

	<div class="container" style="margin-top: 100px;">
		<div class="row">
			<!-- form: -->
			<div class="col-xs-12 col-xs-offset-1 col-sm-8 col-sm-offset-0 col-md-12 col-md-offset-1">
				<div class="col-lg-7 col-lg-offset-2">
				<div class="login-panel panel panel-default">
						<div class="panel-heading">
							<h2>登陆失败！</h2>
						</div>
						<div class="panel-body">
							<form id="defaultForm" method="post" class="form-horizontal">
								<fieldset>
										<h2>用户名或密码错误！</h2>
								</fieldset>
							</form>
						</div>
					</div>
					</div>
			</div>
			<!-- :form -->
		</div>
	</div>
</body>
</html>
