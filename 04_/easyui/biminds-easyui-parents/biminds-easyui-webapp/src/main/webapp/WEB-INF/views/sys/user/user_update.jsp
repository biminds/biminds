<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
	<!--弹出窗口开始-->
	<div>
		<form id="userForm" method="post">
			<input type="hidden" name="userId" id="userId"/>
			<input type="hidden" name="userOldName" id="userOldName">
					<div style="padding:10px" title="用户信息">
				        <div class="ap-form-container">
							<ul>
								<li>
									<div>
										<label>用户名：</label> 
										<c:if test="${operate != 'update' }">
											<input id="userName" name="userName" class="easyui-textbox" data-options="required:true,missingMessage:'用户名不能为空'"
											style="width: 180px; height: 25px" validtype="length[1,20]" invalidMessage="用户名不能超过20个字符" />
										</c:if>
										<c:if test="${operate == 'update' }">
											<input name="userName" id="userName" class="easyui-textbox"  style="width: 180px; height: 25px" readonly="readonly"/>
										</c:if>
									</div>
									
									<div>
										<label>姓名：</label> 
										<input name="realName" class="easyui-textbox" data-options="required:true,missingMessage:'姓名不能为空'"
											style="width: 180px; height: 25px" validtype="length[1,6]" invalidMessage="姓名不能超过6个字符"/>
									</div>
									
									<c:if test="${operate != 'update' }">
										<div>
											<label>密码：</label> 
											<input name="password" type="password" class="easyui-textbox" data-options="required:true,missingMessage:'密码不能为空'"
												style="width: 180px; height: 25px" validtype="length[1,128]" invalidMessage="密码不能超过128个字符"/>
										</div>
									</c:if>
									
									<div>
										<label>是否有效：</label> 
										<input name="enabled" type="radio" checked="checked" value="10"/>是&nbsp;<input name="enabled" type="radio" value="20"/>否
									</div>
									
									
									</li>
									<li>
									<div class="ap-div">
										<label>角色：</label> 
										<div style="margin-left:104px;margin-top:-11px;">
											<div id="roleId" style="height: 200px; width: 180px;"
											title="角色列表" class="easyui-datalist" name="roleId"
											data-options="editable:false,singleSelect: false,checkbox: true,
												required:true,missingMessage:'角色不能为空',
							                    selectOnCheck:true,
												checkOnSelect:true,
												url: 'sys/role/all',
												valueField: 'roleId',
												textField: 'roleName',
												onLoadSuccess: function () { 
							                         var operate = '${operate}';
							                         if(operate == 'update'){
							                         		var data = '${roles}';
								                         	if (data.length > 0) {
								                         	    var dataArray = data.split(',');
								                         		var allRolesList = $('#roleId').datalist('getRows');
								                         		for(var i in dataArray){
								                         			for(var j in allRolesList){
								                         				if(dataArray[i] == allRolesList[j].roleId){
																			$('#roleId').datalist('selectRow',j);
																		}
								                         			}
								                         		}
								                         }
							                         }
												}"
											>
											</div>
										</div>
									</div>
									
									</li>
							</ul>
					</div>
			        </div>
		</form>
	</div>
	<p></p>
	<div class="dlg-buttons dialog-button"  style="position: relative;margin-top: 344px;"> 
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" id ="userSubmit">提交</a> 
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="EasyUiUtil.closeWindow('userWin')" >关闭</a>
	</div>
		
	<!--弹出窗口结束-->
	<script>
	
	var $user = {
			
			userUpdateUrl : 'sys/user/update',
			checkUserExistUrl : 'sys/user/check',
			
			init : function(){
				$('#userSubmit').click($user.updateUser);
			},
			
			checkUserExist : function(userName){
				var isExist = false;
				$.ajax({
					type : "POST",
					async : false,
					url: this.checkUserExistUrl,
					dataType:"json",
					data:{
						'userName':userName
					},
					success:function(result)
					{
						if(result){
							isExist = true;
						}
					}
				});
				
				return isExist;
			},
			
			updateUser : function() {
				var r = $('#userForm').form('validate');
				if(!r) {
					return false;
				}
				var operate;
				if(isNotEmpty($('#userId').val())){
					operate = 'update';
				}else{
					operate = 'create';
				}
				var oldName = $('#userOldName').val();
				var userName = $('#userName').textbox('getValue');
				if(operate == 'create' || (operate == 'update'  && (oldName != userName))){
					if($user.checkUserExist(userName)){
						$.messager.alert('提示',"该用户已存在",'error');
						return false;
					}
				}
				var selectedRoles = $('#roleId').datalist("getSelections");
				var roles = '';
				if(selectedRoles.length == 0){
					$.messager.alert('提示',"请至少选择一个角色",'error');
					return false;
				}else{
					$.each(selectedRoles,function(i,item)
					{
						roles = roles + selectedRoles[i].roleId + ",";	
					});
				}
				
				var operate;
				if(isNotEmpty($('#userId').val())){
					operate = 'update';
				}else{
					operate = 'create';
				}
				
				EasyUiUtil.showLoading("加载中..");
				$.post($user.userUpdateUrl + '?operate=' + operate + '&roles=' + roles,EasyUiUtil.form2Json('userForm'),function(data){
					EasyUiUtil.closeLoading();
					$('#userWin').window('close');
					$('#userTable').datagrid('reload');  
				});
			}
	};
	
	$user.init();
	
	</script>
