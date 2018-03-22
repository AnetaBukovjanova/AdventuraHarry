package com.github.AnetaBukovjanova.adventuraHarry.logika;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Aneta Bukovjanová, Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2017/2018
 */
public class Prostor {
    private String nazev; //Název prostoru
    private String popis; //Popis prostoru
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Set<Vec> veci;  // Věci v prostoru
    private Set<Postava> postavy; // Postavy v prostoru
    private boolean jeZamceno=false; //Zamčeno
    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis, boolean jeZamceno) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        veci = new HashSet <Vec> ();
        postavy = new HashSet<Postava>();
        this.jeZamceno = jeZamceno;
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);//Vkládá sousední místnosti
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
    @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

        return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru.
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi v místnosti " + nazev + ".\n"
        + popisVychodu()+ "\n"
        + popisVeci()+ "\n"
        + seznamPostav();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "vychody:";
        for (Prostor sousedni : vychody) 
        {
            vracenyText += " " + sousedni.getNazev();    
            if (sousedni.jeZamceno())
            {
                vracenyText += "-(zamknuto)";
            }
        }
        return vracenyText;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
            .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
        .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    /**
     * Přidá věc do seznamu(set) veci - vloží věc do prostoru
     */
    public void vlozVec(Vec vec) {
        veci.add (vec);
    }

    /**
     * vypíše seznam věcí v daném prostoru
     * 
     * @return text vypisující věci
     */
    public String popisVeci() {
        String seznam = "Věci v místnosti: ";
        if (veci.isEmpty())
        {
            seznam = seznam + " žádné věci zde nejsou.";
        }
        else
        {
            for (Vec v : veci) 
            {
                seznam += "\n *** " + v.getNazev();
            }
        }
        return seznam;
    }

    /**
     * Metoda vrací hodnotu true nebo false, podle toho jestli daná věc je obsažena v prostoru.
     */
    public boolean obsahujeVec (String jmeno){
        for (Vec v : veci) 
        {
            if (v.getNazev().equals(jmeno))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Metoda vybere vec z prostoru.
     */
    public Vec vyberVec (String jmeno) {
        for ( Vec v : veci ){
            if (v.getNazev().equals(jmeno)) 
            {
                Vec vybranaVec = v;
                veci.remove(vybranaVec);
                return vybranaVec;
            }
        }
        return null;
    }  

   /**
     * Metoda vypíše seznam osob v prostoru.
     */
    public String seznamPostav() {
        String seznam = "\n Osoby v místnosti: ";
        if (postavy.isEmpty())
        {
            seznam = seznam + " žádné osoby zde nejsou.";
        }
        else
        {
            for (Postava u : postavy) 
            {
                seznam += "\n < " + u.getJmeno();
            }
        }
        return seznam;
    }

    /**
     * Metoda vrací údaj o tom, je-li prostor zamčený.
     * 
     * @return zamcena
     */
    public boolean jeZamceno(){
        return jeZamceno;
    }

     /**
     * Metoda, která zjistuje stav jestli je Zamceno
     * 
     * @ return true nebo false
     */
    public void odemknout(boolean stav) {
        this.jeZamceno = stav;
    } 
    
    /**
     * Metoda přidá osobu do prostoru.
     */
    public void setPostava(Postava o) {
        postavy.add (o);
    }

    /**
     * Metoda vybere osobu v prostoru.
     */
    public Postava vyberPostava (String jmeno) {
        for ( Postava u : postavy )
        {
            if (u.getJmeno().equals(jmeno))
            {

                return u;
            }
        }
        return null;
    }
    
    /**
     * Metoda vymaže osobu v daném prostoru.
     */
    public Postava vymazPostavu(String jmeno) {
        for ( Postava u : postavy )
        {
            if (u.getJmeno().equals(jmeno)) 
            {
                Postava vybranaPostava = u;
                postavy.remove(u);
                return vybranaPostava;
            }
        }
        return null;
    }
    
    /**
     * Metoda vymaže věc v daném prostoru.
     */
    public Vec vymazVec(String nazev) {
        for ( Vec vec : veci )
        {
            if (vec.getNazev().equals(nazev)) 
            {
                Vec vybranaVec = vec;
                veci.remove(vec);
                return vybranaVec;
            }
        }
        return null;
    }
}

