/**
 *@author 白钧翰
 *首页导航栏的JavaScript脚本 
 */
$(function() {
	$(window).scroll(function() {
		if($(window).scrollTop() > 180) {
			$(".navigation_bar").css("position", "fixed");
			$(".navigation_bar").css("top", "0px");
			$(".navigation_bar").css("width", "1024px");
			$(".navigation_bar").css("box-shadow", "0px 3px 6px #000000");

		} else if($(window).scrollTop() <= 180) {
			$(".navigation_bar").css("position", "absolute");
			$(".navigation_bar").css("top", "160px");
			$(".navigation_bar").css("box-shadow", "");
		}
	})
	$(".navigation_bar>ul>li").mouseenter(function() {
		$(this).children(".navigation_effect").fadeIn("normal");
		$(this).children("a").css("color", "aliceblue");
	})
	$(".navigation_bar>ul>li").mouseleave(function() {
		$(this).children(".navigation_effect").fadeOut("normal");
		$(this).children(".down_list").fadeOut("normal");
		$(this).children("a").css("color", "chocolate");
	})
	$(".navigation_bar>ul>li>a").click(function() {
		if($(this).parent().children(".down_list").css("display") == "none") {
			$(this).parent().children(".down_list").slideDown("slow");
		} else {
			$(this).parent().children(".down_list").slideUp("slow");
		}
	})
})