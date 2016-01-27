$(function(){
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
    