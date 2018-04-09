package com.github.AnetaBukovjanova.adventuraHarry.ui;


import com.github.AnetaBukovjanova.adventuraHarry.logika.Hra;
import com.github.AnetaBukovjanova.adventuraHarry.logika.IHra;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Třída Application spouští adventuru i s grafickým rozhraním.
 */

public class Application extends javafx.application.Application {

	public static void main(String[] args) {
		if (args.length == 0) {
            launch(args);
        } else {
            if (args[0].equals("-text")) {
                IHra hra = new Hra();
                TextoveRozhrani ui = new TextoveRozhrani(hra);
                ui.hraj();
            } else {
                System.out.println("Neplatný parametr");
            }
}
	}
	
	/**
     * Metoda načítá fxml s grafickým rozhraním
     */
	
	@Override
	   public void start(Stage stage) throws Exception {
	        Parent root = FXMLLoader.load(getClass().getResource("Main_Window.fxml"));
	        
	        Scene scene = new Scene(root);
	        
	        stage.setScene(scene);
	        stage.show();
}
}
		
