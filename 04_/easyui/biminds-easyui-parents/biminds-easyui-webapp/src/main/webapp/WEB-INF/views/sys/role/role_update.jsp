<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
	<!--弹出窗口开始-->
	<div>
		<form id="roleForm" method="post">
			<input type="hidden" name="roleId" id="roleId"/>
			<input type="hidden" name="roleOldName" id="roleOldName"/>
			<div class="ap-form-container">
				<ul>
					<li>
						<div>
							<label>角色名称：</label> 
							<input id="roleName" name="roleName" class="easyui-textbox" data-options="required:true,missingMessage:'角色名称不能为空'"
								style="width: 180px; height: 25px" validtype="length[1,50]" invalidMessage="角色名称不能超过50个字符"/>
						</div>
					</li>
					
					<li>
						<div>
						    <label>状态：</label>
							<input type="radio" name="enabled" value="10" checked="checked">启用</input>
							<input type="radio" name="enabled" value="20">停用</input>
						</div>
					</li>
					
				</ul>
				<ul>
					<li class="ap-remark">
						<label>角色描述：</label>
						<input class="easyui-textbox"
										data-options="multiline:true" name="roleDesc"
										style="width: 300px; height: 100px" validtype="length[1,100]" invalidMessage="不能超过100个字符"/>
					</li>
				</ul>
			</div>
		</form>
	</div>
	<p></p>
	<div class="dlg-buttons dialog-button"  style="position: relative;margin-top: 203px;"> 
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" id ="roleSubmit">提交</a> 
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="EasyUiUtil.closeWindow('roleWin')" >关闭</a>
	</div>
		
	<!--弹出窗口结束-->
	<script>
	
	var $role = {
			
			roleUpdateUrl : 'sys/role/update',
			roleCheckUrl : 'sys/role/check',
			
			init : function(){
				$('#roleSubmit').click($role.updateRole);
			},
			
			checkRoleIsExist : function(roleName){
				var isExist = false;
				$.ajax({
					type : "POST",
					async : false,
					url: this.roleCheckUrl,
					dataType:"json",
					data:{'roleName':roleName},
					success:function(result)
					{
						if(result){
							isExist = true;
						}
					}
				});
				return isExist;
			},
			
			updateRole : function() {
				var r = $('#roleForm').form('validate');
				if(!r) {
					return false;
				}
				var operate;
				if(isNotEmpty($('#roleId').val())){
					operate = 'update';
				}else{
					operate = 'create';
				}
				var oldName = $('#roleOldName').val();
				var roleName = $('#roleName').textbox('getValue');
				
				if(operate == 'create' || (operate == 'update'  && (oldName != roleName))){
					if($role.checkRoleIsExist(roleName)){
						$.messager.alert('提示',"该角色已存在",'error');
						return false;
					}
				}
				
				EasyUiUtil.showLoading("加载中..");
				$.post($role.roleUpdateUrl + '?operate=' + operate,EasyUiUtil.form2Json('roleForm'),function(data){
					EasyUiUtil.closeLoading();
					$('#roleWin').window('close');
					$('#roleTable').datagrid('reload');  
				});
			}
	};
	
	$role.init();
	
	</script>
