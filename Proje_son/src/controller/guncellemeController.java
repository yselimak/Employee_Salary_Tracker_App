package controller;

import java.time.LocalDate;

import application.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import personel.Personel;

public class guncellemeController {
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
	
	private Personel seciliPersonel;
	
	public void seciliPersonelMetod(Personel personel) {
		seciliPersonel=personel;
	}
	
	@FXML
	public void initialize() {
	    pozisyonChoiceBox.setItems(FXCollections.observableArrayList("STAJYER", "JUNİOR", "MİD LEVEL", "SENİOR", "TEAM LEAD", "SOFTWARE ARCHİTECT", "FREELANCER", "CTO"));
	    if (!pozisyonChoiceBox.getItems().isEmpty()) {
	        pozisyonChoiceBox.getSelectionModel().selectFirst(); // Varsayılan olarak ilk öğeyi seçin
	    }
	}
	
	@FXML
	public void onaylaButonKod() {
	    if (seciliPersonel == null) {
	        System.err.println("Secili personel bilgisi eksik!");
	        return; // Hata sonrası geri dönme
	    }
	    if (pozisyonChoiceBox.getValue() == null) {
	        System.err.println("Pozisyon seçilmedi!");
	        return; // Hata sonrası geri dönme
	    }
	    
	    try {
	        long maas = Long.parseLong(maasTextField.getText());
	        String isim = isimTextField.getText().toUpperCase();
	        String soyisim = soyisimTextField.getText().toUpperCase();
	        String pozisyon = pozisyonChoiceBox.getValue().toUpperCase();
	        int yas = Integer.parseInt(yasTextField.getText());

	       

	        
	        seciliPersonel.setIsim(isim);
	        seciliPersonel.setMaas(maas);
	        seciliPersonel.setSoyisim(soyisim);
	        if (!seciliPersonel.getPozisyon().equals(pozisyon)) {
	            seciliPersonel.setTerfiBilgileri(seciliPersonel.getTerfiBilgileri() + "\n " + pozisyon + ": " + LocalDate.now());
	            seciliPersonel.getTerfiListesi().add(pozisyon+": "+LocalDate.now());
	        }
	        Main.bL.guncelleme(seciliPersonel, maas, isim, soyisim, pozisyon, yas);
	        seciliPersonel.setPozisyon(pozisyon);
	        seciliPersonel.setYas(yas);

	        // Stage'i kapatma
	        Stage kapastage = (Stage) onaylaButon.getScene().getWindow();
	        kapastage.close();
	    } catch (NumberFormatException e) {
	        System.err.println("Lütfen geçerli bir sayı girin: " + e.getMessage());
	        // Kullanıcıya hata mesajı gösterme
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Geçersiz Girdi");
	        alert.setHeaderText("Sayısal bir değer girmelisiniz!");
	        alert.setContentText("Lütfen maaş ve yaş gibi alanlara geçerli bir sayı girin.");
	        alert.showAndWait();
	    }
	}
}