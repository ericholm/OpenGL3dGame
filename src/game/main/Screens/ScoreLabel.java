package game.main.Screens;

import game.main.Managers.GameStateManager;

import java.util.ArrayList;

import GameEngine.font.FontLoader;
import GameEngine.guis.GuiRenderer;
import GameEngine.guis.GuiTexture;

public class ScoreLabel {
	
	private int score = 0;
	private GuiRenderer renderer;
	private GameStateManager manager;
	private ArrayList<GuiTexture> text;
	
	public ScoreLabel(GuiRenderer renderer, GameStateManager manager) {
		this.renderer = renderer;
		this.manager = manager;
		this.text = FontLoader.drawStringG("Score: " + score, 360, 360, score);
		System.out.println(renderer);
		System.out.println(manager);
		System.out.println(text);
	}
	
	public void render(GuiRenderer g) {
		//System.out.println("Score Label");
		if (manager.getCurrentPlayer().getScore() != score) {
			score = manager.getCurrentPlayer().getScore();
			text = FontLoader.drawStringG("Score: " + score, 50, 50, 0.2f);
		}
		g.render(text);
	}
	
	

}
