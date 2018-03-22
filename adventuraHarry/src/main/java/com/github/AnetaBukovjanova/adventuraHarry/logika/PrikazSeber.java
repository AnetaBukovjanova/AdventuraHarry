/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.AnetaBukovjanova.adventuraHarry.logika;

/*******************************************************************************
 * Třída PrikazSeber implementuje pro hru příkaz seber.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Aneta Bukovjanová
 * @version   1.00.000
 */
public class PrikazSeber implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "seber";
    private HerniPlan plan;
    private Batoh batoh;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy
     *  parametr batoh - batoh, který hráč používá
     *  plan - herní plán, ve kterém se hráč pohybuje
     */
    public PrikazSeber(HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Provádí příkaz "seber". Sebere danou věc v místnosti. Pokud je v místnosti a nebo je 
     *                          ve věci, která je již prozkoumána.
     *
     *@param parametry - jako  parametr obsahuje jméno věci,
     *                         která se má sebrat.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0)
        {
            return "Musíš mi říct co mám sebrat.";
        }
        if (parametry.length == 2) 
        {
            return "Umím sebrat jen jednu věc.";
        }
        Prostor kdeJsme = plan.getAktualniProstor();
        String nazevCoVzit = parametry[0];
        Vec vecicka = kdeJsme.vyberVec(nazevCoVzit);
        if (vecicka==null || !vecicka.jePrenositelna())
        {
            return "Toto sebrat nemůžeš!";
        }
            
        if(batoh.pridejVec(vecicka) && vecicka.jePrenositelna()){
            return "Věc "+nazevCoVzit+" byla dána do batohu.";
        }
        else {
            return "Tohle už fakt neunesu!";
        }
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
    //== Soukromé metody (instancí i třídy) ========================================
}
