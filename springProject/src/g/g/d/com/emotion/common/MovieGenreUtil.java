package g.g.d.com.emotion.common;
import g.g.d.com.movie.vo.MovieUserGenreVO;

public class MovieGenreUtil {

	public static MovieUserGenreVO MovieGenre(String[] genre) {
		MovieUserGenreVO mugvo=new MovieUserGenreVO();
		
		for (int i=0;i<genre.length;i++) {
			System.out.println(genre[i]);
			if(genre[i].equals("action")){mugvo.setMvaction("액션");}
			if(genre[i].equals("adult")){mugvo.setMvadult("성인물(에로)");}
			if(genre[i].equals("adventure")){mugvo.setMvadventure("어드벤처");}
			if(genre[i].equals("animation")){mugvo.setMvanimation("애니메이션");}
			if(genre[i].equals("comedy")){mugvo.setMvcomedy("코미디");}
			if(genre[i].equals("crime")){mugvo.setMvcrime("범죄");}
			if(genre[i].equals("documentary")){mugvo.setMvdocumentary("다큐멘터리");}
			if(genre[i].equals("drama")){mugvo.setMvdrama("드라마");}
			if(genre[i].equals("family")){mugvo.setMvfamily("가족");}
			if(genre[i].equals("fantasy")){mugvo.setMvfantasy("판타지");}
			if(genre[i].equals("historical")){mugvo.setMvhistorical("사극");}
			if(genre[i].equals("horror")){mugvo.setMvhorror("공포(호러)");}
			if(genre[i].equals("musical")){mugvo.setMvmusical("뮤지컬");}
			if(genre[i].equals("mystery")){mugvo.setMvmystery("미스터리");}
			if(genre[i].equals("opera")){mugvo.setMvopera("공연");}
			if(genre[i].equals("romance")){mugvo.setMvromance("멜로/로맨스");}
			if(genre[i].equals("SF")){mugvo.setMvsf("SF");}
			if(genre[i].equals("thriller")){mugvo.setMvthriller("스릴러");}
			if(genre[i].equals("war")){mugvo.setMvwar("전쟁");}
			if(genre[i].equals("western")){mugvo.setMvwestern("서부극(웨스턴)");}
		}
		System.out.println(mugvo.getMvadult());
		if(mugvo.getMvaction()==null){mugvo.setMvaction("0");}
		if(mugvo.getMvadult()==null){mugvo.setMvadult("0");}
		if(mugvo.getMvadventure()==null){mugvo.setMvadventure("0");}
		if(mugvo.getMvanimation()==null){mugvo.setMvanimation("0");}
		if(mugvo.getMvcomedy()==null){mugvo.setMvcomedy("0");}
		if(mugvo.getMvcrime()==null){mugvo.setMvcrime("0");}
		if(mugvo.getMvdocumentary()==null){mugvo.setMvdocumentary("0");}
		if(mugvo.getMvdrama()==null){mugvo.setMvdrama("0");}
		if(mugvo.getMvfamily()==null){mugvo.setMvfamily("0");}
		if(mugvo.getMvfantasy()==null){mugvo.setMvfantasy("0");}
		if(mugvo.getMvhistorical()==null){mugvo.setMvhistorical("0");}
		if(mugvo.getMvhorror()==null){mugvo.setMvhorror("0");}
		if(mugvo.getMvmusical()==null){mugvo.setMvmusical("0");}
		if(mugvo.getMvmystery()==null){mugvo.setMvmystery("0");}
		if(mugvo.getMvopera()==null){mugvo.setMvopera("0");}
		if(mugvo.getMvromance()==null){mugvo.setMvromance("0");}
		if(mugvo.getMvsf()==null){mugvo.setMvsf("0");}
		if(mugvo.getMvthriller()==null){mugvo.setMvthriller("0");}
		if(mugvo.getMvwar()==null){mugvo.setMvwar("0");}
		if(mugvo.getMvwestern()==null){mugvo.setMvwestern("0");}
		System.out.println(mugvo.getMvadult());
		return mugvo;
	}
}
