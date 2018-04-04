<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="${baseUrl }/">
<title>规则查看</title>
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
		<h3>规则查看</h3>
		<a class="btn btn-warning" onclick="editXML();">编辑</a>
		<a href="admin/deleteXML/${inter.xid }" class="btn btn-danger">删除</a>
	</div>
	<form id="interForm" class="form-horizontal" method=POST>
		<input name="xid" value="${inter.xid }" type="hidden">
		<input name="uid" value="${inter.uid }" type="hidden">
		<div class="form-group">
			<label class="col-sm-2 control-label">起始URL</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" value="<c:out value="${inter.xurl}"/>" name="xurl" readOnly>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">是否为新闻页正则</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" value="<c:out value="${inter.risnews}"/>" name="risnews" readOnly>
			</div>
		</div>
		<hr><h4>新闻列表页解析规则</h4><hr>
		<div class="form-group">
			<label class="col-sm-2 control-label">新闻列表xpath</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" value="<c:out value="${inter.xlist}"/>" name="xlist" readOnly>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">列表下一页xpath</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" value="<c:out value="${inter.xnext}"/>" name="xnext" readOnly>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">列表下一页正则</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" value="<c:out value="${inter.rnext}"/>" name="rnext" readOnly>
			</div>
		</div>
		<hr><h4>新闻详情页解析规则</h4><hr>
		<div class="form-group">
			<label class="col-sm-2 control-label">新闻标题xpath</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" value="<c:out value="${inter.xtitle}"/>" name="xtitle" readOnly>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">新闻标题正则</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" value="<c:out value="${inter.rtitle}"/>" name="rtitle" readOnly>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">新闻内容xpath</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" value="<c:out value="${inter.xcontent}"/>" name="xcontent" readOnly>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">新闻内容正则</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" value="<c:out value="${inter.rcontent}"/>" name="rcontent" readOnly>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">新闻时间xpath</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" value="<c:out value="${inter.xtime}"/>" name="xtime" readOnly>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">新闻时间正则</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" value="<c:out value="${inter.rtime}"/>" name="rtime" readOnly>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">新闻id提取正则</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" value="<c:out value="${inter.rnewid}"/>" name="rnewid" readOnly>
			</div>
		</div>
		<div id="formOP">
			
		</div>
	</form>


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