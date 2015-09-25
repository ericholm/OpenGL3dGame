package game.main.Managers;

import java.util.ArrayList;

public class Question {
	
	private String question;
	private ArrayList<String> possibleAnswers;
	private int possibleAnswerCount;
	private int answerIndex;

	public Question(String question, ArrayList<String> possibleAnswers, int answerIndex) {
		this.question = question;
		this.possibleAnswers = possibleAnswers;
		this.answerIndex = answerIndex;
		this.possibleAnswerCount = possibleAnswers.size();
	}
	
	public String getQuestion() {
		return question;
	}

}
