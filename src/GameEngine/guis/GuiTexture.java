package GameEngine.guis;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

public class GuiTexture implements Cloneable{

	private int texture;
	private ArrayList<Integer> multiTexture = new ArrayList<Integer>();
	private int currentTextureIndex = 0;
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
	
	public GuiTexture(ArrayList<Integer> textures, Vector2f position, Vector2f scale, int width, int height) {
		this.multiTexture = textures;
		this.glPosition = new Vector2f((position.x / 640) - 1, (position.y / 360) - 1);
		this.position = position;
		this.scale = scale;
		this.texture = textures.get(currentTextureIndex);
		//width = GL11.glGetTexLevelParameteri(texture, 0, GL11.GL_TEXTURE_WIDTH);
		//height = GL11.glGetTexLevelParameteri(texture, 0, GL11.GL_TEXTURE_HEIGHT);
		this.width = width;
		this.height = height;
	}
	
	public void swapTextures() {
		if (multiTexture != null) {
			if (currentTextureIndex == 0) {
				currentTextureIndex++;
			}
			else {
				currentTextureIndex = 0;
			}
			texture = multiTexture.get(currentTextureIndex);
		}
	}
	
	public void setTexture(int texture) {
		this.texture = multiTexture.get(texture);
	}
	
	public void setPosition(Vector2f pos) {
		this.position = pos;
		this.glPosition = new Vector2f((position.x / 640) - 1, (position.y / 360) - 1);
	}
	
	public void setScale(Vector2f scale) {
		this.scale = scale;
	}
	
	public Vector2f getGlPositon() {
		return glPosition;
	}
	
	@Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
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
