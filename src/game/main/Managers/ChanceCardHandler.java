package game.main.Managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import GameEngine.guis.GuiRenderer;
import GameEngine.guis.GuiTexture;
import GameEngine.renderEngine.DisplayManager;
import game.main.Screens.GameScreen;

public class ChanceCardHandler {

	private static ArrayList<ChanceCard> chanceCards = new ArrayList<ChanceCard>();
	private static ArrayList<ChanceCard> chanceCardsNotUsed = new ArrayList<ChanceCard>();
	private static GameScreen game;
	private static ChanceCard currentChanceCard;
	private static GuiTexture backOfCard;
	private static final String filePrefix = "ChanceCards/ChanceCard_";
	private static float animationTimeTotal = 1f;
	private static float animationPause = 0.5f;
	private static boolean animationPauseComplete = false;
	private static float currentAnimationTime = 0;
	private static int animationStage = 0;
	private static boolean isCardShown = false;

	public static void loadChanceCards(String textFile) throws IOException {
		//currentChanceCard = new ChanceCard((game.loader.loadTexture(filePrefix + 1)));
		File f = new File("res/textures/ChanceCards/" + textFile);
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String line = "";
		int i = 1;
		while (true) {
			line = reader.readLine();
			if (line == null) {
				break;
			}
			System.out.println(line);
			if (line.startsWith("Luck")) {
				chanceCards.add(new ChanceCard(game.loader.loadTexture(filePrefix + i), ChanceType.Luck, Integer.valueOf(line.split(",")[1])));
			}
			else if (line.startsWith("Robbed")) {
				chanceCards.add(new ChanceCard(game.loader.loadTexture(filePrefix + i), ChanceType.Robbed, Integer.valueOf(line.split(",")[1])));
			}
			else if (line.startsWith("Plague")) {
				chanceCards.add(new ChanceCard(game.loader.loadTexture(filePrefix + i), ChanceType.Plague, Integer.valueOf(line.split(",")[1])));
			}
			i++;
		}
		//chanceCardsNotUsed = chanceCards;

		//		for (int i = 0; i < count; i++) {
		//			chanceCards.add(new ChanceCard(game.loader.loadTexture(filePrefix + i)));
		//		}
	}

	public static void addGame(GameScreen g) {
		game = g;
		backOfCard = new GuiTexture(game.loader.loadTexture("ChanceCards/BackChanceCard"), new Vector2f(640, 360), new Vector2f(0.5f, 0.4f), 0, 0);
	}

	public static void processsCard() {
		if (currentChanceCard.type == ChanceType.Luck) {
			game.getGameStateManager().getCurrentPlayer().increaseScore(currentChanceCard.modifier);
		}
		else if (currentChanceCard.type == ChanceType.Robbed) {
			game.getGameStateManager().getCurrentPlayer().decreaseScore(currentChanceCard.modifier);
		}
		else if (currentChanceCard.type == ChanceType.Plague) {
			game.getGameStateManager().getCurrentPlayer().decreaseScore(currentChanceCard.modifier);
			game.getGameStateManager().movePlayerBack(currentChanceCard.modifier2);
		}
	}

	public static void getRandomChanceCard() {
		if (chanceCardsNotUsed.size() <= 0) {
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
			}
			currentChanceCard = chanceCardsNotUsed.get(objectIndex);
			//chanceCardsNotUsed.remove(objectIndex);
		}
		animationStage = 1;
	}

	public static void clearChanceCard() {
		currentChanceCard = null;
	}

	public static void render(GuiRenderer g) {
		float delta = DisplayManager.getFrameTimeSeconds();

		currentAnimationTime += delta;
		if (animationStage == 1) {
			if (currentAnimationTime >= animationPause && !animationPauseComplete ) {
				animationPauseComplete = true;
				currentAnimationTime = 0;
			}
			if (animationPauseComplete) {
				if (currentAnimationTime >= animationTimeTotal) {
					animationStage++;
					currentAnimationTime = 0;
					currentChanceCard.setRotation(new Vector2f(90, 0));
				}
				else {
					backOfCard.increaseRotation(new Vector2f((90 / animationTimeTotal) * delta, 0));
				}
			}
			g.render(backOfCard);
		}
		else if (animationStage == 2) {
			if (currentAnimationTime >= animationTimeTotal) {
				animationStage++;
				currentAnimationTime = 0;
				backOfCard.setRotation(new Vector2f(0, 0));
			}
			else {
				currentChanceCard.increaseRotation(new Vector2f((-(90 / animationTimeTotal) * delta), 0));
			}
			g.render(currentChanceCard);
		}
		else if (animationStage == 3) {
			animationPauseComplete = false;
			g.render(currentChanceCard);
			if (Mouse.isButtonDown(0)) {
				processsCard();
				animationStage++;
			}
			
		}


		//		if (currentChanceCard != null) {
		//			g.render(currentChanceCard);
		//			
		//		}
	}

}
