package game.main;

import java.util.ArrayList;

import GameEngine.main.Game;
import game.main.Player.Player;
import game.main.Screens.GameScreen;
import game.main.Screens.StartScreen;

public class Main extends Game{
	
	public Main() {
		super();
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Player 1", 0, "King", "white"));
		players.add(new Player("Player 2", 1, "King", "white"));
		this.setScreen(new StartScreen(this));
		//this.setScreen(new GameScreen(players));
	}
	
	public static void main(String[] args) {
		Main main = new Main();
	}

}
