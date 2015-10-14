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
	private ArrayList<Player> players = new ArrayList<Player>();	
	private GameStates currentState;
	private Board board;
	private Dice dice;
	private boolean processTile = false;
	private float playerSpeed = 0.8f;
	private int tileMovement;
	private int finalTileIndex;

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
		processTile = true;
		tileMovement = tiles;
		finalTileIndex = tileIndex;
	}

	public void processTileType(TileType tile, int tilesMove, int currentTile) {
		System.out.println(tile);
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
	
	public Player getCurrentPlayer() {
		return currentPlayersTurn;
	}
	
	public void rollDice() {
		if (currentPlayersTurn.canMove) {
			movePlayer(dice.roll());
			//currentPlayersTurn.canMove = false;
		}
	}
	
	public void handleAnswer(QuestionReturn answer) {
		if (answer.correct) {
			currentPlayersTurn.increaseScore(answer.multiplier);
		}
	}

	public void render() {
		if (currentState == GameStates.PlayersTurn) {
			if (!QuestionHandler.isQuestionOpen()) {
				camera.render(currentPlayersTurn.getDirection());
				if(camera.isAnimatingTurn()) {
					currentPlayersTurn.pause = true;
				}
				else {
					currentPlayersTurn.pause = false;
				}
				if (!currentPlayersTurn.isMoving() && processTile) {
					processTileType(board.tiles.get(finalTileIndex).getTileType(), tileMovement, currentPlayersTurn.getCurrentTileIndex());
					processTile = false;
				}
			}
		}
		else if (currentState == GameStates.Attack) {

		}
	}

}
