package game.main.Player;

import org.lwjgl.util.vector.Vector3f;

import GameEngine.entities.Entity;
import GameEngine.models.RawModel;
import GameEngine.models.TexturedModel;
import GameEngine.renderEngine.Loader;
import GameEngine.renderEngine.OBJLoader;
import GameEngine.textures.ModelTexture;

public class Player {
	private String name;
	private int playerId;
	private String modelName;
	private String textureName;
	private Entity playerPiece;
	
	public Player(String name, int playerId, String modelName, String texureName) {
		this.name = name;
		this.playerId = playerId;
		this.modelName = modelName;
		this.textureName = texureName;
	}
	
	public void init(Loader loader, Vector3f startPos) {
		RawModel r = OBJLoader.loadObjModel(name, loader);
		TexturedModel m = new TexturedModel(r, new ModelTexture(loader.loadTexture(textureName)));
		playerPiece = new Entity(m, startPos, 0, 0, 0, 1);
	}
	
}
