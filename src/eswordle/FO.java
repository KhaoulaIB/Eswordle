
package eswordle;

import java.io.BufferedWriter;
import java.io.FileWriter;


public class FO {

    private Paraula nom;
    private FileWriter fic;
    private BufferedWriter writer;

    public FO(Paraula n) {
        nom = n;
    }

    public void obrir(boolean append) {
        try {
            fic = new FileWriter(nom.toString(), append);
            writer = new BufferedWriter(fic);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void GravarLinia(Paraula p) {
        try {
            writer.write(p.toString());
         //   writer.newLine();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void SaltLinia(){
        try {
            writer.newLine();
           } catch (Exception ex) {
            ex.printStackTrace();
        } 
        
    }

    public void tancar() {
        try {
            writer.close();
            fic.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fic.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
