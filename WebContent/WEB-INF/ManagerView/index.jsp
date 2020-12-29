<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>后台管理系统</title>
  <link rel="stylesheet" href="WebView/css/clear_css.css" />
  <link rel="stylesheet" href="WebView/css/manager_page.css"/>
</head>
<body>
	<div class="body">
		<div class="title">
			<h2>后台管理系统</h2>
		</div>
			<ul class="option_list">
				<li class="news_manager">
					<a href="javascript:">&nbsp;&nbsp;新闻版块</a>
					<ul class="secondary_menu">
						<li class="upload"><a href="javascript:">&nbsp;&nbsp;上传</a></li>
						<li class="view_manager"><a href="javascript:">&nbsp;&nbsp;查看与管理</a></li>
					</ul>
				</li>
				<li class="article_manager">
					<a href="javascript:">&nbsp;&nbsp;文章版块</a>
					<ul class="secondary_menu">
						<li class="upload"><a href="javascript:">&nbsp;&nbsp;上传</a></li>
						<li class="view_manager"><a href="javascript:">&nbsp;&nbsp;查看与管理</a></li>
					</ul>
				</li>
				<li  class="poetry_review_manager">
					<a href="javascript:">&nbsp;&nbsp;瞬诗刊版块</a>
					<ul class="secondary_menu">
						<li class="upload"><a href="javascript:">&nbsp;&nbsp;上传</a></li>
						<li class="view_manager"><a href="javascript:">&nbsp;&nbsp;查看与管理</a></li>
					</ul>
				</li>
				<li class="abouts_manager">
					<a href="javascript:">&nbsp;&nbsp;关于版块</a>
					<ul class="secondary_menu">
						<li class="organization" ><a href="javascript:">&nbsp;&nbsp;组织简介</a></li>
						<li class="team" ><a href="javascript:">&nbsp;&nbsp;我们的团队</a></li>
					</ul>
				</li>
				<li class="picture_manager">
					<a href="javascript:">&nbsp;&nbsp;主页轮播图</a>
					<ul class="secondary_menu">
						<li class="upload"><a href="javascript:">&nbsp;&nbsp;上传</a></li>
						<li class="view_manager"><a href="javascript:">&nbsp;&nbsp;查看与管理</a></li>
					</ul>
				</li>
				<li>
					<a href="WebView/index.html" target="_blank">&nbsp;&nbsp;进入主页</a>
				</li>
			</ul>
			<div class="main_content">
				<!-- 下面这两个DIV用于背景渲染 -->
				<div class="current_title_bg"></div>
				<div class="manager_area_bg"></div>
				
				<div class="current_title">
					标题
				</div>
				<div class="manager_area">
					
				</div>
			</div>
		<div class="clear_float"></div>
	</div>
	<script src="WebView/js/jquery-3.2.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="WebView/js/manager_page.js"></script>
	<script type="text/javascript" src="WebView/js/DateFormat.js"></script>
</body>
</html>