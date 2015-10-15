package game.main.Screens;

import game.main.Managers.GameStateManager;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import GameEngine.font.FontLoader;
import GameEngine.guis.GuiRenderer;
import GameEngine.guis.GuiTexture;

public class PlayerLabel {
	
	private int score = 0;
	private GameStateManager manager;
	private ArrayList<GuiTexture> text;
	private GuiTexture background;
	private int currentPlayerId;
	
	public PlayerLabel(GameStateManager manager, GuiTexture background) {
		this.background = background;
		this.manager = manager;
		this.currentPlayerId = manager.getCurrentPlayer().getPlayerId();
		this.text = FontLoader.drawStringG("Player " + (currentPlayerId + 1) + " turn", 540, 680, 0.03f);
	}
	
	public void render(GuiRenderer g) {
		//System.out.println("Score Label");
		if (manager.getCurrentPlayer().getPlayerId() != currentPlayerId) {
			currentPlayerId = manager.getCurrentPlayer().getPlayerId();
			score = manager.getCurrentPlayer().getScore();
			text = FontLoader.drawStringG("Player " + (currentPlayerId + 1) + " turn", 540, 680, 0.03f);
		}
		g.render(background);
		g.render(text);
	}
	
	

}
