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
	private TextField t;
	
	public GameScreen() {
		
	}
	
	@Override
	public void create() {
		light = new Light(new Vector3f(200, 200, -100), new Vector3f(1f, 1f, 1f), new Vector3f(0, 1, 0));
		renderer = new MasterRenderer();
		guiRenderer = new GuiRenderer(loader);
		System.out.println("Screen Created");
		initModels();
		ModelData m = OBJFileLoader.loadOBJ("Board2");
		RawModel r = loader.loadToVAO(m.getVertices(), m.getTextureCoords(), m.getNormals(), m.getIndices());
		TexturedModel boardModel = new TexturedModel(r, new ModelTexture(loader.loadTexture("gui/BoardTexture")));
		board = new Board(boardModel, new Vector3f(400, 1, -400), 0, 0, 0, 20);
		entities.add(board);
		players.add(new Player("Player1", 0, "Knight", "white"));
		players.add(new Player("Player2", 1, "King", "white"));
		int i = 0;
		for (Player p : players) {
			p.init(loader, new Vector3f(board.startTile.x, board.startTile.y, board.startTile.z  + (i * 20)));
			entities.add(p.getPlayerPiece());
			i++;
		}
		camera = new Camera();
		gameStateManager = new GameStateManager(GameStates.PlayersTurn, players.get(0), camera, players);
		Button nextTurn = new Button(loader.loadTexture("gui/EndTurnButton"), new Vector2f(1180, 50), new Vector2f(0.12f, 0.09f), 151, 65);
		nextTurn.setActionMessage("Next Turn");
		buttons.add(nextTurn);
		guis.add(nextTurn);
		t = new TextField(loader.loadTexture("gui/EndTurnButton"), new Vector2f(500, 50), new Vector2f(0.12f, 0.09f), 151, 65);
		guis.add(t);
		FontLoader.loadFont("FontLookUp.txt", loader);
		FontLoader.addGame(this);
		QuestionHandler.setGame(this);
		QuestionHandler.loadQuestions("Questions1");
		QuestionHandler.getRandomQuestion();
	}
	
	
	
	@Override
	public void render() {
		//System.out.println("FPS: " + (60 / DisplayManager.getFrameTimeSeconds()));
		if (Mouse.isButtonDown(0)) {
			//System.out.println(Mouse.getX() + ":" + Mouse.getY());
		}
		gameStateManager.render();
		camera.move();
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
		t.render();	
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
				for (Player player : players) {
					player.init(loader, new Vector3f(50, 50, 50));
				}
				
	}
	
	public void genTerrain() {
		Random random = new Random();
		for (int i = 0; i < 500; i++) {
			if (i % 7 == 0) {
				float x = random.nextFloat() * 800;
				float z = random.nextFloat() * -800;
				float y = terrain.getHeightOfTerrain(x, z);
				entities.add(new Entity(fern, random.nextInt(4), new Vector3f(x, y, z), 0, random.nextFloat() * 360, 0, 0.9f));

				x = random.nextFloat() * 800;
				z = random.nextFloat() * -800;
				y = terrain.getHeightOfTerrain(x, z);
				entities.add(new Entity(grass, new Vector3f(x, y, z), 0, 0, 0, 1.8f));

				x = random.nextFloat() * 800;
				z = random.nextFloat() * -800;
				y = terrain.getHeightOfTerrain(x, z);
				entities.add(new Entity(flower, new Vector3f(x, y, z), 0, 0, 0, 2.3f));
			}

			if (i % 3 == 0) {
				float x = random.nextFloat() * 800;
				float z = random.nextFloat() * -800;
				float y = terrain.getHeightOfTerrain(x, z);
				entities.add(new Entity(bobble, new Vector3f(x, y, z), 0, random.nextFloat() * 360, 0, random.nextFloat() * 0.1f + 0.6f));

				x = random.nextFloat() * 800;
				z = random.nextFloat() * -800;
				y = terrain.getHeightOfTerrain(x, z);
				entities.add(new Entity(tree, new Vector3f(x, y, z), 0, 0, 0, random.nextFloat() * 1 + 4));
			}

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
				gameStateManager.changeCurrentPlayer();
			}
			if (action.startsWith("Answer ")) {
				System.out.println(QuestionHandler.checkAnswer(Integer.valueOf(action.split(" ")[1])));
			}
			if (action == "CloseQuestionGui") {
				QuestionHandler.clearQuestion();
			}
		}
	}

	

}
