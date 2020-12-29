/**
 * @author 白钧翰
 * 首页轮播图
 */
$(function() {
	$.ajax({
		type: "get",
		url: "/JZTWebsite/getIndexPictures",
		dataType: "json",
		success: function(data) {
			var imageList = data.pictureList;
			$("#banner_list").empty();
			$(".image_order").empty();
			if(imageList.length > 0){
				for(var i = 0; i < imageList.length; i++) {
					var a = $("<a>");
					a.attr("target", "_blank");
					var img = $("<img>");
					img.attr("src", "/"+imageList[i].pictureSrc);
					img.attr("title", imageList[i].pictureName);
					img.attr("alt", imageList[i].pictureName);
					a.append(img);
					$("#banner_list").append(a);
					var li = $("<li>");
					li.text(i + 1);
					if(i == 0) {
						li.attr("class", "on");
					}
					$(".image_order").append(li);
				}
			}else{
				//若从服务器获取失败
				imageList = [
					{
						"title": "醉美西科（一）",
						"src": "img/main_image/a.png"
					}, {
						"title": "醉美西科（二）",
						"src": "img/main_image/b.png"
					}, {
						"title": "醉美西科（三）",
						"src": "img/main_image/c.png"
					}, {
						"title": "醉美西科（四）",
						"src": "img/main_image/d.png"
					}, {
						"title": "醉美西科（五）",
						"src": "img/main_image/e.png"
					}, {
						"title": "醉美西科（六）",
						"src": "img/main_image/f.png"
					}
				]
				for(var i = 0; i < imageList.length; i++) {
					var a = $("<a>");
					a.attr("target", "_blank");
					var img = $("<img>");
					img.attr("src", imageList[i].src);
					img.attr("title", imageList[i].title);
					img.attr("alt", imageList[i].title);
					a.append(img);
					$("#banner_list").append(a);
					var li = $("<li>");
					li.text(i + 1);
					if(i == 0) {
						li.attr("class", "on");
					}
					$(".image_order").append(li);
				}
			}
			var t = n = 0,
				count;
			count = $("#banner_list a").length;
			$("#banner_list a:not(:first-child)").hide();
			$("#banner_info").html($("#banner_list a:first-child").find("img").attr('alt'));
			$("#banner_info").click(function() {
				window.open($("#banner_list a:first-child").attr('href'), "_blank")
			});
			$("#banner li").click(function() {
				var i = $(this).text() - 1; //获取Li元素内的值，即1，2，3，4
				n = i;
				if(i >= count) return;
				$("#banner_info").html($("#banner_list a").eq(i).find("img").attr('alt'));
				$("#banner_info").unbind().click(function() {
					window.open($("#banner_list a").eq(i).attr('href'), "_blank")
				})
				$("#banner_list a").filter(":visible").fadeOut(500).parent().children().eq(i).fadeIn(1000);
				document.getElementById("banner").style.background = "";
				$(this).toggleClass("on");
				$(this).siblings().removeAttr("class");
			});
			t = setInterval(function() {
				n = n >= (count - 1) ? 0 : ++n;
				$("#banner li").eq(n).trigger('click');
			}, 4000);
			$("#banner").hover(function() {
				clearInterval(t)
			}, function() {
				t = setInterval(function() {
					n = n >= (count - 1) ? 0 : ++n;
					$("#banner li").eq(n).trigger('click');
				}, 4000);
			});
		},
		error: function() {
			var t = n = 0,
				count;
			count = $("#banner_list a").length;
			$("#banner_list a:not(:first-child)").hide();
			$("#banner_info").html($("#banner_list a:first-child").find("img").attr('alt'));
			$("#banner_info").click(function() {
				window.open($("#banner_list a:first-child").attr('href'), "_blank")
			});
			$("#banner li").click(function() {
				var i = $(this).text() - 1; //获取Li元素内的值，即1，2，3，4
				n = i;
				if(i >= count) return;
				$("#banner_info").html($("#banner_list a").eq(i).find("img").attr('alt'));
				$("#banner_info").unbind().click(function() {
					window.open($("#banner_list a").eq(i).attr('href'), "_blank")
				})
				$("#banner_list a").filter(":visible").fadeOut(500).parent().children().eq(i).fadeIn(1000);
				document.getElementById("banner").style.background = "";
				$(this).toggleClass("on");
				$(this).siblings().removeAttr("class");
			});
			t = setInterval(function() {
				n = n >= (count - 1) ? 0 : ++n;
				$("#banner li").eq(n).trigger('click');
			}, 4000);
			$("#banner").hover(function() {
				clearInterval(t)
			}, function() {
				t = setInterval(function() {
					n = n >= (count - 1) ? 0 : ++n;
					$("#banner li").eq(n).trigger('click');
				}, 4000);
			});
		}
	});
			//			var i=1;
			//			var j=1;
			//			var length=$(".main_image").children("img").length;
			//			setInterval(function(){
			//				j=1;
			//				$(".main_image").children("img").each(function(){
			//					if(j>=i){
			//						if($(this).css("opacity") == 1){
			//							$(this).fadeTo(3000,0);
			//							i++;
			//							if(i > length){
			//								i = 1;
			//							}
			//						}else if($(this).css("opacity") == 0){
			//							$(this).fadeTo(3000,1);
			//							i++;
			//							if(i > length){
			//								i = 1;
			//							}
			//							return false;
			//							//$(this).css("opacity",0);
			//						}
			//					}
			//					j++;
			//				})
			//			},3000);
})