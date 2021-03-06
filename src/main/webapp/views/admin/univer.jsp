<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="${baseUrl }/">
<title>${univer.uname }</title>
<link rel="stylesheet" href="static/css/bootstrap.css">
<link rel="stylesheet" href="static/css/admin.css">
</head>
<body>
<div class="header">
</div>
<div class="body">
<jsp:include page="nav.jsp"></jsp:include>
<div class="col-md-10">
	<div>
		<span onclick="editUname();" id="edituname" class="glyphicon glyphicon-pencil utitle_edit"></span>
		<span style="display:none;" onclick="updateUname();" id="updateuname" class="glyphicon glyphicon-ok utitle_edit"></span>
		<span style="display:none;" onclick="rollbackUname();" id="rollbackuname" class="glyphicon glyphicon-remove utitle_edit"></span>
		<input id="uname" class="utitle" type="text" value="${univer.uname }" readOnly>
	</div>
	<h3>规则列表</h3><small><a href="admin/addXML/${univer.uid }" class="btn btn-sm btn-info">添加规则</a>
		<a onclick="changeType();" class="btn btn-sm btn-warning">修改类别</a>
	</small><hr>
	<form class="form-inline" id="change_div" style="display:none;">
		<div class="form-group">
			<label>爬虫类型</label>
			<select class="form-control" id="SpiderType">
				<option <c:if test="${univer.uextend == 0}">selected</c:if> value="0">规则类爬虫</option>
				<option <c:if test="${univer.uextend == 1}">selected</c:if> value="1">自定义类爬虫</option>
			</select>
		</div>
		<div class="form-group">
			<label>爬虫类名</label>
			<input type="text" class="form-control" id="extendSpiderName" value="${univer.uspider }" placeholder="爬虫类名">
		</div>
		<a class="btn btn-success" onclick="updateType();">更新</a>
	</form><hr>
	<ul class="list-group">
		<c:forEach items="${inters }" var="inter">
			<li class="list-group-item">
				 <a href="admin/xml/${inter.xid }" class="btn btn-info btn-sm">查看</a>
				 <a href="admin/deleteXML/${inter.xid }" class="btn btn-danger btn-sm">删除</a>
				<span class="glyphicon glyphicon-arrow-right"></span>
				${inter.xurl }
			</li>
		</c:forEach>
	</ul>
</div>
</div>
<div style="display:none;">
<div id="uid">${univer.uid }</div>
</div>
<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/bootstrap.js"></script>
<script src="static/myjs/admin.js"></script>
<script>
$('#univermanager').addClass('active');
</script>
</body>
</html>