package com.github.AnetaBukovjanova.adventuraHarry.logika;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Aneta Bukovjanová, Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2017/2018
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private Batoh batoh;

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan(this);
        batoh = new Batoh();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(this,batoh));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazZahod(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazMluv(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazVytvor(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazBatoh(batoh));
        platnePrikazy.vlozPrikaz(new PrikazOdemkni(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Vítejte ve hře!\n" +
        "Toto je příběh o Hermioně a Harrym Potterovi.\n" +
        "Vašim cílem je zachránit Hermionu ze spárů smrtijedů\n" +
        "Napište 'nápověda', pokud si nevíte rady, jak hrát dál.\n" +
        "\n" +
        herniPlan.getAktualniProstor().dlouhyPopis();
    }

    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Moc děkuji, že jsi si zahrál. Měj se";
    }

    /** 
     * Vrací true, pokud hra skončila.
     */
    public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
    public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
            parametry[i]= slova[i+1];   
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
        }
        else {
                textKVypsani="Nevím co tím myslíš? Tento příkaz neznám.";
            }
            return textKVypsani;

        }
    

    /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }

    /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
    public HerniPlan getHerniPlan(){
        return herniPlan;
    }
    
    /**
     * Metoda vrací odkaz na batoh
     */ 
    
    public Batoh getBatoh() {
    	return batoh;
    }
    
    /**
     *  Metoda vrací odkaz na platné příkazy.
     */ 
    
    public SeznamPrikazu getPlatnePrikazy() {
    	return platnePrikazy;
    }

}

