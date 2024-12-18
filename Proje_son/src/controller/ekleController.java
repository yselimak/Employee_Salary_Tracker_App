package controller;

import personel.Personel;
import application.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ekleController {
	@FXML
	private Button onaylaButon;
	@FXML
	private TextField isimTextField;
	@FXML
	private TextField soyisimTextField;
	@FXML
	private TextField maasTextField;
	@FXML
	private ChoiceBox<String> pozisyonChoiceBox;
	@FXML
	private TextField yasTextField;
	
	@FXML
    public void initialize() {
        // ChoiceBox öğelerini doldurma
        pozisyonChoiceBox.setItems(FXCollections.observableArrayList("STAJYER","JUNİOR", "MİD LEVEL", "SENİOR","TEAM LEAD","SOFTWARE ARCHİTECT",""
        		+ "FREELANCER","CTO"));
        pozisyonChoiceBox.getSelectionModel().selectFirst(); // Varsayılan olarak ilk öğe seçilir
    }

	@FXML
	public void onaylaButonKod() {
		// TextField'dan alınan değerleri uygun türlere dönüştür
	    long maas = Long.parseLong(maasTextField.getText());
	    String isim = isimTextField.getText().toUpperCase();
	    String soyisim = soyisimTextField.getText().toUpperCase();
	    String pozisyon = pozisyonChoiceBox.getValue();
	    int yas = Integer.parseInt(yasTextField.getText());
	    
	    
	    
	    // Personel nesnesi oluşturuluyor
	    Personel yeniPersonel = new Personel(isim, soyisim, pozisyon, yas,maas);

	    
	    yeniPersonel.getTerfiListesi().add(yeniPersonel.getTerfiBilgileri());
	    
	    Main.bL.Ekle(yeniPersonel);
	    
	    
	    
	    // observable liste yenii personeli ekle
	    Controller.personelList.add(yeniPersonel);
	    
	    
		// onaylaButon'un bulunduğu stage'i kapat
		Stage kapastage = (Stage) onaylaButon.getScene().getWindow(); // onaylaButon'un bulunduğu stage'i bir nesneye atama
		kapastage.close(); // alınan stage'i kapatma
		
		
	}
	
    
	
}
