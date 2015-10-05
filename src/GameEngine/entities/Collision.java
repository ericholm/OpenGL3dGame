package GameEngine.entities;

import org.lwjgl.util.vector.Vector3f;

public class Collision {
	
	public static boolean checkCollisionAxisAllignedCube(Vector3f pos1, Vector3f size1, Vector3f pos2, Vector3f size2) {
		if (pos1.x > pos2.x && pos1.x < pos2.x + size2.x) {
			if (pos1.y > pos2.y && pos1.y < pos2.y + size2.y) {
				if (pos1.z > pos2.z && pos1.z < pos2.z + size2.z) {
					return true;
				}
			}
		}
		return false;
	}

}
