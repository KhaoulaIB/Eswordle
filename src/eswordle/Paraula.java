/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eswordle;

public final class Paraula {

    LT tec = new LT();
    private char let[];
    private int tam;
    private final int TI = 20;
    private final int AF = 5;

    private void inicialitzar() {
        tam = 0;
        let = new char[TI];
    }

    public boolean buida() {
        return (tam == 0);
    }

    public Paraula() {
        inicialitzar();
    }

    public Paraula(char[] p) {
        inicialitzar();
        for (int i = 0; i < p.length; i++) {
            add(p[i]);
        }
    }

    public void add(char c) {
        if (tam == let.length) { // no hi cab
            char[] aux = new char[tam + AF];
            for (int i = 0; i < tam; i++) {
                aux[i] = let[i];
            }
            let = aux;
        }
        let[tam++] = c;
    }

    public char get(int i) {
        return let[i];
    }
   

    public int getTam() {
        return tam;
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < tam; i++) {
            res = res + let[i];
        }
        return res;
    }

    public boolean igual(Paraula p) {
        boolean res = true;
        if ((tam != p.tam)) {
            res = false;
        } else {
            for (int i = 0; (i < tam) && res; i++) {
                if (let[i] != p.let[i]) {
                    res = false;
                }
            }
        }
        return res;
    }


    public boolean existeLLetra(Character[] pa1, Character letra) {
        for (int i = 0; i < pa1.length; i++) {
            if ((pa1[i] == letra)) {
              return true;
            }
            
    } 
        return false;
    }

    
}
