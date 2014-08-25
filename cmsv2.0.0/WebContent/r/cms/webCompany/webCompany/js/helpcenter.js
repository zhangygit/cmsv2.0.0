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
				$(".new").eq(adss).show().siblings(".new").hide();
				})
	});
	$(function(){
		
		$(".leftlist dl dt").click(function(){
			if($(this).nextAll(".leftlist dd").is(":visible"))
			{
					$(this).nextAll(".leftlist dd").hide()
					}
				else
				{
					$(this).nextAll(".leftlist dd").show()
					}
		})
		});
$(function(){
	$(".wenti:not(:first)").hide()
			$(".bigt li").mouseover(function(){
				adss=$(this).index();
				$(this).addClass("zbian").siblings("li").removeClass("zbian");
				$(".wenti").eq(adss).show().siblings(".wenti").hide();
				})


$("#qustion").click(function(){
				wid=$(document).width();
				heig=$(document).height();
				$(".touming").css({opacity:"0.5",width:wid, height:heig, index_z:900}).show()
				stt=$(window).scrollTop()
				kwid=$("#tanchu").width();
				$("#tanchu").animate({opacity:"1",left:wid/2-kwid/2,top:stt+100, index_z:1000 }).show()
				//$(".touming").click(function(){$(this).hide()
//				$("#tanchu").hide()
//				})
				$(".xiao").click(function(){
				$("#title").val("");
				$("#types").val("");
				$("#askcon").val("");
				$("#mail").val("");$("#phone").val("");
				$(".touming").hide()
				$("#tanchu").hide()
				})
				$("#btnsure").click(function(){
					if($("#title").val()==""){alert("提问主题不能为空！");$("#title").focus();return false;}
					else if($("#types").val()==""){alert("问题的类别不能为空，请选择！");$("#types").focus();return false;}
					else if($("#askcon").val()==""){alert("问题的内容不能为空！");$("#askcon").focus();return false;}
					else if($("#mail").val()==""){alert("请输入您的联系邮箱！");$("#mail").focus();return false;}
					else if($("#phone").val()==""){alert("请输入您的联系手机号码！");$("#phone").focus();return false;}
					})
				$("#divtype span").click(function(){
				   var type=$("#types").val();
				   if(type.indexOf($(this).text())>=0){}
				   else{
				   type+=","+$(this).text();}
				   $("#types").val(type);
				})
				})

    });

    function cheIsnull() {
        if (document.getElementById("title").value == "") {
            alert("请填写标题！");
            document.getElementById("title").focus();
            return false;
        }
        if (document.getElementById("title").value == "") {
            alert("请填写问题内容！");
            document.getElementById("title").focus();
            return false;
        }
        if (document.getElementById("title").value == "") {
            alert("请填写您的邮箱地址！");
            document.getElementById("title").focus();
            return false;
        }
        if (document.getElementById("title").value == "") {
            alert("请填写您的手机号码！");
            document.getElementById("title").focus();
            return false;
        }
        return true;
    }
