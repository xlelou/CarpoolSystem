$(function(){
	if(window.sessionStorage){
		if(sessionStorage.getItem("type")){
			$("#toPerson").attr("href","userinfo.html");
			if(sessionStorage.getItem("type")=="1"){
				$(".localpostbutton").attr("data-target","#PostModal");
			}else{
				$(".localpostbutton").click(function(){
					alert("只有司机才能发布本地拼车");
				})
			}
		}else{
			$(".localpostbutton").click(function(){
				alert("请先登录");
			})
		}
	}
	$("#toPost").click(function(){
		var dt = {};
		dt.city = sessionStorage.getItem("city");
		dt.start = $("#driPostStart").val();
		dt.dest = $("#driPostDest").val();
		dt.id = sessionStorage.getItem("id");
		dt.need_count = $("#driPostPeople").val();
		dt.price = $("#driPostPrice").val();
		dt.cartype = $("#driPostCartype").val();
		dt.starttime = $("#driPostHour").val()+":"+$("#driPostMinute").val();;
		dt.startdate = $("#driPostDate").val();
		dt.message = $("#driPostMessage").val()!=""?$("#driPostMessage").val():"无";
		dt.passby1 = $("#driPostPassby1").val()!=""?$("#driPostPassby1").val():"0";
		dt.passby2 = $("#driPostPassby2").val()!=""?$("#driPostPassby2").val():"0";
		dt.passby3 = $("#driPostPassby3").val()!=""?$("#driPostPassby3").val():"0";
		$.ajax({
			type:"POST",
			url:"localpost",
			data:"dt="+JSON.stringify(dt),
			success:function(dt){
				dt = eval('('+dt+')');
				if(dt.result=="fail"){
					alert("发布失败，请重试");
				}else if(dt.result=="success"){
					alert("发布成功");
					location.reload();
				}
			}
		})
	})
})