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
		<h3>规则添加</h3>
	</div>
	<form action="admin/addXML" class="form-horizontal" method=POST>
		<input name="uid" value="${uid }" type="hidden">
		<div class="form-group">
			<label class="col-sm-2 control-label">起始URL</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="xurl">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">是否为新闻页正则</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="risnews">
			</div>
		</div>
		<hr><h4>新闻列表页解析规则</h4><hr>
		<div class="form-group">
			<label class="col-sm-2 control-label">新闻列表xpath</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="xlist">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">列表下一页xpath</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="xnext">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">列表下一页正则</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="rnext">
			</div>
		</div>
		<hr><h4>新闻详情页解析规则</h4><hr>
		<div class="form-group">
			<label class="col-sm-2 control-label">新闻标题xpath</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="xtitle">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">新闻标题正则</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="rtitle">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">新闻内容xpath</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="xcontent">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">新闻内容正则</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="rcontent">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">新闻时间xpath</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="xtime">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">新闻时间正则</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="rtime">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">新闻id提取正则</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="rnewid">
			</div>
		</div>
		<div class="form-group">
			<input class="btn btn-info" type="submit" value="添加"/>
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