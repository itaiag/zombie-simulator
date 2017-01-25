package agmon.zombie.model;

import java.util.Random;

public class Dice {
	
	private final Random rand;
	
	public Dice(){
		rand = new Random();
	}
	
	public int throwDice(float... percentage){
		float result = rand.nextFloat();
		int i = 0;
		for (i = 0 ; i < percentage.length ; i++){
			if (result <= percentage[i]){
				return i;
			}
		}
		return i++;
	}
	
	
}
