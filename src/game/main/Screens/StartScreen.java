package game.main.Screens;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import GameEngine.guis.GuiRenderer;
import GameEngine.guis.GuiTexture;
import GameEngine.guis.components.Button;
import GameEngine.guis.components.ButtonAction;
import GameEngine.guis.components.SelectScreenButton;
import GameEngine.guis.components.TextField;
import GameEngine.main.Screen;
import GameEngine.renderEngine.Loader;
import game.main.Main;
import game.main.Player.Player;

public class StartScreen implements Screen, ButtonAction{

	private List<GuiTexture> guis = new ArrayList<GuiTexture>();
	private List<Button> buttons = new ArrayList<Button>();
	private List<TextField> textfields = new ArrayList<TextField>();
	private Loader loader = new Loader();
	private GuiRenderer guiRenderer;
	private boolean render = true;
	private int buttonClickState = 0;
	private Main game;
	private SelectScreenButton knightPiece;
	private SelectScreenButton kingPiece;
	private SelectScreenButton queenPiece;
	private String selectedPiece;
	private ArrayList<Player> players = new ArrayList<>();
	
	public StartScreen(Main game) {
		this.game = game;
	}
	
	@Override
	public void render() {
		guiRenderer.render(guis);
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).render(this);
		}
		for (int i = 0; i < textfields.size(); i++) {
			textfields.get(i).render();
		}
		
	}


	@Override
	public void create() {
		knightPiece  = new SelectScreenButton(loader.loadTexture("gui/KnightSelectButton"), new Vector2f(900, 350), new Vector2f(0.17f, 0.1f), 418, 100, "knightPiece");
		kingPiece = new SelectScreenButton(loader.loadTexture("gui/QueenSelectButton"), new Vector2f(640, 350), new Vector2f(0.17f, 0.1f), 418, 100, "queenPiece");
		queenPiece = new SelectScreenButton(loader.loadTexture("gui/KingSelectButton"), new Vector2f(400, 350), new Vector2f(0.17f, 0.1f), 418, 100, "kingPiece");
		guiRenderer = new GuiRenderer(loader);
		guis.add(new GuiTexture(loader.loadTexture("gui/Castle"), new Vector2f(640, 360), new Vector2f(1f, 1f), 0, 0));
		guis.add(new GuiTexture(loader.loadTexture("gui/TitleStartScreen"), new Vector2f(640, 400), new Vector2f(1f, 1f), 0, 0));
		Button beginButton = new Button(loader.loadTexture("gui/BeginButton2"), new Vector2f(640, 140), new Vector2f(0.33f, 0.15f), 418, 100);
		buttons.add(beginButton);
		guis.add(beginButton);
	}

	public void playerCreationScreen1() {
		guis.clear();
		buttons.clear();
		//System.out.println("Cleared Lists: " + guis.size());
		guis.add(new GuiTexture(loader.loadTexture("gui/GreyBackground"), new Vector2f(640, 400), new Vector2f(1f, 1.2f), 0, 0));
		guis.add(new GuiTexture(loader.loadTexture("gui/BackgroundBorder"), new Vector2f(640, 360), new Vector2f(1f, 0.98f), 0, 0));
		guis.add(new GuiTexture(loader.loadTexture("gui/CharacterSelection"), new Vector2f(640, 360), new Vector2f(0.90f, 0.90f), 0, 0));
		Button nextPlayerButton = new Button(loader.loadTexture("gui/NextButton"), new Vector2f(640, 140), new Vector2f(0.33f, 0.15f), 418, 100);
		buttons.add(nextPlayerButton);
		guis.add(nextPlayerButton);
		buttons.add(kingPiece);
		buttons.add(queenPiece);
		buttons.add(knightPiece);
		guis.add(kingPiece);
		guis.add(queenPiece);
		guis.add(knightPiece);
		
	}
	
	public void playerCreationScreen2() {
		guis.clear();
		buttons.clear();
		guis.add(new GuiTexture(loader.loadTexture("gui/GreyBackground"), new Vector2f(640, 400), new Vector2f(1f, 1.2f), 0, 0));
		guis.add(new GuiTexture(loader.loadTexture("gui/BackgroundBorder"), new Vector2f(640, 360), new Vector2f(1f, 0.98f), 0, 0));
		guis.add(new GuiTexture(loader.loadTexture("gui/CharacterSelection2"), new Vector2f(640, 360), new Vector2f(0.90f, 0.90f), 0, 0));
		
		Button beginGame = new Button(loader.loadTexture("gui/BeginButton2"), new Vector2f(640, 140), new Vector2f(0.33f, 0.15f), 418, 100);
		buttons.add(beginGame);
		guis.add(beginGame);
		guis.add(kingPiece);
		guis.add(queenPiece);
		guis.add(knightPiece);
	}

	@Override
	public void close() {

	}


	@Override
	public void action(String action) {
		if (action != null) {
			//System.out.println(action);
			selectedPiece = action;
		}
		
		if (buttonClickState == 0 && action == null) {
			playerCreationScreen1();
		}
		else if (buttonClickState == 1 && action == null) {
			players.add(new Player("Player 1", 0, selectedPiece, selectedPiece));
			playerCreationScreen2();
		}
		else if (buttonClickState == 2 && action == null) {
			players.add(new Player("Player 2", 1, selectedPiece, selectedPiece));
			game.setScreen(new GameScreen());
			System.out.println("Player 1: " + players.get(0).getModelName());
			System.out.println("Player 2: " + players.get(1).getModelName());
		}
		if (action == null) {
			buttonClickState++;
		}
	}


}
