package model;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;

public class Uml
{
    private HashSet<Class<?>> ensClass = new HashSet<>();

    public Uml() {}

    public HashSet<Class<?>> getEnsClass() { return this.ensClass; }

    public void addFile(File file)
    {
        try
        {
            URL url = file.toURI().toURL();
            System.out.println(url);
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { url });
            this.ensClass.add(Class.forName("Controleur", true, classLoader));
        }
        catch(Exception e) { System.err.println(e); }
    }

    @Override
    public String toString()
    {
        String res = "classDiagram\n";

        for(Class<?> c : this.ensClass)
            res+="\t" + c.toString() + "\n";

        return res;
    }
}
