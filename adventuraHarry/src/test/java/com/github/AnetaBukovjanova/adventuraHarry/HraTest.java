package com.github.AnetaBukovjanova.adventuraHarry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.github.AnetaBukovjanova.adventuraHarry.logika.Hra;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author   Aneta Bukovjanová
 * @version  pro školní rok 2017/2018
 */
public class HraTest {
    private Hra hra1;

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
        //Komentář proč je to prázdné
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci 
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("chodba", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi mistnost1");
        assertEquals(false, hra1.konecHry());
        assertEquals("mistnost1", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi mistnost3");
        assertEquals(false, hra1.konecHry());
        assertEquals("mistnost3", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber klic");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("batoh");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("napoveda");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("konec");
        assertEquals(true, hra1.konecHry());
    }

    @Test
    public void testVyhra() {
        assertEquals("chodba", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber mapa");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi mistnost1");
        assertEquals(false, hra1.konecHry());
        assertEquals("mistnost1", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber hulka");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi mistnost3");
        assertEquals(false, hra1.konecHry());
        assertEquals("mistnost3", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("zahod mapa");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("zahod hulka");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("seber klic");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("odemkni mistnost4");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi mistnost4");
        assertEquals(false, hra1.konecHry());
        assertEquals("mistnost4", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber bylina");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi mistnost5");
        assertEquals(false, hra1.konecHry());
        assertEquals("mistnost5", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("vytvor");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("mluv Lenka");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi Hermiona");
        assertEquals(true, hra1.konecHry());
    }
}