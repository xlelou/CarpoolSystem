$('#activeajax').click(function(){
	var x = {};
	x.phone = "13539778553";
	x.password = "k.kkk520";
	x.val = [1,2,3];
	x.info = {
		head:1,
		j:2
	}
	$.ajax({
		type:"POST",
		url:"login",
		data:"dt="+JSON.stringify(x),
		success:function(msg){
			if(msg=="success"){
				alert("1");
			}
		}
	})
})
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
			}
		}
	})
})