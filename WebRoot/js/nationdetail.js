function GetQueryString(name){
 	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
 	var r = window.location.search.substr(1).match(reg);
 	if(r!=null)return  unescape(r[2]); return null;
};
$(function(){
	//初始化
	var posterphone;
	var x = {};
	x.trip_id = GetQueryString("id");
	x.type = "2";
	$.ajax({
		type:"POST",
		url:"getcarpool",
		data:"dt="+JSON.stringify(x),
		success:function(dt){
			dt = eval('('+dt+')');
			posterphone = dt.poster.phone;
			if(dt.detail.status=="1"){
				$("#dtStatus").text("有效")
			}else{
				$("#dtStatus").text("已过期");
				$("#pastoapply").hide();
			}
			$("#dtStart").text(dt.detail.start);
			$("#dtDest").text(dt.detail.dest);
			$("#dtStartdate").text(dt.detail.startdate);
			$("#dtStarttime").text(dt.detail.starttime);
			if(dt.detail.type=="1"){
				sessionStorage.setItem("triptype","1");
				$("#dtdriPeople").text(dt.detail.need_count+"人");
				$("#dtdriPeopleapplied").text(dt.detail.in_count+"人");
				$("#dtdriPeopleapply").text(dt.detail.apply_count);
				$("#dtdriCartype").text(dt.detail.cartype);
				if(dt.detail.price=="面议"){
					$("#dtdriPaytype").text(dt.detail.price);
				}else{
					$("#dtdriPaytype").text(dt.detail.price+"/人");
				}
				$("#dtdriMessage").text(dt.detail.message);
				$("#dtdriName").text(dt.poster.name.slice(0,1)+"师傅");
				$("#dtdriId").text(dt.poster.id);
				$("#dtdriViewtime").text(dt.detail.viewtime+"次");
			}else if(dt.detail.type=="2"){
				sessionStorage.setItem("triptype","2");
				$("#dtpasPeople").text(dt.detail.need_count+"人");
				if(dt.detail.price=="面议"){
					$("#dtpasPaytype").text(dt.detail.price);
				}else{
					$("#dtpasPaytype").text(dt.detail.price+"/人");
				}
				$("#dtpasMessage").text(dt.detail.message);
				$("#dtpasName").text("用户"+dt.poster.phone.slice(0,7)+"****");
				$("#dtpasCode").text(dt.poster.id);
				$("#dtpasViewtime").text(dt.detail.viewtime+"次");
				$("#type1div").hide();
				$("#type2div").show();
			}
		}
	})
	//绑定
	if(sessionStorage.getItem("type")){
		$("#pasgetphone").click(function(){
			alert(posterphone);
		});
		$("#drigetphone").click(function(){
			alert(posterphone);
		})
		if(sessionStorage.getItem("type")=="1"){
			if(sessionStorage.getItem("triptype")=="1"){
				$("#pastoapply").click(function(){
					alert("只有乘客才能申请");
				})
			}else{
				$("#dritoapply").click(function(){
					alert("邀请成功");
				})
			}
		}else if(sessionStorage.getItem("type")=="2"){
				var check = {};
				check.apply_id = sessionStorage.getItem("id");
				check.trip_id = GetQueryString("id");
				check.type = "2";
				$.ajax({
					type:"POST",
					url:"checkapply",
					data:"dt="+JSON.stringify(check),
					success:function(dt){
						dt = eval('('+dt+')');
						if(dt.result=="applied"){
							$("#pastoapply").text("取消申请");
							$("#pastoapply").click(function(){
								var x = {} ;
								x.trip_id = GetQueryString("id");
								x.apply_id = sessionStorage.getItem("id");
								x.type = "4";
								$.ajax({
									type:"POST",
									url:"applycarpool",
									data:"dt="+JSON.stringify(x),
									success:function(dt){
										dt = eval('('+dt+')');
										if(dt.result=="success"){
											alert("取消成功");
											location.reload();
										}else{
											alert("取消失败,请重试");
											location.reload();
										}
									}
								})
							})
						}else{
							$("#pastoapply").click(function(){
								var x = {} ;
								x.trip_id = GetQueryString("id");
								x.apply_id = sessionStorage.getItem("id");
								x.type = "2";
								$.ajax({
									type:"POST",
									url:"applycarpool",
									data:"dt="+JSON.stringify(x),
									success:function(dt){
										dt = eval('('+dt+')');
										if(dt.result=="success"){
											alert("申请成功");
											location.reload();
										}else{
											alert("申请失败,请重试");
											location.reload();
										}
									}
								})
							})
						}
					}
				})
			}
		}else{
			$("#dritoapply").click(function(){
				alert("请先登录")
			});
			$("#drigetphone").click(function(){
				alert("请先登录")
			});
			$("#pastoapply").click(function(){
				alert("请先登录")
			})
			$("#pasgetphone").click(function(){
				alert("请先登录")
			})
		}
})