package GameEngine.guis.components;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import GameEngine.guis.GuiTexture;

public class Button extends GuiTexture{
	
	private boolean isButtonPressedLastFrame = false;
	private String actionMessage;

	public Button(int texture, Vector2f position, Vector2f scale, int width, int height) {
		super(texture, position, scale, width, height);
		if (Mouse.isButtonDown(0)) {
			isButtonPressedLastFrame = true;
		}
	}
	
	public void setActionMessage(String a) {
		actionMessage = a;
	}
	
	public void render(ButtonAction action) {
		if (Mouse.isButtonDown(0) && !isButtonPressedLastFrame) {
			//System.out.println("Mouse DOwn");
			isButtonPressedLastFrame = true;
			//System.out.println(Mouse.getX() + ":" + Mouse.getY());
			//System.out.println(this.getPosition().x + ":" + this.getPosition().y);
			//System.out.println(this.getWidth() + ":" + this.getHeight());
			if (Mouse.getX() >= this.getPosition().x - (this.getWidth() / 2) && Mouse.getX() <= this.getPosition().x + (this.getWidth() / 2) &&
					Mouse.getY() >= this.getPosition().y - (this.getHeight() / 2) && Mouse.getY() <= this.getPosition().y + (this.getHeight() / 2)) {
				//System.out.println("Button Pressed");
				if (actionMessage != null) {
					action.action(actionMessage);
				}
				else {
					action.action(null);
				}
				
			}
		}
		else if (!Mouse.isButtonDown(0)) {
			isButtonPressedLastFrame = false;
		}
	}
}
