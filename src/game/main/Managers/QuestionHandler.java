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
import GameEngine.guis.components.Dialog;
import GameEngine.guis.components.TextButton;
import game.main.Screens.GameScreen;

public class QuestionHandler {
	
	private static ArrayList<Question> questionsNotUsed = new ArrayList<Question>();
	private static ArrayList<Question> questions = new ArrayList<Question>();
	private static BufferedReader reader;
	private static String QUESTION_LOC = "res/questions/";
	private static String delimeter = ",";
	private static Random random = new Random();
	private static GameScreen game;
	private static GuiTexture questionBackground;
	private static float questionFontScale = 0.03f;
	private static int charactersPerLine = (int) (9 * (0.1f / questionFontScale));
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
				ArrayList<String> possibleAnswers = new ArrayList<String>();
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
		questionBackground = new GuiTexture(game.loader.loadTexture("gui/QuestionTemplate"), new Vector2f(640,360), new Vector2f(0.6f,0.75f), 0, 0);
	}
	
	public static void getRandomQuestion() {
		clearQuestion();
		//System.out.println(charactersPerLine);
<<<<<<< HEAD
		int objectIndex = random.nextInt(questions.size() - 1);
		Question question = questionsNotUsed.get(objectIndex);
		questionsNotUsed.remove(objectIndex);
		//Generate Question For Screen
		currentQuestionsAnswer = question.getAnswerIndex();
		game.questionGui.add(questionBackground);
		String text = question.getQuestion();
		FontLoader.drawString("Question", 360,(int) 580, 0.05f, true);
//		System.out.println(text);
//		int lines = (int) Math.ceil(((double)text.length() / (double) charactersPerLine));
//		System.out.println(lines);
//		for(int i = 0; i < lines; i++) {
//			FontLoader.drawString(text.substring(charactersPerLine * i, Math.min((charactersPerLine * (i + 1) - 1), text.length() - 1)), 400, 550 - (int) (i * FontLoader.newLineOffset), questionFontScale);
//		}
		int currentIndex = 0;
		int chars = 0;
		String[] words = text.split(" ");
		ArrayList<String> lines = new ArrayList<String>();
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
=======
		int objectIndex = random.nextInt(questionsNotUsed.size());
		if (questionsNotUsed.size() > 0) {
			Question question = questionsNotUsed.get(objectIndex);
			//questionsNotUsed.remove(objectIndex);
			//Generate Question For Screen
			currentQuestionsAnswer = question.getAnswerIndex();
			game.questionGui.add(questionBackground);
			String text = question.getQuestion();
			FontLoader.drawString("Question", 360,(int) 580, 0.05f, true);
//			System.out.println(text);
//			int lines = (int) Math.ceil(((double)text.length() / (double) charactersPerLine));
//			System.out.println(lines);
//			for(int i = 0; i < lines; i++) {
//				FontLoader.drawString(text.substring(charactersPerLine * i, Math.min((charactersPerLine * (i + 1) - 1), text.length() - 1)), 400, 550 - (int) (i * FontLoader.newLineOffset), questionFontScale);
//			}
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
>>>>>>> 9c05c76ac92813e67fa53200f77ee8a35b281728
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
				FontLoader.drawString(line, 340,(int) 565 - 20 - (int)(FontLoader.newLineOffset * i), questionFontScale, true);
				i++;
			}
			
			FontLoader.drawString("Answers", 360,(int) 390, 0.05f, true);
			int answerId = 0;
			for (String answer : question.getPossiblwAnswers()) {
				TextButton answerButton = new TextButton(game.loader.loadTexture("gui/TextField"), new Vector2f(640, 300 - (answerId * 85) + 15),
						new Vector2f(0.58f, 0.10f), 651, 70, answer, questionFontScale, true, game);
				answerButton.setActionMessage("Answer " + answerId);
				answerId++;
			}
		}
		else {
			questionsNotUsed = questions;
			getRandomQuestion();
		}
		
		
	}
	
	public static boolean checkAnswer(int answer) {
		clearQuestion();
		if (answer == currentQuestionsAnswer) {
			Dialog d = new Dialog(game, new Vector2f(640, 360), new Vector2f(0.3f, 0.3f), 0, 0, "Correct", "You  won 200 gold", " OK", 0.03f, 0.05f);
			return true;
		}
		Dialog d = new Dialog(game, new Vector2f(640, 360), new Vector2f(0.3f, 0.3f), 0, 0, "Incorrect", "The correct answer is " + currentQuestionsAnswer + 1, " OK", 0.03f, 0.05f);
		return false;
	}
	
	public static void clearQuestion() {
		game.questionGui.clear();
		game.questionButtons.clear();
	}

}
