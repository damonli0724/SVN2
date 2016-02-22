
var context= $('#globe_context_id').val();
var form =$("#addForm");
var loadResUrl = context+"/background/resource/query/parentId"  //根据父Id加载主菜单，子菜单或者按钮数据
var addUrl = context+"/background/resource/add/data"; //添加Url
var mainMenuParentId=0; //主菜单的父Id为0;


$(function(){
	//表单添加
	$('#submitBtn').bind("click",addAdmin);
	
	//根据不同类型 显示不同的页面  0-主菜单 不显示 1-子菜单 (显示所属主菜单) 2-按钮 (显示所属主菜单，所属子菜单)
	$("input[name='type']").bind("click",changeUI);
	
	//加载主菜单数据
	loadMenuData(mainMenuParentId,'mainMenu'); 
	
	//绑定子菜单监听事件
	$("#mainMenu").bind("change",loadChildMenuData);
	
	
	//根据单选框选中的值做不同的校验
  	jQuery.validator.addMethod("checkMainMenu",checkMainMenu, "主菜单不能为空");  
	jQuery.validator.addMethod("checkChildMenu",checkChildMenu, "子菜单不能为空");  
});


/**
 * 根据不同的type类型显示不同的页面
 */
function changeUI(){
	var type = $(this).val(); 
	if(type=='0'){//隐藏所属主菜单，子菜单
		$("#mainMenuDiv").hide();
		$("#childMenuDiv").hide();
	}
	
	if (type=='1') {//显示所属主菜单
		$("#mainMenuDiv").show();
		$("#childMenuDiv").hide();
	}
	
	if(type=='2'){//显示 所属主菜单，子菜单
		$("#mainMenuDiv").show();
		$("#childMenuDiv").show();
	}
	//将类型赋值给resType 
	$("#resType").val(type);
	
}

/**
 * 加载所属主菜单或者所属子菜单数据
 * @param parentId 父Id
 * @param divId  div的Id
 */
function loadMenuData(parentId,divId){
	$.ajax({
  		url: loadResUrl,
  		type:"GET",
  		data: {parentId:parentId},
  		async : false,
  		success:function(res){
  			if(res.status==1){
  				var $menu = $("#"+divId);
  				
  				$menu.find("option").remove();
  				$menu.append("<option value=''>--请选择--</option>");
  				for (var i = 0; i < res.result.length; i++) {
  			    	$menu.append( "<option value='" + res.result[i].resId + "'>"   + res.result[i].resName + "</option>"); 
  			     }
  			}else{
  			  layer.msg('网络异常!',{icon: 5,time:1000});
  				 }
  			}
  		})
}

/**
 * 	所属主菜单校验
 * @param value
 * @param element
 * @returns {Boolean}
 */
function checkMainMenu(value, element) {   
		var type =$("input[name='type']:checked").val();
		if(type==0)return true;
		if(type==1||type==2)return value!='';
}


/**
 *  所属子菜单校验
 * @param value
 * @param element
 * @returns {Boolean}
 */
function checkChildMenu(value, element) { 
  		var type =$("input[name='type']:checked").val();
  		if(type==0||type==1)return true;
  		if(type==2)return value!='';
    }

/**
 * 表单提交
 */
function addAdmin(){
	var type =$("input[name='type']:checked").val();
	
	  if (validateAddForm()) {
		  var parentId;
			if(type=="0"){
				parentId=mainMenuParentId;
			}
			if(type=="1"){//获取所属主菜单选中的值
				parentId=$("#mainMenu").find("option:selected").val();
			}
			if(type=="2"){//获取所属子菜单选中的值
				parentId=$("#childMenu").find("option:selected").val();
			}
			$("#resParentId").val(parentId);
			
		$.ajax({
	  		url: addUrl,
	  		type:"POST",
	  		data: form.serialize(),
	  		success:function(res){
	  			if(res.status==1){
	  				closeWindow();  
	  			}else{
	  			  layer.msg('网络异常!',{icon: 5,time:1000});
	  				 }
	  			}
	  		})
	}  
}

/**
 * 加载所属子菜单数据
 */
function loadChildMenuData(){
	loadMenuData($('#mainMenu :selected').val(),'childMenu');
}


function validateAddForm() {
	form.validate({
		rules : {
			resName : {
				required:true,
			},
			mainMenu : {
				checkMainMenu : true,
			},
			childMenu : {
				checkChildMenu : true,
			},
			resKey : {
				required:true,
			},
			resUrl : {
				required : true,
			},
			resLevel : {
				required : true,
			},
			resDes:{
				required:true,
			}
		},
		messages : {
			resName : {
				required:"资源名称不能为空",
			},
			resKey : {
				required:"资源Key不能为空",
			},
			resUrl : {
				required : "Url不能为空",
			},
			resLevel : {
				required : "排序不能为空",
			},
			resDes:{
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

/**
 * 关闭窗口
 */
function closeWindow(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.location.reload(); //父窗口刷新
	parent.layer.close(index);//关闭弹窗
}