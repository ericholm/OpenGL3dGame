package game.main.Screens;

import game.main.Managers.GameStateManager;

import java.util.ArrayList;

import GameEngine.font.FontLoader;
import GameEngine.guis.GuiRenderer;
import GameEngine.guis.GuiTexture;

public class ScoreLabel {
	
	private int score = 0;
	private GameStateManager manager;
	private ArrayList<GuiTexture> text;
	private GuiTexture background;
	
	public ScoreLabel(GameStateManager manager, GuiTexture background) {
		this.background = background;
		this.manager = manager;
		this.text = FontLoader.drawStringG("Score " + score, 55, 680, 0.03f);
	}
	
	public void render(GuiRenderer g) {
		//System.out.println("Score Label");
		if (manager.getCurrentPlayer().getScore() != score) {
			score = manager.getCurrentPlayer().getScore();
			text = FontLoader.drawStringG("Score " + score, 55, 680, 0.03f);
		}
		g.render(background);
		g.render(text);
	}
	
	

}
