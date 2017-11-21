$(function() 
{
	$('#loginBtn').click(function() {
		$('#loading').show();
		$.ajax({
			type : "GET",
			async : false,
			url :  "sys/login",
			dataType : "json",
			data : {'username' : $('#username').val(),'password' : $('#password').val()},
			success : function(result) {
				if(null != result && undefined != result){
					if(result.status == 200 && '' != result.data){
						location = 'main';
					}else{
						$('#loading').hide();
						$("#error").text(result.message);
						return;
					}
				}
			}
		});
	});
	
	$("body").keydown(function(event) {
		if (event.keyCode == 13) {
			$('#loginBtn').click();
		}
	});

});
