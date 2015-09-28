package GameEngine.guis.components;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import GameEngine.font.FontLoader;

public class TextButton extends Button{

	public TextButton(int texture, Vector2f position, Vector2f scale, int width, int height, String text, float textScale) {
		super(texture, position, scale, width, height);
		int charactersPerLine = (int) (6 * (0.1f / textScale));
		int currentIndex = 0;
		int chars = 0;
		String[] words = text.split(" ");
		ArrayList<String> lines = new ArrayList<>();
		System.out.println(charactersPerLine);
		boolean first = true;
		for (String word : words) {
			//System.out.println(lines.size());
			System.out.println(word);
			System.out.println(chars + word.length());
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
			FontLoader.drawString(line, (int) position.x - (width / 2),(int) position.y + (height / 2) - 20 - (int)(FontLoader.newLineOffset * i), textScale);
			i++;
		}
	}

}
