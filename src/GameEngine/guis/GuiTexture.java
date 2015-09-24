package GameEngine.guis;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

public class GuiTexture {

	private int texture;
	private Vector2f glPosition;
	private Vector2f position;
	private Vector2f scale;
	private int width, height;

	public GuiTexture(int texture, Vector2f position, Vector2f scale, int width, int height) {
		this.texture = texture;
		this.glPosition = new Vector2f((position.x / 640) - 1, (position.y / 360) - 1);
		this.position = position;
		this.scale = scale;
		//width = GL11.glGetTexLevelParameteri(texture, 0, GL11.GL_TEXTURE_WIDTH);
		//height = GL11.glGetTexLevelParameteri(texture, 0, GL11.GL_TEXTURE_HEIGHT);
		this.width = width;
		this.height = height;
	}
	
	public Vector2f getGlPositon() {
		return glPosition;
	}

	public int getTexture() {
		return texture;
	}

	public Vector2f getPosition() {
		return position;
	}

	public Vector2f getScale() {
		return scale;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

}
