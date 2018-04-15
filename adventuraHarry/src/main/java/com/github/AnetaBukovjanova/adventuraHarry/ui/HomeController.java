package com.github.AnetaBukovjanova.adventuraHarry.ui;

import com.github.AnetaBukovjanova.adventuraHarry.logika.Hra;
import com.github.AnetaBukovjanova.adventuraHarry.logika.IHra;
import com.github.AnetaBukovjanova.adventuraHarry.logika.IPrikaz;
import com.github.AnetaBukovjanova.adventuraHarry.logika.Vec;
import java.util.Observable;
import java.util.Observer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Map;


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.ComboBox;
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
	@FXML private ListView<Object> seznamVeciMistnost = new ListView<>();
    @FXML private ListView<Object> seznamVeciBatoh = new ListView<>();
	@FXML private ListView<Object> seznamVychodu = new ListView<>();
	@FXML private ImageView panacek;
	@FXML private ComboBox comboPrikaz;
	private IHra hra;
	
	private ObservableList<Object> vychody = FXCollections.observableArrayList();
	private ObservableList<Object> seznamveci = FXCollections.observableArrayList();
    private ObservableList<Object> obsahbatohu = FXCollections.observableArrayList();
    private ObservableList<String> seznamPrikazu = FXCollections.observableArrayList();
	
    /**
	 * metoda čte příkaz ze vstupního textového pole nebo z comboboxu
	 * a příkaz zpracuje
	 */
    
@FXML public void odesliPrikaz() {
	String vypis = "";
    String pomocny = "" + comboPrikaz.getSelectionModel().getSelectedItem();
    if(pomocny.equals("null"))
    {
        vypis = hra.zpracujPrikaz(textVstup.getText());
        textVypis.appendText("\n--------\n"+textVstup.getText()+"\n--------\n");
    }
    else
    {
        vypis = hra.zpracujPrikaz(pomocny + " " + textVstup.getText());
        textVypis.appendText("\n--------\n"+ pomocny + " " + textVstup.getText()+"\n--------\n");
    }
textVypis.appendText(vypis);
textVstup.setText("");
    
	if(hra.konecHry()) 
            {
		textVypis.appendText("\n\n Konec hry \n");
		textVstup.setDisable(true);
	}
	hra.getHerniPlan().notifyObservers();
    comboPrikaz.setValue(null);
}


/**
 * Metoda bude fungovat jako předání objektu se spuštěnou hrou
 * kontroleru a zobrazí stav hry v grafice.
 * @param objekt spuštěné hry
 */
@Override
    public void initialize(URL url, ResourceBundle rb)  
    {
            hra = new Hra();
	textVypis.setText(hra.vratUvitani());
	textVypis.setEditable(false);
            
    Map<String, IPrikaz> sPrikazu = hra.getPlatnePrikazy().getMapaSPrikazy();
    
    for(String prikaz : sPrikazu.keySet())
    {
        seznamPrikazu.add(prikaz);
    }
    
    seznamVeciMistnost.setItems(seznamveci);
    seznamVeciBatoh.setItems(obsahbatohu);
    seznamVychodu.setItems(vychody);
    comboPrikaz.setItems(seznamPrikazu);
    
hra.getHerniPlan().addObserver(this);
    hra.getHerniPlan().notifyObservers();
}

/**
 * Metoda bude spouštět novou hru.
 */

@FXML public void novaHra() 
{
hra = new Hra();
textVypis.setText(hra.vratUvitani());
textVstup.setDisable(false);
hra.getHerniPlan().addObserver(this);
hra.getHerniPlan().notifyObservers();
}

/**
 * Metoda nastaví konec hry.
 */

@FXML public void konecHry() 
{
    textVstup.setText("konec");
    odesliPrikaz();
}

/**
 * Metoda zobrazí v html nápovědu.
 */
@FXML public void Napoveda() 
{
   Stage stage = new Stage();
   stage.setTitle("Nápověda");
   
   WebView webView = new WebView();               
   webView.getEngine().load(com.github.AnetaBukovjanova.adventuraHarry.ui.Application.class.getResource("/zdrojeproadventuru/napoveda.html").toExternalForm());
   stage.setScene(new Scene(webView, 1200, 650));
   stage.show();
}

/**
 * Metoda nastavuje panáčka, zobrazuje východy, zobrazuje seznam věcí v místnosti pomocí obrázků a obsah batohu pomocí obrázků
 */

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
            
            Set <Vec> sBatoh = hra.getBatoh().getSeznamVeci();
            for (Vec pomocna : sBatoh) 
            {
                ImageView obrazek = new ImageView(new Image(com.github.AnetaBukovjanova.adventuraHarry.ui.Application.class.getResourceAsStream("/zdrojeproadventuru/"+pomocna.getObrazek()), 100, 100, false, false));
                obsahbatohu.add(obrazek);
           }
            
            Set <Vec> sVeci = hra.getHerniPlan().getAktualniProstor().getSeznamVeci();
            for (Vec pomocna : sVeci) 
            {
                
               ImageView obrazek = new ImageView(new Image(com.github.AnetaBukovjanova.adventuraHarry.ui.Application.class.getResourceAsStream("/zdrojeproadventuru/"+pomocna.getObrazek()), 100, 100, false, false));
               seznamveci.add(obrazek);
            }
}
/**
 * 
 */

@FXML public void Mistnost() 
{
    String nazev = seznamVychodu.getSelectionModel().getSelectedItem().toString();
    if(!hra.konecHry())
    {
    textVstup.setText("jdi " + nazev);
    odesliPrikaz();
    }
}

}