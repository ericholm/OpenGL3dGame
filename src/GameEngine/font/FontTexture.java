package GameEngine.font;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.TextureLoader;

import GameEngine.guis.GuiTexture;
import GameEngine.renderEngine.Loader;
import GameEngine.textures.ModelTexture;

public class FontTexture implements Cloneable{
	
	private GuiTexture g;
	private int height, width;
	
	public FontTexture(int[][] charImage, Loader loader, String[] properties) {
		this.width = Integer.valueOf(properties[3]);
		this.height = Integer.valueOf(properties[4]);
		BufferedImage image = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		for (int cubeY = 0; cubeY < 128; cubeY++) {
			for (int cubeX = 0; cubeX < 128; cubeX++) {
				image.setRGB(cubeX, cubeY, charImage[cubeX][cubeY]);
			}
		}
		try {
			ImageIO.write(image, "png", os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		InputStream is = new ByteArrayInputStream(os.toByteArray());
		g = new GuiTexture(loader.loadTexture(is), new Vector2f(500, 500), new Vector2f(0.10f, 0.10f), 0, 0);
	}
	
	public GuiTexture getTexture() {
		return g;
	}
	
	@Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
	
	public int getWidth() {
		return width;
	}
	
	public void setPosition(Vector2f pos) {
		g.setPosition(pos);
	}
	
	public void setScale(int scale) {
		this.g.setScale(new Vector2f(scale, scale));
	}
	
	public FontTexture(int[][] charImage, Loader loader) {
		BufferedImage image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		for (int cubeY = 0; cubeY < 32; cubeY++) {
			for (int cubeX = 0; cubeX < 32; cubeX++) {
				image.setRGB(cubeX, cubeY, charImage[cubeX][cubeY]);
			}
		}
		try {
			ImageIO.write(image, "png", os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		InputStream is = new ByteArrayInputStream(os.toByteArray());
		g = new GuiTexture(loader.loadTexture(is), new Vector2f(500, 500), new Vector2f(0.1f, 0.1f), 0, 0);
	}
	
	
	public void getInputStream() {
		
	}

}
