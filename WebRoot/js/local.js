$(function(){
	if(window.sessionStorage){
		$("#city").text(sessionStorage.getItem("city"));
		if(sessionStorage.getItem("type")){//已登录
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
	//切换城市
	$(".ul_city_list").each(function(){
		$(this).find("li").find("a").click(function(e){
			e.preventDefault();
			sessionStorage.setItem("city",$(this).text());
			location.reload();
		})
	})
	//搜索
	$("#search").click(function(){
		var start = $("#searchStart").val();
		var dest  = $("#searchDest").val();
		$("#tbody").find("tr").each(function(){
			var x = $(this).find("td").eq(1).text();
			var str = x.split("到");
			console.log(start);
			console.log(str[0]);
			console.log(str[0].indexOf(start));
			if(str[0].indexOf(start)==-1||str[1].indexOf(dest)==-1){
				$(this).hide();
			}else{
				$(this).show();
			}
		})
	})
	//发布拼车
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
	//获取拼车
	var x = {};
	x.type = "1";
	x.city = sessionStorage.getItem("city");
	$.ajax({
		type:"POST",
		url:"getallpost",
		data:"dt="+JSON.stringify(x),
		success:function(dt){
			dt = eval('('+dt+')');
			var tb = document.getElementById("tbody");
			for(var count=dt.detail.length-1;count>-1;count--){
				var tr = document.createElement("tr");
				var td = document.createElement("td");
				td.innerHTML = dt.detail[count].id;
				tr.appendChild(td);
				td = document.createElement("td");
				td.innerHTML = "<a href='localDetail.html?id="+dt.detail[count].id+"'>"+dt.detail[count].start+"到"+dt.detail[count].dest+"</a>";
				tr.appendChild(td);
				td = document.createElement("td");
				td.innerHTML = dt.detail[count].startdate;
				tr.appendChild(td);
				td = document.createElement("td");
				td.innerHTML = dt.detail[count].starttime;
				tr.appendChild(td);
				td = document.createElement("td");
				td.innerHTML = dt.detail[count].price+"/人";
				tr.appendChild(td);
				td = document.createElement("td");
				td.innerHTML = dt.detail[count].in_count;
				tr.appendChild(td);
				td = document.createElement("td");
				td.innerHTML = dt.detail[count].need_count;
				tr.appendChild(td);
				td = document.createElement("td");
				if(dt.detail[count].status=="1"){
					td.innerHTML = "有效";
				}else{
					td.innerHTML = "过期";
				}
				tr.appendChild(td);
				td = document.createElement("td");
				tb.appendChild(tr);
			}	
		}
	})
})