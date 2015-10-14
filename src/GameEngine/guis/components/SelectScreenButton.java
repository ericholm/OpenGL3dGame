package GameEngine.guis.components;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

public class SelectScreenButton extends Button {

	public SelectScreenButton(int texture, Vector2f position, Vector2f scale, int width, int height, String selectionValue) {
		super(texture, position, scale, width, height);
		this.setActionMessage(selectionValue);
	}
	
	public SelectScreenButton(ArrayList<Integer> texture, Vector2f position, Vector2f scale, int width, int height, String selectionValue) {
		super(texture, position, scale, width, height);
		this.setActionMessage(selectionValue);
	}
	
//	public void render(ButtonAction action) {
//		if (Mouse.isButtonDown(0) && !isButtonPressedLastFrame) {
//			isButtonPressedLastFrame = true;
//			if (Mouse.getX() >= this.getPosition().x - (this.getWidth() / 2) && Mouse.getX() <= this.getPosition().x + (this.getWidth() / 2) &&
//					Mouse.getY() >= this.getPosition().y - (this.getHeight() / 2) && Mouse.getY() <= this.getPosition().y + (this.getHeight() / 2)) {
//				//System.out.println("Button Pressed");
//				action.action(selectionValue);
//			}
//		}
//		else if (!Mouse.isButtonDown(0)) {
//			isButtonPressedLastFrame = false;
//		}
	//}

}
