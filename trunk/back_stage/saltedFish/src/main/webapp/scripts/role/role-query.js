/**
 * 角色管理
 */
var deleteUrl = $('#globe_context_id').val()+"/background/role/delete/data"  //删除数据Url

function admin_role_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*管理员-角色-编辑*/
function admin_role_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*管理员-角色-删除*/
function admin_role_del(obj,id){
	layer.confirm('角色删除须谨慎，确认要删除吗？',function(index){
		$.ajax({
	  		url: deleteUrl,
	  		type:"POST",
	  		data: {roleId:id},
	  		success:function(res){
	  			if(res.status==1){
	  				$(obj).parents("tr").remove();
	  				layer.msg('已删除!',{icon:1,time:1000});
	  			}else{
	  			  layer.msg('网络异常!',{icon: 5,time:1000});
	  				 }
	  			}
	  		})
		
	});
}
