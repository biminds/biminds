<%@ page language="java" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Language" content="zh-cn" />
<meta http-equiv="Cache-Control" content="no-store" />
<title>大连百米科技有限公司运营管理平台</title>
<base href="${basePath}/" />
<link rel="stylesheet" href="${ctx }/styles/common/comm.css">
<link rel="stylesheet" href="${ctx}/componments/layui/css/layui.css" />
<script src="${ctx}/componments/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/componments/layui/layui.all.js"></script>
<script src="${ctx}/js/index.js"></script>
<script type="text/javascript">
	var base = '${ctx}';
</script>
</head>
<body>
	<!-- begin -->
	<div id="login">
		<div class="wrapper">
			<div class="login">
				<form action="#" method="post" class="container offset1 loginform">
					<div id="owl-login">
						<div class="hand"></div>
						<div class="hand hand-r"></div>
						<div class="arms">
							<div class="arm"></div>
							<div class="arm arm-r"></div>
						</div>
					</div>
					<div class="pad">
						<div class="control-group">
							<div class="controls">
								<label for="userName" class="control-label fa fa-envelope"></label>
								<input id="userName" type="text" name="userName"
									placeholder="userName" tabindex="1" autofocus="autofocus"
									class="form-control input-medium">
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<label for="password" class="control-label fa fa-asterisk"></label>
								<input id="password" type="password" name="password"
									placeholder="Password" tabindex="2"
									class="form-control input-medium">
							</div>
						</div>
					</div>
					<span id="loading" style="display:none;"><img src="${ctx}/images/common/login/loading.gif"/></span>
					<div class="form-actions">
						<a href="javascript:void(0)" tabindex="5" class="btn pull-left btn-link text-muted">Forgot
							password?</a><a href="javascript:void(0)" tabindex="6" class="btn btn-link text-muted">Sign
							Up</a>
						<button type="button" tabindex="4" class="btn btn-primary" id="loginBtn">Login</button>
					</div>
				</form>
				<div
					style="text-align: center; clear: both; font-size: 12px; color: #000000; line-height: 50px; margin-top: inherit;">@2017&nbsp;&nbsp;&nbsp;大连百米科技有限公司</div>
			</div>
		</div>
	</div>
</body>
</html>