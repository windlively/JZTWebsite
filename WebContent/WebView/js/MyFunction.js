/**
 * @author 白钧翰
 * @param data
 * @param entityInfo
 * @returns
 */
/*

	 * 功能：构建一个实体列表
	 * author： Bai Jun-han
	 * 参数：
	 * data:服务器端返回的Json数据
	 * entityInfo:Json数据信息
	 * 	示例：	    entityInfo = {
					"entitylistName":"newsList",//实体列表名称
					"idName":"newsId",//实体的Id字段名
					"displayFileds"://需要显示的字段
					[
						{"name":"newsTitle","headName":"新闻标题"},//name:对应的实体字段名称,headName：列名
						{"name":"newsAuthor","headName":"记者姓名"},
						{"name":"uploadTime","headName":"上传时间"},
						{"name":"readAmounts","headName":"阅读量"}
					]
			}
	 * */
	function initEntityList(data,entityInfo){
			$(".list_header").empty();
			for(var i = 0; i < entityInfo.displayFileds.length; i ++){
				var li = $("<li>");
				li.text(entityInfo.displayFileds[i].headName);
				li.attr("class",entityInfo.displayFileds[i].name);
				$(".list_header").append(li);
			}
			if(typeof(data) == undefined || data == null){
				return;
			}
			var entityList = data[entityInfo.entitylistName];
			if(entityList.length <= 0){
				var p = $("<p>").html("<br/>　暂无数据。");
				$(".page_control").css("display","none");
				$(".main_context").css("height","500px");
				$(".article_list").append(p);
				return;
			}
			for(var i = 0; i < entityList.length;i ++){
				var ul = $("<ul>");
				ul.attr("class","article_item");
				var itemId = $("<input>");
				itemId.attr("type","hidden");
				itemId.val(entityList[i][entityInfo.idName]);
				ul.append(itemId);
				var a = $("<a>");
				a.attr("href","javascript:");
				for(var j = 0; j < entityInfo.displayFileds.length; j ++){
					var li = $("<li>");
					var displayFiled = entityInfo.displayFileds[j];
					li.attr("class",displayFiled.name);
					if(displayFiled.name == "uploadTime" || displayFiled.name == "updateTime"){
						li.text(new Date(entityList[i][displayFiled.name]).Format("yyyy-MM-dd hh:mm:ss"));
					}else{
						li.text(entityList[i][displayFiled.name]);
					}
					a.append(li);
				}
				ul.append(a);
				$(".article_list").append(ul);
			}
		}

		function getEntityList(url,page,displayCount,type,useSessionPage){
			var dataResult = null;
			$.ajax({
				type:"post",
				url:url,
				dataType:"json",
				data:{"page":page,"displayCount":displayCount,"useSessionPage":useSessionPage,"type":type},
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
		
		