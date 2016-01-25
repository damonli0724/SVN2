/**
 * 资源管理
 */
var PAGE_SIZE = 10; 
var dataList = $('#J_DataList');
var noDataMsg = $('#J_NoDataMsg'); 
var submitButton = $("#searchBtn");
var form =$("#searchForm");
var searchCondition = null;

var deleteUrl = $('#globe_context_id').val()+"/background/resource/delete"  //删除数据Url

Handlebars.registerHelper('formatTime', _formatTime);   //格式化日期
Handlebars.registerHelper('formatType', _formatType);  //格式化性别
Handlebars.registerHelper('fromatEnable', _fromatEnable);  //格式化是否禁用


$(function(){
	load(1,PAGE_SIZE); //加载数据
	
	submitButton.bind("click", {curPageNo : 1, pageSize : PAGE_SIZE}, dataSearch);//表单搜索
})

//表单搜索
function dataSearch(dataObj){
		searchCondition = form.serialize();
     	load(dataObj.data.curPageNo, dataObj.data.pageSize) ;
}

function load(curPageNo, pageSize) {
        dataList.show();
		noDataMsg.hide();
		
		var queryUrl =$("#globe_context_id").val()+"/background/resource/list/page/data"; // 查询数据url
		queryUrl = queryUrl + '?' + 'startPage=' + curPageNo + '&pageSize=' + pageSize;
		$.ajax(queryUrl, {
			type : 'POST',
			dataType : 'json',
			data : searchCondition 
		}).done(function(response) {
			if (response && response.result  && response.result.length) {
 
				var template = Handlebars.compile($("#dataListTemplate").html());  //编译模板数据
		        dataList.html(template(response));
		        
		        $("#count").text(response.count);   //共有数据
		        $('.pagination').jqPagination({   //初始化 分页插件
		        	current_page:curPageNo,
		        	max_page:parseInt(response.count/PAGE_SIZE+(response.count%10==0?0:1)),
		        	page_string:'当前第{current_page}页,共{max_page}页',
		        	paged: function(page) {
		        		load(page,PAGE_SIZE); //加载数据
		        	}	
		    	});
			} else {
				dataList.empty().css('height', '40px').hide();
				noDataMsg.show();
			}
		});
	};
	
	
/* 
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
/*数据-增加*/
function data_add(title,url,w,h){
	layer_show(title,url,800,600);
}
/*数据-删除*/
function data_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			   type: "POST",
			   url: deleteUrl,
			   data: {resId:id},
			   success: function(res){
				   if(res&&res.status==1){
						layer.msg('已删除!',{icon:1,time:1000});
						load(1,PAGE_SIZE); 
				   }else{
					   layer.msg('网络异常!',{icon: 5,time:1000});
				   }  
			   }
			   });
	});
}
/*数据-编辑*/
function data_edit (title,url,id,w,h){
	layer_show(title,url,800,600);
}




//格式化时间
function _formatTime(datetime){
	if (datetime === null) {
		return '';
	}
	datetime = new Date(datetime);
	return datetime.getFullYear() + '-' + _fillZero((datetime.getMonth() + 1)) + '-' + _fillZero(datetime.getDate());

}
//补零
function _fillZero(data) {
	if (data < 10) {
		return "0" + data;
	}
	return data;
}
/**
 * 格式化参数 0．表示主菜单　1，表示子菜单．2，表示按扭
 * @param data
 */
function _formatType(data){
	var result =""
		switch (data) {
		case '0':
			result='主菜单';
			break;
		case '1':
			result='子菜单';
			break;
		case '2':
			result='按钮';
			break;	
		default:
			result="根节点";
			break;
		}
	return result;
}

function _fromatEnable(data){   
	var nbsp = "&nbsp;";
	var edit ='';
	if(data==1){
		edit= $("<span class='label label-success radius'>").html('已启用')
	}else if(data==0){
		edit=$("<span class='label label-default radius'>").html('已禁用');
	}
	var html=$('<span />').html(edit).append(nbsp).append(nbsp).html();
	return new Handlebars.SafeString(html); 
}

