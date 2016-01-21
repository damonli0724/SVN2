
var tree = null;  //权限树
var loadDataUrl =$("#globe_context_id").val()+"/background/resource/tree/data"; //url
var selectNodeIds=null;
var setting = {   //Ztree  setting;
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback :{
			onCheck:onCheck
		}
	};

	
	$(function(){
		var nodes  =getNodes();
		tree = $.fn.zTree.init($("#tree"), setting, nodes);  //初始化tree  :加载出json 数据就可以了 
	})
	

function onCheck(e,treeId,treeNode){
     var treeObj=tree,
     nodes=treeObj.getCheckedNodes(true),
     v="";
     for(var i=0;i<nodes.length;i++){    //将选中的Id 传回到数据库中，进行修改(除了根节点0)
			if(nodes[i].id==1010)continue;
			v+=nodes[i].id + ",";
     }
     selectNodeIds = v ;
}
	
	
//加载节点
function getNodes(){   
	var result ="";
	var roleId = $("#tree").attr("roleId");  //根据Tree 的属性 roleId 进行查询
	$.ajax({
		   type: "GET",
		   url: loadDataUrl,
		   data: {roleId:roleId},
		   async: false,
		   success: function(res){
		     if(res&&res.status=='1'){
		    	result = res.result;
		     }
		   }
		});
	return result;
}

function add(){
	
	alert(selectNodeIds);
}

