package GameEngine.font;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.lwjgl.util.vector.Vector2f;

import GameEngine.guis.GuiTexture;
import GameEngine.renderEngine.Loader;
import game.main.Screens.GameScreen;

public class FontLoader {

	private static Hashtable<String, FontTexture> fontTextures = new Hashtable<>();
	public static ArrayList<FontTexture> t = new ArrayList<>();
	private static final String fontLoc = "res/fonts/";
	private static final int gridSize = 128;
	private static GameScreen game;
	public static float newLineOffset = 94;
	private static final float xOffset = 15;
	private static float scaleFactor = 1;


//	public static void loadFont(String textureFileName, Loader loader) {
//		Image image;
//		//Color currentColour;
//		int[][] currentChar = new int[32][32];
//		int pixel = 0;
//		try {
//			image = ImageIO.read(new File(fontLoc + textureFileName + ".bmp"));
//			BufferedImage fontImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
//			Graphics g = fontImage.getGraphics();
//			g.drawImage(image, 0, 0, null);
//			for (int y = 0; y < fontImage.getHeight() / gridSize; y++) {
//				for (int x = 0; x < fontImage.getWidth() / gridSize; x++) {
//					currentChar = new int[32][32];
//					for (int cubeY = 0; cubeY < gridSize; cubeY++) {
//						for (int cubeX = 0; cubeX < gridSize; cubeX++) {
//							Color c = new Color(fontImage.getRGB(x * gridSize + cubeX, y * gridSize + cubeY));
//							if (c.getBlue() == 255 && c.getGreen() == 255 && c.getRed() == 255) {
//								System.out.println("White");
//								c = new Color(0, 0, 0, 0);
//							}
//							else {
//								System.out.println(c.getBlue() + ":" + c.getGreen() + ":" + c.getRed() + ":" + c.getAlpha());
//								
//							}
//							currentChar[cubeX][cubeY] = (c.getAlpha() << 24)
//									             | (c.getRed() << 16)
//									             | (c.getGreen() << 8)
//									             | (c.getBlue() << 0);
//						}
//					}
//					t.add(new FontTexture(currentChar, loader));
//					//currentColour = new Color(fontImage.getRGB(x, y));
//					//System.out.println(currentColour.getBlue());
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.exit(0);
//		}
//	}
	
	public static void addGame(GameScreen screen) {
		game = screen;
	}
	
	public static void check(String picture) {
		//Image image = ImageIO.read(new File(fontLoc + picture));
		//Image image = Toolkit.getDefaultToolkit().getImage(fontLoc + picture);
		ImageIcon c = new ImageIcon(fontLoc + picture);
		System.out.println(c.getImage().getHeight(null));
		BufferedImage fontImage = new BufferedImage(c.getImage().getWidth(null), c.getImage().getHeight(null), BufferedImage.TYPE_INT_ARGB);
		//fontImage = ImageIO.read(new File(fontLoc + picture));
		//BufferedImage fontImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics g = fontImage.getGraphics();
		g.drawImage(c.getImage(), 0, 0, null);
		for (int y = 0; y < fontImage.getHeight(); y++) {
			for (int x = 0; x < fontImage.getWidth(); x++) {
				Color cs = new Color(fontImage.getRGB(x, y));
				System.out.println(cs.getAlpha());
			}
		}
	}

	public static void loadFont(String textFileName, Loader loader) {
		Image image;
		//Color currentColour;
		int[][] currentChar = new int[128][128];
		int pixel = 0;
		
		try {
			
			BufferedReader fileReader = new BufferedReader(new FileReader(new File(fontLoc + textFileName)));
			String line;
			Color c;
			int test = 0;
			while (true) {
				line = fileReader.readLine();
				if (line != null) {
					String[] charProp = line.split(",");
					
					image = ImageIO.read(new File(fontLoc + charProp[0] + "1.png"));
					BufferedImage fontImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
					Graphics g = fontImage.getGraphics();
					g.drawImage(image, 0, 0, null);
					currentChar = new int[gridSize][gridSize];
					for (int cubeY = 0; cubeY < gridSize; cubeY++) {
						for (int cubeX = 0; cubeX < gridSize; cubeX++) {
							c = new Color(fontImage.getRGB(cubeX, cubeY));
							if (c.getBlue() == 255 && c.getGreen() == 255 && c.getRed() == 255) {
								//System.out.println("White");
								c = new Color(0, 0, 0, 0);
							}
							else {
								//System.out.println(c.getBlue() + ":" + c.getGreen() + ":" + c.getRed() + ":" + c.getAlpha());
								c = Color.BLACK;
							}
							currentChar[cubeX][cubeY] = (c.getAlpha() << 24)
									             | (c.getRed() << 16)
									             | (c.getGreen() << 8)
									             | (c.getBlue() << 0);
						}
					}
					fontTextures.put(charProp[0], new FontTexture(currentChar, loader, charProp));
					test++;
				}
				else {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void drawString(String text, int x, int y, float scale) {
		text = text.toUpperCase();
		scaleFactor = 0.1f / scale;
		newLineOffset = 94 / scaleFactor;
		float offset = 0;
		for (int i = 0; i < text.length(); i++) {
			String currentChar = String.valueOf(text.charAt(i));
			if (!fontTextures.keySet().contains(currentChar)) {
				offset += (50 / scaleFactor);
			}
			else if (currentChar == "I") {
				System.out.println("FFF");
				offset -= 20;
			}
			else {
				try {
					FontTexture f = (FontTexture) fontTextures.get(String.valueOf(currentChar)).clone();
					f.getTexture().setPosition(new Vector2f(500,500));
					GuiTexture character = f.getTexture();
					character.setPosition(new Vector2f(x + offset - f.getWidth() / 2, y));
					character.setScale(new Vector2f(scale, scale));
					offset += ((gridSize / scaleFactor) - fontTextures.get(String.valueOf(currentChar)).getWidth() / scaleFactor) + (xOffset / scaleFactor);
					//System.out.println(offset);
					game.guisFont.add((GuiTexture) character.clone());
					
					character = null;
					f = null;
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				
			}
			
		}
	}
	
	public static void drawString(String text, int x, int y, float scale, boolean questionQui) {
		if (questionQui) {
			text = text.toUpperCase();
			scaleFactor = 0.1f / scale;
			newLineOffset = 94 / scaleFactor;
			float offset = 0;
			for (int i = 0; i < text.length(); i++) {
				String currentChar = String.valueOf(text.charAt(i));
				if (!fontTextures.keySet().contains(currentChar)) {
					offset += (50 / scaleFactor);
				}
				else if (currentChar == "I") {
					System.out.println("FFF");
					offset -= 20;
				}
				else {
					try {
						FontTexture f = (FontTexture) fontTextures.get(String.valueOf(currentChar)).clone();
						f.getTexture().setPosition(new Vector2f(500,500));
						GuiTexture character = f.getTexture();
						character.setPosition(new Vector2f(x + offset - f.getWidth() / 2, y));
						character.setScale(new Vector2f(scale, scale));
						offset += ((gridSize / scaleFactor) - fontTextures.get(String.valueOf(currentChar)).getWidth() / scaleFactor) + (xOffset / scaleFactor);
						//System.out.println(offset);
						game.questionGui.add((GuiTexture) character.clone());
						
						character = null;
						f = null;
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
					
				}
				
			}
		}
		
	}
	
	public static void clearText() {
		game.guisFont.clear();
	}
	
	
}
