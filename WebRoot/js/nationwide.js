$(function(){
    //获取拼车
	$.ajax({
		type:"POST",
		url:"getallpost",
		data:"dt="+JSON.stringify({"type":"2"}),
		success:function(dt){
			dt = eval('('+dt+')');
			var tb = document.getElementById("tbody");
			for(var count=dt.detail.length-1;count>-1;count--){
				var tr = document.createElement("tr");
				var td = document.createElement("td");
				if(dt.detail[count].type=="1"){
					td.innerHTML = "拼车";
				}else{
					td.innerHTML = "求拼";
				}
				tr.appendChild(td);
				td = document.createElement("td");
				td.innerHTML = dt.detail[count].startdate;
				tr.appendChild(td);
				td = document.createElement("td");
				if(dt.detail[count].price=="面议"){
					td.innerHTML = dt.detail[count].price;
				}else{
					td.innerHTML = dt.detail[count].price+"/人";
				}
				tr.appendChild(td);
				td = document.createElement("td");
				if(dt.detail[count].cartype=="0"){
					td.innerHTML = "-";
				}else{
					td.innerHTML = dt.detail[count].cartype;
				}
				tr.appendChild(td);
				td = document.createElement("td");
				td.innerHTML = dt.detail[count].start;
				tr.appendChild(td);
				td = document.createElement("td");
				td.innerHTML = dt.detail[count].dest;
				tr.appendChild(td);
				td = document.createElement("td");
				td.innerHTML = "<a href='nationwideDetail.html?id="+dt.detail[count].id+"'>查看详情</a>";
				tr.appendChild(td);
				tb.appendChild(tr);
			}
            var driving = new BMap.DrivingRoute(map, {    
             renderOptions: {    
               map   : map,     
               panel : "results",    
               autoViewport: true    
             }    
            });    
            driving.search(start,dest);	
		}
	})
	if(sessionStorage.getItem("type")){
		$(".nationpostbutton").attr("data-target","#PostModal");
	}else{
		$(".nationpostbutton").click(function(){
			alert("请先登录");
		})
	}
    //日历
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
    //地图
    $("#searchStart").blur(function(){
        var start = $("#searchStart").val();
        var dest  = $("#searchDest").val();
        if(start!=""&&dest!=""){
            //输入起点跟终点显示路线
            var driving = new BMap.DrivingRoute(map, {    
             renderOptions: {    
               map   : map,     
               panel : "results",    
               autoViewport: true    
             }    
            });    
            driving.search(start,dest);
        }
    })
    $("#searchDest").blur(function(){
        var start = $("#searchStart").val();
        var dest  = $("#searchDest").val();
        if(start!=""&&dest!=""){
            //输入起点跟终点显示路线
            var driving = new BMap.DrivingRoute(map, {    
             renderOptions: {    
               map   : map,     
               panel : "results",    
               autoViewport: true    
             }    
            });    
            driving.search(start,dest);
        }
    })
    //搜索
    $("#search").click(function(){
        var start = $("#searchStart").val();
        var dest = $("#searchDest").val();
        var date = $("#searchDate").val();
        $("#tbody").find("tr").each(function(){
            if($(this).find("td").eq(1).text().indexOf(date)==-1||$(this).find("td").eq(4).text().indexOf(start)==-1||$(this).find("td").eq(5).text().indexOf(dest)==-1){
                $(this).hide();
            }else{
                $(this).show();
            }
        })
    })
    //发布
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