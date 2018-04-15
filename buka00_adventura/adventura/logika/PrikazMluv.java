/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Třída PrikazMluv implementuje pro hru příkaz mluv.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Aneta Bukovjanová
 * @version   0.00.000
 */
public class PrikazMluv implements IPrikaz {
    private static final String NAZEV = "mluv";
    private HerniPlan plan;
    
    /**
     *  Konstruktor třídy
     *  parametr 
     *  plan - herní plán, ve kterém se hráč pohybuje
     * 
     */    
    public PrikazMluv(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "mluv". Zkouší mluvit s osobou v daném prostoru. Osoba musí být v prostoru.
     *
     *@param parametry - jako  parametr obsahuje jméno osoby,
     *                         s kterou se má mluvit.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length != 1)
        {
            // pokud chybí druhé slovo (osoba)
            return "A s kým mám mluvit?";
        }   
        Prostor kdeJsme = plan.getAktualniProstor();
        String nazevSKymMluvit = parametry[0];
        Postava postava = kdeJsme.vyberPostava(nazevSKymMluvit);
        if (postava==null)
        {
            return "Tak tahle osoba tu není.";
        }
        return postava.rekniProslov();
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