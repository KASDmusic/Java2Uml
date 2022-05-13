import java.io.File;

import model.Uml;

public class Main
{
    public static void main(String[] args)
    {
        // %% Commentaires en mermaidJS
        Uml uml = new Uml();

        for(String file : args)
            uml.addFile(new File(file));

        System.out.println(uml.toString());
    }
}