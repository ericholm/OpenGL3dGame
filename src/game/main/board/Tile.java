package game.main.board;

import org.lwjgl.util.vector.Vector3f;

public class Tile {
	
	public Vector3f position;
	private TileType tileType;
	private float scalingFactor = 5.238f;
	private int modifier;
	
	public Tile (Vector3f position, TileType tileType) {
		this.position = new Vector3f(position.x / scalingFactor, position.y, position.z / scalingFactor);
		this.tileType = tileType;
	}
	
	public Tile (Vector3f position, TileType tileType, int modifier) {
		this.position = new Vector3f(position.x / scalingFactor, position.y, position.z / scalingFactor);
		this.tileType = tileType;
		this.modifier = modifier;
	}
	
	public TileType getTileType() {
		return tileType;
	}

}
