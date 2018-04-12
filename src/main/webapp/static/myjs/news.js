function genUniver(){
	uid = Number($('#active_uid').html());
	$.get('rest/univers',function(data){
		unis = data;
		univers = $('#univers');
		univers.html('');
		univers.append('<li class="list_title">学校列表</li>');
		for(var i=0;i<unis.length;i++){
			if(unis[i]['uid'] == uid){
				univers.append('<li><a value="'+unis[i]['uid']+'" onclick="clickUniver('+unis[i]['uid']+');" class="active">'+unis[i]['uname']+'</a></li>')
			}else{
				univers.append('<li><a value="'+unis[i]['uid']+'" onclick="clickUniver('+unis[i]['uid']+');">'+unis[i]['uname']+'</a></li>')
			}
		}
	});
}
function changeUniver(uid){
	aas = univers.find('a');
	for(var i=0;i<aas.length;i++){
		li = $(aas[i]);
		if(li.attr('value') == uid){
			li.addClass('active');
		}else{
			li.removeClass('active');
		}
	}
}
function genItems(){
	items = $('#items');
	items.html('');
	addItems();
}
function addItems(){
	uid = Number($('#active_uid').html());
	page = Number($('#pagelimit').html());
	items = $('#items');
	$.post('rest/news',{uid:uid,page:page},function(data){
		for(var i=0;i<data.length;i++){
			content = '<div class="item"><p class="title"><a target="_blank" href="news/'
						+data[i]['aid']
						+'">'
						+data[i]['atitle']
						+'</a></p><p class="time">'
						+new Date(data[i]['atime']).toLocaleString()
						+'</p></div>';
			items.append(content)
		}
		page = page+data.length;
		$('#pagelimit').html(page);
	});
}
function clickUniver(uid){
	$('#active_uid').html(uid);
	$('#pagelimit').html(0);
	changeUniver(uid);
	genItems();
}