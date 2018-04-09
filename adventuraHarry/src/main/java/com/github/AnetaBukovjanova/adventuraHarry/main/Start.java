/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.AnetaBukovjanova.adventuraHarry.main;


import com.github.AnetaBukovjanova.adventuraHarry.logika.*;
import com.github.AnetaBukovjanova.adventuraHarry.ui.TextoveRozhrani;

/*******************************************************************************
 * Třída  Start je hlavní třídou projektu,
 * který představuje jednoduchou textovou adventuru určenou k dalším úpravám a rozšiřování
 *
 * @author    Aneta Bukovjanová
 * @version   ZS 2017/2018
 */
public class Start 
{
	
	    /***************************************************************************
	     * Metoda, prostřednictvím níž se spouští celá aplikace.
	     *
	     * @param args Parametry příkazového řádku
	     */
	    public static void main(String[] args)
	    {
	        
	        IHra hra = new Hra();
	        TextoveRozhrani ui = new TextoveRozhrani(hra);
	        ui.hraj();
	    }
	    
	    private Start(){}
	}
