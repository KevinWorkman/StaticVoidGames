function updatePoints(index){
	if(index >= $( ".points" ).length){
		return;
	}
	else{
		var element = $( ".points" ).eq(index);
		var member = element.attr("class").split(' ')[1];
		
		var m = baseUrl;
		
		console.log("m: " + m);
				
		//$.ajax({url: baseUrl+"api/members/" + member + "/points", success: function(result){
	//	$.ajax({url: "<c:url value="/members/${loggedInUser}/points" />", success: function(result){
			$.ajax({url: baseUrl + "/members/" + member + "/points", success: function(result){
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