package g.g.d.com.emotion.common;

import java.util.List;

public class RandomNumbering {
	public int[] RandomNumber(int size, List list){
		int number[]=new int[size];
		for(int i=0;i<number.length;i++){
			number[i]=(int)(Math.random()*list.size());
			for(int j=0;j<i;j++){
				if(number[j]==number[i]){
					i--;
					break;
				}
			}
		}
		return number;
	}
}
