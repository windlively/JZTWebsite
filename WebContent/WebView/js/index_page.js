/**
 * @author 白钧翰
 * 首页的JavaScript脚本
 */
$(function(){
	$.ajax({
		type:"get",
		url:"/JZTWebsite/getArticleList",
		async:true,
		data:{"page":1,"displayCount":3},
		dataType:"JSON",
		success:function(data){
			var articleList = data.articleList;
			var ul = $(".article_area > ul");
			ul.empty();
			if(articleList.length <= 0){
				var li = $("<li>");
				li.text("暂无数据");
				li.attr("class","no_data");
				ul.append(li);
				return;
			}
			for(var i = 0; i < articleList.length; i ++){
				var li = $("<li>");
				var input = $("<input>");
				input.attr("type","hidden");
				input.val(articleList[i].articleId);
				li.append(input);
				var a = $("<a>");
				a.attr("href","javascript:");
				a.append($("<span class='article_title'>").text(articleList[i].articleTitle));
				a.append("　　");
				a.append($("<span class='article_author'>").text(articleList[i].articleAuthor));
				li.append(a);
				var p = $("<p>");
				p.attr("class","article_preview");
				var articleContent = articleList[i].articleContent;
				if(articleContent.length > 190){
					articleContent = articleContent.substring(0,200) + "......";
				}
				p.text(articleContent);
				li.append(p);
				ul.append(li);
			}
			$(".article_area>ul>li>a").click(function(){
				var articleId = $(this).prev("input").val();
				location = "essay/essay.html?articleId="+articleId;
			})
			$(".article_area > .area_title > a").attr("href","essay/essay_list.html?type=" + (articleList[0].articleType == "散文" ? "sw" : "xs"));
		},
		error:function(){
			$(".article_area>ul").empty();
			var li = $("<li>");
			li.text("暂无数据");
			li.attr("class","no_data");
			$(".article_area>ul").append(li);
		}
	});
	
	$.ajax({
		type:"get",
		url:"/JZTWebsite/getNewsList",
		async:true,
		data:{"page":1,"displayCount":8},
		dataType:"JSON",
		success:function(data){
			var newsList = data.newsList;
			var ul = $(".news_area>ul");
			ul.empty();
			if(newsList.length <= 0){
				var li = $("<li>");
				li.text("暂无数据");
				ul.append(li);
				return;
			}
			for (var i = 0; i < newsList.length; i++){
				var li = $("<li>");
				var input = $("<input>");
				input.val(newsList[i].newsId);
				input.attr("type","hidden");
				li.append(input);
				var a = $("<a>");
				a.attr("href","javascript:");
				a.text(newsList[i].newsTitle);
				li.append(a);
				ul.append(li);
			}
			$(".news_area>ul>li>a").click(function(){
				var newsId = $(this).prev("input").val();
				location = "news/news.html?newsId="+newsId;
			})
		},
		error:function(){
			var ul = $(".news_area>ul");
			ul.empty();
			var li = $("<li>");
			li.text("暂无数据");
			ul.append(li);
		}
	});
	
	$.ajax({
		type:"get",
		url:"/JZTWebsite/getPoetryReviewList",
		async:true,
		data:{"page":1,"displayCount":8},
		dataType:"JSON",
		success:function(data){
			poetryReviewList = data.poetryReviewList;
			var ul = $(".poetryReview_area>ul");
			ul.empty();
			if(poetryReviewList.length <= 0){
				var li = $("<li>");
				li.text("暂无数据");
				ul.append(li);
				return;
			}
			for(var i = 0; i < poetryReviewList.length; i ++){
				var li = $("<li>");
				var input = $("<input>");
				input.val(poetryReviewList[i].reviewId);
				input.attr("type","hidden");
				li.append(input);
				var a = $("<a>");
				a.attr("href","javascript:");
				if(poetryReviewList[i].poetryType == "原创诗"){
					a.text(poetryReviewList[i].poetryTitle);
				}else{
					a.text(poetryReviewList[i].reviewTitle);
				}
				li.append(a);
				ul.append(li);
				var t = "jxds";
				switch(poetryReviewList[0].poetryType){
					case "古体诗": t = "gts"; break;
					case "原创诗": t = "ycs"; break;
				}
				$(".poetryReview_area > .area_title > a").attr("href","ssk/ssk_list.html?type=" + t );
			}
			$(".poetryReview_area>ul>li>a").click(function(){
				var reviewId = $(this).prev("input").val();
				location = "ssk/ssk.html?reviewId="+reviewId;
			})
		},
		error:function(){
			$(".poetryReview_area>ul").empty();
			var li = $("<li>");
			li.text("暂无数据。");
			$(".poetryReview_area>ul").append(li);
		}
	});
	
	$("#searchForm").submit(function(){
		var data = $("#searchForm").serialize();
		var name = $("#searchForm select[name='entityType']").val();
		var isNull = true;
		$("#searchForm > .searchPara").children().each(function(){
			if($(this).val() != null && $(this).val() != ""){
				isNull = false;
				return;
			}
		});
		if(isNull){
			alert("请至少填写一项搜索条件。");
			return false;
		}
		if($("#searchForm input[name='startDate']").val() > $("#searchForm input[name='endDate']").val()){
			alert("起始日期不能大于结束日期，请重新选择。");
			return false;
		}
		if(name == "article"){
			location = "essay/essay_list.html?type=search&"+data;
		}else if(name == "news"){
			location = "news/news_list.html?type=search&"+data;
		}else if(name == "poetry" || name == "poetryReview"){
			location = "ssk/ssk_list.html?type=search&"+data;
		}
		return false;
	});
	
	$("#searchForm > select[name='entityType']").change(function(){
		if($(this).val() == "article"){
			$(".searchPara > .author").attr("name","articleAuthor");
			$(".searchPara > .title").attr("name","articleTitle");
		}else if($(this).val() == "news"){
			$(".searchPara > .author").attr("name","newsAuthor");
			$(".searchPara > .title").attr("name","newsTitle");
		}else if($(this).val() == "poetry"){
			$(".searchPara > .author").attr("name","poetryAuthor");
			$(".searchPara > .title").attr("name","poetryTitle");
		}else if($(this).val() == "poetryReview"){
			$(".searchPara > .author").attr("name","reviewAuthor");
			$(".searchPara > .title").attr("name","reviewTitle");
		}
	})
	
})
