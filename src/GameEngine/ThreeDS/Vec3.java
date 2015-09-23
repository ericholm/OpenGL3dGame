package GameEngine.ThreeDS;

public class Vec3
{
    public float x,y,z;

    public Vec3()
    {
    }

    public Vec3(float _x, float _y, float _z)
    {
        x = _x;
        y = _y;
        z = _z;
    }

    public Vec3(Vec3 v)
    {
        x = v.x;
        y = v.y;
        z = v.z;
    }
}