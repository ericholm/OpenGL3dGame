package game.main.board;

import java.util.Random;

public class Dice {
	
	private Random random;
	private int maxRoll;
	private int minRoll;
	
	public Dice(int minRoll, int maxRoll) {
		this.maxRoll = maxRoll;
		this.minRoll = minRoll;
	}
	
	public int roll() {
		return random.nextInt(maxRoll);
	}

}
