$(function() {
	$('#loginBtn').click(function() {
		$.ajax({
			type : "POST",
			async : false,
			url : base + "/sys/login",
			dataType : "json",
			data : {
				'username' : $('#userName').val(),
				'password' : $('#password').val()
			},
			success : function(result) {
				if (null != result && undefined != result) {
					if (result.status == 200 && '' != result.data) {
						location = 'main';
					} else {
						layer.open({
							type : 0,
							content : result.message
						});
						return;
					}
				}
			},
			error : function() {
				layer.open({
					type : 0,
					content : '系统异常！' // 这里content是一个普通的String
				});
			}
		});
	});

	$("body").keydown(function(event) {
		if (event.keyCode == 13) {
			$('#loginBtn').click();
		}
	});

});
