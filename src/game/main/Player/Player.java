package game.main.Player;

import org.lwjgl.util.vector.Vector3f;

import GameEngine.entities.Entity;
import GameEngine.models.RawModel;
import GameEngine.models.TexturedModel;
import GameEngine.renderEngine.DisplayManager;
import GameEngine.renderEngine.Loader;
import GameEngine.renderEngine.OBJLoader;
import GameEngine.textures.ModelTexture;

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
	//private float startRotation;
	
	public Player(String name, int playerId, String modelName, String texureName) {
		this.name = name;
		this.playerId = playerId;
		this.modelName = modelName;
		this.textureName = texureName;
	}
	
	public void init(Loader loader, Vector3f startPos) {
		RawModel r = OBJLoader.loadObjModel(modelName, loader);
		TexturedModel m = new TexturedModel(r, new ModelTexture(loader.loadTexture(textureName)));
		if (modelName == "King") {
			//startPos.y = startPos.y - 3.8f;
			//startPos.x = startPos.x + 14;
			//startRotation = 0;
		}
		if (modelName == "Knight") {
			//startPos.y = startPos.y - 6.5f;
			//startPos.z = startPos.z + 3;
			//startRotation = -90;
		}
		playerPiece = new Entity(m, startPos, 0, 0, 0, 3f);
	}
	
	public void render() {
		delta = DisplayManager.getFrameTimeSeconds();
		if (moveTo) {
			elapsedMovingTime += delta;
			if (elapsedMovingTime >= totalMovementTime) {
				moveTo = false;
				elapsedMovingTime = 0;
				totalMovementTime = 0;
				movementPerSecond.x = 0;
				movementPerSecond.y = 0;
				movementPerSecond.z = 0;
				
			}
			else {
				this.getPlayerPiece().increasePosition(movementPerSecond.x * delta, movementPerSecond.y * delta, movementPerSecond.z * delta);
			}
		}
		
	}
	
	public void movePieceTo(Vector3f pos, float seconds) {
		totalMovementTime = seconds;
		movementPerSecond.x = (pos.x - this.getPlayerPiece().getPosition().x) / seconds;
		movementPerSecond.y = (pos.y - this.getPlayerPiece().getPosition().y) / seconds;
		movementPerSecond.z = (pos.z - this.getPlayerPiece().getPosition().z) / seconds;
		moveTo = true;
		System.out.println(movementPerSecond);
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
