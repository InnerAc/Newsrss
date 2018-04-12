function univer(uid){
	window.location.href = 'admin/univer/'+uid;
}
function addUniversity(){
	uname = $('#newUname').val();
	$.post('admin/addUni',{uname:uname},function(data){
		if(data > 0){
			location.reload();
		}
	});
}
var old_uname;
/**
 * 编辑高校名称
 */
function editUname(){
	uid = $('#uid').html();
	input = $('#uname');
	old_uname = input.val();
	input.removeAttr('readOnly');
	input.focus();
	$('#edituname').hide();
	$('#updateuname').show();
	$('#rollbackuname').show();
}
/**
 * 更新高校名称
 */
function updateUname(){
	uid = $('#uid').html();
	input = $('#uname');
	new_uname = input.val();
	
	$.post('admin/editUname',{uid:uid,uname:new_uname},function(data){
		if(data == 0){
			alert('修改成功')
			input.attr("readOnly","true");
			$('#edituname').show();
			$('#updateuname').hide();
			$('#rollbackuname').hide();	
		}else{
			alert('修改失败');
		}
	});
	
}
/**
 * 放弃修改高校名称
 */
function rollbackUname(){
	input = $('#uname');
	input.attr("readOnly","true");
	input.val(old_uname);
	$('#edituname').show();
	$('#updateuname').hide();
	$('#rollbackuname').hide();
}

/**
 * 修改高校类别
 */
function changeType(){
	change_div = $('#change_div');
	change_div.show();
}

/**
 * 更新高校类别
 */
function updateType(){
	uid = $('#uid').html();
	spiderName = $('#extendSpiderName').val();
	spiderType = $('#SpiderType').val();
	$.post('admin/editType',{uid:uid,uspider:spiderName,uextend:spiderType},function(data){
		if(data == 0){
			alert('修改成功')
		}else{
			alert('修改失败');
		}
	});
}
/**
 * 编辑规则
 */
function editXML(){
	interForm = $('#interForm');
	interForm.attr('action','admin/updateXML');
	$('input[readOnly]').removeAttr('readOnly');
	formOP = $('#formOP');
	formOP.html('<input style="margin-left:10px;" type="submit" value="更新" class="btn btn-info" /><a style="margin-left:10px;" onclick="location.reload();" class="btn btn-warning">返回</a>')
}

/**
 * 排序完成提交
 */
function select(){
	univerlist = $('#ableUnivers');
	univers = univerlist.children();
	uids = '';
	for(var i=0;i<univers.length;i++){
		if(i>0){
			uids += ',';
		}
		uids += univers[i].getAttribute('uid');
	}
	$.post('admin/sortlist',{sorts:uids},function(data){
		location.reload();
	});
}

/**
 * 生成爬虫状态html
 * @param item
 */
function genMonitor(item){
	str = '<div class="col-md-4"><div class="panel panel-info"><div class="panel-heading">'
			+ item['uname']+'->'+item['xid']
			+ '</div><div class="panel-body"><ul class="list-group" style="margin:0;"><li class="list-group-item">爬虫状态:'
			+ item['status']
			+ '</li><li class="list-group-item">爬取数量:'
			+ item['count']
			+ '</li><li class="list-group-item">启动时间:'
			+ item['time']
			+ '</li></ul></div></div></div>';
	return str;
}

/**
 * 展示爬虫信息
 */
function spiderInfo(){
	$.getJSON('admin/spiderInfos',function(data){
		content = $('#spiders');
		content.html('');
		for(var i=0;i<data.length;i++){
			content.append(genMonitor(data[i]));
		}
	});
}

function startSpider(){
	$.get('admin/startSpider',function(data){
		spiderInfo();
	});
}