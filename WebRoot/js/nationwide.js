$(function(){
	if(window.sessionStorage){
		if(sessionStorage.getItem("type")){
			$("#toPerson").attr("href","userinfo.html");
			$(".nationpostbutton").attr("data-target","#PostModal");
		}else{
			$(".nationpostbutton").click(function(){
				alert("请先登录");
			})
		}
	}
	$("#searchDate").datepicker({
  		showAnim:"slide",
  		dateFormat:"yy-mm-dd"
    });
    $("#driPostStartDate").datepicker({
  		showAnim:"slide",
  		dateFormat:"yy-mm-dd"
    });
    $("#pasPostStartDate").datepicker({
  		showAnim:"slide",
  		dateFormat:"yy-mm-dd"
    });
    $("#toPost").click(function(){
    	var dt = {};
    	if($("#PostModal").find("div.tab-pane").eq(0).hasClass("active")){
    		//司机发布
    		if(sessionStorage.getItem("type")=="2"){
    			alert("只有司机才能发布");
    		}
    		dt.type = "1";
    		dt.id = sessionStorage.getItem("id");
    		dt.start = $("#driPostStartCity").val();
    		dt.dest = $("#driPostDestCity").val();
    		dt.startdate = $("#driPostStartDate").val();
    		dt.starttime = $("#driPostStartTime").val();
    		dt.count = $("#driPostPeople").val();
    		dt.price = $("input[name=priceRadios]:checked").val()=="一口价"?$("#driPostPrice").val():$("input[name=priceRadios]:checked").val();
    		dt.message = $("#driPostMessage").val()==""?"无":$("#driPostMessage").val();
    		dt.cartype = $("input[name=carRadios]:checked").val()
    		$.ajax({
    			type:"POST",
    			url:"nationpost",
    			data:"dt="+JSON.stringify(dt),
    			success:function(dt){
    				dt = eval('('+dt+')');
    				if(dt.result=="fail"){
    					alert("发布失败，请重新发布")
    				}else if(dt.result=="success"){
    					alert("发布成功");
    					location.reload();
    				}
    			}
    		})
    	}else{
    		//乘客发布
    		if(sessionStorage.getItem("type")=="1"){
    			alert("只有乘客才能发布");
    		}
    		dt.type = "2";
    		dt.id = sessionStorage.getItem("id");
    		dt.start = $("#pasPostStartCity").val();
    		dt.dest = $("#pasPostDestCity").val();
    		dt.startdate = $("#pasPostStartDate").val();
    		dt.starttime = $("#pasPostStartTime").val();
    		dt.count = $("#pasPostPeople").val();
    		dt.price = $("input[name=priceRadios]:checked").val()=="一口价"?$("#pasPostPrice").val():$("input[name=priceRadios]:checked").val();
    		dt.message = $("#pasPostMessage").val()==""?"无":$("#pasPostMessage").val();
    		$.ajax({
    			type:"POST",
    			url:"nationpost",
    			data:"dt="+JSON.stringify(dt),
    			success:function(dt){
    				dt = eval('('+dt+')');
    				if(dt.result=="fail"){
    					alert("发布失败，请重新发布")
    				}else if(dt.result=="success"){
    					alert("发布成功");
    					location.reload();
    				}
    			}
    		})
    	}
    })
})