package g.g.d.com.emotion.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import g.g.d.com.board.service.BoardService;
import g.g.d.com.board.vo.BoardVO;
import g.g.d.com.emotion.common.MovieGenreUtil;
import g.g.d.com.emotion.common.NaverMovieApi;
import g.g.d.com.emotion.common.RandomNumbering;
import g.g.d.com.emotion.common.emotionRankUtil;
import g.g.d.com.food.service.FoodService;
import g.g.d.com.food.vo.FoodVO;
import g.g.d.com.mem.common.Encode;
import g.g.d.com.mem.service.MemberService;
import g.g.d.com.mem.vo.MemberVO;
import g.g.d.com.movie.service.MovieService;
import g.g.d.com.movie.vo.MovieListVO;
import g.g.d.com.movie.vo.MovieUserGenreVO;
import g.g.d.com.movie.vo.MovieVO;



@Controller
@RequestMapping(value="emotion")
public class EmotionController {
		
		Logger logger = Logger.getLogger(EmotionController.class);
		@Autowired(required=false)
		private FoodService foodService;
		@Autowired(required=false)
		private MovieService movieService;
		@Autowired(required=false)
		private BoardService boardService;
		@Autowired(required=false)
		private MemberService memberSelect;
		
		@RequestMapping(value="/moviecount",method=RequestMethod.GET)
		public String movieCount(String movieNum, String movieLink) {
			String mvnum=movieNum;
			
			logger.info(mvnum+"><><"+movieLink.length());
			int movicnt=0;
			movicnt=movieService.MovieCountService(mvnum);
			logger.info(movicnt);
			
			if(movieLink.equals("")&&movieLink.equals("-")) {
				return "redirect:emotionSearch";
			}else {
				return "redirect:"+movieLink;
			}
		}
		@RequestMapping(value="/mainboard", method= RequestMethod.POST)
		@ResponseBody
		public List<BoardVO> mainboard(){
			logger.info("메인보더 조회시작~~~~~~~~~~~~~~");
			BoardVO bvo=new BoardVO();
			List<BoardVO> boardList = boardService.boardListAllService(bvo);
			logger.info(boardList.get(0).getBnum());
			
			return boardList;
		}
		
		@RequestMapping(value="/foodrank", method= RequestMethod.POST)
		@ResponseBody
		public List<FoodVO> foodRank(){
			logger.info("푸드랭킹 시작~~~~~~~~~~~~~~");
			FoodVO fdvo=new FoodVO();
			List<FoodVO> foodRankList=foodService.FoodRankService(fdvo);
			logger.info(foodRankList.get(0).getFnum());
			
			return foodRankList;
		}
		
		@RequestMapping(value="/movierank", method= RequestMethod.POST)
		@ResponseBody
		public List<MovieVO> movieRank(){
			logger.info("무비랭킹 시작~~~~~~~~~~~~~~");
			String genre="";
			MovieListVO mvlvo=new MovieListVO();
			List<MovieListVO> movieRankList = movieService.MovieRankService(mvlvo);
			logger.info(movieRankList.get(0).getMvnum());


			List<MovieVO> movieList=new ArrayList<>();
			if(movieRankList.size()>0) {
				for(int i=0;i<movieRankList.size();i++) {
					movieList.add(NaverMovieApi.returnMovieData(movieRankList.get(i).getMvname(), genre, movieRankList.get(i).getMvnum(), movieRankList.get(i).getMoviecnt(), null));	
				}
			}
			
			return movieList;
		}
		
		@RequestMapping(value="/logout",method= {RequestMethod.GET , RequestMethod.POST})
		public String logout(HttpServletRequest hsr, Model model) {
			logger.info("로그아웃... 메인페이지로 이동");
			HttpSession session = hsr.getSession();
			session.invalidate();
			return "../../index";
		}
		
		@RequestMapping(value="/login",method= {RequestMethod.GET , RequestMethod.POST})
		public String login(HttpServletRequest hsr, Model model) {
			logger.info("메인페이지로 이동");
			HttpSession session = hsr.getSession();
			String seName=session.getAttribute("mid").toString();
			String seNum=session.getAttribute("mnum").toString();
			model.addAttribute("seName", seName);
			model.addAttribute("seNum", seNum);
			return "../../index";
		}
		@RequestMapping(value="/mainpage",method= {RequestMethod.GET , RequestMethod.POST})
		public String mainpage(HttpServletRequest hsr, Model model) {
			logger.info("메인페이지로 이동");
			HttpSession session = hsr.getSession();
			if(session.getAttribute("mid")!=null&&session.getAttribute("mnum")!=null) {
				String seName=session.getAttribute("mid").toString();
				String seNum=session.getAttribute("mnum").toString();
				model.addAttribute("seName", seName);
				model.addAttribute("seNum", seNum);
			}
			return "../../index";
		}
			
		
		
		@RequestMapping(value="/emotionSearch",method= {RequestMethod.GET , RequestMethod.POST})
		public String search(HttpServletRequest hsr,Model model, FoodVO fdvo, @ModelAttribute MovieListVO mvlvo) {
			
			String emotionText=Encode.utf8(hsr.getParameter("text").toString());
			String textData=emotionText;
			model.addAttribute("textData",textData);
			System.out.println(emotionText+"==================");
			HttpSession session = hsr.getSession();
			String seName="";
			String seNum="";
			if(session.getAttribute("mid")!=null&&session.getAttribute("mnum")!=null) {
				seName=session.getAttribute("mid").toString();
				seNum=session.getAttribute("mnum").toString();
				model.addAttribute("seName", seName);
				model.addAttribute("seNum", seNum);
			}else {
				return "redirect:logout.ggd";
			}
		    //=============this Emotion_thisName Method Start
			String emotionUrl = "http://192.168.219.128:5000/tospring?TextData=";
			String emotionSb = "";
			try {
				
				emotionText = URLEncoder.encode(emotionText, "UTF-8")
                        .replaceAll("\\+", "%20")
                        .replaceAll("\\%21", "!")
                        .replaceAll("\\%27", "'")
                        .replaceAll("\\%28", "(")
                        .replaceAll("\\%29", ")")
                        .replaceAll("\\%7E", "~");
				
				emotionUrl=emotionUrl+emotionText;
				
				HttpURLConnection conn = (HttpURLConnection) new URL(emotionUrl).openConnection();
				

				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

				String line = null;

				while ((line = br.readLine()) != null) {
					emotionSb = emotionSb + line;
				}
				System.out.println("========br======" + emotionSb.toString());
				if (emotionSb.toString().contains("ok")) {
					System.out.println("test");
					
				}
				br.close();

				System.out.println("" + emotionSb.toString());
			} catch (Exception e) {
				emotionText="Error";
			} 
			if (emotionText.equals("Error")) {
				model.addAttribute("emotionText",emotionText.toString());
			}else {
				model.addAttribute("emotionText",emotionSb.toString());
			}
			MemberVO mvo=null;
			mvo=new MemberVO();
			mvo.setMnum(seNum);
			mvo.setMid(seName);
			logger.info(seNum);
			mvo=memberSelect.memberSelect(mvo);
			
			logger.info("푸드 시작");
			
			String[] emotionRank=emotionSb.toString().replaceAll("'", "").split(",");
			logger.info(emotionRank[0]);
			int emotionFoodResult = emotionRankUtil.emotionFood(emotionRank);
			if(emotionFoodResult==2) {
				fdvo.setFoodtype("999");
				logger.info("에러");
			}
			if(emotionFoodResult==1) {
				fdvo.setFoodtype(mvo.getNegativefood());
				logger.info("네거"+mvo.getNegativefood());
			}
			if(emotionFoodResult==0) {
				fdvo.setFoodtype(mvo.getPositivefood());
				logger.info("포지"+mvo.getPositivefood());
			}
			
			
			List<FoodVO> foodList=foodService.FoodSelectListService(fdvo);
			
			if (foodList.size()>0) {
				model.addAttribute("foodList", foodList);
			}
			//============= this Food_thisName Method End
			
			//============= this Movie_thisName Method Start
			
			logger.info(mvo.getMovietaste());
			//유저 영화 취향...
			String[] genre=mvo.getMovietaste().substring(0,mvo.getMovietaste().length()-1).split(",");
			
			MovieUserGenreVO mugvo=MovieGenreUtil.MovieGenre(genre);
			
			//mvlvo.setMvgenre(genre);
			logger.info(mugvo.getMvaction());
			logger.info(mugvo.getMvadventure());
			logger.info(mugvo.getMvcrime());
			logger.info(mugvo.getMvfantasy());
			logger.info(mugvo.getMvmystery());
			logger.info(mugvo.getMvromance());
			logger.info(mugvo.getMvdocumentary());
			
			List<MovieListVO> movieListData=movieService.MovieSelectListService(mugvo);
			logger.info("사이즈>>>>>>"+movieListData.size());
			logger.info("영화 제목>>>"+movieListData.get(0).getMvname());
			logger.info("영화 넘버>>>"+movieListData.get(0).getMvnum());
			logger.info("영화 넘버>>>"+movieListData.get(0).getMvgenre());
			
			List<MovieVO> movieList=new ArrayList<MovieVO>();
			MovieVO mvo_test=new MovieVO();
			String movieName="";
			if(movieListData.size()>0) {
				int movieNumber[]=new RandomNumbering().RandomNumber(100, movieListData);
				for(int i=0;i<movieNumber.length;i++) {
					logger.info("넘버>>>"+movieNumber[i]+">>>"+i+"번쨰");
					logger.info(">>>"+movieListData.get(movieNumber[i]).getMvpubdate());
					logger.info(">>>"+movieListData.get(movieNumber[i]).getMvname());
					logger.info(">>>"+movieListData.get(movieNumber[i]).getMvnum());
					logger.info(">>>"+movieListData.get(movieNumber[i]).getMvgenre());
					mvo_test=(NaverMovieApi.returnMovieData(movieListData.get(movieNumber[i]).getMvname(), movieListData.get(movieNumber[i]).getMvgenre(), movieListData.get(movieNumber[i]).getMvnum(), 0, movieListData.get(i)));	
					
					if(mvo_test.getMvname().equals(movieListData.get(movieNumber[i]).getMvname())) {
						movieList.add(mvo_test);
					}else {
						movieName=mvo_test.getMvname();
						for(int j=0;mvo_test.getMvname().equals(movieListData.get(movieNumber[i]).getMvname())==false;j++) {
							movieNumber[i]=movieNumber[i]+1;
							if(movieListData.size()<movieNumber[i]){
								movieNumber[i]=0;
								if(j>movieListData.size()) {
									break;
								}
							}
							mvo_test=(NaverMovieApi.returnMovieData(movieListData.get(movieNumber[i]).getMvname(), movieListData.get(movieNumber[i]).getMvgenre(), movieListData.get(movieNumber[i]).getMvnum(), 0, movieListData.get(i)));
							if(mvo_test.getMvname().equals(movieName)) {
								for(int k=0;k<movieList.size();k++) {
									if(movieList.get(k).getMvname().equals(movieName)) {
										break;
									}else {
										movieList.add(mvo_test);
									}
								}	
							}
						}
					}
				}
			}
			model.addAttribute("movieList",movieList);
			
			//============= this Movie_thisName Method End
			return "emotion/emotionpage";
		}
		
		
		

		
		
		// androidEmotion
		@RequestMapping(value="/androidEmotionSearch",method= {RequestMethod.GET , RequestMethod.POST}, produces ="application/text; charset=utf8")
		@ResponseBody
		public String searchAnd(HttpServletRequest hsr,Model model, FoodVO fdvo, @ModelAttribute MovieListVO mvlvo) {
			String emotionT=hsr.getParameter("text");
			String emotionText = "";
			try{
				emotionText = new String(emotionT.getBytes("8859_1"), "UTF-8");
			}catch(Exception e) {
				e.printStackTrace();
			}
			String textData=emotionText;
			System.out.println("android text >>>>>>>>>> : " + emotionText);
		    //=============this Emotion_thisName Method Start
			String emotionUrl = "http://192.168.219.128:5000/tospring?TextData=";
			String emotionSb = "";
			try {
				
				emotionText = URLEncoder.encode(emotionText, "UTF-8")
                        .replaceAll("\\+", "%20")
                        .replaceAll("\\%21", "!")
                        .replaceAll("\\%27", "'")
                        .replaceAll("\\%28", "(")
                        .replaceAll("\\%29", ")")
                        .replaceAll("\\%7E", "~");
				
				emotionUrl=emotionUrl+emotionText;
				
				HttpURLConnection conn = (HttpURLConnection) new URL(emotionUrl).openConnection();
				

				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

				String line = null;

				while ((line = br.readLine()) != null) {
					emotionSb = emotionSb + line;
				}
				System.out.println("========br======" + emotionSb.toString());
				if (emotionSb.toString().contains("ok")) {
					System.out.println("test");
					
				}
				br.close();

				System.out.println("" + emotionSb.toString());
			} catch (Exception e) {
				emotionText="Error";
			} 
			if (emotionText.equals("Error")) {
//				model.addAttribute("emotionText",emotionText.toString());
			}else {
//				model.addAttribute("emotionText",emotionSb.toString());
			}
			
			String[] emotionRank=emotionSb.toString().replaceAll("'", "").split(",");
			logger.info(emotionRank[0]);
			int emotionFoodResult = emotionRankUtil.emotionFood(emotionRank);
			if(emotionFoodResult==1) {
				fdvo.setFoodtype("1");
				logger.info("네거"+ "1");
			}
			if(emotionFoodResult==0) {
				fdvo.setFoodtype("1");
				logger.info("포지"+"1");
			}
			
			JSONObject jsonObjectF = new JSONObject();
			JSONArray jsonArrayF = new JSONArray();
			
			
			/*
			List<FoodVO> foodList=foodService.FoodSelectListService(fdvo);
			
			if(foodList != null && foodList.size() > 0) {
				for(int i=0; i<foodList.size(); i++) {
					logger.info("foodList >>>>>>>>>>>>>: " + foodList.get(i).getFname());					
					logger.info("foodList >>>>>>>>>>>>>: " + foodList.get(i).getFindex());
					JSONObject jsonfood = null;
					jsonfood = new JSONObject();
					jsonfood.put("food", foodList.get(i).getFname());
					jsonfood.put("foodImg", foodList.get(i).getFindex());
					jsonArrayF.add(jsonfood);
				}
			}
			jsonObjectF.put("F", jsonArrayF);
			System.out.println("json FOOD>>>>>>>>>>>>>> : " + jsonObjectF.toString());
			
			
			
			//============= this Movie_thisName Method End
			String aaa = "crime,romance,opera,";
			String[] genre = aaa.substring(0,aaa.length()-1).split(",");
						
			MovieUserGenreVO mugvo=MovieGenreUtil.MovieGenre(genre);
			
			//mvlvo.setMvgenre(genre);
			logger.info(mugvo.getMvaction());
			logger.info(mugvo.getMvadventure());
			logger.info(mugvo.getMvcrime());
			logger.info(mugvo.getMvfantasy());
			List<MovieListVO> movieListData=movieService.MovieSelectListService(mugvo);
			logger.info("사이즈>>>>>>"+movieListData.size());
			logger.info("영화 제목>>>"+movieListData.get(0).getMvname());
			logger.info("영화 넘버>>>"+movieListData.get(0).getMvnum());
			logger.info("영화 넘버>>>"+movieListData.get(0).getMvgenre());
			List<MovieVO> movieList=new ArrayList<MovieVO>();
			
			JSONObject jsonObjectM = new JSONObject();
			JSONArray jsonArrayM=new JSONArray();
			
			if(movieListData!=null&&movieListData.size()>0) {
				int movieNumber[]=new RandomNumbering().RandomNumber(500, movieListData);
				for(int i=0;i<movieNumber.length;i++) {
//					logger.info("넘버>>>"+movieNumber[i]+">>>"+i+"번쨰");
//					logger.info(">>>"+movieListData.get(movieNumber[i]).getMvname());
//					logger.info(">>>"+movieListData.get(movieNumber[i]).getMvnum());
//					logger.info(">>>"+movieListData.get(movieNumber[i]).getMvgenre());
					//movieList.add(NaverMovieApi.returnMovieData(movieListData.get(movieNumber[i]).getMvname(), movieListData.get(movieNumber[i]).getMvgenre(), movieListData.get(movieNumber[i]).getMvnum(), 0));	
					MovieVO mmmvo = null;
					mmmvo = NaverMovieApi.returnMovieData(movieListData.get(movieNumber[i]).getMvname(), movieListData.get(movieNumber[i]).getMvgenre(), movieListData.get(movieNumber[i]).getMvnum(), 0);
//					logger.info(mmmvo.getMvname());
//					logger.info(mmmvo.getMvimage());
					JSONObject jsonMovie = null;
					jsonMovie = new JSONObject();
					jsonMovie.put("movie", mmmvo.getMvname());
					jsonMovie.put("movieImg",mmmvo.getMvimage());
					jsonArrayM.add(jsonMovie);
				}
			}
			jsonObjectM.put("M", jsonArrayM);
			System.out.println("json MOVIE>>>>>>>>>>"+jsonArrayM.toString());
		
			
			JSONObject jsonObjectE = new JSONObject();
			JSONArray jsonArrayE = new JSONArray();
			*/
			JSONObject jsonOb = new JSONObject();
			JSONArray jsonArr = new JSONArray();
			String emotionResult = "";
			try {
				emotionResult = new String(emotionSb.getBytes("UTF-8"),"8859_1");
				emotionSb.replace("'", "");
				String abc[] = emotionSb.replace("'", "").split(",");
				for(int i=0; i<abc.length; i++) {
					System.out.println(abc[i]);
//					jsonArr.add(new String(abc[i].getBytes("UTF-8"),"8859_1"));					
					jsonArr.add(abc[i]);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			jsonOb.put("result",jsonArr);
			return jsonOb.toJSONString();
		}
		
		

		@RequestMapping(value="/androidFoodSearch",method= {RequestMethod.GET , RequestMethod.POST})
		@ResponseBody
		public JSONObject foodSearchAnd(HttpServletRequest request,Model model, FoodVO fdvo, @ModelAttribute MovieListVO mvlvo) {
			logger.info("androidFoodSearch >>>>>>>>>>>>>>>>>>>> : ");
			String andId=request.getParameter("id");
			String emotionText=Encode.utf8(request.getParameter("emoText").toString());
			logger.info("emotionText >>>>>>>>>>>>>>> : " + emotionText);
			
			
			
			
			MemberVO mvo=null;
			mvo=new MemberVO();
			mvo.setMid(andId);
			mvo=memberSelect.memberSelect(mvo);
			
			int emotionFoodResult = emotionRankUtil.emotionFoodStr(emotionText);
			//or String[] emotionRank=emotionSb.toString().replaceAll("'", "").split(",");
			//logger.info(emotionRank[0]);
			
			//이부분 1=>emotionFoodResult으로 수정
			if(emotionFoodResult==1) {
				fdvo.setFoodtype("1");
				logger.info("네거"+ "1");
			}
			if(emotionFoodResult==0) {
				fdvo.setFoodtype("1");
				logger.info("포지"+"1");
			}
			
			JSONObject jsonObjectF = new JSONObject();
			JSONArray jsonArrayF = new JSONArray();
			
			
			
			List<FoodVO> foodList=foodService.FoodSelectListService(fdvo);
				
			
			if(foodList != null && foodList.size() > 0) {
				int foodNumber[]=new RandomNumbering().RandomNumber(10, foodList);
				for(int i=0; i<foodNumber.length; i++) {
					logger.info("foodList >>>>>>>>>>>>>: " + foodList.get(foodNumber[i]).getFname());					
					logger.info("foodList >>>>>>>>>>>>>: " + foodList.get(foodNumber[i]).getFindex());
					JSONObject jsonfood = null;
					jsonfood = new JSONObject();
					jsonfood.put("food", foodList.get(foodNumber[i]).getFname());
					jsonfood.put("foodImg", foodList.get(foodNumber[i]).getFindex());
					jsonArrayF.add(jsonfood);
				}
			}
			jsonObjectF.put("F", jsonArrayF);
			System.out.println("json FOOD>>>>>>>>>>>>>> : " + jsonObjectF.toString());
		
			
			
			return jsonObjectF;
			
			/*
			
			int emotionFoodResult = 1//emotionRankUtil.emotionFood(emotionRank);
			if(emotionFoodResult==1) {
				fdvo.setFoodtype("1");
				logger.info("네거"+ "1");
			}
			if(emotionFoodResult==0) {
				fdvo.setFoodtype("1");
				logger.info("포지"+"1");
			}
			
			JSONObject jsonObjectF = new JSONObject();
			JSONArray jsonArrayF = new JSONArray();
			
			
			
			List<FoodVO> foodList=foodService.FoodSelectListService(fdvo);
			
			if(foodList != null && foodList.size() > 0) {
				for(int i=0; i<foodList.size(); i++) {
					logger.info("foodList >>>>>>>>>>>>>: " + foodList.get(i).getFname());					
					logger.info("foodList >>>>>>>>>>>>>: " + foodList.get(i).getFindex());
					JSONObject jsonfood = null;
					jsonfood = new JSONObject();
					jsonfood.put("food", foodList.get(i).getFname());
					jsonfood.put("foodImg", foodList.get(i).getFindex());
					jsonArrayF.add(jsonfood);
				}
			}
			jsonObjectF.put("F", jsonArrayF);
			System.out.println("json FOOD>>>>>>>>>>>>>> : " + jsonObjectF.toString());
			
			return jsonObjectF;
			  
			*/
			
		}
		
		
		
		@RequestMapping(value="/androidMovieSearch",method= {RequestMethod.GET , RequestMethod.POST})
		@ResponseBody
		public JSONObject movieSearchAnd(HttpServletRequest request,Model model, FoodVO fdvo, @ModelAttribute MovieListVO mvlvo) {
			
			String andId=request.getParameter("id");
			//if 
				//return
						
			MemberVO mvo=null;
			mvo=new MemberVO();
			mvo.setMid(andId);
			mvo=memberSelect.memberSelect(mvo);
			
			//유저 영화 취향 불러오기
			String[] genre=mvo.getMovietaste().substring(0,mvo.getMovietaste().length()-1).split(",");
			MovieUserGenreVO mugvo=MovieGenreUtil.MovieGenre(genre);
			
			logger.info(mugvo.getMvaction());
			logger.info(mugvo.getMvadventure());
			logger.info(mugvo.getMvcrime());
			logger.info(mugvo.getMvfantasy());
			List<MovieListVO> movieListData=movieService.MovieSelectListService(mugvo);
			logger.info("사이즈>>>>>>"+movieListData.size());
			logger.info("영화 제목>>>"+movieListData.get(0).getMvname());
			logger.info("영화 넘버>>>"+movieListData.get(0).getMvnum());
			logger.info("영화 넘버>>>"+movieListData.get(0).getMvgenre());
			
			JSONObject jsonObjectM = new JSONObject();
			JSONArray jsonArrayM=new JSONArray();
			String movieName="";
			List<MovieVO> movieList=new ArrayList<MovieVO>();
			if(movieListData!=null&&movieListData.size()>0) {
				int movieNumber[]=new RandomNumbering().RandomNumber(10, movieListData);
				for(int i=0;i<movieNumber.length;i++) {
	
					MovieVO mmmvo = null;
					mmmvo = NaverMovieApi.returnMovieData(movieListData.get(movieNumber[i]).getMvname(), movieListData.get(movieNumber[i]).getMvgenre(), movieListData.get(movieNumber[i]).getMvnum(), 0, movieListData.get(i));
					if(mmmvo.getMvname().equals(movieListData.get(movieNumber[i]).getMvname())) {
						logger.info(mmmvo.getMvname());
						logger.info(mmmvo.getMvimage());
						JSONObject jsonMovie = null;
						jsonMovie = new JSONObject();
						jsonMovie.put("movielink", mmmvo.getMvlink());
						jsonMovie.put("movieimg",mmmvo.getMvimage());
						jsonArrayM.add(jsonMovie);
						movieList.add(mmmvo);
					}else {
						movieName=mmmvo.getMvname();
						for(int j=0;mmmvo.getMvname().equals(movieListData.get(movieNumber[i]).getMvname())==false;j++) {
							movieNumber[i]=movieNumber[i]+1;
							if(movieListData.size()<movieNumber[i]){
								movieNumber[i]=0;
								if(j>movieListData.size()) {
									break;
								}
							}
							mmmvo=(NaverMovieApi.returnMovieData(movieListData.get(movieNumber[i]).getMvname(), movieListData.get(movieNumber[i]).getMvgenre(), movieListData.get(movieNumber[i]).getMvnum(), 0, movieListData.get(i)));
							if(mmmvo.getMvname().equals(movieName)) {
								for(int k=0;k<movieList.size();k++) {
									if(movieList.get(k).getMvname().equals(movieName)) {
										break;
									}else {
										movieList.add(mmmvo);
									}
								}
							}
						}
					}
				}
			}
			jsonObjectM.put("M", jsonArrayM);
			System.out.println("json MOVIE>>>>>>>>>>"+jsonArrayM.toString());
			 
			return jsonObjectM;
			
			/*
			String aaa = "crime,romance,opera,";
			String[] genre = aaa.substring(0,aaa.length()-1).split(",");
						
			MovieUserGenreVO mugvo=MovieGenreUtil.MovieGenre(genre);
			
			//mvlvo.setMvgenre(genre);
			logger.info(mugvo.getMvaction());
			logger.info(mugvo.getMvadventure());
			logger.info(mugvo.getMvcrime());
			logger.info(mugvo.getMvfantasy());
			List<MovieListVO> movieListData=movieService.MovieSelectListService(mugvo);
			logger.info("사이즈>>>>>>"+movieListData.size());
			logger.info("영화 제목>>>"+movieListData.get(0).getMvname());
			logger.info("영화 넘버>>>"+movieListData.get(0).getMvnum());
			logger.info("영화 넘버>>>"+movieListData.get(0).getMvgenre());
			List<MovieVO> movieList=new ArrayList<MovieVO>();
			
			JSONObject jsonObjectM = new JSONObject();
			JSONArray jsonArrayM=new JSONArray();
			
			if(movieListData!=null&&movieListData.size()>0) {
				int movieNumber[]=new RandomNumbering().RandomNumber(500, movieListData);
				for(int i=0;i<movieNumber.length;i++) {
//					logger.info("넘버>>>"+movieNumber[i]+">>>"+i+"번쨰");
//					logger.info(">>>"+movieListData.get(movieNumber[i]).getMvname());
//					logger.info(">>>"+movieListData.get(movieNumber[i]).getMvnum());
//					logger.info(">>>"+movieListData.get(movieNumber[i]).getMvgenre());
					//movieList.add(NaverMovieApi.returnMovieData(movieListData.get(movieNumber[i]).getMvname(), movieListData.get(movieNumber[i]).getMvgenre(), movieListData.get(movieNumber[i]).getMvnum(), 0));	
					MovieVO mmmvo = null;
					mmmvo = NaverMovieApi.returnMovieData(movieListData.get(movieNumber[i]).getMvname(), movieListData.get(movieNumber[i]).getMvgenre(), movieListData.get(movieNumber[i]).getMvnum(), 0);
					logger.info(mmmvo.getMvname());
					logger.info(mmmvo.getMvimage());
					JSONObject jsonMovie = null;
					jsonMovie = new JSONObject();
					jsonMovie.put("movie", mmmvo.getMvname());
					jsonMovie.put("movieImg",mmmvo.getMvimage());
					jsonArrayM.add(jsonMovie);
				}
			}
			jsonObjectM.put("M", jsonArrayM);
			System.out.println("json MOVIE>>>>>>>>>>"+jsonArrayM.toString());
			return jsonObjectM;
			*/
		}
			
}
