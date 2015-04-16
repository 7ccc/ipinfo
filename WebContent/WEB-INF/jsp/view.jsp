<%@page import="web.JsonEntity"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>IP信息获取</title>
    <!-- 网页加载进度条，拿来玩玩 -->
    <link rel="stylesheet" href="css/pace.css"/>
    <script type="text/javascript" src="js/pace.js"></script>
    
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap-theme.min.css"/>
	<link rel="stylesheet" href="css/flags.css"/>
    <!--[if lt IE 9]>
	  	<script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	  	<script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
    <style type="text/css">
        .layout{
            width: 640px;
            margin: 0 auto;
        }
        .top{
            width: 100%;
            height: 60px;
            padding: 25px 0 25px 0;
        }
        .search{
            float: right;
        }
        .logo{
            font-size: 30px;
            line-height: 35px;
            color: #cccccc;
        }
        .logo:hover{
            color: #9999B0;
        }
        .show-ip{
            font-size: 60px;
            text-align: center;
            font-weight: bold;
            font-family: 微软雅黑;
        }
        .info-table{
            margin: 30px 0 30px 0;
        }
        .key{
            width: 35%;
        }
        .value{
            width: 65%;
        }
        .json{
            margin: 30px 0 30px 0;
        }
        .copyright{
            text-align: right;
            color: #cccccc;
            margin: 30px 0 30px 0;
        }
        .map-img{
        	width: 640px;
        	height: 200px;
        	text-align: center;
        	line-height: 200px;
        }
        #loc-img{
        	margin: 0 auto;
        }
        #tips{
        	color: #CCCCCC;
        	font-family: 微软雅黑;
        	font-size: 16px;
        }
    </style>
</head>
<body>
    <div class="layout">
        <div class="top">
            <a class="logo" href="#">IP-INFO</a>
            <div class="search">
                <form action="" method="post" class="form-inline">
                    <input class="form-control" id="ip-input" name="ip" placeholder="IP Address">
                    <button type="submit" class="btn btn-default">Search</button>
                </form>
            </div>
        </div>
        <hr/>
        <h1 class="show-ip">${info.ip}</h1>
        <div class="map-img">
        	<img id="loc-img" src="images/loading.gif"/>
        	<a id="tips">图片正在路上...</a>
        </div>
        <div class="info-table">
            <table class="table table-striped">
                <tr>
                    <td class="key">As</td>
                    <td class="value">${info.as}</td>
                </tr>
                <tr>
                    <td class="key">City</td>
                    <td class="value">${info.city}</td>
                </tr>
                <tr>
                    <td class="key">Region</td>
                    <td class="value">${info.region}</td>
                </tr>
                <tr>
                    <td class="key">Country</td>
                    <td class="value"><span class="flag flag-<%=((JsonEntity)request.getAttribute("info")).getCountry().toLowerCase()%>"></span>${info.country}</td>
                </tr>
                <tr>
                    <td class="key">Loc</td>
                    <td class="value">${info.loc}</td>
                </tr>
                <tr>
                    <td class="key">Org</td>
                    <td class="value">${info.org}</td>
                </tr>
            </table>
        </div>
        <div class="json">
            <pre>The json result from <abbr title="此JSON结果集来自ip-api.com">ip-api.com</abbr>:
${info.json}</pre>
        </div>
        <hr/>
        <p class="copyright">By&nbsp;<a href="tencent://Message/?uin=412436000">OneSky</a>.</p>
    </div>
</body>
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		$.get("GetLocImg","r="+new Date().getTime(),function(data){
			$("#loc-img").attr('src','data:image/png;base64,'+data);
			$("#tips").remove();
		});
	});
</script>
</html>