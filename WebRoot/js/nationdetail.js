function GetQueryString(name){
 	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
 	var r = window.location.search.substr(1).match(reg);
 	if(r!=null)return  unescape(r[2]); return null;
};
$(function(){
	var x = {};
	x.trip_id = GetQueryString("id");
	x.type = "2";
	$.ajax({
		type:"POST",
		url:"getcarpool",
		data:"dt="+JSON.stringify(x),
		success:function(dt){
			dt = eval('('+dt+')');
			console.log(dt);
		}
	})
})