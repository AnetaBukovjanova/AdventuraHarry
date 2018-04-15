/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


/*******************************************************************************
 * Třída PrikazZahod implementuje pro hru příkaz zahod.
 * Tato třída je součástí jednoduché textové hry.
 *
 * Tato třída je součástí jednoduché textové hry.
 * @author    Aneta Bukovjanová
 * @version   1.00.000
 */
public class PrikazZahod implements IPrikaz
{
    private static final String NAZEV = "zahod";
    private HerniPlan plan;
    private Batoh batoh;

    /***************************************************************************
     *  Konstruktor třídy
     *  parametr batoh - batoh, který hráč používá
     *  plan - herní plán, ve kterém se hráč pohybuje
     */
    public PrikazZahod(HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
    }
    
    /**
     *  Provádí příkaz "zahod". Pokládá věc do daného prostoru. Věc musí být v batohu.
     *
     *@param parametry - jako  parametr obsahuje jméno věci,
     *                         která se má zahodit.
     *@return zpráva, kterou vypíše hra hráči
     */
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Takhle to nepůjde!";
        }
        Prostor kdeJsme = plan.getAktualniProstor();
        String nazevCoPolozit = parametry[0];
        Vec vecicka = batoh.vemVec(nazevCoPolozit);
        if (vecicka==null){
            return "Tak tuhle věc v batohu nemám.";
        }
        batoh.vyberVec(nazevCoPolozit);
        kdeJsme.vlozVec(vecicka);
        return "Věc "+nazevCoPolozit+" byla zahozena z batohu.";
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }
}
