<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="${baseUrl }/">
<title>爬虫状态</title>
<link rel="stylesheet" href="static/css/bootstrap.css">
<link rel="stylesheet" href="static/css/admin.css">
<style>
.connectedSortable{
	min-height: 100px;
}
</style>
</head>
<body>
<div class="header">
</div>
<div class="body">
<jsp:include page="nav.jsp"></jsp:include>
<div class="col-md-10">
	<div>
		<h3>爬虫状态</h3>
		<button onclick="startSpider();" class="btn btn-success">开始爬取</button>
	</div>
	<hr>
	<div class="col-md-12" id="spiders">
	</div>
</div>
</div>

<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/bootstrap.js"></script>
<script src="static/myjs/admin.js"></script>
<script>
$('#spidermonitor').addClass('active');
spiderInfo();
</script>
</body>
</html>