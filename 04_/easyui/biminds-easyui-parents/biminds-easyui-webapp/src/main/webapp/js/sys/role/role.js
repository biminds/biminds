/**
 *  Role Model
 */

var RoleModel = {
	
	
	roleUpdateOpenUrl : 'sys/web/role/update',
	resourceAllocateUrl : 'sys/web/resource/allocate',
	width :500,
	height : 400,
	roleCreateTitle : '新建角色',
	roleUpdateTitle : '编辑角色',
	resourceAllocateTitle : '分配权限',
	roleWindowId : 'roleWin',
	
	init : function(){
		
		$('#roleSearch').click(RoleModel.searchRoleList);
		$('#roleReset').click(RoleModel.resetSearchForm);
		$('.oper').click(function()
		{
			if($(this).attr('operate') == 'allocate'){
				RoleModel.allocateResource($(this).attr('operate'));
			}else{
				RoleModel.openRoleUpdate($(this).attr('operate'));
			}
		});
	},
	
	searchRoleList : function() {
		$('#roleTable').datagrid({ queryParams: EasyUiUtil.form2Json('searchForm') });
	},
	
	resetSearchForm : function() {
		$('#searchForm').form('clear');
	},
	
	formatStatus : function(value, row){
		console.log(value);
		if(row.enabled == 10){
			return "已启用";
		}else{
			return "已停用";
		}
	},
	
	/**
	 * 打开角色创建和编辑页面
	 * @param operate 创建或编辑
	 */
	openRoleUpdate : function(operate) {
		
		var rows = [];
		if(operate == 'update'){
			rows = $('#roleTable').datagrid('getSelections');
			if(rows.length == 0){
				$.messager.alert('提示',"请选择一条记录",'error');
				return;
			}
		}
		EasyUiUtil.showWindow(this.roleWindowId,{
			title: operate == 'create' ? this.roleCreateTitle : this.roleUpdateTitle,
			href: this.roleUpdateOpenUrl + '?operate=' + operate,
			width: this.width,
			height: this.height,
			onLoad: function(){
				if(operate == 'update'){
				   $("#roleForm").form('load', rows[0]);
				   $("#roleOldName").val(rows[0].roleName);
				}
			}
		});
	},
	
	allocateResource : function(operate) {
		var rows = $('#roleTable').datagrid('getSelections');
		if(rows.length == 0){
			$.messager.alert('提示',"请选择一条记录",'error');
			return;
		}
		EasyUiUtil.showWindow(this.roleWindowId,{
			title:  this.resourceAllocateTitle,
			href: this.resourceAllocateUrl + '?roleId=' + rows[0].roleId ,
			width: 800,
			height: 400
		});
	}
	
	
};