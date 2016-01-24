/**
 * 角色添加js
 */
var context= $('#globe_context_id').val();
var form =$("#addForm");
var addUrl = context+"/background/role/add/data"; //添加Url

$(function(){
	$('#addBtn').bind("click",addData);
	
});

function addData(){
	var data ={};
	data.resources=selectNodeIds ; //从公用tree.dataLoad.js 里面获取被选中的值
	data.roleDesc = $("#roleDesc").val();
	data.roleName=$("#roleName").val();
	data.roleId=$("#roleId").val();
	
		$.ajax({
	  		url: addUrl,
	  		type:"POST",
	  		data: data,
	  		success:function(res){
	  			if(res.status==1){
	  				closeWindow();  //跳转到列表页面
	  			}else{
	  			  layer.msg('网络异常!',{icon: 5,time:1000});
	  				 }
	  			}
	  		})
}


function closeWindow(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.location.reload(); //父窗口刷新
	parent.layer.close(index);//关闭弹窗
}