/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.AnetaBukovjanova.adventuraHarry.logika;

/*******************************************************************************
 * Třída PrikazOdemkni implementuje pro hru příkaz odemkni. Příkaz Odemkni odemkne pomocí předmětů zamčené místnosti.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Aneta Bukovjanová
 * @version   1.00.000
 */
public class PrikazOdemkni implements IPrikaz {
    private static final String NAZEV = "odemkni";
    private HerniPlan plan;
    private Batoh batoh;
    /***************************************************************************
     *  Konstruktor třídy
     *  parametr batoh - batoh, který hráč používá
     *  plan - herní plán, ve kterém se hráč pohybuje
     */
    public PrikazOdemkni(HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
    }

    /**
     * Tímto příkazem odemknu místnosti. Místnost musí být zamčená a musí 
     * mít východy
     *
     * @param   parametry   jako  parametr obsahuje jmeno mistnosti,
     *                      ktera ma byt odemcena
     */
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Zadejte jméno místnosti";
        }
        String prostor = parametry [0];
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(prostor);
        if (sousedniProstor == null) {
            return "Odsud se tu jít nedá";
        }
        else
        { 
            if (sousedniProstor.jeZamceno()) {
                if (sousedniProstor.getNazev().equals("mistnost4"))
                {
                    if (batoh.obsahujeVec("klic"))
                    {
                        sousedniProstor.odemknout(false);
                        batoh.vyberVec("klic");
                        return "Prostor "+prostor+" je odemčena. Pokračuj dál v hledání!";
                    }
                    else
                    {
                        return "Pro odemčení "+prostor+" potřebuješ klíč!";
                    }
                }
                return "Místnost není zamknuta!";
            }
        }
        return "Máš vůbec klíč? Běž ho najít jinak se nedostaneš dál!";   
    }

    /**
     * Metoda vraci nazev prikazu (slovo ktere pouziva hrac pro jeho vyvolani)
     * 
     * @return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }

}
