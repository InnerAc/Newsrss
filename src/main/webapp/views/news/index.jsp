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
<div class="col-md-2">
	<ul id="univers" class="list">
		<li class="list_title">学校列表</li>
	</ul>
</div>
<div class="col-md-7">
	<div id="items">
	</div>
	<div class="loadMore" onclick="addItems();">
		<span class="glyphicon glyphicon-chevron-down"></span>加载更多
	</div>
</div>
<div class="col-md-3">
	<form class="form-inline">
		<div class="form-group">
			<input type="text" class="form-control" id="searchInput" placeholder="关键字">
		</div>
		<a class="btn btn-default" onclick="">搜索</a>
	</form>
</div>
<div class="back_top"><a href="#top" class="back-to-top"> 返回顶部 </a></div>
</div>
<div style="display:none">
<div id="active_uid">${uid }</div>
<div id="pagelimit">0</div>
</div>
<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/bootstrap.js"></script>
<script src="static/myjs/news.js"></script>
<script>
genUniver();
genItems();
</script>
</body>
</html>