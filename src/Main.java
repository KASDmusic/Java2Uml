
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import model.Uml;

public class Main
{
    public static void main(String[] args)
    {
        // %% Commentaires en mermaidJS
        // https://mermaid-js.github.io/mermaid-live-editor/edit
        Uml uml = new Uml();

        for(String filePath : args)
        {
            try
            {
                File file = new File(filePath);
                URL url = file.toURI().toURL();
                URLClassLoader classLoader = new URLClassLoader(new URL[] { url });
                uml.addFile(file, classLoader);
            }
            catch(Exception e) {}
        }

        for(int i=0 ; i<5 ; i++)
            System.out.println("");
        
        System.out.println(uml.toString());
    }
}