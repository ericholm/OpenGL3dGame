package game.main.Screens;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import GameEngine.entities.Animation;
import GameEngine.entities.Camera;
import GameEngine.entities.Entity;
import GameEngine.entities.Light;
import GameEngine.font.FontLoader;
import GameEngine.guis.GuiRenderer;
import GameEngine.guis.GuiTexture;
import GameEngine.guis.components.Button;
import GameEngine.guis.components.ButtonAction;
import GameEngine.guis.components.Dialog;
import GameEngine.guis.components.TextButton;
import GameEngine.guis.components.TextField;
import GameEngine.main.Screen;
import GameEngine.models.ModelData;
import GameEngine.models.RawModel;
import GameEngine.models.TexturedModel;
import GameEngine.renderEngine.DisplayManager;
import GameEngine.renderEngine.Loader;
import GameEngine.renderEngine.MasterRenderer;
import GameEngine.renderEngine.OBJFileLoader;
import GameEngine.renderEngine.OBJLoader;
import GameEngine.terrains.Terrain;
import GameEngine.textures.ModelTexture;
import GameEngine.textures.TerrainTexture;
import GameEngine.textures.TerrainTexturePack;
import game.main.Managers.GameStateManager;
import game.main.Managers.GameStates;
import game.main.Managers.QuestionHandler;
import game.main.Player.Player;
import game.main.board.Board;

public class GameScreen implements Screen, ButtonAction {
	
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public ArrayList<Player> players = new ArrayList<Player>();
	public ArrayList<GuiTexture> guisFont = new ArrayList<GuiTexture>();
	public ArrayList<GuiTexture> questionGui = new ArrayList<GuiTexture>();
	public Animation c;
	public Entity dragon;
	//public List<Light> lights = new ArrayList<Light>();
	public Light light;
	public Loader loader = new Loader();
	public MasterRenderer renderer;
	public Camera camera;
	public Player player;
	public ArrayList<GuiTexture> guis = new ArrayList<GuiTexture>();
	public GuiRenderer guiRenderer;
	public Terrain terrain;
	public TexturedModel tree;
	public TexturedModel fern;
	public TexturedModel grass;
	public TexturedModel flower;
	public TexturedModel bobble;
	private Board board;
	public ArrayList<Button> buttons = new ArrayList<>();
	public ArrayList<Button> questionButtons = new ArrayList<>();
	private GameStateManager gameStateManager;
	private Boolean down = false;
	
	public GameScreen() {
		
	}
	
	@Override
	public void create() {
		light = new Light(new Vector3f(200, 200, -100), new Vector3f(1f, 1f, 1f), new Vector3f(0, 1, 0));
		renderer = new MasterRenderer();
		guiRenderer = new GuiRenderer(loader);
		System.out.println("Screen Created");
		initModels();
		genTerrain();
		ModelData m = OBJFileLoader.loadOBJ("Board2");
		RawModel r = loader.loadToVAO(m.getVertices(), m.getTextureCoords(), m.getNormals(), m.getIndices());
		TexturedModel boardModel = new TexturedModel(r, new ModelTexture(loader.loadTexture("gui/BoardTexture")));
		board = new Board(boardModel, new Vector3f(400, 1, -400), 0, 0, 0, 20);
		entities.add(board);
		players.add(new Player("Player1", 0, "Knight", "white", board));
		players.add(new Player("Player2", 1, "King", "white", board));
		int i = 0;
		for (Player p : players) {
			//p.init(loader, new Vector3f(board.startTile.x, board.startTile.y, board.startTile.z  + (i * 20)));
			p.init(loader, new Vector3f(board.tiles.get(0).position.x, board.tiles.get(0).position.y, board.tiles.get(0).position.z), Float.valueOf(i * 20));
			entities.add(p.getPlayerPiece());
			
			//p.movePieceToTile(3, board.tiles, 0.2f);
			i++;
		}
		System.out.println(board.tiles.get(1).position);
		camera = new Camera();
		gameStateManager = new GameStateManager(GameStates.PlayersTurn, players.get(0), camera, players, board);
		ArrayList<Integer> t = new ArrayList<Integer>();
		t.add(loader.loadTexture("gui/TextField"));
		t.add(loader.loadTexture("gui/TextField-hover"));
		//Button nextTurn = new Button(loader.loadTexture("gui/EndTurnButton"), new Vector2f(1180, 50), new Vector2f(0.12f, 0.09f), 151, 65);
		
		FontLoader.loadFont("FontLookUp.txt", loader);
		FontLoader.addGame(this);
		QuestionHandler.setGame(this);
		QuestionHandler.loadQuestions("Questions1");
		
		TextButton nextTurn = new TextButton(t, new Vector2f(1180, 50), new Vector2f(0.12f, 0.09f), 151, 65, "End", 0.04f);
		nextTurn.setActionMessage("Next Turn");
		buttons.add(nextTurn);
		guis.add(nextTurn);
		
		TextButton rollDice = new TextButton(t, new Vector2f(1180, 150), new Vector2f(0.12f, 0.09f), 151, 65, "Roll", 0.04f);
		rollDice.setActionMessage("RollDice");
		buttons.add(rollDice);
		guis.add(rollDice);
		///QuestionHandler.getRandomQuestion();
	}
	
	
	
	@Override
	public void render() {
		if (Mouse.isButtonDown(0)) {
			//System.out.println(Mouse.getX() + ":" + Mouse.getY());
		}
		//System.out.println("FPS: " + (60 / DisplayManager.getFrameTimeSeconds()));
		gameStateManager.render();
		//camera.move();
		//camera.render();
		renderer.processTerrain(terrain);
		for (Entity entity : entities) {
			renderer.processEntity(entity);
		}
		for (Player player : players) {
			player.render();
		}
		for (Button button : buttons) {
			button.render(this);
		}
		for (int i = 0; i < questionButtons.size(); i++) {
			questionButtons.get(i).render(this);
		}
		renderer.render(light, camera);
		guiRenderer.render(guis);
		guiRenderer.render(questionGui);
		guiRenderer.render(guisFont);
	}
	
	public void initModels() {
		// *********TERRAIN TEXTURE STUFF***********
				TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy"));
				TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("dirt"));
				TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("pinkFlowers"));
				TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));
				TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
				TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap1"));
				terrain = new Terrain(0, -1, loader, texturePack, blendMap, "heightmap2");
				// *****************************************
				RawModel model = OBJLoader.loadObjModel("tree", loader);
				tree = new TexturedModel(model, new ModelTexture(loader.loadTexture("tree")));
				grass = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader), new ModelTexture(loader.loadTexture("grassTexture")));
				flower = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader), new ModelTexture(loader.loadTexture("flower")));
				ModelTexture fernTexture = new ModelTexture(loader.loadTexture("fern"));
				fernTexture.setNumberOfRows(2);
				fern = new TexturedModel(OBJLoader.loadObjModel("fern", loader), fernTexture);
				bobble = new TexturedModel(OBJLoader.loadObjModel("lowPolyTree", loader), new ModelTexture(loader.loadTexture("lowPolyTree")));
				TexturedModel lamp = new TexturedModel(OBJLoader.loadObjModel("lamp", loader), new ModelTexture(loader.loadTexture("lamp")));
				grass.getTexture().setHasTransparency(true);
				grass.getTexture().setUseFakeLighting(true);
				flower.getTexture().setHasTransparency(true);
				flower.getTexture().setUseFakeLighting(true);
				fern.getTexture().setHasTransparency(true);
				
//				RawModel dragonModel = OBJLoader.loadObjModel("Catapult/Catapult_Animation_000001", loader);
//				TexturedModel dragonTex = new TexturedModel(dragonModel, new ModelTexture(loader.loadTexture("white")));
//				dragon = new Entity(dragonTex, new Vector3f(150, 40, -275), 0, 0, 0, 0.1f);
//				entities.add(dragon);
//				c = new Animation("Catapult/Catapult_Animation", 30, loader, "white");
//				for (Player player : players) {
//					player.init(loader, new Vector3f(50, 50, 50));
//				}
				
	}
	
	public void genTerrain() {
		RawModel model = OBJLoader.loadObjModel("pine", loader);
		TexturedModel pine = new TexturedModel(model, new ModelTexture(loader.loadTexture("pine")));
		//Side One
		for (int x = 220; x < 560; x += 30) {
			entities.add(new Entity(pine, new Vector3f(x, 0, -600), 0, 0, 0, 2));
			entities.add(new Entity(pine, new Vector3f(x, 0, -200), 0, 0, 0, 2));
		}
		
		for (int z = -230; z > -570; z -= 30) {
			entities.add(new Entity(pine, new Vector3f(220, 0, z), 0, 0, 0, 2));
			entities.add(new Entity(pine, new Vector3f(560, 0, z), 0, 0, 0, 2));
		}
	}

	

	@Override
	public void close() {
	}

	@Override
	public void action(String action) {
		System.out.println(action);
		if (action != null) {
			if (action == "Next Turn") {
				//gameStateManager.changeCurrentPlayer();
				QuestionHandler.clearQuestion();
				QuestionHandler.getRandomQuestion();
			}
			if (action.startsWith("Answer ")) {
				System.out.println(QuestionHandler.checkAnswer(Integer.valueOf(action.split(" ")[1])));
			}
			if (action == "CloseQuestionGui") {
				QuestionHandler.clearQuestion();
			}
			if (action == "RollDice") {
				gameStateManager.rollDice();
			}
		}
	}

	

}
