package eswordle;
import java.io.IOException;

/**
 *
 * @khaoulaikkene
 */
public class Eswordle {

    Joc j = new Joc();
    menus l = new menus();

    public void inici() throws IOException {
        l.presentació();
        j.Vopcions();
           }

    public static void main(String[] args) throws IOException {
        (new Eswordle()).inici();
    }
}
