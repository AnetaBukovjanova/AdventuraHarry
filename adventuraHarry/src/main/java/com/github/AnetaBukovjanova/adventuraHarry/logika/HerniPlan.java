package com.github.AnetaBukovjanova.adventuraHarry.logika;
import java.util.*;

import java.util.Observable;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *  Dále vytváří věci a postavy.
 *
 *@author    Aneta Bukovjanová
 *@version   pro zimní semestr 2017/2018
 */
public class HerniPlan extends Observable 
{

    private Prostor aktualniProstor;
    private Prostor viteznyProstor;
    private Batoh batoh;
   

    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví chodbu.
     */
    public HerniPlan() {
        zalozProstoryHry();
        batoh = new Batoh();
    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví chodba.
     *  Vytváří také věci a postavy a poté je vkládá do místností.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor chodba = new Prostor("chodba","Nacházíš se v chodbě, kde máš před sebou dvoje dveře. V jedné místnosti se nachází smrt a v druhé místnosti nalezneš věci, které potřebuješ k dokončení hry.",false);
        Prostor mistnost1 = new Prostor("mistnost1", "Zde se nachází spousta věcí. Seber ty, které ti budou k užitku", false);
        Prostor mistnost2 = new Prostor("mistnost2","Zde se nachází smrt. Voldemort tě právě zabil zaklínadlem Avada Kedavra.\n", false);
        Prostor mistnost3 = new Prostor("mistnost3","Nacházíte se v místnosti,kde jsou důležité věci. Svažte jestli je potřebujete.", false); 
        Prostor mistnost4 = new Prostor("mistnost4","Jste v místnosti Snapa. Je tu něco, co Vám pomůže vyhrát", true);
        Prostor mistnost5 = new Prostor("mistnost5","Vlezli jste do místnosti s Lenkou. Pomůže Vám vytvořit lektvar na záchranu", false);
        Prostor hermiona = new Prostor("Hermiona","Nalezli jste Hermionu. Pokud nemáte lektvar. Prohrál jste.", false);

        // přiřazují se průchody mezi prostory (sousedící prostory)

        chodba.setVychod(mistnost1);
        chodba.setVychod(mistnost2);
        mistnost1.setVychod(chodba);
        mistnost1.setVychod(mistnost3);
        mistnost3.setVychod(mistnost4);
        mistnost4.setVychod(mistnost5);
        mistnost5.setVychod(hermiona);
        
        //vkládání postav do místnosti

        mistnost5.setPostava(new Postava("Lenka", "[Mumlá] Hermionu zachráníš jen s lektvarem. Použij všechny byliny co máš"));
        
        //vytváření věcí, které jsou přenositelné
    
        Vec hulka = new Vec("hulka", true, "hulka.png");
        Vec bylina = new Vec ("bylina", true, "bylina.png");
        Vec bylina2 = new Vec ("bylina2", true, "bylina2.png");
        Vec bylina3 = new Vec ("bylina3", true, "bylina3.png");
        Vec bylina4 = new Vec ("bylina4", true, "bylina4.png");
        Vec klic = new Vec("klic", true, "klic.ico");
        Vec mapa = new Vec("mapa", true, "mapa.png");
        Vec baterka = new Vec ("baterka", true, "baterka.png");
            
        //vytváření věcí, které nejsou přenositelné
        
        Vec kamen_mudrcu = new Vec("kamen_mudrcu", false, "kamen_mudrcu.png");
        Vec koste = new Vec ("koste", false, "koste.png");
        Vec neviditelny_habit = new Vec ("neviditelny_habit", false, "neviditelny_plast" );
        
        //vkládání věcí do místností
        
        chodba.vlozVec(mapa);
        mistnost1.vlozVec(hulka);
        mistnost1.vlozVec(kamen_mudrcu);
        mistnost1.vlozVec(koste);
        mistnost3.vlozVec(neviditelny_habit);
        mistnost3.vlozVec(baterka);
        mistnost3.vlozVec(klic);
        mistnost4.vlozVec(bylina);
        mistnost4.vlozVec(bylina2);
        mistnost4.vlozVec(bylina3);
        mistnost4.vlozVec(bylina4);
        

        aktualniProstor = chodba;// hra začíná v chodbě  
        viteznyProstor = hermiona;//hra končí v místnosti Hermiona
    }

    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
    }

    /** 
     * Metoda testující, zdali je výhra. Hráč vyhrál, pokud se dostal do výherního prostoru.
     * 
     * @return je výhra, není výhra
     *
     */
    public boolean jeVyhra(){
        if(aktualniProstor.equals(viteznyProstor)){
            return true;
        }
        return false;
    }

    /**
     * Metoda vraci odkaz na aktualni batoh.
     *
     * @return batoh
     */
    public Batoh getBatoh(){
        return batoh;
    }

    /**
     * Metoda, ktera zjistuje, zda ma hrac v batohu lektvar
     * 
     */
    public boolean maLektvar(){
        return batoh.obsahujeVec("lektvar");
    }   
    @Override
    public void notifyObservers(){
        setChanged();
        super.notifyObservers();
    }
}
