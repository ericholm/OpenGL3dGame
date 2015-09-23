package game.main.Screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import GameEngine.entities.Animation;
import GameEngine.entities.Camera;
import GameEngine.entities.Entity;
import GameEngine.entities.Light;
import GameEngine.guis.GuiRenderer;
import GameEngine.guis.GuiTexture;
import GameEngine.guis.components.Button;
import GameEngine.main.Screen;
import GameEngine.models.RawModel;
import GameEngine.models.TexturedModel;
import GameEngine.renderEngine.Loader;
import GameEngine.renderEngine.MasterRenderer;
import GameEngine.renderEngine.OBJLoader;
import GameEngine.terrains.Terrain;
import GameEngine.textures.ModelTexture;
import GameEngine.textures.TerrainTexture;
import GameEngine.textures.TerrainTexturePack;
import game.main.Player.Player;

public class GameScreen implements Screen{
	
	public List<Entity> entities = new ArrayList<Entity>();
	public List<Player> players = new ArrayList<Player>();
	public Animation c;
	public Entity dragon;
	//public List<Light> lights = new ArrayList<Light>();
	public Light light;
	public Loader loader = new Loader();
	public MasterRenderer renderer;
	public Camera camera;
	public Player player;
	public List<GuiTexture> guis = new ArrayList<GuiTexture>();
	public GuiRenderer guiRenderer;
	public Terrain terrain;
	public TexturedModel tree;
	public TexturedModel fern;
	public TexturedModel grass;
	public TexturedModel flower;
	public TexturedModel bobble;
	Button b;
	
	public GameScreen() {
		
	}
	
	@Override
	public void create() {
		light = new Light(new Vector3f(200, 200, -100), new Vector3f(1f, 1f, 1f), new Vector3f(0, 1, 0));
		renderer = new MasterRenderer();
		guiRenderer = new GuiRenderer(loader);
		initModels();
		//genTerrain();
		//GuiTexture health = new GuiTexture(loader.loadTexture("gui/health"), new Vector2f(-0.74f, 0.925f), new Vector2f(0.25f, 0.25f));
		//guis.add(health);
		b = new Button(loader.loadTexture("gui/health"), new Vector2f(640, 360), new Vector2f(0.20f, 0.25f), 0, 0);
		
		guis.add(b);
	}
	
	@Override
	public void render() {
		camera.move();
		renderer.processTerrain(terrain);
		for (Entity entity : entities) {
			renderer.processEntity(entity);
		}
		b.render();
//		c.render(dragon);
		renderer.render(light, camera);
		guiRenderer.render(guis);
	}
	
	public void initModels() {
		// *********TERRAIN TEXTURE STUFF***********
				TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy"));
				TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("dirt"));
				TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("pinkFlowers"));
				TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));

				TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
				TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));

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
				
				camera = new Camera();
				
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

	

}
