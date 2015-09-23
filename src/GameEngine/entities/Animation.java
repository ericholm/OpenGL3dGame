package GameEngine.entities;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import GameEngine.models.RawModel;
import GameEngine.models.TexturedModel;
import GameEngine.renderEngine.Loader;
import GameEngine.renderEngine.OBJFileLoader;
import GameEngine.renderEngine.OBJLoader;
import GameEngine.textures.ModelTexture;

public class Animation {

	private ArrayList<TexturedModel> models = new ArrayList<>();
	private int totalFrames;
	private int frame;
	private int modelNo = 0;
	private boolean playReverse = false;

	public Animation(String baseFileName, int count, Loader loader, String texture) {
		totalFrames = count;
		for (int i = 0; i < count; i++) {
			String name = baseFileName;
			if (i < 9) {
				name += "_00000" + (i + 1);
			}
			else {
				name += "_0000" + (i + 1);
			}
			RawModel r = OBJLoader.loadObjModel(name, loader);
			models.add(new TexturedModel(r, new ModelTexture(loader.loadTexture(texture))));
		}
	}

	public void render(Entity entity) {
		if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
			modelNo = 0;
			frame = 0;
		}
		if (modelNo <= totalFrames - 1 && !playReverse) {
			frame++;
			if (frame % 4 == 0) {
				entity.setModel(models.get(modelNo));
				modelNo++;
			}
		}
//		else {
//			playReverse = true;
//		}
//		if (playReverse) {
//			if (modelNo <= totalFrames - 1) {
//				frame++;
//				System.out.println("Reverse");
//				if (frame % 6 == 0) {
//					entity.setModel(models.get((totalFrames - 1) - modelNo));
//					modelNo++;
//				}
//			}
//			else {
//				playReverse = false;
//			}
//		}
//		
	}


}

