package GameEngine.ThreeDS;

public class Obj
{
    public int  numOfVerts = 0;
    public int  numOfFaces = 0;
    public int  numTexVertex = 0;
    public int  materialID = 0;
    public boolean hasTexture = false;
    public String strName = null;
    public int indices = 0;
    public Vec3 verts[] = null;
    public Vec3 normals[] = null;
    public Vec3 texVerts[] = null;
    public Face faces[] = null;
}