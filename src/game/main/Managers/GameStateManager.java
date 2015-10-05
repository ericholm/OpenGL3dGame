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
	private boolean processTile = false;
	private float playerSpeed = 0.2f;

	public GameStateManager(GameStates state, Player currentPlayersTurn, Camera camera, ArrayList<Player> players, Board board) {
		currentState = state;
		this.currentPlayersTurn = currentPlayersTurn;
		this.camera = camera;
		this.players = players;
		camera.setPlayerToTrack(currentPlayersTurn);
		this.board = board;
		dice = new Dice(1, 3);
	}

	public void changeCurrentPlayer() {
		processTile = false;
		if (currentPlayersTurn.getPlayerId() == 0) {
			currentPlayersTurn = players.get(1);
			camera.setPlayerToTrack(currentPlayersTurn);
		}
		else {
			currentPlayersTurn = players.get(0);
			camera.setPlayerToTrack(currentPlayersTurn);
		}
		currentPlayersTurn.canMove = true;
	}

	public void movePlayer(int tiles) {
		//System.out.println(tiles);
		int tileIndex = currentPlayersTurn.getCurrentTileIndex() + tiles;
		if (tileIndex >= 16) {
			tileIndex -= 16;
		}
		//System.out.println(tileIndex);
		currentPlayersTurn.movePieceToTile(tileIndex, board.tiles, playerSpeed);
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
			QuestionHandler.getRandomQuestion();
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
		if (currentPlayersTurn.canMove) {
			movePlayer(dice.roll());
			//currentPlayersTurn.canMove = false;
		}
	}

	public void render() {
		if (currentState == GameStates.PlayersTurn) {
			camera.render(currentPlayersTurn.getDirection());
			if (!currentPlayersTurn.isMoving() && !processTile) {
				processTile = true;
			}
		}
		else if (currentState == GameStates.Attack) {

		}
	}

}
