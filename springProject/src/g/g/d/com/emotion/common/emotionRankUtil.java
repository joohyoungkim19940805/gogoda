package g.g.d.com.emotion.common;

public class emotionRankUtil {

	public static int emotionFood(String[] emotionData) {
		System.out.println("============"+emotionData[0].indexOf("혐오")+"===========");
		int emotionInfo=2;
		if(emotionData[0].indexOf("혐오")==0) {
			emotionInfo=1;
		}
		if(emotionData[0].indexOf("분노")==0) {
			emotionInfo=1;
		}
		if(emotionData[0].indexOf("슬픔")==0) {
			emotionInfo=1;
		}
		if(emotionData[0].indexOf("공포")==0) {
			emotionInfo=1;
		}
		if(emotionData[0].indexOf("놀람")==0) {
			emotionInfo=0;
		}
		if(emotionData[0].indexOf("행복")==0) {
			emotionInfo=0;
		}
		
		return emotionInfo;
	}
	
	public static int emotionFoodStr(String emotionData) {
		System.out.println("============"+emotionData.indexOf("혐오")+"===========");
		int emotionInfo=2;
		if(emotionData.indexOf("혐오")==0) {
			emotionInfo=1;
		}
		if(emotionData.indexOf("분노")==0) {
			emotionInfo=1;
		}
		if(emotionData.indexOf("슬픔")==0) {
			emotionInfo=1;
		}
		if(emotionData.indexOf("공포")==0) {
			emotionInfo=1;
		}
		if(emotionData.indexOf("놀람")==0) {
			emotionInfo=0;
		}
		if(emotionData.indexOf("행복")==0) {
			emotionInfo=0;
		}
		
		return emotionInfo;
	}
	
}
