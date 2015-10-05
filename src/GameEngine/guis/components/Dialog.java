package GameEngine.guis.components;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import GameEngine.font.FontLoader;
import GameEngine.guis.GuiTexture;
import game.main.Screens.GameScreen;

public class Dialog extends GuiTexture{
	
	private TextButton button;

	public Dialog(GameScreen game, Vector2f position, Vector2f scale, int width, int height, String title, String text, String buttonText, float textScale, float buttonTextScale) {
		super(game.loader.loadTexture("gui/Dialog"), position, scale, width, height);
		game.questionGui.add(this);
		FontLoader.drawString(title, (int) position.x - 120, (int) position.y + 60, 0.04f, true);
		int currentIndex = 0;
		int chars = 0;
		String[] words = text.split(" ");
		ArrayList<String> lines = new ArrayList<String>();
		boolean first = true;
		for (String word : words) {
			if (chars + word.length() < 16) {
				if (first) {
					lines.add(currentIndex, word);
					first = false;
					chars += word.length();
				}
				else {
					String sentence = lines.get(currentIndex);
					sentence += (" " + word);
					lines.set(currentIndex, sentence);
					chars += word.length();
				}
			}
			else {
				chars = 0;
				currentIndex++;
				lines.add(currentIndex, word);
				chars += word.length();
			}
		}
		int i = 0;
		for (String line : lines) {
			FontLoader.drawString(line,(int) position.x - 100, (int) position.y + 20 - (int)(FontLoader.newLineOffset * i), textScale, true);
			i++;
		}
		button = new TextButton(game.loader.loadTexture("gui/TextField"), new Vector2f(position.x, position.y - 70), new Vector2f(0.12f, 0.09f), 151, 65, buttonText, buttonTextScale, true, game);
		button.setActionMessage("CloseQuestionGui");
	}

}
