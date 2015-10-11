package GameEngine.entities;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import GameEngine.renderEngine.DisplayManager;
import game.main.Player.Player;

public class Camera {

	private Player currentPlayerTracking;
	private float distanceFromPlayer = 125;
	private float angleAroundPlayer = 0;
	private float rotationTime = 0.2f;
	private float yawTransform = 0;
	private float transformX = 0;
	private float transformY = 0;
	private ArrayList<Vector3f> turnPoint = new ArrayList<Vector3f>();
	private float yawTransformPerSecond;
	private int directionLastFrame = 0;
	private int numberPoints = 30;

	private Vector3f position = new Vector3f(400, 50, -200);
	private float pitch = 25;
	private float yaw = 0;
	private float roll;
	//private float speed = 0.5f;
	private boolean animate = false;
	private int speed = 1;
	
	private boolean changeDir = false;
	
	private float time = 0;
	public Camera() {
		//this.player = player;
	}

	public void setPlayerToTrack(Player player) {
		currentPlayerTracking = player;
	}

	public void render(int playerDirection) {
		
		time++;
		
		if (yawTransformPerSecond != yaw) {
<<<<<<< HEAD
			//yaw += yawTransformPerSecond * DisplayManager.getFrameTimeSeconds();
		}
		
		if (turnPoint.size() > 0) {
			currentPlayerTracking.pause = true;
=======
			yaw += yawTransformPerSecond * DisplayManager.getFrameTimeSeconds();
			if (yawTransformPerSecond == yaw) {
				//playerDirection++;
			}
		}
		
		if (turnPoint.size() > 0) {
			animate = true;
>>>>>>> 9c05c76ac92813e67fa53200f77ee8a35b281728
			if (time % speed == 0) {
				this.position = turnPoint.get(turnPoint.size() - 1);
				turnPoint.remove(turnPoint.size() - 1);
			}
<<<<<<< HEAD
			yaw += yawTransformPerSecond * DisplayManager.getFrameTimeSeconds();
		}
		
		
		
		else {
			currentPlayerTracking.pause = false;
=======
			//yaw += yawTransformPerSecond * DisplayManager.getFrameTimeSeconds();
		}
		
		else {
			animate = false;
>>>>>>> 9c05c76ac92813e67fa53200f77ee8a35b281728
			if (playerDirection == 0) {
				if (playerDirection != directionLastFrame) {
					for (int i = 0; i < numberPoints; i++) {

					}
				}
				else {
					pitch = 33;
					yawTransform = 0;
					this.position.x = currentPlayerTracking.getPlayerPiece().getPosition().x;
					this.position.y = currentPlayerTracking.getPlayerPiece().getPosition().y + distanceFromPlayer / 1.5f;
					this.position.z = currentPlayerTracking.getPlayerPiece().getPosition().z + distanceFromPlayer;
				}
			}
			else if (playerDirection == 1) {
				if (playerDirection != directionLastFrame) {
					for (int i = 0; i <= numberPoints; i++) {
						turnPoint.add(new Vector3f((float) ((currentPlayerTracking.getPlayerPiece().getPosition().x) - distanceFromPlayer * (Math.cos(Math.toRadians(90 / numberPoints) * i))), this.position.y,
								(float) ((currentPlayerTracking.getPlayerPiece().getPosition().z) + (distanceFromPlayer * (Math.sin(Math.toRadians(90 / numberPoints) * i))))));
						yawTransform = 90;
					}
				}
				else {
					pitch = 33;
					yawTransform = 90;
					this.position.x = currentPlayerTracking.getPlayerPiece().getPosition().x - distanceFromPlayer;
					this.position.y = currentPlayerTracking.getPlayerPiece().getPosition().y + distanceFromPlayer / 1.5f;
					this.position.z = currentPlayerTracking.getPlayerPiece().getPosition().z;
				}
			}
			else if (playerDirection == 2) {
				if (playerDirection != directionLastFrame) {
<<<<<<< HEAD
					for (int i = 0; i <= numberPoints; i++) {
					turnPoint.add(new Vector3f((float) ((currentPlayerTracking.getPlayerPiece().getPosition().x) + distanceFromPlayer * (Math.cos(Math.toRadians(90 / numberPoints) * i))), this.position.y,
							(float) ((currentPlayerTracking.getPlayerPiece().getPosition().z) - (distanceFromPlayer * (Math.sin(Math.toRadians(90 / numberPoints) * i))))));
					yawTransform = 90;
					}
=======
>>>>>>> 9c05c76ac92813e67fa53200f77ee8a35b281728
				}
				else {
					pitch = 33;
					yawTransform = -90;
					this.position.x = currentPlayerTracking.getPlayerPiece().getPosition().x;
					this.position.y = currentPlayerTracking.getPlayerPiece().getPosition().y + distanceFromPlayer / 1.5f;
					this.position.z = currentPlayerTracking.getPlayerPiece().getPosition().z - distanceFromPlayer;
				}
			}
			else if (playerDirection == 3) {
				if (playerDirection != directionLastFrame) {

				}
				else {
					pitch = 33;
					yawTransform = 270;
					this.position.x = currentPlayerTracking.getPlayerPiece().getPosition().x + distanceFromPlayer;
					this.position.y = currentPlayerTracking.getPlayerPiece().getPosition().y + distanceFromPlayer / 1.5f;
					this.position.z = currentPlayerTracking.getPlayerPiece().getPosition().z;
				}
			}
		}
<<<<<<< HEAD
		//yawTransformPerSecond = (float) ((yawTransform - yaw) / (float)((numberPoints * speed) / DisplayManager.getFpsCap()));
		//System.out.println(yawTransformPerSecond);
		//System.out.println((float)(numberPoints * speed) / 120);
		//System.out.println((yawTransform - yaw));
		yawTransformPerSecond = 240;
=======
		
		yawTransformPerSecond = ((float) (yawTransform - yaw) * 5f);
>>>>>>> 9c05c76ac92813e67fa53200f77ee8a35b281728
		directionLastFrame = playerDirection;
	}
	
	public boolean isAnimatingTurn() {
		return animate;
	}
	
	public void addPoints() {
		
	}

	public void move() {
		//System.out.println("Yaw: " + yaw);
		//System.out.println("Pitch: " + pitch);
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
