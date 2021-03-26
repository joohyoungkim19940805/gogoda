function ratingRoad(placeId,index){
	let ratingURL = "reviewRating.ggd";
	let method = "POST";
	let dataParam = {
		kakaoid:placeId
	}
	$.ajax({
		url : ratingURL,
		type : method,
		data : dataParam,
		success : whenSuccess,
		error : whenError
	});
	
	function whenSuccess(resData){
		console.log(resData);
		var str = resData.split(",");
		var ratingAvg = str[0].substr(0,3);
		var amount ="(" + str[1] + ")";
		
		
		var starImg = $("<img>");
 		starImg.attr({"src":"https://upload.wikimedia.org/wikipedia/commons/e/e7/Empty_Star.svg", "id":"starOne"});
		var avgSpan = $("<div>");
		avgSpan.html(ratingAvg);
		var amountSpan = $("<div>");
		amountSpan.html(amount);
		
		console.log(index);
		$('.review_'+(index+1)).append(starImg).append(avgSpan).append(amountSpan);
		//console.log($('.markerbg marker_'+index+1).next().find(".review"));
	}
	
	function whenError(e){
		//alert("에러 발생 : " + e);
		console.log(e);
	}
}


