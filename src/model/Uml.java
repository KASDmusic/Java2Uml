package model;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class Uml
{
    private ArrayList<Class<?>> ensClass = new ArrayList<>();

    public Uml() {}

    public ArrayList<Class<?>> getEnsClass() { return this.ensClass; }

    public void addFile(File file, URLClassLoader classLoader)
    {
        try
        {
            //if directory
            if(file.isDirectory())
            {
                for(File f : file.listFiles())
                    addFile(f, classLoader);
            }
            else
            {
                try
                {
                    //if class file
                    if(file.getName().split("\\.")[1].equals("class"))
                        addClass(file, classLoader);
                }
                catch(Exception e) { System.err.println(e); }
            }
        }
        catch(Exception e) { System.err.println(e); }
    }

    public void addClass(File file, URLClassLoader classLoader)
    {
        try
        {
            URL url = file.toURI().toURL();

            //tester toutes les possibilités de nom de package
            String fullClassName = url.toString().substring(url.toString().lastIndexOf("/")+1, url.toString().indexOf("."));
            String urlToTest = url.toString().replaceAll("file:/", "");
            urlToTest = urlToTest.substring(0, urlToTest.lastIndexOf("/"));
            System.out.println(urlToTest + " | " + fullClassName);
            
            try
            {
                this.ensClass.add(Class.forName(fullClassName, true, classLoader));
                System.out.println("\t\tClass imported\n");
            }
            catch(ClassNotFoundException cnfe)
            {
                for(int i = urlToTest.split("/").length-1 ; i>=0 ; i--)
                {  
                    fullClassName = urlToTest.split("/")[i] + "." + fullClassName;
                    System.out.println("\t\t" + fullClassName);
                    try
                    {
                        this.ensClass.add(Class.forName(fullClassName, true, classLoader));
                        System.out.println("\t\tClass imported\n");
                        break;
                    }
                    catch(ClassNotFoundException cnfe2) {}
                }
            }
        }
        catch(Exception e) { System.err.println(e); }   
    }

    @Override
    public String toString()
    {
        String res = "classDiagram\n";

        for(Class<?> c : this.ensClass)
        {
            //System.out.println(c.toString());
            for(String s : c.toString().split("."))
                System.out.println(s);

            res+="\t" + c.toString() + "\n";
        }
            

        return res;
    }
}
