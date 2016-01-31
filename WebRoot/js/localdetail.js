function GetQueryString(name){
 	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
 	var r = window.location.search.substr(1).match(reg);
 	if(r!=null)return  unescape(r[2]); return null;
};
$(function(){
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
			}
			$("#dtMessage").text(dt.detail.message);
			$("#dtName").text(dt.poster.name.slice(0,1)+"师傅");
			$("#dtId").text(dt.poster.id);
			$("#dtViewtime").text(dt.detail.viewtime+"次");
			phone = dt.poster.phone;
		}
	})
})