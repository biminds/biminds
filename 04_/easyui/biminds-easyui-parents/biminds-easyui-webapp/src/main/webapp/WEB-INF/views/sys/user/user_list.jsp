<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>

<jsp:include page="/WEB-INF/views/common/tag.jsp" />

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<!-- 左侧菜单 -->
<jsp:include page="/WEB-INF/views/common/left.jsp" />

	<div data-options="region:'center'" class="ap-content">
		<div class="ap-right-top"> <a href="javascript:void(0);">系统管理</a> <span>&nbsp;&gt;</span> <a href="javascript:void(0);">用户管理</a></div>
		<div class="easyui-panel" style="width:100%;height:auto;padding:5px; margin-bottom:10px;" title="用户信息查询" collapsible="true">		
		 <form id="searchForm" method="post">
			  <div class="ap-form-container">
              	<ul>
                	
              	<ul>
              		<li>
               			<label>用户名：</label>
              		    <input class="easyui-textbox" name="userName" data-options="" style="width: 180px; height: 25px;"/>
                	</li>
                	
                    <li>
		                <label>姓名：</label>
		                <input class="easyui-textbox" name="realName" data-options="" style="width: 180px; height: 25px;"/>
	                </li>
	            </ul>
	              
               <div class="wrapper"></div>
            
		        <div class="ap-form-btn"> 
		        	<a href="javascript:void(0)" class="easyui-linkbutton" id="userSearch">查询</a>&nbsp; 
		        	<a href="javascript:void(0)" class="easyui-linkbutton" id="userReset">重置</a>
		        </div>
          
              </div>	
          </form>
              
              </div>
              
      <!--tabs_megs end-->
      
        <table id="userTable" class="easyui-datagrid" style="width:100%;height:auto;"
			data-options="
				fitColumns:true,
				striped : true,
				nowrap : false,
				rownumbers : true,
				pagination: true,
				singleSelect:true,
				remoteSort:false,
				title:'用户列表',
				url : 'sys/user/list',
				idField : 'id',
		    	loadMsg : '数据装载中......',
		    	toolbar : '#apUserToolbar',
		    	pageSize: 20,
        		pageList: [10,20,50,100],
        		onLoadSuccess: function () {  
		      		$('#userTable').datagrid('clearSelections');
		     	}
				">  
				
            <thead>
              <tr>
                <th data-options="field:'userName',width:30,align:'center'" >用户名</th>
                <th data-options="field:'realName',width:50,align:'center'">姓名</th>
                <th data-options="field:'roleName',width:90,align:'center'">所属岗位</th>
                <th data-options="field:'enabled',width:90,formatter:UserModel.formatEnabled" align="center">是否有效</th>
              </tr>
             </thead>
        </table>
          
         <!-- 工具栏 -->
        <div id="apUserToolbar"  class="ap-toolbar">
         	<a href="javascript:void(0);" class="easyui-linkbutton oper" data-options="iconCls:'icon-add',plain:'true'" operate = 'create'>新建</a> 
         	<a href="javascript:void(0);" class="easyui-linkbutton oper" data-options="iconCls:'icon-edit',plain:'true'" operate = 'update'>编辑</a>
         	<a href="javascript:void(0);" class="easyui-linkbutton oper" data-options="iconCls:'icon-cog',plain:'true'" operate = 'repass'>重置密码</a>
         </div>
         
         <!-- 子窗体 -->
		 <div id="userWin" 
		      data-options="iconCls:'icon-save',modal:true,shadow:false,minimizable:false,cache:false,maximizable:false,collapsible:false,resizable:false"
			  style="padding-top:35px;"></div> 
						
 </div>
          
<!-- 用户自定义JS -->
<script type="text/javascript" src="js/sys/user/user.js"></script>
<script type="text/javascript">
	UserModel.init();
</script>
</body>
</html>
