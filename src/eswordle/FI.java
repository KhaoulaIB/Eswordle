/*
 * Clase que manipula fitxers d'entrada
 */
package eswordle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 *
 * @author mascport
 */
public class FI {

    private Paraula nom;
    private FileReader fic;
    private BufferedReader reader;

    public FI(Paraula n) {
        nom = n;
    }

    public void obrir() {
        try {
            fic = new FileReader(nom.toString());
            reader = new BufferedReader(fic);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public char[] llegirLinia() {
        char[] res = null;
        try {
            String fr = reader.readLine();
            if (fr != null) {
                res = fr.toCharArray();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public void tancar() {
        try {
            reader.close();
            fic.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fic.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
