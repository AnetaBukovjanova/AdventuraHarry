package com.github.AnetaBukovjanova.adventuraHarry.ui;

import com.github.AnetaBukovjanova.adventuraHarry.logika.Hra;
import java.util.Observable;
import java.util.Observer;

import com.github.AnetaBukovjanova.adventuraHarry.logika.IHra;
import com.github.AnetaBukovjanova.adventuraHarry.logika.Prostor;
import com.github.AnetaBukovjanova.adventuraHarry.logika.Vec;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * 
 * @author Filip Vencovsky
 *
 */

public class HomeController extends AnchorPane implements Observer, Initializable {

	@FXML private TextField textVstup;
	@FXML private TextArea textVypis;
	@FXML private Button odesli;
	@FXML private ListView<Object> seznamVeciMistnost = new ListView();
    @FXML private ListView<Object> seznamVeciBatoh = new ListView<>();
	@FXML private ListView<Object> seznamVychodu = new ListView<>();
	@FXML private ImageView panacek;
	private IHra hra;
	
	private ObservableList<Object> vychody = FXCollections.observableArrayList();
	private ObservableList<Object> seznamveci = FXCollections.observableArrayList();
    private ObservableList<Object> obsahbatohu = FXCollections.observableArrayList();
	
    /**
	 * metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho
	 */
    
@FXML public void odesliPrikaz() {
	String vypis = hra.zpracujPrikaz(textVstup.getText());
	textVypis.appendText("\n--------\n"+textVstup.getText()+"\n--------\n");
	textVypis.appendText(vypis);
	textVstup.setText("");
		if(hra.konecHry()) {
			textVypis.appendText("\n\n Konec hry \n");
			textVstup.setDisable(true);
			odesli.setDisable(true);
		}
		hra.getHerniPlan().notifyObservers();
}

/**
 * Metoda bude soužit pro předání objektu se spuštěnou hrou
 * kontroleru a zobrazí stav hry v grafice.
 * @param objekt spuštěné hry
 */
@Override
    public void initialize(URL url, ResourceBundle rb)  
    {
            hra = new Hra();
	textVypis.setText(hra.vratUvitani());
	textVypis.setEditable(false);
            
            
            seznamVeciMistnost.setItems(seznamveci);
            seznamVeciBatoh.setItems(obsahbatohu);
            seznamVychodu.setItems(vychody);
            
	hra.getHerniPlan().addObserver(this);
            hra.getHerniPlan().notifyObservers();

	
}

@FXML public void novaHra() 
{
hra = new Hra();
textVypis.setText(hra.vratUvitani());
textVstup.setDisable(false);
hra.getHerniPlan().addObserver(this);
hra.getHerniPlan().notifyObservers();
}

@FXML public void konecHry() 
{
    textVstup.setText("konec");
    odesliPrikaz();
}

@FXML public void Napoveda() 
{
   Stage stage = new Stage();
   stage.setTitle("Nápověda");
   
   WebView webView = new WebView();               
   webView.getEngine().load(com.github.AnetaBukovjanova.adventuraHarry.ui.Application.class.getResource("/zdrojeproadventuru/napoveda.html").toExternalForm());
   
   stage.setScene(new Scene(webView, 1200, 650));
   stage.show();
}

@Override
public void update(Observable arg0, Object arg1) 
    {
	panacek.setX(hra.getHerniPlan().getAktualniProstor().getX());
	panacek.setY(hra.getHerniPlan().getAktualniProstor().getY());
        
            seznamveci.clear();
            obsahbatohu.clear();
            vychody.clear();
	String sVychody = hra.getHerniPlan().getAktualniProstor().seznamVychodu();
            String[] oddeleneVychody = sVychody.split(" ");
            for (int i = 1; i < oddeleneVychody.length; i++) 
            {
                vychody.add(oddeleneVychody[i]);
            }
            
            Map<String, Vec> sBatoh = hra.getHerniPlan().getBatoh().vratSeznamVeci();
            for (String x : sBatoh.keySet()) 
            {
                Vec pomocna = sBatoh.get(x);
                ImageView obrazek = new ImageView(new Image(com.github.AnetaBukovjanova.adventuraHarry.ui.Application.class.getResourceAsStream("/zdrojeproadventuru/"+pomocna.getObrazek()), 100, 100, false, false));
                obsahbatohu.add(obrazek);
            }
            
            Map<String, Vec> sVeci = hra.getHerniPlan().getAktualniProstor().getSeznamVeci();
            for (String x : sVeci.keySet()) 
            {
                Vec pomocna = sVeci.get(x);
                ImageView obrazek = new ImageView(new Image(com.github.AnetaBukovjanova.adventuraHarry.ui.Application.class.getResourceAsStream("/zdrojeproadventuru/"+pomocna.getObrazek()), 100, 100, false, false));
               seznamveci.add(obrazek);
            }
}
}