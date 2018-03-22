/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.AnetaBukovjanova.adventuraHarry;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.github.AnetaBukovjanova.adventuraHarry.logika.*;

/*******************************************************************************
 * Testovací třída VecTest slouží ke komplexnímu otestování třídy ... 
 *
 * @author    Aneta Bukovjanová
 * @version   1.00.000
 */
public class VecTest
{
    
    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
        //prázdné - komentář proč
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
        //prázdné - komentář proč
    }

    /***************************************************************************
     * Test, který testuje, jestli věc je přenositelná.
     */
    @Test
    public void test1()
    {
        Vec vec1 = new Vec("koste", false);
        assertEquals("koste", vec1.getNazev()); 
        assertEquals(false, vec1.jePrenositelna());
    }
}

