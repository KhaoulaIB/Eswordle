/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eswordle;

import com.diogonunes.CC;
import java.time.LocalDate;

/**
 * @AmalKhaoula
 */

public class menus {


    public void menu2() {
        System.out.println("En quina llengua vols jugar?");
        System.out.println("1. Castellà");
        System.out.println("2. Català");
        System.out.println("3. Anglès");
        System.out.println("");
    }

    public void presentació() {
        CC.imprln("                   WORDLE                     ", CC.TBlanc, CC.FVerd);
        CC.imprln("##############################################", CC.TVermell, "");
        System.out.println("");
        System.out.println("   MENÚ PRINCIPAL DEL JOC: ");
        System.out.println("   1. Jugar");
        System.out.println("   2. Estadístiques generals");
        System.out.println("   3. Estadístiques específiques");
        System.out.println("   4. Opcions per defecte ");
        System.out.println("   s. Sortir ");
        System.out.println("");
        CC.imprln("##############################################", CC.TVermell, "");
        System.out.print("Tria una opció: ");


    }
    public void Estadístiquesgenerals() {
        Joc j = new Joc();
        j.Imprimir();
    }

    public void Rondes(){
        System.out.print("Quantes partides necessites per jugar? [3-6]: ");
    }
    public void Sortir() {
        System.out.println("Has finalitzar el joc ");
        System.out.println("Gràcies per jugar! ");
        try {
            Thread.sleep(1212);
            System.out.println("Fins aviat!!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void EstadistiquesEsp(){
        System.out.println("Quin jugador vols consultar les seves estadístiques?");
        Joc j = new Joc();
        j.ImprimirEstad();
    }

    public void menujugada(Integer llengua, Paraula jugador, int rondes, int tamany) {
        LocalDate temps = LocalDate.now();
        Paraula idioma = null;
        switch(llengua){
            case 1:
                idioma = new Paraula("Castellà".toCharArray());
                break;
            case 2:
                idioma = new Paraula("Català".toCharArray());
                break;
            case 3:
                idioma = new Paraula("Anglès".toCharArray());
                break;
        }

        System.out.println("****************************** ");
        System.out.println(temps);
        System.out.println("Jugador:" + jugador);
        System.out.println("Llengua:" + idioma);
       
        if ((rondes != 6) || (tamany !=5)) {
            System.out.println("Intents:" + rondes);
            System.out.println(("Longitud de la paraula: " + tamany));
           

        }
         System.out.println("****************************** ");

    }
    public void NumLletres() {
        System.out.print("Tria la longitud de la paraula [3-4-6]: ");
    }

    public void Opcionsperdefecte(){
        CC.impr("1", CC.TBlanc, CC.FBlau);
        System.out.println(" .Modificar el nombre de rondes");
        System.out.println();
        CC.impr("2", CC.TBlanc, CC.FBlau);
        System.out.println(" .Modificar el  nombre de lletres");
        System.out.println();
        CC.impr("3", CC.TBlanc, CC.FBlau);
        System.out.println(" Tornar al menú principal");
        System.out.println(" ");
        System.out.print("Tria una opció:");

    }
    
    
}
