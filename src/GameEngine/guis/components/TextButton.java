package GameEngine.guis.components;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import GameEngine.font.FontLoader;
import game.main.Screens.GameScreen;

public class TextButton extends Button{

	public TextButton(int texture, Vector2f position, Vector2f scale, int width, int height, String text, float textScale) {
		super(texture, position, scale, width, height);
		int charactersPerLine = (int) (6 * (0.1f / textScale));
		int currentIndex = 0;
		int chars = 0;
		String[] words = text.split(" ");
		ArrayList<String> lines = new ArrayList<>();
		boolean first = true;
		for (String word : words) {
			if (chars + word.length() < charactersPerLine) {
				if (first) {
					lines.add(currentIndex, word);
					first = false;
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
			FontLoader.drawString(line, (int) position.x - (width / 2) + 75,(int) position.y + (height / 2) - 30 - (int)(FontLoader.newLineOffset * i), textScale);
			i++;
		}
	}
	
	public TextButton(int texture, Vector2f position, Vector2f scale, int width, int height, String text, float textScale, boolean questionGui, GameScreen game) {
		super(texture, position, scale, width, height);
		int charactersPerLine = (int) (6 * (0.1f / textScale));
		int currentIndex = 0;
		int chars = 0;
		String[] words = text.split(" ");
		ArrayList<String> lines = new ArrayList<>();
		boolean first = true;
		for (String word : words) {
			if (chars + word.length() < charactersPerLine) {
				if (first) {
					lines.add(currentIndex, word);
					first = false;
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
				System.out.println(word);
				lines.add(word);
				chars += word.length();
			}
		}
		game.questionGui.add(this);
		game.questionButtons.add(this);
		int i = 0;
		for (String line : lines) {
			FontLoader.drawString(line, (int) position.x - (width / 2) + 70,(int) position.y + (height / 2) - 30 - (int)(FontLoader.newLineOffset * i), textScale, true);
			i++;
		}
		
	}
}
