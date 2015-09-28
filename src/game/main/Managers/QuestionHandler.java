package game.main.Managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.util.vector.Vector2f;

import GameEngine.font.FontLoader;
import GameEngine.guis.GuiTexture;
import GameEngine.guis.components.TextButton;
import game.main.Screens.GameScreen;

public class QuestionHandler {
	
	private static ArrayList<Question> questionsNotUsed = new ArrayList<>();
	private static ArrayList<Question> questions = new ArrayList<Question>();
	private static BufferedReader reader;
	private static String QUESTION_LOC = "res/questions/";
	private static String delimeter = ",";
	private static Random random = new Random();
	private static GameScreen game;
	private static GuiTexture questionBackground;
	private static float questionFontScale = 0.03f;
	private static int charactersPerLine = (int) (8 * (0.1f / questionFontScale));
	private static int currentQuestionsAnswer;
	
	public static void loadQuestions(String fileName) {
		File f = new File(QUESTION_LOC + fileName + ".txt");
		try {
			reader = new BufferedReader(new FileReader(f));
			String line;
			while (true) {
				line = reader.readLine();
				if (line == null) {
					break;
				}
				String[] questionSplit = line.split(delimeter);
				ArrayList<String> possibleAnswers = new ArrayList<>();
				for (int i = 1; i <= questionSplit.length - 2; i++) {
					possibleAnswers.add(questionSplit[i]);
				}
				questionsNotUsed.add(new Question(questionSplit[0], possibleAnswers, Integer.valueOf(questionSplit[questionSplit.length - 1])));
				questions.add(new Question(questionSplit[0], possibleAnswers, Integer.valueOf(questionSplit[questionSplit.length - 1])));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println(questions.get(0).getQuestion());
	}
	
	public static void setGame(GameScreen screen) {
		game = screen;
		questionBackground = new GuiTexture(game.loader.loadTexture("gui/QuestionTemplate"), new Vector2f(640,360), new Vector2f(0.5f,0.65f), 0, 0);
	}
	
	public static void getRandomQuestion() {
		clearQuestion();
		//System.out.println(charactersPerLine);
		int objectIndex = random.nextInt(questions.size() - 1);
		Question question = questionsNotUsed.get(objectIndex);
		questionsNotUsed.remove(objectIndex);
		//Generate Question For Screen
		currentQuestionsAnswer = question.getAnswerIndex();
		game.questionGui.add(questionBackground);
		String text = question.getQuestion();
		FontLoader.drawString("Question", 420,(int) 560, 0.05f);
//		System.out.println(text);
//		int lines = (int) Math.ceil(((double)text.length() / (double) charactersPerLine));
//		System.out.println(lines);
//		for(int i = 0; i < lines; i++) {
//			FontLoader.drawString(text.substring(charactersPerLine * i, Math.min((charactersPerLine * (i + 1) - 1), text.length() - 1)), 400, 550 - (int) (i * FontLoader.newLineOffset), questionFontScale);
//		}
		int currentIndex = 0;
		int chars = 0;
		String[] words = text.split(" ");
		ArrayList<String> lines = new ArrayList<>();
		//System.out.println(charactersPerLine + ":" + text.length());
		boolean first = true;
		for (String word : words) {
			//System.out.println(lines.size());
			//System.out.println(word);
			//System.out.println(chars + word.length());
			if (chars + word.length() < charactersPerLine) {
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
			FontLoader.drawString(line, 400,(int) 540 - 20 - (int)(FontLoader.newLineOffset * i), questionFontScale);
			i++;
		}
		
		FontLoader.drawString("Answers", 420,(int) 370, 0.05f);
		int answerId = 0;
		for (String answer : question.getPossiblwAnswers()) {
			TextButton answerButton = new TextButton(game.loader.loadTexture("gui/TextField"), new Vector2f(600, 300 - (answerId * 85) + 15),
					new Vector2f(0.48f, 0.10f), 453, 85, answer, 0.03f);
			game.questionGui.add(answerButton);
			answerButton.setActionMessage("Answer " + answerId);
			answerId++;
		}
	}
	
	public boolean checkAnswer(int answer) {
		if (answer == currentQuestionsAnswer) {
			return true;
		}
		return false;
	}
	
	public static void clearQuestion() {
		game.questionGui.clear();
	}

}
