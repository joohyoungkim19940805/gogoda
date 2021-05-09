import java.util.Scanner;

public class TestMobigenStrcmp {

	public static int strcmp(String x, String y) {
		//System.out.println((int)"ÿ".charAt(0));
		int result=0;
		int len=0;
		/*==>abcde, abc = 1
		x+="\0";
		y+="\0";
		*/
		x+="ÿ";
		y+="ÿ";
		len = Math.min(x.length(), y.length());
		
		System.out.println(len);
		for(int i=0;i<len;i++) {
			if((int)x.charAt(i)==(int)y.charAt(i)) {
				result=0;
			}else {
				if((int)x.charAt(i)<(int)y.charAt(i)) {
					result=-1;
					return result;
				}
				if((int)x.charAt(i)>(int)y.charAt(i)) {
					//System.out.println((int)x.charAt(i));
					//System.out.println((int)y.charAt(i));
					result=1;
					return result;
				}
			}
		}
		return result;

	}
	
	public static void main(String args[]) {
		/*==>Test Method
		Scanner sc= new Scanner(System.in);
		String x=sc.next();
		String y=sc.next();
		int result=strcmp(x,y);
		System.out.println(result);
		*/
		
		String[] x={"abc","abc","cbc","abcde"};
		String[] y={"abc","cbc","abc","abc"};
		for(int i=0;i<x.length;i++) {
			System.out.println(x[i]+", "+y[i]+"="+strcmp(x[i],y[i]));
			/*==>Result Print
			==>abc, abc=0
			==>abc, cbc=-1
			==>cbc, abc=1
			==>abcde, abc=-1
			*/
		}
		
	}
}