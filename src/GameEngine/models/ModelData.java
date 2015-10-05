package GameEngine.models;

import org.lwjgl.util.vector.Vector3f;

public class ModelData {

	private float[] vertices;
	private float[] textureCoords;
	private float[] normals;
	private int[] indices;
	private float furthestPoint;
	private Vector3f size = new Vector3f();

	public ModelData(float[] vertices, float[] textureCoords, float[] normals, int[] indices, float furthestPoint) {
		this.vertices = vertices;
		this.textureCoords = textureCoords;
		this.normals = normals;
		this.indices = indices;
		this.furthestPoint = furthestPoint;
	}
	
	public void setSize(Vector3f size) {
		this.size = size;
	}
	
	public Vector3f getSize() {
		return size;
	}

	public float[] getVertices() {
		return vertices;
	}

	public float[] getTextureCoords() {
		return textureCoords;
	}

	public float[] getNormals() {
		return normals;
	}

	public int[] getIndices() {
		return indices;
	}

	public float getFurthestPoint() {
		return furthestPoint;
	}

}
