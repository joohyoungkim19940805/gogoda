package g.g.d.com.mem.common;

public class Encode {

	public static String utf8(String str) {
		String strEncode="";
		try {

			strEncode = new String(str.getBytes("8859_1"), "UTF-8");
			System.out.println();

		}catch(Exception e) {}
		
		return strEncode;
	}
	
	
	
	public static void main(String args[]) {
		
		System.out.println(allEncoding("ìì¸ ê°ë¨êµ¬ ë°¤ê³ ê°ë¡ 76-2 (ììë)"));
	}
	
	public static String allEncoding(String str) {
		String regex=".[ㄱ-ㅎㅏ-ㅣ가-힣]+.";
		String strEncode="";
		String x="";
		String y="";
		
		try {
			strEncode =new String( str.getBytes( "8859_1"), "UTF-8"); System.out.println("8859_1= UTF-8>>>"+strEncode); 
			if(strEncode.matches(regex)) {x="8859_1"; y="UTF-8";}
			strEncode =new String( str.getBytes( "8859_1"), "UTF-16"); System.out.println("8859_1= UTF-16>>>"+strEncode); 
			if(strEncode.matches(regex)) {x="8859_1"; y="UTF-16";}
			strEncode =new String( str.getBytes( "8859_1"), "UTF-32"); System.out.println("8859_1= UTF-32>>>"+strEncode); 
			if(strEncode.matches(regex)) {x="8859_1"; y="UTF-32";}
			strEncode =new String( str.getBytes( "8859_1"), "KSC5601"); System.out.println("8859_1= KSC5601>>>"+strEncode);
			if(strEncode.matches(regex)) {x="8859_1"; y="KSC5601";}
			strEncode =new String( str.getBytes( "8859_1"), "MS949"); System.out.println("8859_1= MS949>>>"+strEncode); 
			strEncode =new String( str.getBytes( "8859_1"), "EUC-KR"); System.out.println("8859_1= EUC-KR>>>"+strEncode); 
			strEncode =new String( str.getBytes( "8859_1"), "CP949"); System.out.println("8859_1= CP949>>>"+strEncode); 
			strEncode =new String( str.getBytes( "UTF-8"), "EUC-KR"); System.out.println("UTF-8= EUC-KR>>>"+strEncode); 
			
			strEncode =new String( str.getBytes( "KSC5601"), "8859_1"); System.out.println("KSC5601= 8859_1>>>"+strEncode); 
			strEncode =new String( str.getBytes( "KSC5601"), "EUC-KR"); System.out.println("KSC5601= EUC-KR>>>"+strEncode); 
			strEncode =new String( str.getBytes( "KSC5601"), "UTF-8"); System.out.println("KSC5601= UTF-8>>>"+strEncode); 
			strEncode =new String( str.getBytes( "EUC-KR"), "8859_1"); System.out.println("EUC-KR= 8859_1>>>"+strEncode); 
			strEncode =new String( str.getBytes( "EUC-KR"), "KSC5601"); System.out.println("EUC-KR= KSC5601>>>"+strEncode); 
			strEncode =new String( str.getBytes( "EUC-KR"), "UTF-8"); System.out.println("EUC-KR= UTF-8>>>"+strEncode); 
			strEncode =new String( str.getBytes( "UTF-8"), "KSC5601"); System.out.println("UTF-8= KSC5601>>>"+strEncode); 
			strEncode =new String( str.getBytes( "UTF-8"), "8859_1"); System.out.println("UTF-8= 8859_1>>>"+strEncode); 
			strEncode =new String( str.getBytes( "UTF-8"), "EUC-KR"); System.out.println("UTF-8= EUC-KR>>>"+strEncode); 
			strEncode =new String(str.getBytes( "8859_1"), "EUC-KR"); System.out.println("8859_1= EUC-KR>>>"+strEncode);
		}catch(Exception e) {}
		
		return null;
	}
	
	
	
	public static String ztest(String str, String x, String y) {
		String strEncode="";
		try {

			strEncode = new String(str.getBytes(x), y);
			System.out.println();

		}catch(Exception e) {}
		
		return strEncode;
	}
	
}