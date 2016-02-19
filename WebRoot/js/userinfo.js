function logout(){
    sessionStorage.clear();
    location.reload();
}
$(function(){
    //登录检测
    if(window.sessionStorage){
        if(sessionStorage.getItem("id")){
            $("#nameBtn").text("用户"+sessionStorage.getItem("name"));
            $("#loginBtn").hide();
            $("#nameBtn").show();
            $("#logoutBtn").show();
            //getinfo
            var x = {};
            x.account = sessionStorage.getItem("name");
            $.ajax({
                type:"POST",
                url:"getinfo",
                data:"dt="+JSON.stringify(x),
                success:function(dt){
                    dt = eval('('+dt+')');
                    if(dt.info.type=="1"){
                        $("#drivali").show();
                        $("#dridetail").show();
                        $("#send_thismonth").text(dt.info.send_thismonth);
                        $("#send_count").text(dt.info.send_count);
                        $("#success_count").text(dt.info.success_count);
                        $("#stPhone").text(dt.info.phone);
                        $("#stEmail").text(dt.info.email);
                    }else if(dt.info.type=="2"){
                        $("#pasvali").show();
                        $("#pasdetail").show();
                        $("#take_thismonth").text(dt.info.take_thismonth);
                        $("#take_count").text(dt.info.take_count);
                        $("#stPhone").text(dt.info.phone);
                        $("#stEmail").text(dt.info.email);
                    }
                }
            })
        }else{
            location.href="index.html";
        }
    }else{
        alert("this browser does not support sessionStorage");
        window.close();
    };
    //获取拼车
    var getmessage = {};
    getmessage.id = sessionStorage.getItem("id");
    if(sessionStorage.getItem("type")=="1"){
        $.ajax({
            type:"POST",
            url:"getsomeonepost",
            data:"dt="+JSON.stringify(getmessage),
            success:function(dt){
                dt = eval('('+dt+')');
                for(var x = 0;x<dt.local.length;x++){
                    var tr = document.createElement("tr");
                    var td = document.createElement("td");
                    td.innerHTML = dt.local[x].id;
                    tr.appendChild(td);
                    td = document.createElement("td");
                    td.innerHTML = "<a href='localDetail?id=+"+dt.local[x].id+"'>"+dt.local[x].start+"到"+dt.local[x].dest+"</a>";
                    tr.appendChild(td);
                    td = document.createElement("td");
                    td.innerHTML = dt.local[x].startdate;
                    tr.appendChild(td);
                    td = document.createElement("td");
                    if(dt.local[x].status=="1"){
                        td.innerHTML = "有效";
                        tr.appendChild(td);
                        $(tr).appendTo($("#localPosting").find("tbody"));
                    }else if(dt.local[x].status=="2"){
                        td.innerHTML = "已过期";
                        tr.appendChild(td);
                        $(tr).appendTo($("#localPosted").find("tbody"));
                    }
                }
                for(var x = 0;x<dt.nation.length;x++){
                    var tr = document.createElement("tr");
                    var td = document.createElement("td");
                    td.innerHTML = dt.nation[x].id;
                    tr.appendChild(td);
                    td = document.createElement("td");
                    td.innerHTML = dt.nation[x].start;
                    tr.appendChild(td);
                    td = document.createElement("td");
                    td.innerHTML = dt.nation[x].dest;
                    tr.appendChild(td);
                    td = document.createElement("td");
                    td.innerHTML = dt.nation[x].startdate;
                    tr.appendChild(td);
                    td = document.createElement("td");
                    td.innerHTML = "<a href='nationwideDetail.html?id="+dt.nation[x].id+"'>查看详情</a>";
                    tr.appendChild(td);
                    if(dt.nation[x].status=="1"){
                        $(tr).appendTo($("#nationPosting").find("tbody"));
                    }else if(dt.nation[x].status=="2"){
                        $(tr).appendTo($("#nationPosted").find("tbody"));
                    }
                }
            }
        });
    }else if(sessionStorage.getItem("type")=="2"){
       $.ajax({
            type:"POST",
            url:"getsomeoneapply",
            data:"dt="+JSON.stringify(getmessage),
            success:function(dt){
                dt = eval('('+dt+')');
                for(var x = 0;x<dt.local.length;x++){
                    var tr = document.createElement("tr");
                    var td = document.createElement("td");
                    td.innerHTML = dt.local[x].id;
                    tr.appendChild(td);
                    td = document.createElement("td");
                    td.innerHTML = "<a href='localDetail?id=+"+dt.local[x].id+"'>"+dt.local[x].start+"到"+dt.local[x].dest+"</a>";
                    tr.appendChild(td);
                    td = document.createElement("td");
                    td.innerHTML = dt.local[x].startdate;
                    tr.appendChild(td);
                    td = document.createElement("td");
                    if(dt.local[x].status=="1"){
                        td.innerHTML = "有效";
                        tr.appendChild(td);
                        $(tr).appendTo($("#localApplying").find("tbody"));
                    }else if(dt.local[x].status=="2"){
                        td.innerHTML = "已过期";
                        tr.appendChild(td);
                        $(tr).appendTo($("#localApplied").find("tbody"));
                    }
                }
                for(var x = 0;x<dt.nation.length;x++){
                    var tr = document.createElement("tr");
                    var td = document.createElement("td");
                    td.innerHTML = dt.nation[x].id;
                    tr.appendChild(td);
                    td = document.createElement("td");
                    td.innerHTML = dt.nation[x].start;
                    tr.appendChild(td);
                    td = document.createElement("td");
                    td.innerHTML = dt.nation[x].dest;
                    tr.appendChild(td);
                    td = document.createElement("td");
                    td.innerHTML = dt.nation[x].startdate;
                    tr.appendChild(td);
                    td = document.createElement("td");
                    td.innerHTML = "<a href='nationwideDetail.html?id="+dt.nation[x].id+"'>查看详情</a>";
                    tr.appendChild(td);
                    if(dt.nation[x].status=="1"){
                        $(tr).appendTo($("#nationApplying").find("tbody"));
                    }else if(dt.nation[x].status=="2"){
                        $(tr).appendTo($("#nationApplied").find("tbody"));
                    }
                }
            }
        });
        $.ajax({
            type:"POST",
            url:"getsomeonepost",
            data:"dt="+JSON.stringify(getmessage),
            success:function(dt){
                dt = eval('('+dt+')');
                for(var x = 0;x<dt.local.length;x++){
                    var tr = document.createElement("tr");
                    var td = document.createElement("td");
                    td.innerHTML = dt.local[x].id;
                    tr.appendChild(td);
                    td = document.createElement("td");
                    td.innerHTML = "<a href='localDetail?id=+"+dt.local[x].id+"'>"+dt.local[x].start+"到"+dt.local[x].dest+"</a>";
                    tr.appendChild(td);
                    td = document.createElement("td");
                    td.innerHTML = dt.local[x].startdate;
                    tr.appendChild(td);
                    td = document.createElement("td");
                    if(dt.local[x].status=="1"){
                        td.innerHTML = "有效";
                        tr.appendChild(td);
                        $(tr).appendTo($("#localPosting").find("tbody"));
                    }else if(dt.local[x].status=="2"){
                        td.innerHTML = "已过期";
                        tr.appendChild(td);
                        $(tr).appendTo($("#localPosted").find("tbody"));
                    }
                }
                for(var x = 0;x<dt.nation.length;x++){
                    var tr = document.createElement("tr");
                    var td = document.createElement("td");
                    td.innerHTML = dt.nation[x].id;
                    tr.appendChild(td);
                    td = document.createElement("td");
                    td.innerHTML = dt.nation[x].start;
                    tr.appendChild(td);
                    td = document.createElement("td");
                    td.innerHTML = dt.nation[x].dest;
                    tr.appendChild(td);
                    td = document.createElement("td");
                    td.innerHTML = dt.nation[x].startdate;
                    tr.appendChild(td);
                    td = document.createElement("td");
                    td.innerHTML = "<a href='nationwideDetail.html?id="+dt.nation[x].id+"'>查看详情</a>";
                    tr.appendChild(td);
                    if(dt.nation[x].status=="1"){
                        $(tr).appendTo($("#nationPosting").find("tbody"));
                    }else if(dt.nation[x].status=="2"){
                        $(tr).appendTo($("#nationPosted").find("tbody"));
                    }
                }
            }
        });
    }
    //获取短信
    var messages;
    $.ajax({
        type:"post",
        url:"recmessage",
        data:"dt="+JSON.stringify(getmessage),
        success:function(dt){
            dt = eval('('+dt+')');
            messages = dt;
            for(var x = 0;x<dt.messages.length;x++){
                var tr = document.createElement("tr");
                var tr2 = document.createElement("tr");
                var td2 = document.createElement("td");
                var td = document.createElement("td");
                td.innerHTML = dt.messages[x].id;
                tr.appendChild(td);
                td = document.createElement("td");
                td.innerHTML = "<a href='javascript:void(0)' data-toggle='modal' data-target='#myModal'>"+dt.messages[x].title+"</a>";
                tr.appendChild(td);
                td = document.createElement("td");
                td.innerHTML = dt.messages[x].from;
                tr.appendChild(td);
                td = document.createElement("td");
                td.innerHTML = dt.messages[x].createtime;
                tr.appendChild(td);
                if(dt.messages[x].status=="1"){
                    $(tr).appendTo($("#messageUnread").find("tbody"));
                }else if(dt.messages[x].status=="2"){
                    $(tr).appendTo($("#messageRead").find("tbody"));
                }
            }
            //未读数量提示
            var unreadCount = $("#messageUnread").find("tbody").find("tr").length;
            if(unreadCount!=0){
                $("#unreadCount").text(unreadCount).show();
            }
            //短信操作
            $('#messageUnread').find("tbody").find("tr").find("a").click(function(){
                if($(this).css("font-weight")=="bold"){
                    $(this).css("font-weight","normal");
                    $("#unreadCount").text($("#unreadCount").text()-1);
                    if($("#unreadCount").text()=="0"){
                        $("#unreadCount").hide();
                    }
                }
                for(var x = 0;x<messages.messages.length;x++){
                    if(messages.messages[x].id==$(this).parent().parent().find("td:first-child").text()){
                        $("#myModalLabel").text(messages.messages[x].title);
                        $("#myModalP").text(messages.messages[x].detail);
                    }
                }
                var x = {};
                x.id = $(this).parent().parent().find("td:first-child").text();
                $.ajax({
                    type:"post",
                    url:"readmessage",
                    data:"dt="+JSON.stringify(x),
                    success:function(){
                        
                    }
                })
            })
            $('#messageRead').find("tbody").find("tr").find("a").click(function(){
                for(var x = 0;x<messages.messages.length;x++){
                    if(messages.messages[x].id==$(this).parent().parent().find("td:first-child").text()){
                        $("#myModalLabel").text(messages.messages[x].title);
                        $("#myModalP").text(messages.messages[x].detail);
                    }
                }
            })
        }
    })
    //保存资料
    $("#toSave").click(function(){
        var x = {};
        x.id = sessionStorage.getItem("id");
        x.password = $("#stPastPassword").val();
        if($("#stEmail").val()!=""&&$("#stNewPassword").val()!=""){
            x.type = "3";
            x.newPassword = $("#stNewPassword").val();
            x.email = $("#stEmail").val();
        }else if($("#stEmail").val()==""){
            x.type = "2";
            x.newPassword = $("#stNewPassword").val();
        }else if($("#stNewPassword").val()==""){
            x.type = "1";
            x.email = $("#stEmail").val();
        }
        $.ajax({
            type:"POST",
            url:"saveinfo",
            data:"dt="+JSON.stringify(x),
            success:function(dt){
                if(dt=="success"){
                    alert("保存成功");
                    location.reload();
                }else if(dt=="error"){
                    alert("密码错误，请重试")
                }
            }
        })
    })
	//左侧导航栏
	$("a.list-group-item").each(function(){
		$(this).bind('click',function(){
			$(this).addClass('active');
			$(this).siblings().removeClass('active');
			$(".info-div").hide();
			$('.info-div').eq($(this).index()).fadeIn('slow');
			
		})
	})
	//主体初显
	if(true){
		$('.info-div').eq(0).show();
	}
	//图表开关
	$('#activeChart').click(function(){
		$('#chart').slideToggle(function(){
			$('body').animate({'scrollTop':1000},1000);
		});
	})
	//解决highchart首次渲染问题
	$('#chart').css('width',$('.col-md-9').width()-30);   
	//统计图表
	var newChart = new Highcharts.Chart({                                           
        chart: {
        	renderTo:'chart',                                                           
            type: 'column'                                                    
        },                                                                 
        title: {                                                           
            text: 'Check out your contributions since you joined us!'                    
        },                                                                 
        subtitle: {                                                        
            text: '再次点击蓝色按钮可收起'                                  
        },                                                                 
        xAxis: {                                                           
            categories: ['成功拼车数', '发布拼车信息数'],
            title: {                                                       
                text: null                                                 
            }                                                              
        },                                                                 
        yAxis: {                                                           
            min: 0,                                                        
            title: {                                                       
                text: '次数 (time)',                             
                align: 'high'                                              
            },                                                             
            labels: {                                                      
                overflow: 'justify'                                        
            }                                                              
        },                                                                 
        tooltip: {                                                         
            valueSuffix: ' 次'                                       
        },                                                                 
        plotOptions: {                                                     
            bar: {                                                         
                dataLabels: {                                              
                    enabled: true                                          
                }                                                          
            }                                                              
        },                                                                 
        legend: {                                                          
            layout: 'vertical',                                            
            align: 'right',                                                
            verticalAlign: 'top',                                          
            x: -40,                                                        
            y: 100,                                                        
            floating: true,                                                
            borderWidth: 1,                                                
            backgroundColor: '#FFFFFF',                                    
            shadow: true                                                   
        },                                                                 
        credits: {                                                         
            enabled: false                                                 
        },                                                                 
        series: [{                                                         
            name: 'This month',                                             
            data: [107, 31]                                   
        }, {                                                               
            name: 'Career',                                             
            data: [133, 156]                                  
        }, {                                                               
            name: 'AVG This month',                                             
            data: [97, 91]                                
        }, {
        	name: 'AVG Career',
        	data: [40, 50]
        }]                                                                 
    });
})
    