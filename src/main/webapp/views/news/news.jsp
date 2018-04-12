<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="${baseUrl }/">
<title>新闻聚合</title>
<link rel="stylesheet" href="static/css/bootstrap.css">
<link rel="stylesheet" href="static/css/news.css">
</head>
<body>
<div class="header">
</div>
<div class="body">
<div class="col-md-2"></div>
<div class="col-md-8">
	<center>
		<h1>${article.atitle }</h1>
		<small class="time">${article.atime }</small>
	</center>
	<div id="src">
		原文地址：<a target="_blank" href="${article.asrc }">${article.asrc }</a>
		<hr>
	</div>
	<div id="content" class="news_content">
		${article.acontent }
	</div>
</div>
</div>
<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/bootstrap.js"></script>
<script src="static/myjs/news.js"></script>
<script>
	$('.time').html(new Date(Number($('.time').html())).toLocaleString());
	$('img').addClass('img-responsive');
</script>
</body>
</html>