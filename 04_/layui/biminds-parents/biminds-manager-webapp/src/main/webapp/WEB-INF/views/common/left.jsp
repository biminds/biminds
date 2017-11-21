<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>

<div class="layui-side layui-bg-black">
	<div class="layui-side-scroll">
		<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
		<ul class="layui-nav layui-nav-tree" lay-filter="test">
			<c:if test="${not empty resourceMap or fn:length(resourceMap) gt 0 }">
				<c:forEach items="${resourceMap }" var="auth">
					<li class="layui-nav-item layui-nav-itemed">
						<a href="javascript:;">${auth.key }</a>
						<c:if test="${not empty auth.value or fn:length(auth.value) gt 0 }">
							<dl class="layui-nav-child">
								<c:forEach items="${auth.value }" var="child">
									<dd>
									<c:set var="url" value="${child.url }" />
									<c:choose>
										<c:when test="${fn:contains(url, '?') }">
											<a href="${child.url}&menuId=${child.resourceId}"
													id="menu_${child.id}">${child.resourceName}</a>
										</c:when>
										<c:otherwise>
											<a class="" href="${child.url}?menuId=${child.resourceId}"
													id="menu_${child.resourceId}">${child.resourceName}</a>
										</c:otherwise>
									</c:choose>
									</dd>
								</c:forEach>
							</dl>
						</c:if>
					</li>
				</c:forEach>
			</c:if>
		</ul>
	</div>
</div>


<!--左边树状菜单start-->
<script type="text/javascript">
//JavaScript代码区域
layui.use('element', function(){
	var element = layui.element;
});
</script>
<!--左边树状菜单start-->
<script type="text/javascript">
$(function(){
	//菜单选中
	var menuId = 'menu_${menuId}';
	// 遍历所有菜单
	// 比较用户选择菜单ID是否一直
	$('dd a').each(function(){
		// 如果菜单是用户选择的菜单
		// 为当前菜单添加选中样式
		if($(this).attr("id") == menuId){
			$(this).parent().addClass('layui-this');
			// 为其父类添加打开样式
			$(this).parent().parent().parent().addClass('layui-nav-itemed');
		}else{
			// 取消选中样式
			$(this).parent().removeClass('layui-this');
			// 为其父类添加打开样式
			$(this).parent().parent().parent().removeClass('layui-nav-itemed');
		}
	});
});
</script>
<!---左边树状菜单end-->
