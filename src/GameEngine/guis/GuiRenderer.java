package GameEngine.guis;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import GameEngine.models.RawModel;
import GameEngine.renderEngine.Loader;
import GameEngine.toolbox.Maths;

public class GuiRenderer {

	private final RawModel quad;
	private GuiShader shader;

	public GuiRenderer(Loader loader) {
		float[] positions = {
				-1, 1, -1, -1, 1, 1, 1, -1 };
		quad = loader.loadToVAO(positions);
		shader = new GuiShader();
	}

	public void render(GuiTexture gui) {
		shader.start();
		GL30.glBindVertexArray(quad.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		//		for (GuiTexture gui : guis) {
		//			GL13.glActiveTexture(GL13.GL_TEXTURE0);
		//			GL11.glBindTexture(GL11.GL_TEXTURE_2D, gui.getTexture());
		//			Matrix4f matrix = Maths.createTransformationMatrix(gui.getGlPositon(), gui.getScale());
		//			shader.loadTransformation(matrix);
		//			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());
		//		}
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, gui.getTexture());
		Matrix4f matrix;
		if (gui.getRotation() != null) {
			System.out.println("Rotate");
			matrix = Maths.createTransformationMatrix(gui.getGlPositon(), gui.getRotation().x, gui.getRotation().y, gui.getScale());
		}
		else {
			matrix = Maths.createTransformationMatrix(gui.getGlPositon(), gui.getScale());
		}
		
		shader.loadTransformation(matrix);
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_BLEND);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		shader.stop();
	}

	public void render(List<GuiTexture> guis) {
		shader.start();
		GL30.glBindVertexArray(quad.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		//		for (GuiTexture gui : guis) {
		//			GL13.glActiveTexture(GL13.GL_TEXTURE0);
		//			GL11.glBindTexture(GL11.GL_TEXTURE_2D, gui.getTexture());
		//			Matrix4f matrix = Maths.createTransformationMatrix(gui.getGlPositon(), gui.getScale());
		//			shader.loadTransformation(matrix);
		//			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());
		//		}

		for(int i = 0; i < guis.size(); i++) {
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, guis.get(i).getTexture());
			Matrix4f matrix;
			if (guis.get(i).getRotation() != null) {
				System.out.println("Rotate");
				matrix = Maths.createTransformationMatrix(guis.get(i).getGlPositon(), guis.get(i).getRotation().x, guis.get(i).getRotation().y, guis.get(i).getScale());
			}
			else {
				matrix = Maths.createTransformationMatrix(guis.get(i).getGlPositon(), guis.get(i).getScale());
			}
			shader.loadTransformation(matrix);
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());
		}
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_BLEND);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		shader.stop();
	}

	public void cleanUp() {
		shader.cleanUp();
	}

}
