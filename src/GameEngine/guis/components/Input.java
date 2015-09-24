package GameEngine.guis.components;

import org.lwjgl.input.Keyboard;

public class Input {
	
	public static String getCurrentKeyPressed() {
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			return "a";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_B)) {
			return "b";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
			return "c";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			return "d";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			return "e";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
			return "f";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
			return "g";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_H)) {
			return "h";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_I)) {
			return "i";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_J)) {
			return "j";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_K)) {
			return "k";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_L)) {
			return "l";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_M)) {
			return "m";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_N)) {
			return "n";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
			return "o";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
			return "p";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			return "q";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
			return "r";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			return "s";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
			return "t";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_U)) {
			return "u";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_V)) {
			return "v";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			return "w";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_X)) {
			return "x";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Y)) {
			return "y";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
			return "z";
		}
		return null;
	}

}
