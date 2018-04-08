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
public class PrikazVytvor implements IPrikaz
{
    private static final String NAZEV = "vytvor";
    private HerniPlan plan;
    private Batoh batoh;

    /***************************************************************************
     *  Konstruktor třídy
    *  
    *  @param batoh - hráčuv batoh s věcmi
    *  @param plan - herní plán, ve kterém se hráč pohybuje
     */
    public PrikazVytvor(HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
    }

     /**
    *   Hráč se musí nacházet v konkrétním prostoru a držet v batohu konkrátní věci proto,
    *   aby mohl vytvořit lektvar. Lektvar je věc, díky které hráč vyhraje.
    *   
    *   @return textová odpověď hráčovi
    */ 
    @Override
    public String provedPrikaz(String...parametry){
        Prostor aktualniProstor = plan.getAktualniProstor();
        if(aktualniProstor.getNazev().equals("mistnost5"))
        {
            if (batoh.obsahujeVec("bylina") && batoh.obsahujeVec("bylina2") && batoh.obsahujeVec("bylina3") && batoh.obsahujeVec("bylina4"))
            {
                
                batoh.smazVec("bylina");
                batoh.smazVec("bylina2");
                batoh.smazVec("bylina3");
                batoh.smazVec("bylina4");    
                Vec lektvar = new Vec("lektvar",true, "lektvar.png");
                batoh.pridejVec(lektvar);
                return "Vytvořil jsi lektvar. Jdi zachránit Hermionu.";
            }
            else{
                return "Nemáš suroviny na výrobu lektvaru!";
            }
        }
        else{
            return "Jsi ve správné místnosti, kde máš vytvořit lektvar?";
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
}
