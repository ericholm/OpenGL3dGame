package game.main.Screens;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import GameEngine.guis.GuiRenderer;
import GameEngine.guis.GuiTexture;
import GameEngine.guis.components.Button;
import GameEngine.main.Screen;
import GameEngine.renderEngine.Loader;

public class StartScreen implements Screen{
	
	private List<GuiTexture> guis = new ArrayList<GuiTexture>();
	private List<Button> buttons = new ArrayList<Button>();
	private Loader loader = new Loader();
	private GuiRenderer guiRenderer;

	@Override
	public void render() {
		guiRenderer.render(guis);
		for (Button button : buttons) {
			button.render();
		}
	}

	
	@Override
	public void create() {
		guiRenderer = new GuiRenderer(loader);
		guis.add(new GuiTexture(loader.loadTexture("gui/Castle"), new Vector2f(640, 360), new Vector2f(1f, 1f), 0, 0));
		guis.add(new GuiTexture(loader.loadTexture("gui/TitleStartScreen"), new Vector2f(640, 400), new Vector2f(1f, 1f), 0, 0));
	}
	
	@Override
	public void close() {
		
	}


}
