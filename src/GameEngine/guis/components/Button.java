package GameEngine.guis.components;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import GameEngine.guis.GuiTexture;

public class Button extends GuiTexture{

	private boolean isButtonPressedLastFrame = false;
	private String actionMessage;
	private boolean hoverTexture;
	private boolean mouseLeftButton = true;

	public Button(int texture, Vector2f position, Vector2f scale, int width, int height) {
		super(texture, position, scale, width, height);
		if (Mouse.isButtonDown(0)) {
			isButtonPressedLastFrame = true;
		}
		hoverTexture = false;
	}

	public Button(ArrayList<Integer> texture, Vector2f position, Vector2f scale, int width, int height) {
		super(texture, position, scale, width, height);
		if (Mouse.isButtonDown(0)) {
			isButtonPressedLastFrame = true;
		}
		hoverTexture = true;
	}

	public void setActionMessage(String a) {
		actionMessage = a;
	}

	public void render(ButtonAction action) {

		if (hoverTexture) {
			if (Mouse.getX() >= this.getPosition().x - (this.getWidth() / 2) && Mouse.getX() <= this.getPosition().x + (this.getWidth() / 2) &&
					Mouse.getY() >= this.getPosition().y - (this.getHeight() / 2) && Mouse.getY() <= this.getPosition().y + (this.getHeight() / 2)) {
				if (mouseLeftButton) {
					this.setTexture(1);
					mouseLeftButton = false;
				}
			}
			else {
				this.setTexture(0);
				mouseLeftButton = true;
			}
		}

		if (Mouse.isButtonDown(0) && !isButtonPressedLastFrame) {
			//System.out.println(actionMessage);
			
			isButtonPressedLastFrame = true;
			//System.out.println(Mouse.getX() + ":" + Mouse.getY());
			//System.out.println(this.getPosition().x + ":" + this.getPosition().y);
			//System.out.println(this.getWidth() + ":" + this.getHeight());
			if (Mouse.getX() >= this.getPosition().x - (this.getWidth() / 2) && Mouse.getX() <= this.getPosition().x + (this.getWidth() / 2) &&
					Mouse.getY() >= this.getPosition().y - (this.getHeight() / 2) && Mouse.getY() <= this.getPosition().y + (this.getHeight() / 2)) {
				//System.out.println("Button Pressed");
				//System.out.println(actionMessage);
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
