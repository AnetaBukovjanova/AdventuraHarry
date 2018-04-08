/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.AnetaBukovjanova.adventuraHarry.logika;




/*******************************************************************************
 * Třída Vec implementuje věc a její chování.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Aneta Bukovjanová
 * @version   1.00.000
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean prenositelnost;
    private String obrazek;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor, kde jako parametr je název věci a jestli je přenositelná.
     */
    public Vec(String nazev, boolean prenositelnost, String obrazek)
    {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
        this.obrazek = obrazek;
       
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
     /**
     * metoda vrací název věci
     */
    public String getNazev(){
        return nazev;
    }

     /**
     * metoda vrací přenositelnost věci
     */
    public boolean jePrenositelna(){
        return prenositelnost;
    }



//== Soukromé metody (instancí i třídy) ========================================

public String getObrazek() {
    return obrazek;
}

public void setObrazek(String obrazek) {
    this.obrazek = obrazek;
}
}
