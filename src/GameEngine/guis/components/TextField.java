package GameEngine.guis.components;

import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import GameEngine.guis.GuiTexture;

public class TextField extends GuiTexture{
	
	private String characterPressedLastUpdate;
	private String characterPressed;
	private String text = "";
	private boolean nextCharacter = true;
	private boolean selected = false;
	private int frame = 0;
	private TrueTypeFont font;

	public TextField(int texture, Vector2f position, Vector2f scale, int width, int height) {
		super(texture, position, scale, width, height);
		
	}
	
	public void render() {
		
		if (Mouse.isButtonDown(0)) {
			if (Mouse.getX() >= this.getPosition().x - (this.getWidth() / 2) && Mouse.getX() <= this.getPosition().x + (this.getWidth() / 2) &&
					Mouse.getY() >= this.getPosition().y - (this.getHeight() / 2) && Mouse.getY() <= this.getPosition().y + (this.getHeight() / 2)) {
				selected = true;
				//System.out.println("Selected");
			}
			else {
				//System.out.println("Unselected");
				selected = false;
			}
		}
		
		if (selected) {
			if (frame % 30 == 0) {
				nextCharacter = true;
			}
			characterPressed = Input.getCurrentKeyPressed();
			if (characterPressed != null && nextCharacter) {
				text += characterPressed;
				System.out.println(text);
				characterPressedLastUpdate = characterPressed;
				nextCharacter = false;
				
			}
			frame++;
		}
		
	}

}
