package com.github.AnetaBukovjanova.adventuraHarry.logika;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Aneta Bukovjanová
 *@version    pro školní rok 2017/2018
 */
public class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Hra hra;
    private Batoh batoh;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    *  parametr batoh - batoh, který hráč používá
    */    
    public PrikazJdi(Hra hra, Batoh batoh) {
        this.plan = hra.getHerniPlan();
        this.hra = hra;
        this.batoh = batoh;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *  Dále určuje, kdy hráč vyhraje či prohraje.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);
        if (smer.equals("Hermiona") && batoh.obsahujeVec("lektvar")){
            hra.setKonecHry(true);
            return "Vyhrál jsi! Zachránil jsi Hermionu";
        }
        else if (smer.equals("Hermiona") && !batoh.obsahujeVec("lektvar")){
            hra.setKonecHry(true);
            return "Prohrál jsi! Hermiona zemřela.";
        }

        if (sousedniProstor == null) {
               
            return "Tam se odsud jít nedá!";
        }
        else 
        {
        if(sousedniProstor.getNazev().equals("mistnost2")){
                System.out.println("Jsi v místnosti s Voldemortem"); 
            hra.setKonecHry(true);
            return "Voldemort tě zabil zaklínadlem Avada Kedavra";
        }
        if (sousedniProstor.jeZamceno()) {
            return "dveře do místnosti "+sousedniProstor.getNazev()
            +" jsou zamčené";
        }
        plan.setAktualniProstor(sousedniProstor);
        return sousedniProstor.dlouhyPopis();
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
