// JavaScript Document
$(function () {
    $('#sleft').click(function(){
		// alert($('#smiddle ul').css("left"));
		  if($('#smiddle ul').css("left")!="0px"){
		  $('#smiddle ul').animate({left:'+=335px'});}
		});
    $('#sright').click(function(){
		if($('#smiddle ul').css("left")=="-670px"){}
		else{
		  $('#smiddle ul').animate({left:'-=335px'});}
		});
    //$("#D .touming").css("opacity", "0.8")
   // wid = $(".bimg").width();


})