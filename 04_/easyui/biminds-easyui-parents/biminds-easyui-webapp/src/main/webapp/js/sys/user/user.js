
/**
 * User Model
 */

var UserModel = {
	
	userUpdateOpenUrl : 'sys/web/user/update',
	rePassUrl : 'sys/user/password/reset',
	width :520,
	height :500,
	userCreateTitle : '新建用户',
	userUpdateTitle : '编辑用户',
	userWindowId : 'userWin',
	
	init : function(){
		
		$('#userSearch').click(UserModel.searchUserList);
		$('#userReset').click(UserModel.resetSearchForm);
		$('.oper').click(function()
		{
			if($(this).attr('operate') == 'repass'){
				UserModel.rePassword($(this).attr('operate'));
			}else{
				UserModel.openUserUpdate($(this).attr('operate'));
			}
		});
	},
	
	searchUserList : function() {
		$('#userTable').datagrid({ queryParams: EasyUiUtil.form2Json('searchForm') });
	},
	
	resetSearchForm : function() {
		$('#searchForm').form('clear');
	},
	
	formatEnabled : function(value, row){
		if(value == 10){
			return "有效";
		}else{
			return "无效";
		}
	},
	
	rePassword : function(operate) {
		var rows = $('#userTable').datagrid('getSelections');
		if(rows.length == 0){
			$.messager.alert('提示',"请选择一条记录",'error');
			return;
		}
		$.messager.confirm('重置确认','确定重置密码为123456吗?',function(r){
		    if (r){
				$.ajax({
					type : "POST",
					async : false,
					url: UserModel.rePassUrl,
					dataType:"json",
					data:{'userId':rows[0].userId},
					success:function(result)
					{
						$('#userTable').datagrid('reload');  
					}
				});
		    }}
		  );
	},
	
	openUserUpdate : function(operate) {
		
		var rows = [];
		if(operate == 'update'){
			rows = $('#userTable').datagrid('getSelections');
			if(rows.length == 0){
				$.messager.alert('提示',"请选择一条记录",'error');
				return;
			}
		}
		EasyUiUtil.showWindow(this.userWindowId,{
			title: operate == 'create' ? this.userCreateTitle : this.userUpdateTitle,
			href: this.userUpdateOpenUrl + '?operate=' + operate+ (operate == "update" ? "&userId="+rows[0].userId : ""),
			width: this.width,
			height: this.height,
			onLoad: function(){
				if(operate == 'update'){
				   $("#userForm").form('load', rows[0]);
				   $("#userOldName").val(rows[0].userName);
				}
			}
		});
	}
	
};