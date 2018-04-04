<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="${baseUrl }/">
<title>网站后台</title>
<link rel="stylesheet" href="static/css/bootstrap.css">
<link rel="stylesheet" href="static/css/admin.css">
</head>
<body>
<div class="header">
</div>
<div class="body">
<jsp:include page="nav.jsp"></jsp:include>
<div class="col-md-10">
	<div style="padding:20px;">
		<form class="form-inline">
			<div class="form-group">
				<p class="form-control-static">添加学校</p>
			</div>
			<div class="form-group">
				<input type="text" class="form-control" id="newUname" placeholder="学校名称">
			</div>
			<a class="btn btn-default" onclick="addUniversity();">添加</a>
		</form>
	</div>
	<c:forEach items="${universities }" var='un'>
		<div class="col-md-3 uitem" onclick="univer(${un.uid})">
			<span <c:if test="${un.ulvl == 0 }">class="unable"</c:if>>${un.uname }</span>
		</div>
	</c:forEach>
</div>
</div>

<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/bootstrap.js"></script>
<script src="static/myjs/admin.js"></script>
<script>
$('#univermanager').addClass('active');
</script>
</body>
</html>