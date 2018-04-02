package com.github.AnetaBukovjanova.adventuraHarry.ui;

import java.util.Observable;
import java.util.Observer;

import com.github.AnetaBukovjanova.adventuraHarry.logika.IHra;
import com.github.AnetaBukovjanova.adventuraHarry.logika.Prostor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class HomeController extends AnchorPane implements Observer {

	@FXML private TextField textVstup;
	@FXML private TextArea textVypis;
	@FXML private Button odesli;
	@FXML private ListView<Prostor> seznamMistnosti;
	private IHra hra;
	
public void odesliPrikaz() {
	String vypis = hra.zpracujPrikaz(textVstup.getText());
	textVypis.appendText("\n--------\n"+textVstup.getText()+"\n--------\n");
	textVypis.appendText(vypis);
	textVstup.setText("");
		if(hra.konecHry()) {
			textVypis.appendText("\n\n Konec hry \n");
			textVstup.setDisable(true);
			odesli.setDisable(true);
		}
		
}

public void inicializuj(IHra hra) {
	this.hra = hra;
	textVypis.setText(hra.vratUvitani());
	seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
	
}
@Override
public void update(Observable o, Object arg) {
	seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
}
	
}