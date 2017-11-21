<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<!-- 标签 -->
<jsp:include page="/WEB-INF/views/common/tag.jsp" />
<!-- 头部 -->
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<!-- 左侧菜单 -->
<jsp:include page="/WEB-INF/views/common/left.jsp" />

	<div data-options="region:'center'" class="ap-content">
		<div class="ap-right-top"> <a href="javascript:void(0);">系统管理</a> <span>&nbsp;&gt;</span> <a href="javascript:void(0);">角色管理</a></div>
		<div class="easyui-panel" style="width:100%;height:auto;padding:5px; margin-bottom:10px;" title="角色查询" collapsible="true">		
		 <form id="searchForm" method="post">
			  <div class="ap-form-container">
              	<ul>
              		<li>
              			<label>角色名称：</label>
			          <input class="easyui-textbox" name="roleName" data-options="" style="width: 180px; height: 25px"/>
                	</li>               	
                </ul>
               <div class="wrapper"></div>
		        <div class="ap-form-btn"> 
		        	<a href="javascript:void(0)" class="easyui-linkbutton" id="roleSearch">查询</a>&nbsp; 
		        	<a href="javascript:void(0)" class="easyui-linkbutton" id="roleReset">重置</a>
		        </div>
              </div>	
          </form>
          </div>
          
        <table id="roleTable" class="easyui-datagrid" style="width:100%;height:auto;"
			data-options="
				fitColumns:true,
				striped : true,
				nowrap : false,
				rownumbers : false,
				pagination: true,
				singleSelect:true,
				remoteSort:false,
				title:'角色列表',
				url : 'sys/role/list',
				idField : 'id',
		    	loadMsg : '数据装载中......',
		    	toolbar : '#roleToolbar',
		    	onLoadSuccess: function () {  
		      		$('#roleTable').datagrid('clearSelections');
		     	}
				">  
				
            <thead>
              <tr>
                <th data-options="field:'roleName',width:30" align="center">岗位名称</th>
                <th data-options="field:'roleDesc',width:50" align="center">备注</th>
                <th data-options="field:'enabled',width:50,formatter:RoleModel.formatStatus" align="center">状态</th> 
                
              </tr>
             </thead>
        </table>
          
         <!-- 工具栏 -->
         <div id="roleToolbar"  class="ap-toolbar">
         	<a href="javascript:void(0);" class="easyui-linkbutton oper" data-options="iconCls:'icon-add',plain:'true'" operate = 'create'>新建</a> 
         	<a href="javascript:void(0);" class="easyui-linkbutton oper" data-options="iconCls:'icon-edit',plain:'true'" operate = 'update'>编辑</a> 
         	<a href="javascript:void(0);" class="easyui-linkbutton oper" data-options="iconCls:'icon-cog',plain:'true'" operate = 'allocate'>分配权限</a> 
         </div>
         
         <!-- 子窗体 -->
		 <div id="roleWin" 
		      data-options="iconCls:'icon-save',modal:true,shadow:false,minimizable:false,cache:false,maximizable:false,collapsible:false,resizable:false"
			  style="padding-top:35px;"></div> 
  
 </div>
          
<script type="text/javascript" src="js/sys/role/role.js"></script>
<script type="text/javascript">
	RoleModel.init();
</script>
</body>
</html>
