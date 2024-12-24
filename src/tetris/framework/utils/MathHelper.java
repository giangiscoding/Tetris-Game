package tetris.framework.utils;

import java.util.Random;

public class MathHelper {

	public static int randomInt(int upperBound) {
		Random rand = new Random();
		return rand.nextInt(upperBound);
	}
	
	public static int[] sortArry(int[] array) {
		int appo;
		
		for(int i=0; i<array.length-1; i++) {
			for(int j=i+1; j<array.length; j++) {
				if(array[j]>array[i]) {
					appo = array[i];
					array[i] = array[j];
					array[j] = appo;
				}
			}
		}
		return array;
	}
}
