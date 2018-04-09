package com.github.AnetaBukovjanova.adventuraHarry;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.github.AnetaBukovjanova.adventuraHarry.logika.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 *
 * @author    Jarmila Pavlíčková, Aneta Bukovjanová
 * @version   pro skolní rok 2017/2018
 */
public class ProstorTest
{
    
    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        //prázdné - komentář proč
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
        //prázdné - komentář proč
    }

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry.
     * 
     */
    @Test
    public  void testLzeProjit() {		
        Prostor prostor1 = new Prostor("chodba", "chodba s dvěma dveřmi",true, 240,0);
        Prostor prostor2 = new Prostor("mistnost1", "mistnost s věcmi", true,150,0);
        Prostor prostor3 = new Prostor("mistnost2","smrt", true,170,0);
        Prostor prostor4 = new Prostor("mistnost3","klic",true,300,150);
        Prostor prostor5 = new Prostor("mistnost4","zamčeno", false,0,200);
        Prostor prostor6 = new Prostor("mistnost5","suroviny", true,220,50);
        Prostor prostor7 = new Prostor("Hermiona","konec", true,60,300);
        prostor1.setVychod(prostor2);
        prostor1.setVychod(prostor3);
        prostor2.setVychod(prostor4);
        prostor4.setVychod(prostor5);
        prostor5.setVychod(prostor6);
        prostor6.setVychod(prostor7);
        assertEquals(prostor4, prostor2.vratSousedniProstor("mistnost3"));
        assertEquals(null, prostor2.vratSousedniProstor("pokoj"));
    }
   
}

