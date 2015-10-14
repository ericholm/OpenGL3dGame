package GameEngine.main;

import org.lwjgl.opengl.Display;
import GameEngine.renderEngine.DisplayManager;
import game.main.Screens.GameScreen;

public class Game implements Runnable{
	
	private Screen screen;
	private Boolean debug = true;
	private Thread thread;
	private boolean initialised = false;
	
	public Game() {
		thread = new Thread(this);
		start();
	}
	
	public synchronized void start() {
		thread.start();
	}
	
	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void init() {
		if (debug) {
			System.out.println("Init");
		}
		DisplayManager.createDisplay();
		initialised = true;
	}
	
	public void setScreen(Screen screen) {
		this.screen = screen;
		if (initialised) {
			screen.create();
		}
	}
	
	public void close() {
		if (debug) {
			System.out.println("Closing Screen");
		}
		DisplayManager.closeDisplay();
	}
	
	public void render() {
		if (screen != null) {
			screen.render();
		}
		DisplayManager.updateDisplay();
	}

	@Override
	public void run() {
		init();
		//this.setScreen(new GameScreen());
		screen.create();
		while (!Display.isCloseRequested()) {
			render();
		}
	}

}
