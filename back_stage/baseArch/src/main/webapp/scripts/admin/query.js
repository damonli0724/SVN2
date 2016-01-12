/**
 * 管理员管理
 */

var PAGE_SIZE = 10; 
var dataList = $('#J_DataList');
var noDataMsg = $('#J_NoDataMsg'); 
var submitButton = $("#searchBtn");
var form =$("#searchForm");
var searchCondition = null;

$(function(){

	load(1,PAGE_SIZE); //加载数据
	submitButton.bind("click", {curPageNo : 1, pageSize : PAGE_SIZE}, dataSesaltedfish);
	
	
})

//表单搜索
function dataSesaltedfish(dataObj){
		searchCondition = form.serialize();
     	load(dataObj.data.curPageNo, dataObj.data.pageSize) ;
}

function load(curPageNo, pageSize) {
        dataList.show();
		noDataMsg.hide();
		
		var queryUrl = $("#globe_context_id").val()+"/background/admin/list/page/data"; // 查询数据url
		queryUrl = queryUrl + '?' + 'startPage=' + curPageNo + '&pageSize=' + pageSize;
		$.ajax(queryUrl, {
			type : 'GET',
			dataType : 'json',
			data : searchCondition 
		}).done(function(response) {
			if (response && response.result  && response.result.length) {

				var template = Handlebars.compile($("#dataListTemplate").html());
		        dataList.html(template(response));
		        
		        $('.pagination').jqPagination({
		        	current_page:curPageNo,
		        	max_page:parseInt(response.count/PAGE_SIZE+1),
		        	page_string:'当前第{current_page}页,共{max_page}页',
		        	paged: function(page) {
		        		load(page,PAGE_SIZE); //加载数据
		        	}	
		    	});
		        
		        
			} else {
				dataList.empty().css('height', '40px').hide();
				noDataMsg.show();
			}
			//绑定列表按钮事件处理函数
//			$("a[name='editBtn']").on("click", showEditForm);
//			$("a[name='deleteBtn']").on("click", delData);
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
/*管理员-增加*/
function admin_add(title,url,w,h){
	layer_show(title,url,800,600);
}
/*管理员-删除*/
function admin_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:1000});
	});
}
/*管理员-编辑*/
function admin_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*管理员-停用*/
function admin_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_start(this,id)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已禁用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
	});
}

/*管理员-启用*/
function admin_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_stop(this,id)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!', {icon: 6,time:1000});
	});
}
