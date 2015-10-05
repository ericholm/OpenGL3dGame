package GameEngine.models;

import org.lwjgl.util.vector.Vector3f;

public class RawModel {

	private int vaoID;
	private int vertexCount;
	private Vector3f size;

	public RawModel(int vaoID, int vertexCount) {
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}
	
	public void setSize(float width, float height, float depth) {
		size = new Vector3f(width, height, depth);
	}
	
	public Vector3f getSize() {
		return size;
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}

}
