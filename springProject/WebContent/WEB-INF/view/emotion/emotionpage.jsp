<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="g.g.d.com.food.vo.FoodVO"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="g.g.d.com.emotion.common.RandomNumbering" %>
<%@ page import="g.g.d.com.movie.vo.MovieVO"%>

<% request.setCharacterEncoding("UTF-8"); 
	Object objFood=request.getAttribute("foodList");
	List<FoodVO> foodList=null;
	if(objFood!=null){
		foodList=(List)objFood;	
	}
	Object objMovie=request.getAttribute("movieList");
	ArrayList<MovieVO> movieList=null;
	if(objMovie!=null){
		movieList=(ArrayList)objMovie;
	}
	System.out.println(movieList);
	//System.out.print(foodList.get(0).getFnum());
	int foodNumber[]= new RandomNumbering().RandomNumber(10, foodList);
	/*음식*/
	StringBuffer foodIndexData=new StringBuffer();//1
	StringBuffer foodNameData=new StringBuffer();//2
	StringBuffer foodOneservingData=new StringBuffer();//1회 제공량//3
	StringBuffer foodKcalData=new StringBuffer();//100g당 칼로리//4
	StringBuffer foodBitternessData=new StringBuffer();//쓴맛/담백함//5
	StringBuffer foodUmamiData=new StringBuffer();//감칠맛//6
	StringBuffer foodSaltyData=new StringBuffer();//짠맛//7
	StringBuffer foodSweetnessData=new StringBuffer();//단맛//8
	StringBuffer foodSourtasteData=new StringBuffer();//신맛//9
	StringBuffer foodSweetAndSaltyData=new StringBuffer();//단짠//10
	StringBuffer foodSourceData=new StringBuffer();//출처//11
	/*음식*/
	StringBuffer movieNumData=new StringBuffer();//1//<1
	StringBuffer movieNameData=new StringBuffer();//1//<1
	StringBuffer movieLinkData=new StringBuffer();//2영화정보링크
	StringBuffer movieImageData=new StringBuffer();//3영화이미지링크
	StringBuffer moviePubDateData=new StringBuffer();//4영화개봉일<3
	StringBuffer movieDirectorData=new StringBuffer();//5영화감독<4
	StringBuffer movieActorData=new StringBuffer();//6영화출연진<5
	StringBuffer movieUserRatingData=new StringBuffer();//7영화평점
	StringBuffer movieGenreData=new StringBuffer();//8영화장르<2
	/*영화*/
	
	
	/*음식*/
	for(int i=0;i<foodNumber.length;i++){
		foodIndexData.append(foodList.get(foodNumber[i]).getFindex()+",");//1
		foodNameData.append(foodList.get(foodNumber[i]).getFname()+",");//2
		foodOneservingData.append(foodList.get(foodNumber[i]).getFoneserving()+",");//3
		foodKcalData.append(foodList.get(foodNumber[i]).getFkcal()+",");//4
		foodBitternessData.append(foodList.get(foodNumber[i]).getFbitterness()+",");//5
		foodUmamiData.append(foodList.get(foodNumber[i]).getFumami()+",");//6
		foodSaltyData.append(foodList.get(foodNumber[i]).getFsalty()+",");//7
		foodSweetnessData.append(foodList.get(foodNumber[i]).getFsweetness()+",");//8
		foodSourtasteData.append(foodList.get(foodNumber[i]).getFsourtaste()+",");//9
		foodSweetAndSaltyData.append(foodList.get(foodNumber[i]).getFsweetandsalty()+",");//10
		foodSourceData.append(foodList.get(foodNumber[i]).getFsource().replaceAll("'", "")+",");//11
	}

	System.out.println("1==========================================================");
	/*영화*/
	System.out.println(movieList.get(0).getMvnum());
	for(int i=0;i<movieList.size();i++){
		movieNumData.append(movieList.get(i).getMvnum()+",");
		movieNameData.append(movieList.get(i).getMvname()+",");
		movieLinkData.append(movieList.get(i).getMvlink()+",");
		movieImageData.append(movieList.get(i).getMvimage()+",");
		moviePubDateData.append(movieList.get(i).getMvpubDate()+",");
		movieDirectorData.append(movieList.get(i).getMvdirector()+",");
		movieActorData.append(movieList.get(i).getMvactor()+",");
		movieUserRatingData.append(movieList.get(i).getMvuserRating()+",");
		movieGenreData.append(movieList.get(i).getMvgenre()+",");
	}
	System.out.println("2==========================================================");
%>

<!DOCTYPE html>
<html>
<meta charset="EUC-KR">
<head>
<title>testProject</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 디바이스에 최적화된 크기로 출력됨 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0
      maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">

<style type="text/css">
	#line2 > th > span > span > a >img{width:150px; height:200px;}
	#line3 > th > span > span > a >img{width:150px; height:200px;}
#nameList{
	font-size: 17px
}

#line-top {
position: sticky;
 top: 30px;
  height: 80px;
  background: blue;
}

#lineList {
    border-bottom: 1px solid Gainsboro ;
    padding: 0px;
}

#infoList{
	font-size: 15px;
}
</style>
<!-- 
	<link href="/css/demo.css" rel="stylesheet" type="text/css" />
-->
	<spring:url value="/include/css/jqbar.css" var="jqbarCss"/>
	<spring:url value="/include/js/jqbar.js" var="jqbarJs"/>
	<spring:url value="/include/js/jquery.min.js" var="jquery"/>
	<link href="${jqbarCss}?ver=3" rel="stylesheet"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<script type="text/javascript">
(function ($) {
    $.fn.extend({
        jqbar: function (options) {
            var settings = $.extend({
                animationSpeed: 2000,
                barLength: 200,
                orientation: 'h',
                barWidth: 50,
                barColor: 'red',
                label: '&nbsp;',
                value: 100
            }, options);
            return this.each(function () {

                var valueLabelHeight = 0;
                var progressContainer = $(this);
                if (settings.orientation == 'h') {

                    progressContainer.addClass('jqbar horizontal').append('<span class="bar-label"></span><span class="bar-level-wrapper"><span class="bar-level"></span></span><span class="bar-percent"></span>');

                    var progressLabel = progressContainer.find('.bar-label').html(settings.label);
                    var progressBar = progressContainer.find('.bar-level').attr('data-value', settings.value);
                    var progressBarWrapper = progressContainer.find('.bar-level-wrapper');
					
                    progressBar.css({ height: settings.barWidth, width: 0, backgroundColor: settings.barColor });

                    var valueLabel = progressContainer.find('.bar-percent');
                    valueLabel.html('0');
                }
                else {

                    progressContainer.addClass('jqbar vertical').append('<span class="bar-percent"></span><span class="bar-level-wrapper"><span class="bar-level"></span></span><span class="bar-label"></span>');

                    var progressLabel = progressContainer.find('.bar-label').html(settings.label);
                    var progressBar = progressContainer.find('.bar-level').attr('data-value', settings.value);
                    var progressBarWrapper = progressContainer.find('.bar-level-wrapper');
                    progressContainer.css('height', settings.barLength);
                    progressBar.css({ height: settings.barLength, top: settings.barLength, width: settings.barWidth, backgroundColor: settings.barColor });
                    progressBarWrapper.css({ height: settings.barLength, width: settings.barWidth });

                    var valueLabel = progressContainer.find('.bar-percent');
                    valueLabel.html('0');
                    valueLabelHeight = parseFloat(valueLabel.outerHeight()).toFixed(2);
                    valueLabel.css({ top: (settings.barLength - valueLabelHeight) + 'px' });
                }

                animateProgressBar(progressBar);

                function animateProgressBar(progressBar) {

                    var level = parseFloat(progressBar.attr('data-value'))+30;
                    if (level > 100) {
                        level = 100;
                        //alert('max value cannot exceed 100 percent');
                    }
                    var w = settings.barLength * level / 100;

                    if (settings.orientation == 'h') {
                        progressBar.animate({ width: w }, {
                            duration: 2000,
                            step: function (currentWidth) {
                                var percent = parseInt(currentWidth / settings.barLength * 100);
                                if (isNaN(percent))
                                    percent = 0;
                                progressContainer.find('.bar-percent').html(percent + '%');
                            }
                        });
                    }
                    else {

                        var h = settings.barLength - settings.barLength * level / 100;
                        progressBar.animate({ top: h }, {
                            duration: 2000,
                            step: function (currentValue) {
                                var percent = parseFloat((parseFloat(settings.barLength).toFixed(2) - parseFloat(currentValue)).toFixed(2) / parseFloat(settings.barLength).toFixed(2) * 100).toFixed(2);
                                if (isNaN(percent))
                                    percent = 0;
                                progressContainer.find('.bar-percent').html(Math.abs(percent-30).toFixed(2) + '%');
                                //alert(progressBar.attr('data-value'));
                            }
                        });

                        progressContainer.find('.bar-percent').animate({ top: (h - valueLabelHeight) }, 2000);

                    }
                    
                }

            });
        }
    });

})(jQuery);

	$(document).ready(function () {

		var emotion="${emotionText}";
		var emotionData=emotion.split(',');
		//alert(emotion);
		//alert(emotionTest[0].substring(1,emotionTest[0].indexOf(":")))
		//alert(emotionTest[0].substring(emotionTest[0].indexOf(":")+1,emotionTest[0].length-1))
		//alert("1순위>>>"emotionTest[0].substring(0, emotionTest[0].indexOf(':'))
		//alert("1순위 점수"+emotionTest[0].substring(emotionTest[0].indexOf(':')+1,emotionTest[0].length))
		//$('#bar-1').jqbar({ label: 'ASP.NET', value: 99, barColor: '#D64747' });
		//$('#bar-2').jqbar({ label: 'C#', value: 99, barColor: '#FF681F' });
		//$('#bar-3').jqbar({ label: 'Javascript', value: 90, barColor: '#ea805c' });
		//$('#bar-4').jqbar({ label: 'HTML5', value: 50, barColor: '#88bbc8' });
		//$('#bar-5').jqbar({ label: 'CSS3', value: 60, barColor: '#939393' });
		//$('#bar-6').jqbar({ label: 'jQuery', value: 70, barColor: '#3a89c9' });
		$('#bar-1').jqbar({ label: emotionData[0].substring(1, emotionData[0].indexOf(':')),
							barColor: '#D64747', value: emotionData[0].substring(emotionData[0].indexOf(':')+1,emotionData[0].length-1), orientation: 'v' });
		$('#bar-2').jqbar({ label: emotionData[1].substring(1, emotionData[1].indexOf(':')), 
							barColor: '#FF681F', value: emotionData[1].substring(emotionData[1].indexOf(':')+1,emotionData[1].length-1), orientation: 'v' });
		$('#bar-3').jqbar({ label: emotionData[2].substring(1, emotionData[2].indexOf(':')), 
							barColor: '#ea805c', value: emotionData[2].substring(emotionData[2].indexOf(':')+1,emotionData[2].length-1), orientation: 'v' });
		$('#bar-4').jqbar({ label: emotionData[3].substring(1, emotionData[3].indexOf(':')), 
							barColor: '#88bbc8', value: emotionData[3].substring(emotionData[3].indexOf(':')+1,emotionData[3].length-1), orientation: 'v' });
		$('#bar-5').jqbar({ label: emotionData[4].substring(1, emotionData[4].indexOf(':')), 
							barColor: '#939393', value: emotionData[4].substring(emotionData[4].indexOf(':')+1,emotionData[4].length-1), orientation: 'v' });
		$('#bar-6').jqbar({ label: emotionData[5].substring(1, emotionData[5].indexOf(':')), 
							barColor: '#3a89c9', value: emotionData[5].substring(emotionData[5].indexOf(':')+1,emotionData[5].length-1), orientation: 'v' });
		emo_food();
		emo_movie();
		
	});
		
		
		
	function emo_food(){
		//var foods_emo= [["chicken", "pizza", "seolleongtang"], ["", "", ""], ["", "", ""]];
		//var foods = foods_emo[emo];
		var foodIndex='<%=foodIndexData%>';
		var foodName='<%=foodNameData%>';
		var foodOneserving='<%=foodOneservingData%>';
		var foodKcal='<%=foodKcalData%>';
		var foodBitterness='<%=foodBitternessData%>';
		var foodUmami='<%=foodUmamiData%>';
		var foodSalty='<%=foodSaltyData%>';
		var foodSweetness='<%=foodSweetnessData%>';
		var foodSourtaste='<%=foodSourtasteData%>';
		var foodSource='<%=foodSourceData%>';
		
		foodIndex=foodIndex.substring(0,foodIndex.length-1).split(',');
		foodName=foodName.substring(0,foodName.length-1).split(',');
		foodOneserving=foodOneserving.substring(0,foodOneserving.length-1).split(',');
		foodKcal=foodKcal.substring(0,foodKcal.length-1).split(',');
		foodBitterness=foodBitterness.substring(0,foodBitterness.length-1).split(',');
		foodUmami=foodUmami.substring(0,foodUmami.length-1).split(',');
		foodSalty=foodSalty.substring(0,foodSalty.length-1).split(',');
		foodSweetness=foodSweetness.substring(0,foodSweetness.length-1).split(',');
		foodSourtaste=foodSourtaste.substring(0,foodSourtaste.length-1).split(',');
		foodSource=foodSource.substring(0,foodSource.length-1).split(',');
		
		len = foodIndex.length;
		for(var i=0; i<len; i++){
			var food = foodName[i];
			var foodInfo="<td align='left'><p><b><span id='lineList'><span id='nameList'>"+foodName[i]+"</span></span></b><br><br><span id='infoList'>&nbsp;1회 제공량 : "+foodOneserving[i]+"<br>&nbsp;칼로리(100g당) : "+foodKcal[i]+"kcal<br>&nbsp;쓴맛/담백함 : "+
						foodBitterness[i]+"/5<br>&nbsp;감칠맛 : "+foodUmami[i]+"/5<br>&nbsp;짠맛 : "+foodSalty[i]+"/5<br>&nbsp;단맛 : "+
						foodSweetness[i]+"/5<br>&nbsp;신맛 : "+foodSourtaste[i]+"/5</span></p></td>";
			
			var ss = "<th><span id='food_wrap'>" +
				     	"<span id=food_wrapImg><a href='../review/test1.ggd?food="+food+"' id='foodimg' value='"+foodName[i]+"'>"+
							"<img src='/springProject/img/"+foodIndex[i]+".jpg' align='top'></a></span>"+
							"<span id='food_text'>"+foodInfo+"</span></th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";	
							//'spring:url value=/resources/img/'"+foods[i]+".jpg'>"+foods[i];
								//'/img/"+foods[i]+".jpg'>"+foods[i]+
			$("#line2").append(ss);
			if(i>3){break;}
		}
	}
	function emo_movie(){

		var movieNum='<%=movieNumData%>';
		var movieName='<%=movieNameData%>';
		var movieLink='<%=movieLinkData%>';
		var movieImage='<%=movieImageData%>';
		var moviePubDate='<%=moviePubDateData%>';
		var movieDirector='<%=movieDirectorData%>';
		var movieActor='<%=movieActorData%>';
		var movieUserRating='<%=movieUserRatingData%>';
		var movieGenre='<%=movieGenreData%>';
		
		movieNum=movieNum.substring(0,movieNum.length-1).split(',');
		movieName=movieName.substring(0,movieName.length-1).split(',');
		movieLink=movieLink.substring(0,movieLink.length-1).split(',');
		movieImage=movieImage.substring(0,movieImage.length-1).split(',');
		moviePubDate=moviePubDate.substring(0,moviePubDate.length-1).split(',');
		movieDirector=movieDirector.substring(0,movieDirector.length-1).split(',');
		movieActor=movieActor.substring(0,movieActor.length-1).split(',');
		movieUserRating=movieUserRating.substring(0,movieUserRating.length-1).split(',');
		movieGenre=movieGenre.substring(0,movieGenre.length-1).split(',');
		
		len = movieImage.length;
		
		for(var i=0; i<len; i++){
			var movie = movieImage[i];
			var oneActor=movieActor[i].split("|");
			if(oneActor.length>1){
				alert(oneActor[0]+" 외 "+oneActor.length+"인");
			}
			
			var movieInfo="<td align='left'><p><span id='nameList'><b><a href='"+movieLink[i]+"' style='color:black'>"+movieName[i]+"</a></b></span><br><span id='lineList'><span id='infoList'>"+movieGenre[i]+"<br></span></span><br><span id='infoList'>제작년도 : "+moviePubDate[i]+"<br>감독 : "+
							movieDirector[i].replaceAll("|",", ").substring(0,movieDirector[i].length-1)+"<br>출연진 : "+movieActor[i].replaceAll("|",", ").substring(0,movieActor[i].length)+"<br>평점 : "+movieUserRating[i]+"</span></p></td>";
			
			if(movieImage[i]==""||movieImage[i]=="-"){
				movieImage[i]="../noimage/noimage.gif";
			}
			//String movieName, String movieLink ../review/test1.ggd?food=
				var ss = "<th><span id='movie_wrap'>" +
					     	"<span id=movie_wrapImg><a href='../emotion/moviecount.ggd?movieNum="+movieNum[i]+"&movieLink="+movieLink[i]+"' >"+
								"<img src='"+movieImage[i]+"' align='top'></a></span>"+
								"<span id=movie_text>"+movieInfo+"</span></th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";						
								//'spring:url value=/resources/img/'"+foods[i]+".jpg'>"+foods[i];
								//'/img/"+foods[i]+".jpg'>"+foods[i]+
				$("#line3").append(ss);
			if(i>3){break;}
		}
	}
	
</script>	
</head>
<body>

	<hr>
	<div align="center" >
		<h3>${seName}님의 감정 구성</h3>
	</div>
	<br><!-- 
	<div align="center">
		<h4>${textData}</h4>
	</div>-->
	
	<div align="center">
		<div id="bar-1"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<div id="bar-2"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<div id="bar-3"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<div id="bar-4"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<div id="bar-5"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<div id="bar-6"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</div>

	<br><br><br><br><br><br><br>
	<hr>
	<br>
	<div align="right">
		<a href="">더보기</a>
	</div>
	<div>
		<h3 align=center>음식 추천</h3>
	</div>
	<br>
	<div id="line2" align="center">
		<br>
	</div>
	
	<hr>
	<br>
	<div align="right">
		<a href="">더보기</a>
	</div>
	<div>
		<h3 align=center>영화 추천</h3>
	</div>
	<br>
	<div id="line3" align="center">
		<br>
	</div>
</body>
</html>
