/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.AnetaBukovjanova.adventuraHarry;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.github.AnetaBukovjanova.adventuraHarry.logika.*;


/*******************************************************************************
 * Testovací třída BatohTest slouží ke komplexnímu otestování třídy ... 
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class BatohTest
{
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
        //Komentář proč je to prázdné
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
        //Komentář proč je to prázdné
    }

    /***************************************************************************
     * Test, který testuje vkládání věcí do batohu, vybrání věci, smazání věci a jestli batoh obsahuje věc.
     * Test zkouší na věcech, které se v Adventuře nevyskytují. 
     */
    @Test
    public void testBatoh()
    {
    	Batoh batoh1 = new Batoh();
    	Vec vec1 = new Vec("věc1", true, "vec.png");
        Vec vec2 = new Vec("věc2", true, "vec2.png");
        Vec vec3 = new Vec("věc3", true, "vec3.png");
        Vec vec4 = new Vec("věc4", true, "vec4.png");
        Vec vec5 = new Vec("věc5", true, "vec5.png");
        assertEquals(true, batoh1.pridejVec(vec1));
        assertEquals(true, batoh1.pridejVec(vec2));
        assertEquals(true, batoh1.pridejVec(vec3));
        assertEquals(true, batoh1.pridejVec(vec4));
        assertEquals(false, batoh1.pridejVec(vec5));
        batoh1.vyberVec("věc1");
        assertEquals(false, batoh1.obsahujeVec("věc1"));
        assertEquals(true, batoh1.obsahujeVec("věc2"));
        assertEquals(true, batoh1.obsahujeVec("věc3"));
        assertEquals(true, batoh1.obsahujeVec("věc4"));
        assertEquals(vec2, batoh1.smazVec("věc2"));
    }
}

