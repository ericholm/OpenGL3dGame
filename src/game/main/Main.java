package game.main;

import GameEngine.main.Game;
import game.main.Screens.GameScreen;

public class Main extends Game{
	
	public Main() {
		super();
		this.setScreen(new GameScreen());
	}
	
	public static void main(String[] args) {
		Main main = new Main();
	}

}
