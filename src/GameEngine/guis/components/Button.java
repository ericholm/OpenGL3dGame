package GameEngine.guis.components;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import GameEngine.guis.GuiTexture;

public class Button extends GuiTexture{
	
	private boolean isButtonPressed = false;

	public Button(int texture, Vector2f position, Vector2f scale, int width, int height) {
		super(texture, position, scale, width, height);
		
	}
	
	public void render() {
		if (Mouse.isButtonDown(0)) {
			//System.out.println(Mouse.getX() + ":" + Mouse.getY());
			//System.out.println(this.getWidth() + ":" + this.getHeight());
			if (Mouse.getX() >= this.getPosition().x - (this.getWidth() / 2) && Mouse.getX() <= this.getPosition().x + (this.getWidth() / 2) &&
					Mouse.getY() >= this.getPosition().y - (this.getHeight() / 2) && Mouse.getY() <= this.getPosition().y + (this.getHeight() / 2)) {
				System.out.println("Button Pressed");
			}
		}
	}
}
