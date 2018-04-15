/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
        logika.Batoh batoh1 = new logika.Batoh();
        logika.Vec vec1 = new logika.Vec("věc1", true);
        logika.Vec vec2 = new logika.Vec("věc2", true);
        logika.Vec vec3 = new logika.Vec("věc3", true);
        logika.Vec vec4 = new logika.Vec("věc4", true);
        logika.Vec vec5 = new logika.Vec("věc5", true);
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

