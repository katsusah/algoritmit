package algdemo2;

/**
 * Ohjelma toteuttaa union-find-operaatiot joukolle n.
 * @author K.Ahonen
 * @version 21.4.2021
 */
public class Hedelmat2 {
    
    private Hedelma[] hedelmat =    {
                                    (new Hedelma(-1, "aprikoosi")), (new Hedelma(-1, "banaani")),
                                    (new Hedelma(-1, "kumkvatti")), (new Hedelma(-1, "luumu")),
                                    (new Hedelma(-1, "omena")),     (new Hedelma(-1, "persikka")),
                                    (new Hedelma(-1, "sitruuna")),  (new Hedelma(-1, "mandariini")),
                                    (new Hedelma(-1, "päärynä")),   (new Hedelma(-1, "pitaija"))
                                    };
    
    
    /**
     * Toteuttaa union-operaation yhdistämällä kaksi puuta.
     * @param a Juurisolmu a
     * @param b Juurisolmu b
     */
    public void union(int a, int b) {
        int juuri1 = hedelmat[a].getAvain();
        int juuri2 = hedelmat[b].getAvain();
        int k = juuri1 + juuri2;
        
        if (juuri1 <= juuri2) {
            hedelmat[a].setAvain(k);
            hedelmat[b].setAvain(a);
        } else {
            hedelmat[b].setAvain(k);
            hedelmat[a].setAvain(b);
        }
    }
    
    
    /**
     * Toteuttaa find-operaation.
     * @param indeksi Indeksi, josta hakupolun seuraaminen aloitetaan
     * @return Palauttaa juurisolmun indeksin
     */
    public int find(int indeksi) {
        
        int juuri = indeksi;
        
        // Juurisolmun etsintä:
        while (hedelmat[juuri].getAvain() > 0)
        {
          juuri = hedelmat[juuri].getAvain();
        }
        
        int k;
        int i = indeksi;
        
        // Hakupolun tiivistys:
        while (hedelmat[i].getAvain() > 0)
        {
          k = i;
          i = hedelmat[i].getAvain();
          hedelmat[k].setAvain(juuri);
        }
        
        return juuri;
    }
    
    
    /**
     * Tulostaa hedelmat-taulukon
     */
    public void tulosta() {
        for(int i=0; i<hedelmat.length; i++) {
            System.out.println(i + ": avain " + hedelmat[i].toString());
        }
    }
    
    
    /**
     * Hedelmä-olio, koostuu int-muotoisesta avainkentästä ja String-muotoisesta nimestä.
     */
    public class Hedelma {
        private int avain;
        private String nimi;
        
        
        /**
         * Muodostaja Hedelmälle
         * @param avain Avainkentän arvo
         * @param nimi Hedelmän nimi
         */
        public Hedelma(int avain, String nimi) {
            this.avain = avain;
            this.nimi = nimi;
        }
        
        
        /**
         * 
         */
        @Override
        public String toString() {
            return String.format(getAvain() + ", " + getHedelmanNimi());
        } 
        
        
        /**
         * @return Palauttaa Hedelmän nimen
         */
        public String getHedelmanNimi() {
            return this.nimi;
        }
        
        
        /**
         * @return Palauttaa avainkentän arvon
         */
        public int getAvain() {
            return this.avain;
        }
        
        
        /**
         * Asettaa avainkentälle annetun arvon.
         * @param avain Avaimen uusi arvo
         */
        public void setAvain(int avain) {
            this.avain = avain;
        }
    }
    
    
    /**
     * Testipääohjelma.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        
        Hedelmat2 hedelmaTaulukko = new Hedelmat2();
        hedelmaTaulukko.tulosta();
        System.out.println("******************************************");
        
        System.out.println("Testataan find(0): " + hedelmaTaulukko.find(0));
        System.out.println("Testataan find(5): " + hedelmaTaulukko.find(5));
        System.out.println("******************************************");
        
        hedelmaTaulukko.union(hedelmaTaulukko.find(0), hedelmaTaulukko.find(1));
        hedelmaTaulukko.union(hedelmaTaulukko.find(2), hedelmaTaulukko.find(3));
        hedelmaTaulukko.union(hedelmaTaulukko.find(4), hedelmaTaulukko.find(5));        
        hedelmaTaulukko.union(hedelmaTaulukko.find(6), hedelmaTaulukko.find(7));
        hedelmaTaulukko.union(hedelmaTaulukko.find(8), hedelmaTaulukko.find(9));
        hedelmaTaulukko.tulosta();
        System.out.println("******************************************");
        
        hedelmaTaulukko.union(hedelmaTaulukko.find(8), hedelmaTaulukko.find(0));
        hedelmaTaulukko.union(hedelmaTaulukko.find(6), hedelmaTaulukko.find(3));
        hedelmaTaulukko.tulosta();
        System.out.println("******************************************");
        
        System.out.println("Testataan find(0): " + hedelmaTaulukko.find(0));
        System.out.println("Testataan find(5): " + hedelmaTaulukko.find(5));
        System.out.println("******************************************");
        
        hedelmaTaulukko.union(hedelmaTaulukko.find(4), hedelmaTaulukko.find(6));
        hedelmaTaulukko.tulosta();
        System.out.println("******************************************");
        
        hedelmaTaulukko.union(hedelmaTaulukko.find(6), hedelmaTaulukko.find(8));
        hedelmaTaulukko.tulosta();
    }
}