package game.main.Managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class QuestionHandler {
	
	private static ArrayList<Question> questionsNotUsed = new ArrayList<>();
	private static ArrayList<Question> questions = new ArrayList<>();
	private static BufferedReader reader;
	private static String QUESTION_LOC = "res/questions/";
	private static String delimeter = ",";
	private static Random random;
	
	public static void loadQuestions(String fileName) {
		File f = new File(QUESTION_LOC + fileName);
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
	}
	
	public static String getRandomQuestion() {
		int objectIndex = random.nextInt(arg0)
		return "";
	}

}
