function reBtn(data){
	//alert("콩");
	qwer = data;
	console.log(data.parentElement.parentElement.children[0].textContent);
	var placeName = data.parentElement.parentElement.children[0].textContent;
	placeId = data.parentElement.parentElement.children[5].value;
	console.log(placeId);
	$(".popup-layer").remove();
	
	var popupClose = $("<input>");
	popupClose.attr({"type":"button","id":"popupClose", "value":"닫기"});
	
	var alink = $("<input>");
	alink.attr({"type":"button","id":"insertForm","value":"리뷰 등록"});
	
	$("<div/>").addClass("popup-layer").html(
	//		$("<div/>").addClass("popup").html(
			$(this).children().html()
	//		)
		).appendTo("body").fadeIn();
	$(".popup-layer").append(alink).append(popupClose).append("<br>");
	selectAll(placeId);
}



function selectAll(kakaoid){
	//alert(kakaoid);
	let selectAllURL = "reviewListAll.ggd";
	let method = "GET";
	let dataParam = {
		kakaoid : kakaoid
	}
	$.ajax({
		url:selectAllURL,
		type:method,
		data:dataParam,
		success:whenSuccess,
		error:whenError
	});
	
	function whenSuccess(resData){
		
		if(resData != "" && resData.length>0){
			listStr = resData.split("&");
			for(var i=0; i<listStr.length-1; i++){
				// alert(listStr[i]);
				listReview = listStr[i].split(",");
				var renum = listReview[0];
				var renickname = listReview[1];
				var recontent = listReview[2];
				var rephoto = listReview[3];
				var rerating = listReview[4];
				var reinsertdate = listReview[5];
				// alert(renum + reinsertdate);
				selectList(renum, renickname, recontent, rephoto, rerating, reinsertdate);
			}
		}else{
			$(".popup-layer").append("<p>등록된 리뷰가 없습니다. 리뷰를 등록해주세요</p>");
		}
	}
	
	function whenError(e){
		alert("오류 발생 : " + e);
	}
	
	
}

function selectList(renum, renickname, recontent, rephoto, rerating, reinsertdate){
	var liTag = $("<li>");
	liTag.attr("id", "delLi");
	liTag.addClass("delLi");
	var pTag1 = $("<span>");
	var spanName = $("<span>");
	spanName.addClass("renickname");
	spanName.html(renickname + "님");
	
	var spanDate = $("<span>");
	spanDate.addClass("reinsertdate");
	spanDate.html(" / " + reinsertdate);
	
	var renumTag = $("<input>");
	renumTag.attr({"type":"hidden", "id":"renum", "value":renum});

	var pTag2 = $("<span>");
	
	var imgSpan = $("<span>");
	imgSpan.attr("id","imgdiv");
	
	var spanPa = $("<span>");
	spanPa.attr({"id":"spanpa"});
	
	var spanRate = $("<span>");
	spanRate.addClass("star-prototype");
	spanRate.html(rerating);

	
	spanRate.html($('<span/>'))
	spanRate.children().css('width',rerating*16);
	var spanContent = $("<span>");
	spanContent.addClass("recontent");
	spanContent.html(recontent);
	
	
	pTag1.append(spanPa).append(spanRate).append(renumTag);

	if(rephoto != "null"){
		var imgTag = $("<img>");
		imgTag.attr({"src":"../reviewuploadstorage/"+rephoto, "id":"rephoto"});
		
		imgSpan.append(imgTag);		

	}
	//spanPa;
	//pTag2.append(spanName).append(spanDate).append(spanContent);
	pTag1.append(pTag2);
	liTag.append(pTag1).append(imgSpan).append(spanName).append(spanDate).append(spanContent);;	
	$(".popup-layer").append(liTag);
	
}





function insertForm(){
	var asdf =  '<form id="reviewForm" name="reviewForm" enctype="mutlipart/form-data">' +
	'<table id="insert_review_tb" border="1">' +
	'<tr>' +
		'<td id="insert_review_td1">작성자</td>' +
		'<td><input type="text" id="renickname" name="renickname"></td>' +
		'<input type="hidden" id="renumVal" name="renumVal">' +
	'</tr>' +
	'<tr>' +
		'<td id="insert_review_td1">평점</td>' +
		'<td colspan="3">' +
		'<div class="startRadio"> ' +
		  '<label class="startRadio__box"> '+
		    '<input type="radio" name="rerating" id="rerating" value="0.5"> '+
		    '<span class="startRadio__img"><span class="blind">1</span></span> '+
		  '</label> '+
		  '<label class="startRadio__box"> '+
		    '<input type="radio" name="rerating" id="rerating" value="1.0"> '+
		    '<span class="startRadio__img"><span class="blind">1.5</span></span> '+
		  '</label> '+
		  '<label class="startRadio__box"> '+
		    '<input type="radio" name="rerating" id="rerating" value="1.5"> '+
		    '<span class="startRadio__img"><span class="blind">2</span></span> '+
		  '</label> '+
		  '<label class="startRadio__box"> '+
		    '<input type="radio" name="rerating" id="rerating" value="2.0"> '+
		    '<span class="startRadio__img"><span class="blind">2.5</span></span> '+
		  '</label> '+
		  '<label class="startRadio__box"> '+
		    '<input type="radio" name="rerating" id="rerating" value="2.5"> '+
		    '<span class="startRadio__img"><span class="blind">3</span></span> '+
		  '</label> '+
		  '<label class="startRadio__box"> '+
		    '<input type="radio" name="rerating" id="rerating" value="3.0"> '+
		    '<span class="startRadio__img"><span class="blind">3.5</span></span> '+
		  '</label> '+
		  '<label class="startRadio__box"> '+
		    '<input type="radio" name="rerating" id="rerating" value="3.5"> '+
		    '<span class="startRadio__img"><span class="blind">4</span></span> '+
		  '</label> '+
		  '<label class="startRadio__box"> '+
		    '<input type="radio" name="rerating" id="rerating" value="4.0"> '+
		    '<span class="startRadio__img"><span class="blind">4.5</span></span> '+
		  '</label> '+
		  '<label class="startRadio__box"> '+
		    '<input type="radio" name="rerating" id="rerating" value="4.5"> '+
		    '<span class="startRadio__img"><span class="blind">5</span></span> '+
		  '</label> '+
		  '<label class="startRadio__box"> '+
		    '<input type="radio" name="rerating" id="rerating" value="5.0"> '+
		    '<span class="startRadio__img"><span class="blind">5.5</span></span> '+
		  '</label> '+
		'</div> '+
		'</td>' +
	'</tr>' +
	'<tr>' +
		'<td id="insert_review_td1">내용</td>' +
		'<td colspan="3"> <textarea id="recontent" name="recontent" rows="3" cols="50"></textarea>' +
		'<div style="text-align:right; margin:3px"><span class="bytes">0</span>bytes</div></td>'+
	'</tr>' +
	'<tr>' +
		'<td id="insert_review_td1">사진</td>' +
		'<td colspan="3">' + 
		'<label for="file" id="fileSelect">파일선택</label>' + 
		'<input type="file" id="file" name="file" style="display:none"></td>' +
	'</tr>' +
	'</table>' +
//		'<input type="button" id="insertBtn" value="등록하기">' +
	'</form>';
	$(".popup-layer2").append(asdf); 
}



function updateBtn(){
		//console.log($(this).prev().val());
	var conBool = confirm("정말 수정 하시겠습니까?");
	if(conBool){
		var form = new FormData($("#reviewForm")[0]);
		form.append("kakaoid",placeId);
		form.append("renum", $("#renumVal").val());
		let deleteURL = "reviewUpdate.ggd";
		let method = "POST";
		console.log($("#renumVal").val());
		$.ajax({
			url:deleteURL,
			type:method,
			enctype: 'multipart/form-data',
			data:form,
			cache:false,
			processData: false,
	        contentType: false,	
			success:whenSuccess,
			error:whenError
		});
		
		function whenSuccess(resData){
			//alert(resData);
			reBtn(qwer);
			$(".popup-layer2").fadeOut(function(){ $(this).remove(); });
		}
		
		function whenError(e){
			alert("에러 발생 : " + e);
		}
	}


}



function deleteBtn(){
		//console.log($(this).prev().val());
	var conBool = confirm("정말 삭제 하시겠습니까?");
	if(conBool){
		let deleteURL = "reviewDelete.ggd";
		let method = "GET";
		console.log($("#renumVal").val());
		let dataParam = {
				kakaoid : placeId,
				renum : $("#renumVal").val()
		}
		$.ajax({
			url:deleteURL,
			type:method,
			data:dataParam,
			success:whenSuccess,
			error:whenError
		});
		
		function whenSuccess(resData){
			//alert(resData);
			reBtn(qwer);
			$(".popup-layer2").fadeOut(function(){ $(this).remove(); });
		}
		
		function whenError(e){
			alert("에러 발생 : " + e);
		}

	}
}


