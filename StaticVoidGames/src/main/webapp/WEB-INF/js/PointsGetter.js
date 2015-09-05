function updatePoints(index){
	if(index >= $( ".points" ).length){
		return;
	}
	else{
		var element = $( ".points" ).eq(index);
		var member = element.attr("class").split(' ')[1];
				
		$.ajax({url: baseUrl+"/api/members/" + member + "/points", success: function(result){
			if(result){
				element.html("[" + result + "]");
			}
			updatePoints(index+1);
	    	}});
	}
}

function updateAllPoints(){
	updatePoints(0);
}