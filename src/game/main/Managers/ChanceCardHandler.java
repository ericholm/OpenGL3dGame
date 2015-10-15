package game.main.Managers;

import game.main.Screens.GameScreen;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Random;

import GameEngine.guis.GuiRenderer;

public class ChanceCardHandler {

	private static ArrayList<ChanceCard> chanceCards = new ArrayList<ChanceCard>();
	private static ArrayList<ChanceCard> chanceCardsNotUsed = new ArrayList<ChanceCard>();
	private static GameScreen game;
	private static ChanceCard currentChanceCard;
	private static final String filePrefix = "ChanceCard_";

	public static void loadChanceCards(String textFile) {
		
		BufferedReader reader = new BufferedReader(null);
		String line = "";
		while (line != null) {
			
		}
		
//		for (int i = 0; i < count; i++) {
//			chanceCards.add(new ChanceCard(game.loader.loadTexture(filePrefix + i)));
//		}
	}

	public static void addGame(GameScreen g) {
		game = g;
	}

	public static void getRandomChanceCard() {
		if (chanceCardsNotUsed.size() - 1 <= 0) {
			chanceCardsNotUsed = (ArrayList<ChanceCard>) chanceCards.clone();
			getRandomChanceCard();
		}
		else {
			Random random = new Random();
			int objectIndex;
			if (chanceCardsNotUsed.size() == 1) {
				objectIndex = 0;
			}
			else {
				objectIndex = random.nextInt(chanceCardsNotUsed.size() - 1);
				currentChanceCard = chanceCardsNotUsed.get(objectIndex);
			}
		}
	}
	
	public static void clearChanceCard() {
		currentChanceCard = null;
	}
	
	public static void render(GuiRenderer g) {
		if (currentChanceCard != null) {
			g.render(currentChanceCard);
		}
	}

}
