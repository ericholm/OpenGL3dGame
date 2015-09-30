package game.main.board;

import java.util.Random;

public class Dice {
	
	private Random random;
	private int maxRoll;
	private int minRoll;
	
	public Dice(int minRoll, int maxRoll) {
		this.maxRoll = maxRoll;
		this.minRoll = minRoll;
		random = new Random();
		//random = new 
	}
	
	public int roll() {
		return random.nextInt(maxRoll - minRoll + 1) + minRoll;
	}

}
