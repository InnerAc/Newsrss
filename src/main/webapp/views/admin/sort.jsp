<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="${baseUrl }/">
<title>展示顺序</title>
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
	<div style="padding:20px;">
		<h3>高校展示顺序</h3>
		<button onclick="select();" class="btn btn-success">保存排序</button>
	</div>
	<h4>展示中的高校</h4><hr>
	<div class="col-md-12 connectedSortable" id="ableUnivers">
		<c:forEach items="${ableUniver }" var='un'>
			<div uid="${un.uid }" class="col-md-3 uitem">
				<span>${un.uname }</span>
			</div>
		</c:forEach>
	</div>
	<hr><h4>未展示的高校</h4><hr>
	<div class="col-md-12 connectedSortable" id="unableUnivers">
		<c:forEach items="${unableUniver }" var='un'>
			<div uid="${un.uid }" class="col-md-3 uitem">
				<span class="unable">${un.uname }</span>
			</div>
		</c:forEach>
	</div><hr>
</div>
</div>

<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/jquery-ui.js"></script>
<script src="static/js/bootstrap.js"></script>
<script src="static/myjs/admin.js"></script>
<script>
$('#universort').addClass('active');
$( "#ableUnivers, #unableUnivers" ).sortable({
    connectWith: ".connectedSortable"
  }).disableSelection();
</script>
</body>
</html>