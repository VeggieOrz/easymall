<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/index.css"/>
		<title>欢迎光临EasyMall</title>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/echarts.js"></script>
		<script>
				$(function(){
					// 基于准备好的dom，初始化echarts实例
			        var myChart = echarts.init(document.getElementById('main'));
			        // 指定图表的配置项和数据
			        $.ajax({
						type: "post",
		                url: "${pageContext.request.contextPath }/charts/testJson",
		                dataType:"json",
		                success:function(result){
		                	if(result!=null){
		                		console.log(result);
		                		console.log(result[0]);
		                		var option = {
		        			            title: {
		        			                text: 'ECharts图表'
		        			            },
		        			            tooltip: {},
		        			            legend: {
		        			                data:['销量']
		        			            },
		        			            xAxis: {
		        			                data: []
		        			            },
		        			            yAxis: {},
		        			            series: [{
		        			                name: '销量',
		        			                type: 'bar',
		        			                data: []
		        			            }]
		        			        };
		                		
		                	}myChart.setOption(option);
		                },
		                error:function(data){
		                	alert(data);
		                }
					})
					//获取横坐标
			        $.ajax({
						type: "post",
		                url: "${pageContext.request.contextPath }/charts/testJson",
		                dataType:"json",
		                success:function(result){
		                	if(result!=null){
		                		console.log(result);
		                		var arr=[]
		                		for(var i=0;i<result.length;i++){
		                			arr[i]=result[i].name
		                		}
		                		
		                	}
		                	myChart.setOption({
	                			yAxis: {},
	                			xAxis: {
					                data: arr
					            },
        			            series: [{
        			                name: '销量',
        			                type: 'bar',
        			                data: []
        			            }]
	                		});
		                },
		                error:function(data){
		                	alert(data);
		                }
					})
					//获取catecount
					$.ajax({
						type: "post",
		                url: "${pageContext.request.contextPath }/charts/getCatenum",
		                dataType:"json",
		                success:function(result3){
		                	console.log("success")
		                	if(result3!=null){
		                		console.log(result3);
		                		myChart.setOption({
		                			series: [{
	        			                name: '销量',
	        			                type: 'bar',
	        			                data: result3
	        			            }]
		                		});
		                	}
		                },
		                error:function(data){
		                	alert(data);
		                }
					})
					//end获取catecount
				})
				$(function(){
					// 基于准备好的dom，初始化echarts实例
			        var myChart2 = echarts.init(document.getElementById('main2'));
			        // 指定图表的配置项和数据
			        var option2 = {
			            //title: {
			            //    text: 'ECharts 图2'
			            //},
			            tooltip: {
			                trigger: 'item',
			                formatter: '{a} <br/>{b}: {c} ({d}%)'
			            },
			            legend: {
			            	 orient: 'vertical',
			                 left: 10,
			                data:['手机数码','日用百货','家用电器','图书杂志','服装服饰','床上用品']
			            },
			            xAxis: {
			                data: []
			            },
			            yAxis: {},
			            series: [{
			                name: '销量',
			                type: 'pie',
			                radius: ['50%', '70%'],
			                avoidLabelOverlap: false,
			                label: {
			                    show: false,
			                    position: 'center'
			                },
			                emphasis: {
			                    label: {
			                        show: true,
			                        fontSize: '20',
			                        fontWeight: 'bold'
			                    }
			                },
			                labelLine: {
			                    show: false
			                },
			                data: [
			                    {value: 215, name: '手机数码'},
			                    {value: 210, name: '日用百货'},
			                    {value: 204, name: '家用电器'},
			                    {value: 135, name: '图书杂志'},
			                    {value: 148, name: '服装服饰'},
			                    {value: 150, name: '床上用品'},
			                    {value: 150, name: '汽车用品'}
			                ]
			            }]
			        };
			        // 使用刚指定的配置项和数据显示图表。
			        myChart2.setOption(option2);	        
			        $.ajax({
						type: "post",
		                url: "${pageContext.request.contextPath }/charts/getCatenum",
		                dataType:"json",
		                success:function(result2){
		                	if(result2!=null){
		                		myChart2.setOption({
		                			//xAxis: {
						            //    data: [result[0].name,result[1].name,result[2].name,result[3].name,result[4].name,result[5].name]
						            //}
		                			series: [{
		                				data: [
		                					{value: result2[0], name: '手机数码'},
		    			                    {value: result2[1], name: '日用百货'},
		    			                    {value: result2[2], name: '家用电器'},
		    			                    {value: result2[3], name: '图书杂志'},
		    			                    {value: result2[4], name: '服装服饰'},
		    			                    {value: result2[5], name: '床上用品'},
		    			                    {value: result2[6], name: '汽车用品'}
		                				]
		                			}]
		                		});
		                	}
		                },
		                error:function(data){
		                	alert(data);
		                }
					})
				})
		</script>
	</head>
	<body>
	<!-- 将头部(_head.jsp)包含进来 -->
	<jsp:include page="/WEB-INF/jsp/_head.jsp"/>
		
		<div id="main" style="width: 600px;height:400px;"></div>
		<div id="main2" style="width: 600px;height:500px;"></div>
		<!-- 将尾部(_foot.jsp)包含进来 -->
	<jsp:include page="/WEB-INF/jsp/_foot.jsp"/>
	+
	</body>
</html>
