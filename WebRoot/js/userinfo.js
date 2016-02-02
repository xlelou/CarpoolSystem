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
    $("#toSave").click(function(){
        var x = {};
        x.id = sessionStorage.getItem("id");
        x.password = $("#stLastPassword").val();
        if($("#stEmail").val()!=""&&$("#stNewPassword").val()!=""){
            x.type = "3";
            x.newPassword = $("#stNewPassword").val();
            x.email = $("#stEmail").val();
        }else if($("#stEmail").val()==""){
            x.type = "2";
            x.newPassword = $("#stNewPassword").val();pe = "2";
        }else if($("#stNewPassword").val()==""){
            x.type = "1";
            x.email = $("#stEmail").val();
        }
        $.ajax({
            type:"POST",
            url:"saveinfo",
            data:"dt="+JSON.stringify(x);
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
    