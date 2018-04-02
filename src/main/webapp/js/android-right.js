/**
 * 
 */
$(function(){
	$("#rightLine").hover(
		  function () {
		    $("#rightBar").css("display","block");
		  }
	);
	$("#rightBar").mouseleave(function() {
		 $("#rightBar").animate({
			opacity : 'hide'
		});
	});
 })