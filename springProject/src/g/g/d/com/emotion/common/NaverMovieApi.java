package g.g.d.com.emotion.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import g.g.d.com.movie.vo.MovieListVO;
import g.g.d.com.movie.vo.MovieVO;

public class NaverMovieApi {

	public static MovieVO returnMovieData(String movieName, String genre, String movieNumber, int movieCnt, MovieListVO mlvo) {
		
		//movieList.get(0).getMvname(),
		String clientId = "Ms8QcIFQ4qWlcfeSxIp8";
        String clientSecret = "2HDjh2tfZ4";
        int display = 1;
        MovieVO mvvo= null;
        try {
            String movieText = URLEncoder.encode(movieName, "utf-8");
            String apiURL = "https://openapi.naver.com/v1/search/movie?query=" + movieText + "&display=" + display + "&";
            URL movieUrl = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) movieUrl.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
 
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
 
            br.close();
            con.disconnect();
            //System.out.println(sb); 
            String data = sb.toString();
            String[] array;
            array = data.split("\"");
            String title = null;//new String;
            String link = null;//new String;
            String image = null;//new String;
            String pubDate = null;//new String;
            String director = null;//new String;
            String actor = null;//new String;
            String userRating = null;//new String;

            int k = 0;
            for (int i = 0; i < array.length; i++) {
            	if (array[i].equals("title"))
                    title = array[i + 2];
                if (array[i].equals("link"))
                    link = array[i + 2];
                if (array[i].equals("image"))
                	image = array[i + 2];
                if (array[i].equals("pubDate"))
                	pubDate = array[i + 2];
                if (array[i].equals("director"))
                	director = array[i + 2];
                if (array[i].equals("actor"))
                	actor = array[i + 2];
                if (array[i].equals("userRating")) {
                	userRating = array[i + 2];
                    k++;
                }
            }
            //System.out.println(sb);
            //System.out.println("----------------------------");
            //System.out.println("영화 제목 : " + title.replaceAll("<b>", "").replaceAll("</b>",""));
            //System.out.println("영화 링크 : " + link);
            //System.out.println("영화 이미지 : " + image);
            //System.out.println("영화 제작년도 : " + pubDate);
            //System.out.println("영화 감독 : " + director.replaceAll("<b>", "").replaceAll("</b>",""));
            //System.out.println("영화 출연진 : " + actor.replaceAll("<b>", "").replaceAll("</b>",""));
            //System.out.println("영화 평점 : " + userRating);
            //System.out.println("영화 장르 : "+genre);
            
            mvvo=new MovieVO();
            mvvo.setMvname(title.replaceAll("<b>", "").replaceAll("</b>",""));
            mvvo.setMvlink(link);
            mvvo.setMvimage(image);
            mvvo.setMvpubDate(pubDate);
            mvvo.setMvdirector(director.replaceAll("<b>", "").replaceAll("</b>",""));
            mvvo.setMvactor(actor.replaceAll("<b>", "").replaceAll("</b>",""));
            mvvo.setMvuserRating(userRating);
            mvvo.setMvgenre(genre);
            mvvo.setMvnum(movieNumber);
            mvvo.setMoviecnt(movieCnt);
            

    		
        } catch (Exception e) {
            System.out.println(e);
            mvvo=new MovieVO();
            if(mlvo!=null) {
            	mvvo.setMvname(movieName);
	            mvvo.setMvlink("");
	            mvvo.setMvimage("-");
	            mvvo.setMvpubDate(mlvo.getMvpubdate());
	            mvvo.setMvdirector("데이터가 없습니다..");
	            mvvo.setMvactor("데이터가 없습니다.");
	            mvvo.setMvuserRating("데이터가 없습니다.");
	            mvvo.setMvgenre(genre);
	            mvvo.setMvnum(movieNumber);
	            mvvo.setMoviecnt(movieCnt);
            }else {
	            mvvo.setMvname(movieName);
	            mvvo.setMvlink("");
	            mvvo.setMvimage("-");
	            mvvo.setMvpubDate("데이터가 없습니다.");
	            mvvo.setMvdirector("데이터가 없습니다..");
	            mvvo.setMvactor("데이터가 없습니다.");
	            mvvo.setMvuserRating("데이터가 없습니다.");
	            mvvo.setMvgenre(genre);
	            mvvo.setMvnum(movieNumber);
	            mvvo.setMoviecnt(movieCnt);
            }
        }
		
        return mvvo;
	}
}
