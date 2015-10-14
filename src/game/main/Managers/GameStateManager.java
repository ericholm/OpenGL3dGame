package game.main.Managers;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import GameEngine.entities.Camera;
import GameEngine.guis.GuiRenderer;
import GameEngine.guis.components.ButtonAction;
import GameEngine.guis.components.Dialog;
import game.main.Player.Player;
import game.main.Screens.GameScreen;
import game.main.board.Board;
import game.main.board.Dice;
import game.main.board.TileType;

public class GameStateManager implements ButtonAction{

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
	private Dialog dialog;
	private GameScreen game;

	public GameStateManager(GameScreen game, GameStates state, Player currentPlayersTurn, Camera camera, ArrayList<Player> players, Board board) {
		this.game = game;
		currentState = state;
		this.currentPlayersTurn = currentPlayersTurn;
		this.camera = camera;
		this.players = players;
		camera.setPlayerToTrack(currentPlayersTurn);
		this.board = board;
		dice = new Dice(1, 3);
	}
	
	public GameScreen getGame() {
		return game;
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
			dialog = new Dialog(this, new Vector2f(640, 360), new Vector2f(0.3f, 0.3f), 0, 0, "Church Tax", "You lose 200 gold", " OK", 0.03f, 0.05f, this);
			if (currentPlayersTurn.getScore() - board.tiles.get(currentTile + tilesMove).getModifier() <= 0) {
				currentPlayersTurn.setScore(0);
			}
			else {
				currentPlayersTurn.decreaseScore(board.tiles.get(currentTile + tilesMove).getModifier());
			}
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
			currentPlayersTurn.increaseScore(board.tiles.get(currentTile + tilesMove).getModifier());
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

	public void render(GuiRenderer g) {
		if (currentState == GameStates.PlayersTurn) {
			
			if (dialog != null) {
				g.render(dialog);
				dialog.render(g);
			}
			
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

	@Override
	public void action(String action) {
		dialog = null;
	}

}
