package game.main.Managers;

import java.util.ArrayList;

import GameEngine.entities.Camera;
import game.main.Player.Player;
import game.main.board.Board;
import game.main.board.Dice;
import game.main.board.TileType;

public class GameStateManager {

	private Player currentPlayersTurn;
	private Camera camera;
	private ArrayList<Player> players = new ArrayList<>();	
	private GameStates currentState;
	private Board board;
	private Dice dice;

	public GameStateManager(GameStates state, Player currentPlayersTurn, Camera camera, ArrayList<Player> players, Board board) {
		currentState = state;
		this.currentPlayersTurn = currentPlayersTurn;
		this.camera = camera;
		this.players = players;
		camera.setPlayerToTrack(currentPlayersTurn);
		this.board = board;
		dice = new Dice(9, 10);
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

	public void movePlayer(int tiles) {
		//System.out.println(tiles);
		int tileIndex = currentPlayersTurn.getCurrentTileIndex() + tiles;
		if (tileIndex >= 16) {
			tileIndex -= 15;
		}
		processTileType(board.tiles.get(tileIndex).getTileType(), tiles, currentPlayersTurn.getCurrentTileIndex());
		System.out.println(tileIndex);
		currentPlayersTurn.movePieceToTile(tileIndex, board.tiles, 0.2f);
	}

	public void processTileType(TileType tile, int tilesMove, int currentTile) {
		if (tile == TileType.Barracks) {
			System.out.println("You landed on barracks");
		}
		else if(tile == TileType.ChurchTax) {
			System.out.println("You landed on tax");
		}
		else if(tile == TileType.Question) {
			System.out.println("You landed on question");
		}
		else if(tile == TileType.SiegeFactory) {
			System.out.println("You landed on siege factory");
		}
		else if(tile == TileType.Collect || (currentTile <= 15 && currentTile + tilesMove >= 16)) {
			System.out.println("You passed collect");
		}
		else {
			System.out.println("You landed on nothing");
		}
	}
	
	public void rollDice() {
		movePlayer(dice.roll());
	}

	public void render() {
		if (currentState == GameStates.PlayersTurn) {
			//camera.render();
		}
		else if (currentState == GameStates.Attack) {

		}
	}

}
