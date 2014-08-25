//menu

$(function(){
	$(".changeright:not(:first)").hide()
			$(".changeleft li").mouseover(function(){
				adss=$(this).index();
				$(this).addClass("style").siblings("li").removeClass("style");
				$(".changeright").eq(adss).show().siblings(".changeright").hide();
				})
	});
$(function(){
	$(".new:not(:first)").hide()
			$(".p_m_change li").mouseover(function(){
				adss=$(this).index();
				$(this).addClass("jia").siblings("li").removeClass("jia");
				$(".p_m_change .more").attr("href",$(this).find("a").attr("href"));
				$(".new").eq(adss).show().siblings(".new").hide();
				})
});

$(function(){
	 $(".cspan").hide();
	 $("#caseimg li").mouseover(function(){
		   spdex=$(this).index();
		   $(".cspan").eq(spdex).show().siblings(".cspan").hide();
		 });
	  $("#caseimg li").mouseout(function(){
		   spdex=$(this).index();
		   $(".cspan").eq(spdex).hide();
		  })
	});

$(function(){
	 $(".cspan1").hide();
	 $("#caseimg li").mouseover(function(){
		   spdex1=$(this).index();
		   $(".acaseimg img").eq(spdex1).removeClass("imghb");
		   $(".cspan1").eq(spdex1).show().siblings(".cspan1").hide();
		 });
	  $("#caseimg li").mouseout(function(){
		   spdex1=$(this).index();
		   $(".acaseimg img").eq(spdex1).addClass("imghb");
		   $(".cspan1").eq(spdex1).hide();
		  })
	});

	$(function () {
		var count=$("#cbanner a").length;
		var cbindex=0; 
		$("#cbanner a").eq(0).show();
	    $("#cbanner a:not(:first)").hide();
		$("#cbleft").click(function(){
			  if(cbindex==0){cbindex=count-1;}
			  else {cbindex-=1;}
			  $("#cbanner a").eq(cbindex).show().siblings("#cbanner a").hide()
			});
			
		$("#cbright").click(function(){
			  if(cbindex==count){cbindex=0;}
			  else {cbindex+=1;}
			  $("#cbanner a").eq(cbindex).show().siblings("#cbanner a").hide()
			})
})
$(function () {
    $(".leftlist dl dt").click(function () {
        if ($(this).nextAll(".leftlist dl dd").is(":visible")) {
            $(this).nextAll(".leftlist dl dd").hide();
        }
        else {
            $(this).nextAll(".leftlist dl dd").show();
        }
    })
});
$(function () {
    $(".wenti:not(:first)").hide()
    $(".bigt li").mouseover(function () {
        adss = $(this).index();
        $(this).addClass("zbian").siblings("li").removeClass("zbian");
        $(".wenti").eq(adss).show().siblings(".wenti").hide();
    })


    $("#qustion").click(function () {
        wid = $(document).width();
        heig = $(document).height();
        $(".touming").css({ opacity: "0.5", width: wid, height: heig, index_z: 900 }).show()
        stt = $(window).scrollTop()
        kwid = $("#tanchu").width();
        $("#tanchu").animate({ opacity: "1", left: wid / 2 - kwid / 2, top: stt + 100, index_z: 1000 }).show()
        $(".touming").click(function () {
            $(this).hide()
            $("#tanchu").hide()

        })
        $(".xiao").click(function () {
            $(".touming").hide()
            $("#tanchu").hide()
        })
    })

});

$(function () {
    $("#famlist dl dd").hide();
    $("#famlist dt img").hide();
    $("#famlist dl dt").click(function () {
        $("#famlist dt img").show();
        if ($(this).nextAll("#famlist dl dd").is(":visible")) {
            $(this).nextAll("#famlist dl dd").hide();
        }
        else {
            $(this).nextAll("#famlist dl dd").show();
        }
    })
});
$(function () {
    $(".thenew li img").css(
	{ opacity: '0.5', height: '152px', width: '184px', left: '0px', top: '0px' }).hover(function () {
	    $(this).stop().animate({ opacity: '1', height: '152px', width: '184px', left: '0', top: '0' })
	}, function () {
	    $(this).stop().animate({ opacity: '0.5', height: '152px', width: '184px', left: '0px', top: '0px' })
	})
});
$(function () {
    $("#start li img").css(
	{ opacity: '0.5', height: '342px', width: '241px', left: '0px', top: '0px' }).hover(function () {
	    $(this).stop().animate({ opacity: '1', height: '342px', width: '241px', left: '0', top: '0' })
	}, function () {
	    $(this).stop().animate({ opacity: '0.5', height: '342px', width: '241px', left: '0px', top: '0px' })
	})
}); 