function GetQueryString(name){
 	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
 	var r = window.location.search.substr(1).match(reg);
 	if(r!=null)return  unescape(r[2]); return null;
};
$(function(){
	//初始化
	var phone;
	var x = {};
	x.trip_id = GetQueryString("id");
	x.type = "1";
	$.ajax({
		type:"POST",
		url:"getcarpool",
		data:"dt="+JSON.stringify(x),
		success:function(dt){
			dt = eval('('+dt+')');
			$("#dtStart").text(dt.detail.start);
			$("#dtDest").text(dt.detail.dest);
			if(dt.detail.passby1!="0"){
				$("#dtPassby").text(dt.detail.passby1);
				if(dt.detail.passby2!="0"){
					var str = $("#dtPassby").text();
					$("#dtPassby").text(str+","+dt.detail.passby2);
					if(dt.detail.passby3!="0"){
						str = $("#dtPassby").text();
						$("#dtPassby").text(str+","+dt.detail.passby3);
					}
				}
			}
			$("#dtStartTime").text(dt.detail.startdate+" "+dt.detail.starttime+"出发");
			$("#dtApplyCount").text(dt.detail.apply_count);
			$("#dtCartype").text(dt.detail.cartype);
			$("#dtNeedcount").text(dt.detail.need_count);
			$("#dtIncount").text(dt.detail.in_count);
			if(dt.detail.price=="面议"){
				$("#dtPrice").text("面议");
			}else{
				$("#dtPrice").text(dt.detail.price+"/人");
			}
			if(dt.detail.status=="1"){
				$("#dtStatus").text("有效");
			}else{
				$("#dtStatus").text("过期");
				$("#pastoapply").hide();
			}
			$("#dtMessage").text(dt.detail.message);
			$("#dtName").text(dt.poster.name.slice(0,1)+"师傅");
			$("#dtId").text(dt.poster.id);
			$("#dtViewtime").text(dt.detail.viewtime+"次");
			phone = dt.poster.phone;
		}
	})
	//检测是否加入过
	if(sessionStorage.getItem("id")){
		$("#pasgetphone").click(function(){
			alert(phone);
		})
		if(sessionStorage.getItem("type")=="1"){
			$("#pastoapply").click(function(){
				alert("只有乘客才能申请")
			})
		}
		var check = {};
		check.apply_id = sessionStorage.getItem("id");
		check.trip_id = GetQueryString("id");
		check.type = "1";
		$.ajax({
			type:"POST",
			url:"checkapply",
			data:"dt="+JSON.stringify(check),
			success:function(dt){
				dt = eval('('+dt+')');
				if(dt.result=="applied"){//申请过
					if(sessionStorage.getItem("type")=="2"){
						$("#pastoapply").text("取消申请");
						$("#pastoapply").click(function(){
							var x = {} ;
							x.trip_id = GetQueryString("id");
							x.apply_id = sessionStorage.getItem("id");
							x.type = "3";
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
					}
				}else{
					if(sessionStorage.getItem("type")=="2"){
						$("#pastoapply").click(function(){
							var x = {} ;
							x.trip_id = GetQueryString("id");
							x.apply_id = sessionStorage.getItem("id");
							x.type = "1";
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
			}
		})
	}else{
		$("#pastoapply").click(function(){
			alert("请先登录")
		})
		$("#pasgetphone").click(function(){
			alert("请先登录")
		})
	}
})