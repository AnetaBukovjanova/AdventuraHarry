/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * PrikazBatoh je třída implementující příkat batoh. Příkaz batoh vypíše seznam věcí, které se nachází v batohu.
 * Tato třída je součástí jednoduché textové hry.
 * 
 *
 * @author    Aneta Bukovjanová
 * @version   1.00.000
 */
public class PrikazBatoh implements IPrikaz
{
    private static final String NAZEV = "batoh";
    private Batoh batoh;
    
    
    /**
    *  Konstruktor třídy
    *  
    *  @param batoh - hráčuv batoh s věcmi
    */    
    public PrikazBatoh(Batoh batoh) {
        this.batoh = batoh;
    }

    /**
     *  Provede příkaz batoh nezávisle na parametru.
     *  
     *  @return seznam věcí v batohu
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
       return batoh.seznamVeci();
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
