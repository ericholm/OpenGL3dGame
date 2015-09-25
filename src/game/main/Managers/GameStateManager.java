package game.main.Managers;

import java.util.ArrayList;

import GameEngine.entities.Camera;
import game.main.Player.Player;

public class GameStateManager {
	
	private Player currentPlayersTurn;
	private Camera camera;
	private ArrayList<Player> players = new ArrayList<>();	
	private GameStates currentState;
	
	public GameStateManager(GameStates state, Player currentPlayersTurn, Camera camera, ArrayList<Player> players) {
		currentState = state;
		this.currentPlayersTurn = currentPlayersTurn;
		this.camera = camera;
		this.players = players;
		camera.setPlayerToTrack(currentPlayersTurn);
	}
	
	public void changeCurrentPlayer() {
		if (currentPlayersTurn.getPlayerId() == 0) {
			currentPlayersTurn = players.get(1);
			camera.setPlayerToTrack(currentPlayersTurn);
		}
		else {
			currentPlayersTurn = players.get(0);
			camera.setPlayerToTrack(currentPlayersTurn);
		}
	}
	
	public void render() {
		if (currentState == GameStates.PlayersTurn) {
			//camera.render();
		}
		else if (currentState == GameStates.Attack) {
			
		}
	}

}
