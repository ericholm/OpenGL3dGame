package game.main.Managers;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import GameEngine.guis.GuiTexture;

public class ChanceCard extends GuiTexture{
	
	public ChanceType type;
	public int modifier;
	public int modifier2;

	public ChanceCard(int textures, ChanceType type) {
		super(textures, new Vector2f(640, 360), new Vector2f(0.5f, 0.4f), 0, 0);
		this.type = type;
	}
	
	public ChanceCard(int textures, ChanceType type, int modifier) {
		super(textures, new Vector2f(640, 360), new Vector2f(0.5f, 0.4f), 0, 0);
		this.type = type;
		this.modifier = modifier;
	}
	public ChanceCard(int textures, ChanceType type, int modifier, int modifier2) {
		super(textures, new Vector2f(640, 360), new Vector2f(0.5f, 0.4f), 0, 0);
		this.type = type;
		this.modifier = modifier;
	}

}
