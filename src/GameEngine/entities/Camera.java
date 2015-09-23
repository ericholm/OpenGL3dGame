package GameEngine.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private float distanceFromPlayer = 50;
	private float angleAroundPlayer = 0;

	private Vector3f position = new Vector3f(150, 50, -200);
	private float pitch = 0;
	private float yaw = 0;
	private float roll;
	private float speed = 0.5f;

	public Camera() {
		//this.player = player;
	}

	public void move() {

		//yaw = -(Display.getWidth() - Mouse.getX() / 2);
		//pitch = (Display.getHeight() / 2) - Mouse.getY();
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			pitch += 0.2;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
			pitch -= 0.2;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
			yaw += 0.35;
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_Y)) {
			yaw -= 0.35;
		}


		if (pitch >= 90) {

			pitch = 90;

		} else if (pitch <= -90) {

			pitch = -90;

		} else if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			position.y -= speed;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			position.y += speed;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {

			position.z += -(float) Math.cos(Math.toRadians(yaw)) * speed;
			position.x += (float) Math.sin(Math.toRadians(yaw)) * speed;

		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.z -= -(float) Math.cos(Math.toRadians(yaw)) * speed;
			position.x -= (float) Math.sin(Math.toRadians(yaw)) * speed;

		}

		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {

			position.z += (float) Math.sin(Math.toRadians(yaw)) * speed;
			position.x += (float) Math.cos(Math.toRadians(yaw)) * speed;

		} else if (Keyboard.isKeyDown(Keyboard.KEY_A)) {

			position.z -= (float) Math.sin(Math.toRadians(yaw)) * speed;
			position.x -= (float) Math.cos(Math.toRadians(yaw)) * speed;

		}

	}


	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}

	private void calculateZoom() {
		float zoomLevel = Mouse.getDWheel() * 0.1f;
		position.x *= zoomLevel;
		position.y *= zoomLevel;
		position.z *= zoomLevel;
	}





	//
	//	private void calculateCameraPosition(float horizDistance, float verticDistance) {
	//		float theta = player.getRotY() + angleAroundPlayer;
	//		float offsetX = (float) (horizDistance * Math.sin(Math.toRadians(theta)));
	//		float offsetZ = (float) (horizDistance * Math.cos(Math.toRadians(theta)));
	//		position.x = player.getPosition().x - offsetX;
	//		position.z = player.getPosition().z - offsetZ;
	//		position.y = player.getPosition().y + verticDistance;
	//	}
	//
	//	private float calculateHorizontalDistance() {
	//		return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	//	}
	//
	//	private float calculateVerticalDistance() {
	//		return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	//	}
	//
	//	private void calculateZoom() {
	//		float zoomLevel = Mouse.getDWheel() * 0.1f;
	//		distanceFromPlayer -= zoomLevel;
	//	}
	//
	//	private void calculatePitch() {
	//		if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) {
	//			float pitchChange = Mouse.getDY() * 0.1f;
	//			pitch -= pitchChange;
	//		}
	//	}
	//
	//	private void calculateAngleAroundPlayer() {
	//		if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) {
	//			float angleChange = Mouse.getDX() * 0.3f;
	//			angleAroundPlayer -= angleChange;
	//		}
	//	}

}
