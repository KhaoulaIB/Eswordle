/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eswordle;

import com.diogonunes.CC;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

/**
 *
 * @khaoulaikkene
 */
public class Joc {

    Integer rondes = 6;
    Integer Longitudpalabra = 5;
    private char[] lin;
    private int pos;
    menus l = new menus();
    LT tec = new LT();
    Paraula p = new Paraula();
    Paraula[] pa = new Paraula[6];
    char[] mot = new char[5];
    private Integer llengua;
    private final Character[] opcions = {'1', '2', '3', '4', 's'};

    
    // ---metodes de veirificacions---
    public void Vopcions() throws IOException {// verifica les opcions del menú
        Character choix;
        choix = tec.llegirCaracter(); // choix (en frances)= opcio
        while (!p.existeLLetra(opcions, choix)) {
            System.out.println("Opció errada. Torna a intentar-ho");
            choix = tec.llegirCaracter();
        }
        switch (choix) {
            case '1':
                Jugar();
                break;
            case '2':
                l.Estadístiquesgenerals();
                break;
            case '3':
                l.EstadistiquesEsp();
                break;
            case '4':
                OpcionsDefect();
                break;
            case 's':
                l.Sortir();
                break;
            default:
                break;
        }

    }
    public Integer VerifLlengua() throws IOException { // verifica l'entrada de l'usuari quan tria la llengua del joc
        Integer lengua;
        lengua = tec.llegirSencer();
        while ((lengua == null) || (lengua < 1) || (lengua > 3)) {
            System.out.println("Opció errada. Torna a intentar-ho");
            lengua = tec.llegirSencer();
        }
        return lengua;
    }

    public void VParaula() {  //verifica que la paraula es de la longitud adequada 
        System.out.print("Paraula?");
        mot = tec.llegirLiniaC();
        if (mot.length != Longitudpalabra) {
            System.out.println("La paraula ha de ser de " + Longitudpalabra + " lletres !!!");

            mot = tec.llegirLiniaC();
        }
    }

    public Paraula Vjugador() {// verifica el nom del jugador que no sigui null
        char jugador[] = tec.llegirLiniaC();
        Paraula temporal = new Paraula(jugador);
        while (temporal.buida()) {
            System.out.println("Error!");
            jugador = tec.llegirLiniaC();
            temporal = new Paraula(jugador);
        }
        return temporal;
    }

    public Integer NumRondesLong() {
        Integer num = tec.llegirSencer();
        while ((num == null) || (num > 6) || (num < 3)) {
            System.out.print("Verifica la teva opció!!");
            num = tec.llegirSencer();
        }
        return num;
    }

    
    // ---metodes enfocats en el joc---

    public void OpcionsDefect() throws IOException {
        boolean acabat = false;
        while (!acabat) {
            l.Opcionsperdefecte();
            Integer opcio;
            opcio = VerifLlengua();
            switch (opcio) {
                case 1:
                    l.Rondes();
                    rondes = NumRondesLong();
                    acabat = false;
                    break;
                case 2:
                    l.NumLletres();
                    Longitudpalabra = NumRondesLong();
                    acabat = false;
                    break;
                case 3:
                    l.presentació();
                    Vopcions();
                    acabat = true;
                    break;
                default:
                    break;

            }

        }
    }

    public void Jugar() throws IOException {
        l.menu2();
        llengua = VerifLlengua();
        DemanarParaula();
    }

    public void Espera(int n) {
        try {
            Thread.sleep(n);
            l.presentació();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guardaparaula(int index) { // crea un array de paraula on guarda els inputs de l'usuari
        pa[index] = new Paraula(mot);

    }

    public Paraula[] GuardaParaula(int index) { // guarda els inputs de l'usuari en pa[] i els imprimeix
        pa[index] = new Paraula(mot);
        return pa;
    }

    public void ImprimirParaules(int index, Paraula p) { // imprimeix la paraula recent que ha introduit l'usuari i les anteriors també ìntatdes
        for (int i = 0; i <= index; i++) {
            Colors(p, pa[i], Longitudpalabra);
            System.out.println();
        }

    }

    public void Imprimirlinia(int tamany) {
        char[] Linia = new char[tamany];
        for (int i = 0; i < Linia.length; i++) {
            Linia[i] = '-';
        }
        System.out.println(Linia);
    }

    public void Printespai(int num) { // serveix per imprimir linia[] num vegades
        for (int a = 0; a < num; a++) {
            Imprimirlinia(Longitudpalabra);
        }
    }

    public void DemanarParaula() throws IOException {
        int number;
        boolean acabat = false;
        while (!acabat) {
            System.out.println("Nom del jugador?");
            Paraula Jugador = Vjugador();
            Paraula[] lengdic = Llenguadiccionari(llengua, Longitudpalabra);
            number = NumLinies(lengdic[1]);
            p = TriarParaula(number, lengdic[1]);
            System.out.println("");
            l.menujugada(llengua, Jugador, rondes, Longitudpalabra);
            Printespai(rondes);
            long Començament = System.currentTimeMillis();
            for (int i = 0, j = rondes - 1; i < rondes; i++, j--) {
                VParaula();
                Paraula entrada = new Paraula(mot);
                while (ExisteParaula(entrada, lengdic[0])) {
                    l.menujugada(llengua, Jugador, rondes, Longitudpalabra);
                    guardaparaula(i);
                    ImprimirParaules(i, p);
                    Printespai(j); // pinta les lines dels intents
                    break;
                }
                if (!(ExisteParaula(entrada, lengdic[0]))) {
                    System.out.println("Paraula invalida!");
                    i--;// per no guardar les paraules nul·les en pa[]
                    j++;// per a qué no canvii el numero de linies que havia d'imprimir
                }
                if (entrada.igual(p)) {
                    fitxerestadistiques(Jugador, p, GuardaParaula(i), rondes);//guarda les estadistiques del jugador 
                    long TempsEmprat = ((System.currentTimeMillis() - Començament) / (1000));
                    if (TempsEmprat < 60) {
                        System.out.println("Has esdevinat la paraula en " + (TempsEmprat) + " segons");

                    } else {
                        System.out.println("Has esdevinat la paraula en " + (TempsEmprat / 60)+ " minuts");
                    }
                    System.out.println("Enhorabona !!");
                    acabat = true;
                    break;
                } else if ((i == rondes - 1) && !(entrada.igual(p))) {// en cas d'haver perdut mostra la paraula que havia d'esdevinar
                    fitxerestadistiques(Jugador, p, GuardaParaula(i), rondes);
                    System.out.print("la paraula era:" + p);
                    System.out.println();
                    acabat = true;
                    break;
                }
            }
            break;
        }
        Espera(2000);
        Vopcions();

    }

    public Character[] ParaulatoChar(Paraula k) { // converteix una paraula en Character[]
        Character[] ch;
        ch = new Character[5];
        if (k != null) {   // copia cada carcater a l'array ch
            for (int i = 0; i < 5; i++) {
                ch[i] = k.get(i);
            }
        }
        return ch;
    }

    public void Colors(Paraula objectiu, Paraula pa, int tamany) { // objectiu: paraula correcta, pa: paraula de l'usuari     
        Character[] pa1 = ParaulatoChar(pa);
        Character[] p2 = ParaulatoChar(objectiu);
        int[] ColorLletra = new int[tamany]; // 2 és Verd, 1 és Groc, 0 és gris
        for (int i = 0; i < tamany; i++) {
            if (pa1[i] == p2[i]) {//mateixa lletra & mateixa posicio
                p2[i] = '-';
                ColorLletra[i] = 2;
            }
        }

        for (int j = 0; j < tamany; j++) {
            for (int k = 0; k < tamany; k++) {
                if ((pa1[j] == p2[k]) && (ColorLletra[j] != 2)) { // mateixa lletra & posicio diferent tenguint en compte que no és de color verd
                    ColorLletra[j] = 1;
                    p2[k] = '-';
                }
            }
        }

        for (int k = 0; k < tamany; k++) {
            switch (ColorLletra[k]) {
                case 0:
                    CC.impr("" + convertirToMaj(pa1[k]), CC.TBlanc, CC.FBlanc);
                    break;
                case 1:
                    CC.impr("" + convertirToMaj(pa1[k]), CC.TBlanc, CC.FGroc);
                    break;
                case 2:
                    CC.impr("" + convertirToMaj(pa1[k]), CC.TBlanc, CC.FVerd);
                    break;
                default:
                    break;
            }
        }

    }

    public int NumLinies(Paraula fitxer) throws FileNotFoundException, IOException {//retorna el numero de linies de un fitxer que el passem per parametre
        int number = 0;
        Paraula[] fichero = Llenguadiccionari(llengua, Longitudpalabra);
        Paraula Linia = fichero[1];
        FI fic = new FI(Linia);
        fic.obrir();
        do {
            lin = fic.llegirLinia();
            if (lin != null) {
                pos = 0;
                do {
                    Linia = llegirParaula();
                    if (!(Linia.buida())) {
                        number++;
                    }
                } while (!(Linia.buida()));
            }
        } while (lin != null);
        fic.tancar();
        return number;
    }

    public Paraula TriarParaula(int number, Paraula fitxer) throws IOException {
        Paraula ka = null;
        Random ran = new Random();
        number = NumLinies(fitxer);
        int numeroaleatorio = ran.nextInt(number)+1;
        Paraula contingut = fitxer;
        FI fic = new FI(contingut);
        int i = 0;
        fic.obrir();
        do {
            lin = fic.llegirLinia();
            if (lin != null) {
                pos = 0;
                do {
                    contingut = llegirParaula();
                    if (!(contingut.buida())) {
                        i++;
                        while (i == numeroaleatorio) {
                            lin = fic.llegirLinia();
                            ka = new Paraula(lin);
                            break;
                        }
                    }
                } while (!(contingut.buida()));
            }
        } while (lin != null);
        fic.tancar();
        return ka;

    }

    private boolean ExisteParaula(Paraula entrada, Paraula fitxer) {//cerca en el diccionari si existeix la paraula que ha introduit el jugador i s'ha pasat per parametre
        Paraula paraula = fitxer;
        FI fic = new FI(paraula);
        fic.obrir();
        do {
            lin = fic.llegirLinia();
            if (lin != null) {
                pos = 0;
                do {
                    paraula = llegirParaula();
                    if (paraula.igual(entrada)) {
                        return true;
                    }
                } while (!(paraula.buida()));
            }
        } while (lin != null);
        fic.tancar();
        return false;
    }

    public Paraula llegirParaula() {
        Paraula aux = new Paraula();
        botarNoLletra();
        while ((pos < lin.length) && (esLletra(lin[pos]))) {
            aux.add(lin[pos++]);
        }
        return aux;
    }

    public void botarNoLletra() {
        while ((pos < lin.length) && (!esLletra(lin[pos]))) {
            pos++;
        }
    }

    private boolean esLletra(char c) { // s'ha afegit la ñ
        return ((c >= 'a') && (c <= 'z') || (c >= 'A') && (c <= 'Z') || (c == 'ñ'));
    }

    public Paraula[] Llenguadiccionari(Integer llengua, int tamany) throws IOException {//selecciona el diccionari i la llengua adequats 
        Paraula[] LlenguaiDicio = new Paraula[2];
            switch (llengua) {
                case 1:
                    LlenguaiDicio[0] = new Paraula("FicherosLeng/wordle_esp_diccionari.txt".toCharArray());
                    LlenguaiDicio[1] = new Paraula("FicherosLeng/wordle_esp_solucions.txt".toCharArray());
                    if (tamany != 5) {
                        LlenguaiDicio[1] = new Paraula(("FicherosLeng/" + tamany + "_Esp.txt").toCharArray());
                    }
                    break;
                case 2:
                    LlenguaiDicio[0] = new Paraula("FicherosLeng/wordle_cat_diccionari.txt".toCharArray());
                    LlenguaiDicio[1] = new Paraula("FicherosLeng/wordle_cat_solucions.txt".toCharArray());
                    if (tamany != 5) {
                        LlenguaiDicio[1] = new Paraula(("FicherosLeng/" + tamany + "_Cat.txt").toCharArray());
                    }
                    break;
                case 3:
                    LlenguaiDicio[0] = new Paraula("FicherosLeng/wordle_ang_diccionari.txt".toCharArray());
                    LlenguaiDicio[1] = new Paraula("FicherosLeng/wordle_ang_solucions.txt".toCharArray());
                    if (tamany != 5) {
                        LlenguaiDicio[1] = new Paraula(("FicherosLeng/" + tamany + "_Ang.txt").toCharArray());
                    }
                    break;
                default:
                    break;
            }
        
        return LlenguaiDicio;

    }

    public Character convertirToMaj(Character lletra) {
        if ((lletra >= 'a') && (lletra <= 'z')) {
            lletra = (char) (lletra + ('A' - 'a'));
        } else if (lletra == 'ñ') {
            lletra = 'Ñ';

        }
        return lletra;
    }

    //---metodes en qué s'empren fitxers per gravar o treure informació----
    
    public void fitxerestadistiques(Paraula jugador, Paraula p, Paraula[] at, int rondes) throws IOException {// grava les estadístiques dels jugadors
        Date data = new Date();
        Paraula contingut = new Paraula((data + " " + "[" + rondes + "]" + "@" + jugador + "#" + p).toCharArray());
        FO fic = new FO(new Paraula(("estadistiques.txt").toCharArray()));
        fic.obrir(true);
        fic.GravarLinia(contingut);
        String endLine = System.getProperty("line.separator");
        Paraula[] patemporal = new Paraula[rondes];
        for (int i = 0; i < patemporal.length; i++) { // array de la longitud adecuada de la paraula
            patemporal[i] = at[i];
        }
        at = patemporal;
        for (int i = 0; i < at.length; i++) {
            if (at[i] == null) {//en cas que ha esdevenat la paraula
                at[i] = new Paraula("".toCharArray());
            }
            fic.GravarLinia(new Paraula(("#" + at[i]).toCharArray()));
        }
        fic.GravarLinia(new Paraula(endLine.toCharArray()));
        fic.SaltLinia();
        fic.tancar();
    }

    public void Imprimir() {//imprimeix les estadistiques de tots els jugadors
        Paraula pa = new Paraula("estadistiques.txt".toCharArray());
        FI fic = new FI(pa);
        fic.obrir();
        do {
            lin = fic.llegirLinia();
            if (lin != null) {
                pos = 0;
                do {
                    pa = llegirParaula();
                    if (!(pa.buida())) {
                        System.out.println(lin);
                        break;
                    }

                } while (!(pa.buida()));
            }
        } while (lin != null);
        fic.tancar();
    }
    
   
    public void ImprimirEstad() {//serveix per imprimir les estadistiques d'un jugador determinat
        Paraula jugador = Vjugador();
        int valors[] = DatesJugador(jugador);
        int partides = valors[2];
        int repeticions = valors[1]; // nomre de "#"
        int guanys = valors[0];
        int intents = repeticions - partides; // per eliminar la primera # de cada partida
        int perdides = partides - guanys;
        if (partides != 0) {
            System.out.println(jugador + " ha jugat " + partides + " partides.");
            System.out.println("Ha guanyat " + guanys + " " + "partides i n'ha perdut" + " " + perdides + ".");
            System.out.println("La mitjana d'intents de " + jugador + " és " + (intents / partides) + ".");
        } else {
            System.out.println("Aquesta persona no ha jugat al Wordle.");
        }
    }

    public int[] DatesJugador(Paraula jugador) {
        Paraula paraula = new Paraula("estadistiques.txt".toCharArray());
        FI fic = new FI(paraula);
        int repeticions = 0;
        int partides = 0;
        int guanys = 0;
        int valors[] = new int[3];
        Paraula contenido = null;
        fic.obrir();
        do {
            lin = fic.llegirLinia();
            if (lin != null) {
                pos = 0;
                do {
                    paraula = llegirParaula();
                    if (!(paraula.buida())) {
                        if (paraula.igual(jugador)) {
                            partides++;
                            valors[2] = partides;
                            paraula = llegirParaula();
                            for (int i = 0; i < lin.length; i++) {
                                if (lin[i] == '#') {
                                    repeticions++;// els intents
                                    valors[1] = repeticions;
                                    contenido = llegirParaula();
                                    if (contenido.igual(paraula)){ // si la paraula objectiu es repeteix en la mateixa linia que és equivalent a guanyar
                                        guanys++;
                                    }
                                    valors[0] = guanys;
                                }
                            }
                        }
                    }

                } while (!(paraula.buida()));
            }
        } while (lin != null);
        fic.tancar();
        return valors;
    }

    public void GenerarFitxer() {
        Paraula fichero = new Paraula(("FicherosLeng/Wordle_esp_diccionari.txt").toCharArray());
        FI fic = new FI(fichero);
        FO fa = new FO(new Paraula(("FicherosLeng/algo.txt").toCharArray()));
        fa.obrir(false);
        fic.obrir();
        do {
            lin = fic.llegirLinia();
            if (lin != null) {
                pos = 0;
                do {
                    fichero = llegirParaula();
                    if (fichero.toString().toCharArray().length == 4) {
                        System.out.println(fichero);
                        fa.GravarLinia(fichero);
                        fa.SaltLinia();
                        break;
                    }
                } while (!(fichero.buida()));
            }
        } while (lin != null);
        fic.tancar();
        fa.tancar();

    }

}
