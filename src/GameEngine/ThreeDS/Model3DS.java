package GameEngine.ThreeDS;

import java.util.Vector;

public class Model3DS
{
    protected Loader3DS loader = new Loader3DS();
    protected Vector<Material> materials = new Vector<Material>();
    protected Vector<Obj> objects = new Vector<Obj>();

    // Constructor
    public Model3DS()
    {
    }

    // Load the model
    public boolean load(String file)
    {
        if (!loader.load(this, file))
            return false;

        return true;
    }

    // Add material
    public void addMaterial(Material mat)
    {
        materials.add(mat);
    }

    // Add an object
    public void addObject(Obj obj)
    {
        objects.add(obj);
    }

    // Get material
    public Material getMaterial(int index)
    {
        return materials.get(index);
    }

    // Get an object
    public Obj getObject(int index)
    {
        return objects.get(index);
    }

    // Get the number of objects
    public int getNumberOfObjects()
    {
        return objects.size();
    }

    // Get the number of materials
    public int getNumberOfMaterials()
    {
        return materials.size();
    }
}