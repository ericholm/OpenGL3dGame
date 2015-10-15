package game.main.Managers;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import GameEngine.guis.GuiTexture;

public class ChanceCard extends GuiTexture{

	public ChanceCard(int textures) {
		super(textures, new Vector2f(640, 360), new Vector2f(1f,1f), 0, 0);
	}

}
