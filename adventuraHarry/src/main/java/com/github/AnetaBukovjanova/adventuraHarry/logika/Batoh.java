package com.github.AnetaBukovjanova.adventuraHarry.logika;

import java.util.Set;
import java.util.HashSet;
/*******************************************************************************
 * Třída Batoh - popisuje batoh.
 * 
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *  "Batoh" reprezentuje "úložný prostor" pro sebrané věci (příkazem seber). 
 *  Některé věci jsou přenositelné. Jsou vkládány do batoh a kdykoli si pomocí příkazu
 *  'batoh' můžeme zjistit jeho obsah (vypíše se řetězec znaků). Lze je z batohu
 *  odebrat, resp. je "vymazat" příkazem zahod.
 *
 * @author    Aneta Bukovjanová
 * @version   pro zimní semestr 2017/2018
 */
public class Batoh
{
    public static final int KAPACITA_BATOHU = 4;// Kapacita batohu je 4 
    private Set <Vec> veci;
	

    /**
     * Konstruktor třídy vytváří batoh.
     */
   public Batoh()
    {
        veci = new HashSet <Vec> ();
    }
    
    /**
     * Přidá věc do batohu pokud je v batohu místo a je věc přenositelná
     * 
     * param vec věc, která se má přidat do batohu.
     * 
     * return true, pokud se věc podaří přidat do batohu.
     * 
     * return false pokud není místo či není přenositelná
     */
    public boolean pridejVec(Vec vec) {
        if(jeMistoVBatohu() && (vec.jePrenositelna()))
        {
            veci.add(vec);
            return true;
        }
        return false;
    }
    
     /**
     *  Metoda zjistí, zda se věc vejde do batohu.
     *
     *  return   Vrátí true, pokud se vejde do batohu.
     */
    public boolean jeMistoVBatohu()
    {
        return (veci.size() < KAPACITA_BATOHU);
    }
    
    /**
     * odebere věc z batohu pokud je v batohu
     * 
     * @param název věci, kterou chceme odebrat
     * @retun odebraná věc (pokud není tak null)
     */
    public Vec smazVec (String jmeno){
        for (Vec v : veci) {
            if (v.getNazev().equals(jmeno)){ // Porovná jméno a název
                Vec vybranaVec = v;
                veci.remove(v); // Smaže
                return vybranaVec;
            }
        }
        return null;
    }
    
    /**
     *  vrátí obsah batohu (text)
     *  
     *  @return seznam věcí
     */ 
    public String seznamVeci() {
        String seznam = "Věci v batohu: ";
        if (veci.isEmpty())
        {
            seznam = seznam + " batoh je úplně prázdný";
        }
        else
        {
            for (Vec v : veci) 
            {
                seznam += "\n *" + v.getNazev();
            }
        }
        return seznam;
    }
    
    /**
     *  Metoda vrací seznam věcí.
     */ 
    
    public Set<Vec> getSeznamVeci() {
    	return this.veci;
    }
            
    /**
     * Metoda rozhodne, zda v batohu věc je
     */
    public boolean obsahujeVec(String nazev) {
        for (Vec v : veci) {
            if (v.getNazev().equals(nazev)){ // Porovná jméno a název
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metoda vybere a odstraní věc z batohu.
     */
    public Vec vyberVec (String jmeno) {
        for ( Vec v : veci ){
            if (v.getNazev().equals(jmeno)) // Porovná jméno a název
            { 
                Vec vybranaVec = v;
                veci.remove(v); // Smaže
                return vybranaVec;
            }
        }
        return null;
    }
    
    /**
     * Metoda vybere věc z batohu.
     */
    public Vec vemVec (String jmeno) {
        for ( Vec v : veci ){
            if (v.getNazev().equals(jmeno)) {
                return v;
            }}
        return null;
    }
    
         /**
     *  Metoda zjistí obsah batohu.
     *  
     *  return  Seznam věcí
     */   
    public String nazvyVeci() {
        String nazvy = "";
        for (Vec v : veci)
        {
            nazvy +=  v.getNazev() + " ";
        }
        return nazvy;
    }
    
    /**
     * Metoda vrací kapacitu batohu.
     * 
     * @return int kapacita batohu.
     */
    public int getKapacita() {
        return KAPACITA_BATOHU;
    } 
}