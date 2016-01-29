$(function(){
	if(window.sessionStorage){
		if(sessionStorage.getItem("id")){
			$("#nameBtn").text("用户"+sessionStorage.getItem("name"));
			$("#loginBtn").hide();
			$("#nameBtn").show();
			$("#logoutBtn").show();
		}
	}else{
		alert("this browser does not support sessionStorage");
		window.close();
	};
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