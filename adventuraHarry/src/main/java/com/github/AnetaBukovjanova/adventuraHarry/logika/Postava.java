/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.AnetaBukovjanova.adventuraHarry.logika;

/*******************************************************************************
 * Trida Postava umoznuje vytvaret ve hre postavy. 
 *
 * @author    Aneta Bukovjanová
 * @version   pro zimní semestr 2017/2018
 */
public class Postava
{
    private String jmeno;
    private String proslov;

    /***************************************************************************
     *  Konstruktor třídy Postava deklaruje jmeno a proslov.
     */
    public Postava (String jmeno, String proslov)
    {
        this.jmeno = jmeno;
        this.proslov = proslov;
    }

    /**
     * Metoda vrací jméno osoby
     * 
     * @return String jméno osoby
     * 
     */
    public String getJmeno(){
        return jmeno;
    }

    /**
     * Metoda vrací proslov osoby
     * 
     * @return String proslov osoby
     * 
     */
    public String getProslov(){
        return proslov;
    }

    /**
     * Metoda vrací řeč osoby po příkazu mluv
     */
    public String rekniProslov() {
        return jmeno+" říká: "+proslov;
    }

}