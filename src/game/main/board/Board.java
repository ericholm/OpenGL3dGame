package game.main.board;

import org.lwjgl.util.vector.Vector3f;

import GameEngine.entities.Entity;
import GameEngine.models.TexturedModel;

public class Board extends Entity{
	
	public Vector3f startTile = new Vector3f(455, 15f, -330);

	public Board(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
	}

}
