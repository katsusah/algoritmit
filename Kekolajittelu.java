package algdemo2;

import java.util.Arrays;
import java.util.Random;

/**
 * Muodostetaan satunnaisia kokonaislukuja sisältävä taulukko. Muodostetaan taulukon alkioista keko.
 * Lajitellaan keon alkiot kekolajittelun (heapsort) avulla vähenevään suuruusjärjestykseen.
 * @author K.Ahonen
 * @version 7.4.2021
 */
public class Kekolajittelu {
    
    /**
     * Pääohjelma kekolajittelua varten.
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        
        // taulukon luominen
        int n = 12;         // taulukon alkioiden lkm
        int[] taulukko = new int[n+1];
        taulukko[0] = n;    // indeksissä 0 on alkioiden lkm
        for (int i=1; i < taulukko.length; i++) {
            taulukko[i] = new Random().nextInt(500 + 500) - 500;
        }
        System.out.println("Alkuperäinen taulukko:");
        System.out.println(Arrays.toString(taulukko));
        
        // keon muodostaminen
        teeKeko(taulukko);
        System.out.println("Keko:");
        System.out.println(Arrays.toString(taulukko));
        
        // keon lajittelu
        kekolajittelu(taulukko, n);
        System.out.println("Lajiteltu keko:");
        System.out.println(Arrays.toString(taulukko));
     }
    
    
    /**
     * Järjestää taulukon alkiot siten, että ne muodostavat kekorakenteen.
     * @param t Taulukko, josta muodostetaan keko.
     */
    public static void teeKeko(int[] t) {
        for (int i = t[0]/2; i >= 1; i--) {
            korjaaKeko(t, i);
        }
    }
    
    
    /**
     * Korjaa taulukon alkiot kekorakenteen mukaiseen järjestykseen.
     * @param t Taulukko, josta muodostetaan keko.
     * @param i Taulukon indeksi, jossa käsiteltävä alkio sijaitsee
     */
    private static void korjaaKeko(int[] t, int i) {        
        if (2*i > t[0]) return; // i:llä ei lapsia, ei tehdä mitään
        int j = 2*i;
        int alkio = t[i];
        while (j <= t[0]) {     // Siirretään alkiota kohti lehtisolmuja
            if ((j < t[0]) && (t[j] > t[j+1])) j = j+1;
            if (alkio <= t[j]) break; // lopetetaan silmukka
            t[j/2] = t[j];
            j = 2*j;
        }
        t[j/2] = alkio;
    }
    
    
    /**
     * Lajittelee keossa olevat alkiot vähenevään järjestykseen.
     * @param t Keko, jonka alkiot lajitellaan
     * @param n Lajiteltavien alkioiden lkm
     */
    private static void kekolajittelu(int[] t, int n) {
        for (int i = n; i > 1; i--) {
            int eka = t[1];
            t[1] = t[i];
            t[i] = eka;
            t[0]--;
        korjaaKeko(t, 1);
        }
    }
}