$(function(){
	if(window.sessionStorage){
		if(sessionStorage.getItem("city")==null){
			sessionStorage.setItem("city","广州");
		}
		if(sessionStorage.getItem("id")){//已登录
			$("#nameBtn").text("用户"+sessionStorage.getItem("name"));
			$("#loginBtn").hide();
			$("#nameBtn").show();
			$("#logoutBtn").show();
		}else{//未登录
			$("#toPerson").click(function(){
				alert("请先登录");
			})
		}
	}else{
		alert("this browser does not support sessionStorage");
		window.close();
	};
	$('#loginPassword').focus(function(){
		$('#findPassword').fadeToggle('fast');
	}).blur(function(){
		$('#findPassword').fadeToggle('fast');
	});
	$("#tologin").click(function(){
		var dt = {};
		dt.phone = $("#loginAccount").val();
		dt.password = $("#loginPassword").val();
		$.ajax({
			type:"POST",
			url:"login",
			data:"dt="+JSON.stringify(dt),
			success:function(dt){
				dt = eval('('+dt+')');
				console.log(dt.result);
				if(dt.result=="fail"){
					alert("账号或密码错误,请再次尝试")
				}else{
					sessionStorage.setItem("id",dt.id);
					sessionStorage.setItem("name",dt.account);
					sessionStorage.setItem("type",dt.type);
					location.reload();
				}
			}
		})
	});
	$('#driverRegBtn').click(function(){
		var x = {};
		x.account = $("#driRegAccount").val();
		x.password = $("#driRegPassword").val();
		x.username = $("#driRegUsername").val();
		x.id = $("#driRegId").val();
		x.email = $("#driRegEmail").val();
		x.type="1";
		$.ajax({
			type:"POST",
			url:"register",
			data:"dt="+JSON.stringify(x),
			success:function(msg){
				if(msg=="success"){
					alert("注册成功");
					location.reload();
				}
			}
		})
	})
	$('#pasRegBtn').click(function(){
		var x = {};
		x.type="2";
		x.account = $("#pasRegAccount").val();
		x.password = $("#pasRegPassword").val();
		x.email = $("#pasRegEmail").val();
		$.ajax({
			type:"POST",
			url:"register",
			data:"dt="+JSON.stringify(x),
			success:function(msg){
				if(msg=="success"){
					alert("注册成功");
					location.reload();
				}
			}
		})
	})

})
function logout(){
	sessionStorage.clear();
	location.reload();
}