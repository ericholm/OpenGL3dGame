package game.main.board;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

import GameEngine.entities.Entity;
import GameEngine.models.TexturedModel;

public class Board extends Entity{
	
	public Vector3f startTile = new Vector3f(455, 15f, -330);
	public ArrayList<Tile> tiles = new ArrayList<Tile>();

	public Board(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
		tiles.add(new Tile(new Vector3f(895, 0, 890), TileType.Collect, 200));
		tiles.add(new Tile(new Vector3f(676, 0, 890), TileType.ChurchTax, 200));
		tiles.add(new Tile(new Vector3f(513, 0, 890), TileType.ChurchTax, 200));
		tiles.add(new Tile(new Vector3f(350, 0, 890), TileType.ChurchTax, 200));
		tiles.add(new Tile(new Vector3f(135, 0, 890), TileType.ChurchTax, 200));
		tiles.add(new Tile(new Vector3f(135, 0, 665), TileType.ChurchTax, 200));
		tiles.add(new Tile(new Vector3f(135, 0, 502), TileType.Question));
		tiles.add(new Tile(new Vector3f(135, 0, 339), TileType.Question));
		tiles.add(new Tile(new Vector3f(135, 0, 135), TileType.Question));
		tiles.add(new Tile(new Vector3f(350, 0, 135), TileType.Question));
		tiles.add(new Tile(new Vector3f(513, 0, 135), TileType.ChurchTax, 50));
		tiles.add(new Tile(new Vector3f(676, 0, 135), TileType.Question));
		tiles.add(new Tile(new Vector3f(895, 0, 135), TileType.Question));
		tiles.add(new Tile(new Vector3f(895, 0, 350), TileType.Question));
		tiles.add(new Tile(new Vector3f(895, 0, 513), TileType.Question));
		tiles.add(new Tile(new Vector3f(895, 0, 676), TileType.Question, 100));
		
		
//		tiles.add(new Tile(new Vector3f(890, 0, 895), TileType.Nothing));
//		tiles.add(new Tile(new Vector3f(890, 0, 676), TileType.Nothing));
//		tiles.add(new Tile(new Vector3f(890, 0, 513), TileType.Nothing));
//		tiles.add(new Tile(new Vector3f(890, 0, 350), TileType.Nothing));
//		tiles.add(new Tile(new Vector3f(890, 0, 135), TileType.Nothing));
//		tiles.add(new Tile(new Vector3f(665, 665, 135), TileType.Nothing));
//		tiles.add(new Tile(new Vector3f(502, 502, 135), TileType.Nothing));
//		tiles.add(new Tile(new Vector3f(339, 339, 135), TileType.Nothing));
//		tiles.add(new Tile(new Vector3f(135, 0, 135), TileType.Nothing));
//		tiles.add(new Tile(new Vector3f(350, 665, 350), TileType.Nothing));
//		tiles.add(new Tile(new Vector3f(513, 665, 513), TileType.Nothing));
//		tiles.add(new Tile(new Vector3f(676, 665, 676), TileType.Nothing));
//		tiles.add(new Tile(new Vector3f(895, 665, 895), TileType.Nothing));
//		tiles.add(new Tile(new Vector3f(339, 0, 895), TileType.Nothing));
//		tiles.add(new Tile(new Vector3f(502, 0, 895), TileType.Nothing));
//		tiles.add(new Tile(new Vector3f(605, 0, 895), TileType.Nothing));
		for (Tile tile : tiles) {
			tile.position.x = tile.position.x + 285;
			tile.position.z = tile.position.z - 499.9f;
			tile.position.y = 15f;
		}
	}
	

}
