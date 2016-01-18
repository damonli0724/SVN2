
var context= $('#globe_context_id').val();
var form =$("#addForm");
var checkUrl = context+"/backgrond/admin/check/query"  //判断用户唯一性URL
var addUrl = context+"/background/admin/add/page/data"; //添加Url

$(function(){
	$('#submitBtn').bind("click",addAdmin);
	
  	jQuery.validator.addMethod("checkPwd", function(value, element) {      
  		
         return value==$("#originalPassword").val();       
    }, "初始密码和确认密码不同");  
  	
  	jQuery.validator.addMethod("checkAccountUnique", function(value, element) { 
  		var  flag = false;
  		$.ajax({
  		url: checkUrl,
  		type:"POST",
  		data:{loginName:value},
  		async : false,
  		success:function(res){
  			if(res.status==1){
  				if(res.result==0)flag=true;
  			}else{
  			  layer.msg('网络异常!',{icon: 5,time:1000});
  				 }
  			}
  		})
        return flag;       
   }, "改账号已存在");  
  	
});

function addAdmin(){
	  if (validateAddForm()) {
		$.ajax({
	  		url: addUrl,
	  		type:"POST",
	  		data: form.serialize(),
	  		async : false,
	  		success:function(res){
	  			if(res.status==1){
	  				layer.msg('添加成功!',{icon:1,time:1000});
	  				closeWindow();  //跳转到列表页面
	  			}else{
	  			  layer.msg('网络异常!',{icon: 5,time:1000});
	  				 }
	  			}
	  		})
	}  
}

function validateAddForm() {
	form.validate({
		rules : {
			name : {
				required:true,
				isAccount:true,
				checkAccountUnique:true
			},
			originalPassword : {
				required : true,
				isPwd:true
			},
			confirmPassword : {
				required : true,
				isPwd:true,
				checkPwd:true
			},
			userName : {
				required:true,
			},
			sex : {
				required : true,
			},
			mobile : {
				required : true,
				isMobile:true
			},
			email:{
				required:true,
				isEmail:true
			},
			roleId:{
				required:true,
			},
			description:{
				required:true,
			}
		},
		messages : {
			name : {
				required:"账户不能为空",
			},
			originalPassword : {
				required : "初始密码不能为空",
			},
			confirmPassword : {
				required : "确认密码不能为空",
			},
			userName : {
				required:"真实姓名不能为空",
			},
			sex : {
				required :"性别不能为空",
			},
			mobile : {
				required : "手机号码不能为空",
			},
			email:{
				required:"邮箱不能为空",
			},
			roleId:{
				required:"角色不能为空",
			},
			description:{
				required:"描述不能为空",
			}
		},
		errorPlacement : function(error, element) {
			var p = element.parent().parent();
			error.addClass("Validform_checktip Validform_wrong");
			error.appendTo(p); 
		},
		validClass : "success",
		onkeyup : false
	});
	return form.valid();
}

function closeWindow(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.location.reload(); //父窗口刷新
	parent.layer.close(index);//关闭弹窗
}