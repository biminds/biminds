<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>

	<!--弹出窗口开始-->
	<div>
		<form id="userPassForm" method="post">
			<runlin:token/>
			<div class="ap-form-container">
				<ul>
					<li>
						<div>
							<label>原密码:</label> 
							<input id="oldPass" name="oldPass" type="password" class="easyui-textbox" data-options="required:true,missingMessage:'原密码不能为空'"
								style="width: 180px; height: 25px" validtype="length[1,20]" invalidMessage="原密码不能超过20个字符"/>
						</div>
						
						<div>
							<label>新密码:</label> 
							<input id="newPass" name="newPass" type="password" class="easyui-textbox" data-options="required:true,missingMessage:'新密码不能为空'"
								style="width: 180px; height: 25px" validtype="length[1,20]" invalidMessage="新密码不能超过20个字符"/>
						</div>
						
						<div>
							<label>确认新密码:</label> 
							<input id="confirmPass" name="confirmPass" type="password" class="easyui-textbox" data-options="required:true,missingMessage:'确认新密码不能为空'"
								style="width: 180px; height: 25px" validtype="length[1,20]" invalidMessage="确认新密码不能超过20个字符"/>
						</div>
						
				</ul>
			</div>
		</form>
	</div>
	<p></p>
	<div class="dlg-buttons dialog-button"  style="position: relative;margin-top: 234px;"> 
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" id ="userPassSubmit">提交</a> 
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="$userPass.closeWindow();" >关闭</a>
	</div>
		
	<!--弹出窗口结束-->
	<script>
	
	var $userPass = {
			
			//初始化属性
			userPassUpdateUrl : 'sys/user/pass/update',
			checkPassRightUrl : 'sys/user/pass/check',
			
			//初始化
			init : function(){
				$('#userPassSubmit').click($userPass.updatePassword);
			},
			
			//校验密码是否正确
			checkPassRight : function(oldPass){
				var isRight = false;
				$.ajax({
					type : "POST",
					async : false,
					url: this.checkPassRightUrl,
					dataType:"json",
					data:{'oldPass':oldPass},
					success:function(result)
					{
						if(result){
							isRight = true;
						}
					}
				});
				
				return isRight;
			},
			
			closeWindow : function(){
				$("#headerWin").window('close');
			},
			
			updatePassword : function() {
				var r = $('#userPassForm').form('validate');
				if(!r) {
					return false;
				}
				if(!$userPass.checkPassRight($('#oldPass').val())){
					$.messager.alert('提示',"原密码不正确",'error');
					return false;
				}
				
				if($('#newPass').val() != $('#confirmPass').val()){
					$.messager.alert('提示',"两次密码不一致",'error');
					return false;
				}
				
				$.post($userPass.userPassUpdateUrl,{'newPass':$('#newPass').val()},function(data){
					$('#headerWin').window('close');
				});
			}
	};
	
	$userPass.init();
	
	</script>
