package game.main.Player;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

import GameEngine.entities.Entity;
import GameEngine.models.RawModel;
import GameEngine.models.TexturedModel;
import GameEngine.renderEngine.DisplayManager;
import GameEngine.renderEngine.Loader;
import GameEngine.renderEngine.OBJLoader;
import GameEngine.textures.ModelTexture;
import game.main.board.Board;
import game.main.board.Tile;

public class Player {
	private String name;
	private int playerId;
	private String modelName;
	private String textureName;
	private Entity playerPiece;
	private Vector3f movementPerSecond = new Vector3f();
	private float delta;
	private float elapsedMovingTime;
	private float totalMovementTime;
	private boolean moveTo = false;
	private int currentTile = 0;
	private Board board;
	private float offset;
	private int direction = 0;
<<<<<<< HEAD
	private ArrayList<Vector3f> path = new ArrayList<Vector3f>();
=======
	private int score = 0;
	private ArrayList<Vector3f> path = new ArrayList<>();
	public boolean canMove = true;
>>>>>>> 49ae2b60c20684b1dd87e2a469c918fda88692d9
	//private float startRotation;
	Vector3f poss;

	public Player(String name, int playerId, String modelName, String texureName, Board board) {
		this.board = board;
		this.name = name;
		this.playerId = playerId;
		this.modelName = modelName;
		this.textureName = texureName;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void increaseScore(int amount) {
		score += amount;
	}

	public void init(Loader loader, Vector3f startPos, float offset) {
		this.offset = offset;
		RawModel r = OBJLoader.loadObjModel(modelName, loader);
		TexturedModel m = new TexturedModel(r, new ModelTexture(loader.loadTexture(textureName)));
		playerPiece = new Entity(m, startPos, 0, 0, 0, 3f);
		playerPiece.increasePosition(0, 0, offset);
	}
	
	public int getCurrentTileIndex() {
		return currentTile;
	}
	
	public boolean isMoving() {
		return moveTo;
	}


	public void render() {
		delta = DisplayManager.getFrameTimeSeconds();
		if (moveTo) {
			elapsedMovingTime += delta;
			if (elapsedMovingTime >= totalMovementTime) {
				if (path.size() > 1) {
					path.remove(0);
					elapsedMovingTime = 0;
					movePieceTo(path.get(0), totalMovementTime);
					//System.out.println(currentTile);
				}
				else {
					path.remove(0);
					moveTo = false;
					elapsedMovingTime = 0;
					totalMovementTime = 0;
					movementPerSecond.x = 0;
					movementPerSecond.y = 0;
					movementPerSecond.z = 0;
				}

			}
			else {
				this.getPlayerPiece().increasePosition(movementPerSecond.x * delta, movementPerSecond.y * delta, movementPerSecond.z * delta);
//				if (poss != null) {
//					this.getPlayerPiece().setPosition(poss);
//				}
				
			}
		}

	}

	public void movePieceTo(Vector3f pos, float seconds) {
		poss = pos;
		currentTile++;
		if (currentTile >= 16) {
			currentTile = 0;
		}
		totalMovementTime = seconds;
<<<<<<< HEAD
		//System.out.println(pos);
		//System.out.println("Player: " + playerPiece.getPosition());
//		movementPerSecond.x = (pos.x - this.getPlayerPiece().getPosition().x) / seconds;
//		movementPerSecond.y = (pos.y - this.getPlayerPiece().getPosition().y) / seconds;
//		movementPerSecond.z = (pos.z - this.getPlayerPiece().getPosition().z) / seconds;
=======
>>>>>>> 49ae2b60c20684b1dd87e2a469c918fda88692d9
		if (currentTile >= 0 && currentTile <= 3) {
			//System.out.println("0 - 3");
			direction = 0;
			movementPerSecond.x = (pos.x - this.getPlayerPiece().getPosition().x) / seconds;
			movementPerSecond.y = (pos.y - this.getPlayerPiece().getPosition().y) / seconds;
			movementPerSecond.z = (pos.z - this.getPlayerPiece().getPosition().z + offset) / seconds;
		}
		else if (currentTile >= 4 && currentTile <= 7) {
			//System.out.println("4 - 7");
			direction = 1;
			movementPerSecond.x = (pos.x - this.getPlayerPiece().getPosition().x - offset) / seconds;
			movementPerSecond.y = (pos.y - this.getPlayerPiece().getPosition().y) / seconds;
			movementPerSecond.z = (pos.z - this.getPlayerPiece().getPosition().z) / seconds;
		}
		else if (currentTile >= 8 && currentTile <= 11) {
			//System.out.println("8 - 11");
			direction = 2;
			movementPerSecond.x = (pos.x - this.getPlayerPiece().getPosition().x) / seconds;
			movementPerSecond.y = (pos.y - this.getPlayerPiece().getPosition().y) / seconds;
			movementPerSecond.z = (pos.z - this.getPlayerPiece().getPosition().z - offset) / seconds;
		}
		else if (currentTile >= 12 && currentTile <= 15) {
			//System.out.println("12 - 15");
			direction = 3;
			movementPerSecond.x = (pos.x - this.getPlayerPiece().getPosition().x + offset) / seconds;
			movementPerSecond.y = (pos.y - this.getPlayerPiece().getPosition().y) / seconds;
			movementPerSecond.z = (pos.z - this.getPlayerPiece().getPosition().z) / seconds;
		}
		moveTo = true;
	}

	//	public float timeTo(float speed, Vector3f startPos, Vector3f endPos) {
	//		float travelX = endPos.x - startPos.x;
	//		float travelY = endPos.y - startPos.y;
	//		float distance = (float) (Math.sqrt(travelX) * travelX - travelY * travelY);
	//		System.out.println(distance / speed);
	//		return Math.max(distance / speed, 1);
	//		
	//		//return 0.5f;
	//	}
	//	
	public void movePieceToTile(int tileIndex, ArrayList<Tile> tiles, float seconds) {
		//System.out.println(tileIndex);
		if (currentTile >= tileIndex) {
			for (int i = currentTile; i <= tiles.size() - 1; i++) {
				path.add(tiles.get(i).position);
			}
			for (int i = 0; i <= tileIndex; i++) {
				path.add(tiles.get(i).position);
			}
		}
		else {
			for (int i = currentTile; i <= tileIndex; i++) {
				path.add(tiles.get(i).position);
			}
		}
		totalMovementTime = seconds;
		moveTo = true;
	}

	public Entity getPlayerPiece() {
		return playerPiece;
	}

	public String getModelName() {
		return modelName;
	}

	public int getPlayerId() {
		return playerId;
	}

	public String getName() {
		return name;
	}

}
