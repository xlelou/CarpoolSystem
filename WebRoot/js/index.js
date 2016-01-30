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
