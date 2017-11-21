<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<jsp:include page="/WEB-INF/views/common/tags.jsp" />
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<jsp:include page="/WEB-INF/views/common/left.jsp" />
<!-- 页面主体部分展示 -->
<div class="layui-tab layui-tab-brief site-demo-table">
	<ul class="layui-tab-title site-demo-title">
		<li class="layui-this">用户列表</li>
	</ul>
	<div class="layui-body">
		<!-- 内容主体区域 -->
		<div style="padding: 15px;">
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
//注意：选项卡 依赖 element 模块，否则无法进行功能性操作
layui.use('element', function(){
  var element = layui.element;
});
</script>
</html>
