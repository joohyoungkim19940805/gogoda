import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
	
public class test {

    public static StringBuilder sb;//
 
    static String getString(String input, String data) // API에서 필요한 문자 자르기.
    {
        String[] dataSplit = data.split("{" + input + "}");
        String[] dataSplit2 = dataSplit[1].split("\"" + input + "\"");
        return dataSplit2[0];
    }
 
    public static void main(String[] args) {
 
        String clientId = "Ms8QcIFQ4qWlcfeSxIp8";
        String clientSecret = "2HDjh2tfZ4";
        int display = 1;
 
        try {
            String text = URLEncoder.encode("리얼", "utf-8");
            String apiURL = "https://openapi.naver.com/v1/search/movie?query=" + text + "&";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
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
            sb = new StringBuilder();
            String line;
 
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
 
            br.close();
            con.disconnect();
            System.out.println(sb); 
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
            String delText=actor;
            StringBuffer sbf=new StringBuffer();
            //actor=actor.replace(actor.substring(0,actor.indexOf("|"))+"|","");
            sbf.append(actor.substring(0,actor.indexOf("|"))+"_");
            
            for(int i=0;delText.indexOf("|")!=-1;i++ ) {
            	//System.out.println("1="+delText.substring(0,delText.indexOf("|")));
            	if(delText.substring(0,delText.indexOf("|")).length()==delText.length()-1) {break;}
            	//System.out.println("2="+delText.length());
            	delText=delText.replace(delText.substring(0,delText.indexOf("|"))+"|","");
            	sbf.append(delText.substring(0,delText.indexOf("|"))+"_");
            	
            }
            
            actor=sbf.toString().substring(0,sbf.toString().length()-1);
            
            delText=director;
            sbf.delete(0, sbf.length());
            sbf.append(director.substring(0,director.indexOf("|"))+"_");
            System.out.println(delText.indexOf("|"));
            for(int i=0;delText.indexOf("|")!=-1;i++ ) {
            	//System.out.println(delText.substring(0,delText.indexOf("|")).length());
            	if(delText.substring(0,delText.indexOf("|")).length()==delText.length()-1) {break;}
            	System.out.println(delText.length());
            	delText=delText.replace(delText.substring(0,delText.indexOf("|"))+"|","");
            	sbf.append(delText.substring(0,delText.indexOf("|"))+"_");
            }
            director=sbf.toString().substring(0,sbf.toString().length()-1);
            
            //System.out.println(sb);
            //System.out.println("----------------------------");
            System.out.println("영화 제목 : " + title.replaceAll("<b>", "").replaceAll("</b>",""));
            System.out.println("영화 링크 : " + link);
            System.out.println("영화 이미지 : " + image);
            System.out.println("영화 제작년도 : " + pubDate);
            System.out.println("영화 감독 : " + director.replaceAll("<b>", "").replaceAll("</b>",""));
            System.out.println("영화 출연진 : " + actor.replaceAll("<b>", "").replaceAll("</b>",""));
            System.out.println("영화 평점 : " + userRating);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
