package g.g.d.com.emotion.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import g.g.d.com.emotion.common.NaverMovieApi;
import g.g.d.com.emotion.common.RandomNumbering;
import g.g.d.com.food.service.FoodService;
import g.g.d.com.food.vo.FoodVO;

import g.g.d.com.movie.service.MovieService;
import g.g.d.com.movie.vo.MovieVO;
import g.g.d.com.movie.vo.MovieListVO;



@Controller
@RequestMapping(value="emotion")
public class EmotionController {
	
		Logger logger = Logger.getLogger(EmotionController.class);
		@Autowired(required=false)
		private FoodService foodService;
		
		@Autowired(required=false)
		private MovieService movieService;

		@RequestMapping(value="/emotionSearch",method= {RequestMethod.GET , RequestMethod.POST})
		public String search(HttpServletRequest hsr,Model model, FoodVO fdvo, @ModelAttribute MovieListVO mvlvo) {
			
			String emotionText=hsr.getParameter("text");
			String textData=emotionText;
			model.addAttribute("textData",textData);
			System.out.println(emotionText+"==================");
			HttpSession session = hsr.getSession();
			String seName=session.getAttribute("mid").toString();
		    System.out.println(seName);
		    //=============this Emotion_thisName Method Start
			String emotionUrl = "http://127.0.0.1:5000/tospring?TextData=";
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
			//============== this Emotion_thisName Method End
			
			//============== this Food_thisName Method Start
			logger.info("푸드 시작");
			
			List<FoodVO> foodList=foodService.FoodSelectListService(fdvo);
			
			if (foodList.size()>0) {
				model.addAttribute("foodList", foodList);
			}
			//============= this Food_thisName Method End
			
			//============= this Movie_thisName Method Start
			
			//유저 영화 취향...
			String genre="다큐멘터리";
			
			mvlvo.setMvgenre(genre);
			logger.info("무비 시작"+genre);
			List<MovieListVO> movieListData=movieService.MovieSelectListService(mvlvo);
			logger.info("영화 제목>>>"+movieListData.get(0).getMvname());
			List<MovieVO> movieList=new ArrayList<>();
			if(movieListData.size()>0) {
				int movieNumber[]=new RandomNumbering().RandomNumber(10, movieListData);
				for(int i=0;i<movieNumber.length;i++) {
					logger.info("넘버>>>"+movieNumber[i]+">>>"+i+"번쨰");
					logger.info(">>>"+movieListData.get(movieNumber[i]).getMvname()+genre);
					movieList.add(NaverMovieApi.returnMovieData(movieListData.get(movieNumber[i]).getMvname(), genre));	
				}
			}
			model.addAttribute("movieList",movieList);
			
			//============= this Movie_thisName Method End
			return "emotion/emotionpage";
		}
		


}
