package game.main;

import GameEngine.main.Game;
import game.main.Screens.GameScreen;
import game.main.Screens.StartScreen;

public class Main extends Game{
	
	public Main() {
		super();
		//this.setScreen(new StartScreen(this));
		this.setScreen(new GameScreen());
	}
	
	public static void main(String[] args) {
		Main main = new Main();
	}

}
