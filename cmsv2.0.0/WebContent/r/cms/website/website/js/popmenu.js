

$(document).ready(function(){   
  
    $(".mainnav li").mouseover(function() { //When trigger is clicked...   
        $(this).find("ul").show(); //Drop down the subnav on click   
  
        $(this).hover(function() {   
        }, function(){   
            $(this).find("ul").hide(); //When the mouse hovers out of the subnav, move it back up   
        });           
    });   
	
	  $(".service-list li").mouseover(function() {
        $(this).find(".descr").show();  
		 $(this).find(".mask2").show();               
    }); 
	 $(".service-list li").mouseout(function() {
        $(this).find(".descr").hide(); 
		$(this).find(".mask2").hide();         
    }); 
  
});  
