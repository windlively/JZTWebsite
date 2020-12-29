/**
 * @author 白钧翰
 * 管理页面的JavaScript脚本
 */
//构建一个表单
/*
	author:Bai Jun-han
	参数：
	formId:表单Id号
	action:提交的URL
	enctype:表单编码方式
	para:表单中的控件的参数，为数组类型
	示例：[{"element":控件类型,"preText":控件的前缀文字,"type":type属性值,"name":name属性值,"isMust":是否必须,"class":class属性值,"id":id属性值}]
		如果为select控件，需要传递values数组
		！ name的值必须与对应的实体类中的字段名称一致
	hasPicture:是否含有图片
*/
function initForm(formId,action,enctype,para,hasPicture,callBackFun){
	var form = $("<form>");
	form.attr("id",formId)
	form.attr("action",action);
	form.attr("enctype","multipart/form-data");
	for(var i = 0; i < para.length;i++){
		if(para[i].type == "file"){
			continue;
		}
		var element = $("<"+para[i].element+">");
		element.attr("type",para[i].type);
		element.attr("name",para[i].name);
		element.attr("class",para[i].class);
		element.attr("id",para[i].id);
		if(para[i].element == "select"){
			for(var j = 0; j < para[i].values.length;j++){
				var option = $("<option>");
				option.attr("value",para[i].values[j]);
				if(para[i].values[j] == "justify"){
					option.text("正常");
				}else if(para[i].values[j] == "center"){
					option.text("居中");
				}else{
					option.text(para[i].values[j]);
				}
				
				element.append(option);
			}
		}
		
		var span = $("<span>");
		span.attr("class","preText");
		span.text(para[i].preText+"：");
		if(para[i].type == "hidden"){	
			span.css("display","none");	
		}
		form.append(span);
		form.append(element);
		if(para[i].isMust == true){
			form.append("<span style = 'color:red; font-size:14px;'>*</span>");
		}
		if(para[i].type != "hidden"){
			form.append("<br/>");
		}
	}
	if(hasPicture == true){
		form.append("<br/><p style='display:inline-block; float:left;'>图　　片：</p>");
		form.append("<img id='imagePreview' style='display:none'>");
		form.append($("<input id='uploadPicture' type='file' accept='image/png,image/jpeg,image/jpg' name='illuatrationPicture' style='display:inline-block'>"));
		form.append($("<input id='deletePicture' type='button' value='删除' style='display:none'>"));
		form.append("<br/>");
	}
	var div=$("<div style='text-align:center'>");
	div.append($("<input type='reset' value='重置'>"));
	div.append("&nbsp;&nbsp;&nbsp;&nbsp;");
	div.append($("<input type='submit' value='确认'>"));
	form.append(div);
	form.css("display","none");
	$(".manager_area").append(form);
	form.show(1000);
	

	
	$("select[name='poetryType']").change(function(){
		//如果是原创诗禁止填写诗评部分的信息
		if($("form select[name='poetryType']").val() == "原创诗"){
			$("form input[name='reviewTitle']").attr("disabled",true);
			$("form input[name='reviewAuthor']").attr("disabled",true);
			$("form textarea[name='reviewContent']").attr("disabled",true);
		}else if($("form select[name='poetryType']").val() == "古体诗" ||
				$("form select[name='poetryType']").val() == "近现代诗"){
			$("form input[name='reviewTitle']").attr("disabled",false);
			$("form input[name='reviewAuthor']").attr("disabled",false);
			$("form textarea[name='reviewContent']").attr("disabled",false);
		}
	})
	
	$("#uploadPicture").change(function(){
		var fileDom = this;
        //判断是否支持FileReader
        if (window.FileReader) {
            var reader = new FileReader();
        } else {
            alert("您的浏览器不支持图片预览功能，请使用最新版的Internet Explorer或者Chrome浏览器！");
        }

        //获取文件
        var file = fileDom.files[0];
        var imageType = /^image\//;
        
        if(file == null){
        	$("#imagePreview").css("display","none");
        	$("#uploadPicture").css("margin-left","0px");
        	$("#deletePicture").css("display","none");
        	return;
        }
        
        //是否是图片
        if (!imageType.test(file.type)) {
            alert("请选择图片类型的文件！");
            $(":input[type='file']").val("");
            return;
        }
        //读取完成
        reader.onload = function(file) {
            //获取图片dom
            var img = $("#imagePreview");
            img.css("display","none");
            var fileSize = fileDom.files[0].size;
            if(fileSize > 1400000){
            	alert("图片太大，请上传小于1400KB的图片");
            	$(":input[type='file']").val("");
	        	$("#imagePreview").css("display","none");
	        	$("#uploadPicture").css("margin-left","0px");
	        	$("#deletePicture").css("display","none");
	        	return;
            }
            
            img.attr("src",file.target.result);
			var imgTemp = new Image();
			imgTemp.src = file.target.result;
			imgTemp.onload = function(){
				
				if(parseInt(imgTemp.width) > 600){
					img.css("width",600);
					
				}else{
					img.css("width","");
					img.after("<br/>");
				}
				img.fadeIn(1000);
				$("#uploadPicture").css("margin-left","6em");
	            $("#uploadPicture").css("margin-top","2px");
	            $("#deletePicture").css("display","inline-block");
			}
            
        };
        reader.readAsDataURL(file);
	});
	
	$("#deletePicture").click(function(){
		$(":input[type='file']").val("");
    	$("#imagePreview").css("display","none");
    	$("#uploadPicture").css("margin-left","0px");
    	$("#deletePicture").css("display","none");
	});

	
	/*使用ajax方式异步提交二进制数据的表单*/
	$("#"+formId).on("submit", function(event){
		/*验证表单数据是否完整*/
		for(var i = 0;i < para.length;i ++){
			if(para[i].isMust == true && $(para[i].element+"[name="+para[i].name+"]").attr("disabled") != "disabled" && $(para[i].element+"[name="+para[i].name+"]").val().length <= 0){
				alert("请填写"+para[i].preText+"!");
				return false;
			}
		}
		
	    var form = this;//this代表的就是当前提交表单的DOM对象
	    //用HTML5的FormData对象对表单数据进行构造
	    var formData = new FormData(form);//FormData构造器接收的是一个form的DOM对象

	    $.ajax({
	        url: action,
	        type: "POST",
	        data: formData,
	        dataType: "text",
	        async: true,
	        //要想用jquery的ajax来提交FormData数据,则必须要把这两项设为false
	        processData: false, 
	        contentType: false,
	        enctype: "multipart/form-data",
	        //这里是防表单重复提交
	        beforeSend: function(xhr){
	            $("#"+formId + " :submit").attr("disabled",true);
	        },
	        complete: function(xhr,status){
	            $("#"+formId + " :submit").attr("disabled", false);
	        },
	        error: function(xhr,status,error){
	            alert("请求出错，表单提交失败！");
	        },
	        success: function(result){
	            alert("表单提交成功!");
	            $("#imagePreview").css("display","none");
	        	$("#uploadPicture").css("margin-left","0px");
	        	$("#deletePicture").css("display","none");
	            form.reset();
	            if(callBackFun != undefined){
	            	callBackFun();
	            }
	        }
	    });
	    //阻止表单默认submit按钮的提交事件
	    return false;
	});
}
/*
	初始化一个用于数据管理的表格
	author:Bai Jun-han
	参数：
	actions:对象类型，用于传入所需要的请求地址
		示例	actions = {"getList":"获取实体列表的url地址",
			"deleteEntity":"通过Id删除某个实体的url地址",
			"getEntity":"通过Id获得某个实体的url地址",
			"updateEntityFull":"可更新某个实体所有内容的url地址（包含二进制数据）",
			"updateEntity":"更新某个实体部分内容的url地址（不包含二进制数据）"}
	page:int类型，表示初始化的页码
	diaplayCount：int类型，一页显示的条数
	ths:数组类型，表头数据
	dataInfo：对象类型，需要显示的数据信息
		示例  dataInfo = {
			"dataListName":"实体列表的名称",
			"entityName":"实体的名称",
			"pictureSrcName":"实体的图片的字段名称",
			"props"://数组类型，需要在表格中显示的字段，name对应实体中的字段名称
				[
				{"name":"newsId","id":"entityId"},//第一项必须为实体的Id字段,id也必须为entityId
				{"name":"newsTitle","class":"update_area"},//class为update_area的单元格可以直接在列表中修改信息
				{"name":"newsAuthor","class":"update_area"},
				{"name":"readAmounts"},
				{"name":"uploadTime"},//如果为uploadTime或者updateTime会格式化为正确的时间格式
				{"name":"updateTime"}
			]//！props的长度必须等于ths的长度减一，并且相互对应，name的值必须与实体类中的字段名称一致
	}
	initFormPara：数组类型，为initForm()中的para参数
	
	备注：该函数需要与initForm()配合使用
*/
function initTableList(actions,page,displayCount,ths,dataInfo,initFormPara){
	$("table").remove();
	var data = getEntityList(actions["getList"],page,displayCount,false);
	if(data == null){
		alert("服务器请求失败");
		return;
	}
		
	$(".max_page").text(data.maxPage);
	$(".current_page").text(data.currentPage);
	
	var table = $("<table>");
	table.css("margin","4px");
	table.css("text-align","center");
	table.attr("class",dataInfo.dataListName)
	var tr = $("<tr>");
	for(var i = 0; i < ths.length; i++){
		var th = $("<th>");
		th.text(ths[i]);
		tr.append(th);
	}
	table.append(tr);
	var entityList = data[dataInfo.dataListName];
	for(var n = 0; n < entityList.length; n ++){
		var tr = $("<tr>");
		tr.css("height","30px");
		for(var j = 0; j <= dataInfo.props.length;j ++){
			var td = $("<td>");
			if(j == dataInfo.props.length){
				td.html("<input class='update' type = 'button' value = '修改'/> <input class='delete' type = 'button' value = '删除'/>"); 
				tr.append(td);
				break;
			}
			td.text(entityList[n][dataInfo.props[j].name]);
			td.attr("name",dataInfo.props[j].name);
			td.attr("class",dataInfo.props[j].class);
			td.attr("id",dataInfo.props[j].id);
			if(dataInfo.props[j].name == "uploadTime" || dataInfo.props[j].name == "updateTime"){
				td.text(new Date(entityList[n][dataInfo.props[j].name]).Format("yyyy-MM-dd hh:mm:ss")); 
			}
			tr.append(td);
		}
		table.append(tr);
	}
	table.css("display","none");
	$(".manager_area").append(table);
	table.fadeIn(1000);
		
	var idName = dataInfo.props[0].name;

	$(".delete").click(function(){
		if($(this).val() == "删除"){
			var entityId = Number($(this).parent().parent().children("#entityId").text());
			var dataJson = "{\"" + idName + "\":" + entityId + ",\"isRead\":false}";
			var sendData = eval("("+dataJson+")");
			$.ajax({
				url:actions["deleteEntity"],
				type:"post",
				data:sendData,
				anysc:false,
				dataType:"json",
				success:function(data){
					if(data.isSuccess == 1){
						alert("删除成功！");
						$("table").remove();
						initTableList(actions,page,displayCount,ths,dataInfo,initFormPara);
					}else{
						alert("删除失败");
					}
				},
				error:function(){
					alert("向服务器提交修改失败！");
				}
			})
		}
	});
	$(".update").click(function(){
		if($(this).val() == "修改"){
			var entityId = Number($(this).parent().parent().children("#entityId").text());
			var dataJson = "{\"" + idName + "\":" + entityId + ",\"isRead\":false}";
			var sendData = eval("("+dataJson+")");
			$.ajax({
				type:"post",
				url:actions["getEntity"],
				dataType:"json",
				data:sendData,
				async:false, 
				success:function(data){
					$(".manager_area").empty();
					initForm("form",actions["updateEntityFull"],"multipart/form-data",initFormPara,true,function(){
						//使用回调函数待修改完毕之后重新初始化列表；
						$(".manager_area").empty();
						initTableList(actions,page,displayCount,ths,dataInfo,initFormPara);
					});
					for(var i = 0; i < initFormPara.length;i++){
						var dataName = initFormPara[i]["name"];
						$("form").find(initFormPara[i]["element"]+"[name="+"'"+dataName+"']").val(data[dataInfo.entityName][dataName]);
					}
					//禁止填写诗评部分信息
					if($("form select[name='poetryType']").val() == "原创诗"){
						$("form input[name='reviewTitle']").attr("disabled",true);
						$("form input[name='reviewAuthor']").attr("disabled",true);
						$("form textarea[name='reviewContent']").attr("disabled",true);
					}
					
					var img = $("#imagePreview");
					img.attr("src","/" + data[dataInfo.entityName][dataInfo.pictureSrcName]);
					var imgTemp = new Image();
					imgTemp.src = "/" + data[dataInfo.entityName][dataInfo.pictureSrcName];
					imgTemp.onload = function(){
						if(parseInt(imgTemp.width) > 600){
							img.css("width",600);
						}else{
							img.css("width","");
							img.after("<br/>");
						}
						img.fadeIn(1000);
						$("#uploadPicture").css("margin-left","6em");
			            $("#uploadPicture").css("margin-top","2px");
			            $("#deletePicture").css("display","inline-block");
					}
				},
				error:function(){
					alert("服务器错误");
				}
			})
		}
	});
	$(".update_area").click(function(){
		var opTr = $(this).parent();
		if($(this).children("input")[0] == null){
			var originVals = new Array($(opTr.find(".update_area")).length);
			var opTds = new Array($(opTr.find(".update_area")).length);
			var i = 0;
			opTr.find(".update_area").each(function(){
				originVals[i] = $(this).text();
				opTds[i] = $(this);
				var input = $("<input>");
				input.attr("type","text");
				input.val(originVals[i]);
				$(this).empty();
				$(this).append(input);
				i ++;
			});
			var entityId = Number(opTr.find("#entityId").text());
			var upBu = opTr.find(".update");
			var deBu = opTr.find(".delete");
			upBu.val("确认");
			deBu.val("取消");
			upBu.click(function(){
				var i = 0;
				var dataJson = "{\""+idName+"\":"+entityId;
				opTr.find(".update_area").each(function(){
					dataJson += ",\""+opTds[i].attr("name")+"\":\""+$(this).children("input").val()+"\"";
					i++;
				})
				dataJson += "}";
				var sendData = eval("("+dataJson+")");
				if(upBu.val() == "确认"){
					$.ajax({
						url:actions["updateEntity"],
						type:"post",
						data:sendData,
						dataType:"json",
						anysc:false,
						success:function(data){
							if(data.isSuccess == 1){
								alert("修改成功！");
								$("table").remove();
								initTableList(actions,page,displayCount,ths,dataInfo,initFormPara);
								/*
									该函数在执行时，原先的DOM元素会被移除，之前绑定的事件
									也随之移除，所以不会发生重复绑定事件的问题。
								*/
							}else{
								alert("修改失败！");
								$("table").remove();
								initTableList(actions,page,displayCount,ths,dataInfo,initFormPara);
							}
						},
						error:function(){
							alert("向服务器提交修改失败！");
						}
					})
				}
			});
			deBu.click(function(){
				if(deBu.val() == "取消"){
					var i = 0
					opTr.find(".update_area").each(function(){
						$(this).empty();
						$(this).text(originVals[i]);
						i ++;
					})
					upBu.val("修改");
					deBu.val("删除");
				}
			})
		}
	});
}	

function getEntityList(url,page,displayCount,useSessionPage){
	var dataResult = null;
	$.ajax({
		type:"post",
		url:url,
		dataType:"json",
		data:{"page":page,"displayCount":displayCount,"useSessionPage":useSessionPage},
		async:false,
		success:function(data){
			dataResult = data;
		},
		error:function(){
			alert("服务器请求失败！");
			return null;
		}
	});
	return dataResult;
}

function initNewsTableList(page,displayCount){
	var actions = {"getList":"getNewsList",
			"deleteEntity":"deleteNewsById",
			"getEntity":"getNewsById",
			"updateEntityFull":"updateNewsFull",
			"updateEntity":"updateNewsWithoutFile"}
	var ths = ["编号","标题","作者","阅读量","上传时间","修改时间","操作"];
	var dataInfo = {
			"dataListName":"newsList",
			"entityName":"news",
			"pictureSrcName":"illuatrationPictureSrc",
			"props":[
				{"name":"newsId","id":"entityId"},
				{"name":"newsTitle","class":"update_area","id":"title"},
				{"name":"newsAuthor","class":"update_area","id":"title"},
				{"name":"readAmounts"},
				{"name":"uploadTime"},
				{"name":"updateTime"}
			]
	}
	initTableList(actions,page,displayCount,ths,dataInfo,newsFormPara);
}

function initArticleTableList(page,displayCount){
	var actions = {"getList":"getArticleList",
			"deleteEntity":"deleteArticleById",
			"getEntity":"getArticleById",
			"updateEntityFull":"updateArticleFull",
			"updateEntity":"updateArticleWithoutFile"}
	var ths = ["编 号","类 型","标 题","作 者","阅读量","上传时间","修改时间","操 作"];
	var dataInfo = {
			"dataListName":"articleList",
			"entityName":"article",
			"pictureSrcName":"illuatrationPictureSrc",
			"props":[
				{"name":"articleId","id":"entityId"},
				{"name":"articleType"},
				{"name":"articleTitle","class":"update_area"},
				{"name":"articleAuthor","class":"update_area"},
				{"name":"readAmounts"},
				{"name":"uploadTime"},
				{"name":"updateTime"}
			]
	}
	initTableList(actions,page,displayCount,ths,dataInfo,articleFormPara);
}

function initPoetryReviewTableList(page,displayCount){
	var actions = {"getList":"getPoetryReviewList",
			"deleteEntity":"deletePoetryReviewById",
			"getEntity":"getPoetryReviewById",
			"updateEntityFull":"updatePoetryReviewFull",
			"updateEntity":"updatePoetryReviewWithoutFile"}
	var ths = ["编 号","类 型","诗评标题","诗评作者","原诗标题","原诗作者","阅读量","上传时间","修改时间","操 作"];
	var dataInfo = {
			"dataListName":"poetryReviewList",
			"entityName":"poetryReview",
			"pictureSrcName":"illuatrationPictureSrc",
			"props":[
				{"name":"reviewId","id":"entityId"},
				{"name":"poetryType"},
				{"name":"reviewTitle","class":"update_area"},
				{"name":"reviewAuthor","class":"update_area"},
				{"name":"poetryTitle","class":"update_area"},
				{"name":"poetryAuthor","class":"update_area"},
				{"name":"readAmounts"},
				{"name":"uploadTime"},
				{"name":"updateTime"}
			]
	}
	
	initTableList(actions,page,displayCount,ths,dataInfo,poetryReviewFormPara);

}

var newsFormPara = [{"element":"input","preText":"编号","type":"hidden","name":"newsId"},
	{"element":"input","preText":"新闻标题","type":"text","name":"newsTitle","isMust":true},
	{"element":"input","preText":"记者姓名","type":"text","name":"newsAuthor","isMust":true},
	{"element":"input","preText":"新闻导读","type":"text","name":"newsInfo","isMust":false},
	{"element":"textarea","preText":"新闻内容","type":"text","name":"newsContent","isMust":true}]
var articleFormPara = [
	{"element":"input","preText":"编号","type":"hidden","name":"articleId"},
	{"element":"input","preText":"文章标题","type":"text","name":"articleTitle","isMust":true},
	{"element":"input","preText":"文章作者","type":"text","name":"articleAuthor","isMust":true},
	{"element":"select","values":["散文","小说"],"preText":"文章类型","name":"articleType","isMust":true},
	{"element":"textarea","preText":"文章正文","type":"text","name":"articleContent","isMust":true}]
var poetryReviewFormPara = [
	{"element":"input","preText":"编号","type":"hidden","name":"reviewId"},
	{"element":"select","values":["古体诗","近现代诗","原创诗"],"preText":"类　　型","name":"poetryType","isMust":true},
	{"element":"input","preText":"诗评标题","type":"text","name":"reviewTitle","isMust":true},
	{"element":"input","preText":"诗评作者","type":"text","name":"reviewAuthor","isMust":true},
	{"element":"input","preText":"原诗标题","type":"text","name":"poetryTitle","isMust":true},
	{"element":"input","preText":"原诗作者","type":"text","name":"poetryAuthor","isMust":true},
	{"element":"select","values":["justify","center"],"preText":"版　　式","name":"poetryAlign","isMust":true},
	{"element":"textarea","preText":"原诗内容","type":"text","name":"poetryContent","isMust":true},
	{"element":"textarea","preText":"诗评内容","type":"text","name":"reviewContent","isMust":true}
]

$(function(){
	$(".title").fadeIn(1000);
	$(".main_content").show(2000);
	$(".option_list").show(1000);
	
	//控制左侧选项栏
	$(".option_list>li>a").click(function(){
		if($(this).parent().children("ul").css("display") == "none"){
			$(this).parent().children("ul").slideDown(600);
		}else{
			$(this).parent().children("ul").slideUp(600);
		}
	});
	
	$(".news_manager .upload").click(function(){
		$(".manager_area").empty();
		initForm("addNews","uploadNews","multipart/form-data",newsFormPara,true);
		$("#addNews input[type='hidden']").val(-1);
		$(".current_title").text("新闻版块 >> 上传新闻");
	})
	
	$(".news_manager .view_manager").click(function(){
		$(".current_title").text("新闻版块 >> 查看与管理");
		$(".manager_area").empty();	
		
		var div = $("<div>");
		div.attr("class","page_control");
		var ul = $("<ul>");
		ul.append("<li><a class='last_page' href='javascript:'>末 页</a></li>");
		ul.append("<li><a class='next_page' href='javascript:'>下一页</a></li>");
		ul.append("<li><a class='pre_page' href='javascript:'>上一页</a></li>");
		ul.append("<li><a class='first_page' href='javascript:'>首 页</a></li>");
		ul.append("<li class='page_index'>第<span class='current_page'>0</span>页|共<span class = 'max_page'>0</span>页</li>");
		div.append(ul);
		$(".manager_area").append(div);
		initNewsTableList(1,30);

		$(".next_page").click(function(){
			var currentPage = Number($(".current_page").text());
			var maxPage = Number($(".max_page").text());
			if(currentPage == maxPage){
				alert("已经是最后一页。");
				return;
			}
			$("table").remove();
			initNewsTableList(currentPage + 1,30);
		})
		
		$(".pre_page").click(function(){
			var currentPage = Number($(".current_page").text());
			if(currentPage == 1){
				alert("已经是第一页。");
				return;
			}
			$("table").remove();
			initNewsTableList(currentPage - 1,30);
		})
		
		$(".first_page").click(function(){
			$("table").remove();
			initNewsTableList(1,30);
		})
		
		$(".last_page").click(function(){
			var maxPage = Number($(".max_page").text());
			$("tablee").remove();
			initNewsTableList(maxPage,30);
		})
	})
	
	$(".article_manager .upload").click(function(){
		$(".current_title").text("文章板块 >> 上传文章");
		$(".manager_area").empty();
		initForm("addArticle","uploadArticle","multipart/form-data",articleFormPara,true);
		$("#addArticle input[type='hidden']").val(-1);
	})
	
	$(".article_manager .view_manager").click(function(){
		$(".current_title").text("文章板块 >> 查看与管理");
		$(".manager_area").empty();
		
		var div = $("<div>");
		div.attr("class","page_control");
		var ul = $("<ul>");
		ul.append("<li><a class='last_page' href='javascript:'>末 页</a></li>");
		ul.append("<li><a class='next_page' href='javascript:'>下一页</a></li>");
		ul.append("<li><a class='pre_page' href='javascript:'>上一页</a></li>");
		ul.append("<li><a class='first_page' href='javascript:'>首 页</a></li>");
		ul.append("<li class='page_index'>第<span class='current_page'>0</span>页|共<span class = 'max_page'>0</span>页</li>");
		div.append(ul);
		$(".manager_area").append(div);
		initArticleTableList(1,30);

		$(".next_page").click(function(){
			var currentPage = Number($(".current_page").text());
			var maxPage = Number($(".max_page").text());
			if(currentPage == maxPage){
				alert("已经是最后一页。");
				return;
			}
			$("table").remove();
			initArticleTableList(currentPage + 1,30);
		})
		
		$(".pre_page").click(function(){
			var currentPage = Number($(".current_page").text());
			if(currentPage == 1){
				alert("已经是第一页。");
				return;
			}
			$("table").remove();
			initArticleTableList(currentPage - 1,30);
		})
		
		$(".first_page").click(function(){
			$("table").remove();
			initArticleTableList(1,30);
		})
		
		$(".last_page").click(function(){
			var maxPage = Number($(".max_page").text());
			$("tablee").remove();
			initArticleTableList(maxPage,30);
		})
	})
	
	$(".poetry_review_manager .upload").click(function(){
		$(".current_title").text("瞬诗刊版块 >> 上传瞬诗刊");
		$(".manager_area").empty();
		initForm("addPoetryReview","uploadPoetryReview","multipart/form-data",poetryReviewFormPara,true);
		$("#addPoetryReview input[type='hidden']").val(-1);
	})
	
	$(".poetry_review_manager .view_manager").click(function(){
		$(".current_title").text("瞬诗刊版块 >> 查看与管理");
		$(".manager_area").empty();
		
		var div = $("<div>");
		div.attr("class","page_control");
		var ul = $("<ul>");
		ul.append("<li><a class='last_page' href='javascript:'>末 页</a></li>");
		ul.append("<li><a class='next_page' href='javascript:'>下一页</a></li>");
		ul.append("<li><a class='pre_page' href='javascript:'>上一页</a></li>");
		ul.append("<li><a class='first_page' href='javascript:'>首 页</a></li>");
		ul.append("<li class='page_index'>第<span class='current_page'>0</span>页|共<span class = 'max_page'>0</span>页</li>");
		div.append(ul);
		$(".manager_area").append(div);
		initPoetryReviewTableList(1,30);

		$(".next_page").click(function(){
			var currentPage = Number($(".current_page").text());
			var maxPage = Number($(".max_page").text());
			if(currentPage == maxPage){
				alert("已经是最后一页。");
				return;
			}
			$("table").remove();
			initPoetryReviewTableList(currentPage + 1,30);
		})
		
		$(".pre_page").click(function(){
			var currentPage = Number($(".current_page").text());
			if(currentPage == 1){
				alert("已经是第一页。");
				return;
			}
			$("table").remove();
			initPoetryReviewTableList(currentPage - 1,30);
		})
		
		$(".first_page").click(function(){
			$("table").remove();
			initPoetryReviewTableList(1,30);
		})
		
		$(".last_page").click(function(){
			var maxPage = Number($(".max_page").text());
			$("tablee").remove();
			initPoetryReviewTableList(maxPage,30);
		})
	})
	$(".picture_manager .upload").click(function(){
		$("current_title").text("主页轮播图 >> 上传图片");
		$(".manager_area").empty();
		var form = $("<form>");
		form.attr("id","uploadPictureForm");
		form.attr("enctype","multipart/form-data");
		var para = [{"element":"input","preText":"编号","type":"hidden","name":"pictureId"},
			{"element":"input","preText":"图片标题","type":"text","name":"pictureName","isMust":true},
			{"element":"textarea","preText":"图片描述","type":"text","name":"pictureInfo","isMust":false}]
		for(var i = 0; i < para.length;i++){
			var element = $("<"+para[i].element+">");
			element.attr("type",para[i].type);
			element.attr("name",para[i].name);
			element.attr("class",para[i].class);
			element.attr("id",para[i].id);
			
			var span = $("<span>");
			span.attr("class","preText");
			span.text(para[i].preText+"：");
			if(para[i].type == "hidden"){	
				span.css("display","none");	
			}
			form.append(span);
			form.append(element);
			if(para[i].isMust == true){
				form.append("<span style = 'color:red; font-size:14px;'>*</span>");
			}
			if(para[i].type != "hidden"){
				form.append("<br/>");
			}
		}
		
		form.append("<br/><p style='display:inline-block; float:left;'>图　　片：</p>");
		form.append("<img id='imagePreview' style='display:none'>");
		form.append($("<input id='uploadPicture' type='file' accept='image/png,image/jpeg,image/jpg' name='indexPicture' style='display:inline-block'>"));
		form.append($("<input id='deletePicture' type='button' value='删除' style='display:none'>"));
		form.append("<br/>");
		
		var div=$("<div>");
		div.append($("　　　　　<input type='reset' value='重置'>"));
		div.append("&nbsp;&nbsp;");
		div.append($("<input type='submit' value='确认'>"));
		form.append(div);
		form.css("display","none");
		$(".manager_area").append(form);
		$("form input[name='pictureId']").val(-1);
		form.show(1000);
		
		$("#uploadPicture").change(function(){
			var fileDom = this;
			//判断浏览器是否支持FileReader对象
	        if (window.FileReader) {
	            var reader = new FileReader();//创建FileReader对象
	        } else {
	            alert("您的浏览器不支持图片预览功能，请使用最新版的Internet Explorer或者Chrome浏览器！");
	        }
			
	        var file = fileDom.files[0];//获取用户选择的文件
	        var imageType = /^image\  //正则表达式设置接收的文件类型
	        
	        if(file == null){
	        	$("#imagePreview").css("display","none");
	        	$("#uploadPicture").css("margin-left","0px");
	        	$("#deletePicture").css("display","none");
	        	return;//如果文件为空，则返回
	        }
	        //判断文件类型是否符合要求
	        if (!imageType.test(file.type)) {
	            alert("请选择图片类型的文件！");
	            $(":input[type='file']").val("");//如果不符合要求的文件类型，则清空当前input的值并返回
	            return;
	        }
	        //当reader读取文件完成后执行这个函数
	        reader.onload = function(file) {
	            var img = $("#imagePreview");
	            img.css("display","none");
	            var fileSize = fileDom.files[0].size;
	            //判断上传的文件大小
	            if(fileSize > 1048576){
	            	alert("图片太大，请上传小于1024KB的图片");
	            	$(":input[type='file']").val("");
		        	$("#imagePreview").css("display","none");
		        	$("#uploadPicture").css("margin-left","0px");
		        	$("#deletePicture").css("display","none");
		        	return;
	            }
	            
	            img.attr("src",file.target.result);
				var imgTemp = new Image();//新建一个Image对象，用来辅助判断图片的信息
				imgTemp.src = file.target.result;//将上传的图片的src给Image对象
				
				//等待Image对象将图片加载完成之后
				imgTemp.onload = function(){
					//判断图片的宽高是多少像素，如果不符合要求给与提示并则清空刚才选择的文件
					if(parseInt(imgTemp.width) != 1024 || parseInt(imgTemp.height) != 500){
						alert("您选择的图片不符合要求，请上传1024*500的图片。");
						$(":input[type='file']").val("");
			        	$("#imagePreview").css("display","none");
			        	$("#uploadPicture").css("margin-left","0px");
			        	$("#deletePicture").css("display","none");
			        	return;
					}
					img.fadeIn(1000);
					$("#uploadPicture").css("margin-left","6em");
		            $("#uploadPicture").css("margin-top","2px");
		            $("#deletePicture").css("display","inline-block");
				}
	        };
	        reader.readAsDataURL(file);
		});
		
		$("#deletePicture").click(function(){
			$(":input[type='file']").val("");
        	$("#imagePreview").css("display","none");
        	$("#uploadPicture").css("margin-left","0px");
        	$("#deletePicture").css("display","none");
		});
		
		$("#uploadPictureForm").on("submit", function(event){
			if($(":input[type='file']").val() == "" || $(":input[type='file']").val() == null){
				alert("您没有选择图片！");
				return false;
			}
			for(var i = 0;i < para.length;i ++){
				if(para[i].isMust == true && $(para[i].element+"[name="+para[i].name+"]").attr("disable") != true && $(para[i].element+"[name="+para[i].name+"]").val().length <= 0){
					alert("请填写"+para[i].preText+"!");
					return false;
				}
			}
			
		    var form = this;
		    var formData = new FormData(form);

		    $.ajax({
		        url: "/JZTWebsite/uploadIndexPicture",
		        type: "POST",
		        data: formData,
		        dataType: "JSON",
		        async: true,
		        processData: false, 
		        contentType: false,
		        enctype: "multipart/form-data",
		        beforeSend: function(xhr){
		            $("#uploadPictureForm :submit").attr("disabled",true);
		        },
		        complete: function(xhr,status){
		            $("#uploadPictureForm :submit").attr("disabled", false);
		        },
		        error: function(xhr,status,error){
		            alert("请求出错，表单提交失败！");
		        },
		        success: function(result){
		        	if(result.isSuccess == 1){
		            	alert("表单提交成功!");
			            $("#imagePreview").css("display","none");
			        	$("#uploadPicture").css("margin-left","0px");
			        	$("#deletePicture").css("display","none");
			            form.reset();
		        	}
		        }
		    });
		    return false;
		});
	})
	$(".picture_manager .view_manager").click(function(){
		$(".current_title").text("主页轮播图 >> 查看与管理");
		$(".manager_area").empty();
		var imageList = null;
		$.ajax({
			type:"GET",
			url:"/JZTWebsite/getIndexPictures",
			dataType:"JSON",
			async:false,
			success:function(data){
				imageList = data.pictureList;
			}
		})
		if(imageList == null){
			return;
		}
		for(var i = 0; i < imageList.length;i ++){
			var div = $("<div class = 'indexPictureManager'>");
			div.css("width","1024px");
			div.css("height","500px");
			
			var pName = $("<p class = 'pictureName'>");
			pName.append("<span>编号：</span>");
			pName.append("<span class='pictureId'>" + imageList[i].pictureId + "</span>");
			pName.append("&nbsp;&nbsp;&nbsp;&nbsp;")
			pName.append("<span>标题：</span>");
			pName.append("<span class='pictureName'>" + imageList[i].pictureName + "</span>");
			div.append(pName);
			
			if(imageList[i].pictureInfo != null && imageList[i].pictureInfo != ""){
				var pInfo = $("<p class = 'pictureInfo'>");
				pInfo.append("<span>图片信息：</span>");
				pInfo.append("<span class='pictureInfo'>" + imageList[i].pictureInfo + "</span>");
				div.append(pInfo);
			}
			var img = $("<img>");
			img.css("display","block");
			img.attr("src","/" + imageList[i].pictureSrc);
			div.append("<a href='javascript:' class='update'>修改</a>");
			div.append("<a href='javascript:' class='delete'>删除</a>");
			$("div[class='indexPictureManager'] a").css("display","none");
			div.append(img);
			div.css("display","none");
			$(".manager_area").append(div);
			div.slideDown(1000);
		}
		
		$("div[class='indexPictureManager']").mouseenter(function(){
			$(this).children("a").fadeIn(500);
		})
		
		$("div[class='indexPictureManager']").mouseleave(function(){
			$(this).children("a").fadeOut(500);
		})
		
		$("div[class='indexPictureManager'] a[class='update']").click(function(){
			if($(this).parent("div").next().prop("tagName") == "FORM"){
				return;
			}
			var form = $("<form>");
			form.attr("action","/JZTWebsite/updateIndexPicture")
			var para = [{"element":"input","preText":"编号","type":"hidden","name":"pictureId"},
				{"element":"input","preText":"图片标题","type":"text","name":"pictureName","isMust":true},
				{"element":"textarea","preText":"图片描述","type":"text","name":"pictureInfo","isMust":false}]
			for(var i = 0; i < para.length;i++){
				var element = $("<"+para[i].element+">");
				element.attr("type",para[i].type);
				element.attr("name",para[i].name);
				element.attr("class",para[i].class);
				element.attr("id",para[i].id);
				
				var span = $("<span>");
				span.attr("class","preText");
				span.text(para[i].preText+"：");
				if(para[i].type == "hidden"){	
					span.css("display","none");	
				}
				form.append(span);
				form.append(element);
				if(para[i].isMust == true){
					form.append("<span style = 'color:red; font-size:14px;'>*</span>");
				}
				if(para[i].type != "hidden"){
					form.append("<br/>");
				}
			}
			form.append("　　　　　");
			form.append("<input type='button' id='submit' value='确认'>");
			form.append("&nbsp;&nbsp;&nbsp;&nbsp;")
			form.append("<input type='button' id='reset' value='取消'>");
			form.css("display","none")
			$(this).parent("div").after(form);
			form.slideDown(1000);
			$(this).parent("div").next().children("input[name='pictureId']").val($(this).parent("div").children("p").children("span[class='pictureId']").text());
			$(this).parent("div").next().children("input[name='pictureName']").val($(this).parent("div").children("p").children("span[class='pictureName']").text());
			$(this).parent("div").next().children("textarea[name='pictureInfo']").val($(this).parent("div").children("p").children("span[class='pictureInfo']").text());
			
			$("form #submit").click(function(){
				var form = $(this).parent("form");
				$.ajax({
					type:"get",
					url:"/JZTWebsite/updateIndexPicture",
					data:form.serialize(),
					dataType:"JSON",
					success:function(data){
						if(data.isSuccess == 1){
							alert("修改成功");
							form.prev().find("span[class='pictureName']").text($("form input[name='pictureName']").val());
							form.prev().find("span[class='pictureInfo']").text($("form input[name='pictureInfo']").val());
							form.slideUp(1000,function(){
								form.remove();
							});
						}else{
							alert("修改失败");
						}
					},
					error:function(){
						alert("修改失败");
					}
				})
			})
			$("form #reset").click(function(){
				var form = $(this).parent("form");
				form.slideUp(1000,function(){
					form.remove();
				});
			})
		})
		
		$("div[class='indexPictureManager'] a[class='delete']").click(function(){
			var tDiv = $(this).parent();
			var pictureId = $(this).parent("div").children("p").children("span[class='pictureId']").text();
			$.ajax({
				type:"get",
				url:"/JZTWebsite/deletePictureById",
				data:{"pictureId":pictureId},
				dataType:"JSON",
				success:function(data){
					if(data.isSuccess == 1){
						alert("删除成功！");
						tDiv.hide(1000,function(){
							tDiv.remove();
						})
					}else{
						alert("删除失败！")
					}
				},
				error:function(){
					alert("删除失败！");
				}
			})
		})
	})
	
	$(".abouts_manager .organization").click(function(){
		$(".current_title").text("关于版块 >> 组织简介");
		$(".manager_area").empty();
		
		var form = $("<form>");
		form.attr("id","updateOrganization");
		
		var textarea = $("<textarea>");
		textarea.attr("name","content");
		textarea.css("height","500px");
		form.append(textarea);
		form.append("<br/>");
		form.append("<input type='submit' value='确认'>");
		form.append("&nbsp;&nbsp;&nbsp;&nbsp;")
		form.append("<input type='reset' value='取消'>");
		$(".manager_area").append(form);
		
		$.ajax({
			type:"GET",
			url:"/JZTWebsite/getAboutsOrganization",
			dataType:"JSON",
			success:function(data){
				textarea.val(data.aboutsOrganization.content);
			}
		})
		
		$("#updateOrganization").submit(function(){
			if(textarea.val() == null || textarea.val() == ""){
				alert("请填写内容。");
				return false;
			}
			var sendData = $(this).serialize(); 
			$.ajax({
				type:"GET",
				url:"/JZTWebsite/updateAboutsOrganization",
				data:sendData,
				dataType:"JSON",
				success:function(data){
					if(data.isSuccess == 1){
						alert("修改成功！");
						return;
					}
					alert("修改失败！");
				},
				error:function(){
					alert("服务器请求失败！");
				}
			})
			return false;
		});
	})
	
	$(".abouts_manager .team").click(function(){
		$(".current_title").text("关于版块 >> 我们的团队");
		$(".manager_area").empty();
		
		var form = $("<form>");
		form.attr("id","updateTeam");
		
		var textarea = $("<textarea>");
		textarea.attr("name","content");
		textarea.css("height","500px");
		form.append(textarea);
		form.append("<br/>");
		form.append("<input type='submit' value='确认'>");
		form.append("&nbsp;&nbsp;&nbsp;&nbsp;")
		form.append("<input type='reset' value='取消'>");
		$(".manager_area").append(form);
		
		$.ajax({
			type:"GET",
			url:"/JZTWebsite/getAboutsTeam",
			dataType:"JSON",
			success:function(data){
				textarea.val(data.aboutsTeam.content);
			}
		})
		
		$("#updateTeam").submit(function(){
			if(textarea.val() == null || textarea.val() == ""){
				alert("请填写内容。");
				return false;
			}
			var sendData = $(this).serialize(); 
			$.ajax({
				type:"GET",
				url:"/JZTWebsite/updateAboutsTeam",
				data:sendData,
				dataType:"JSON",
				success:function(data){
					if(data.isSuccess == 1){
						alert("修改成功！");
						return;
					}
					alert("修改失败！");
				},
				error:function(){
					alert("服务器请求失败！");
				}
			})
			return false;
		});
	})
	
//	$("div.manager_area").resize(()=>{
//		$("div.main_content").css("width",$("div.manager_area").css("width"))
//		$("div.main_content").css("height",$("div.manager_area").css("height"))
//	})
});
