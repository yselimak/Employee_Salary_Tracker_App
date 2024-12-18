package controller;

import java.io.IOException;
import java.util.Hashtable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import routing.Routing;

public class girisController {
	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button girisYapButon;
	@FXML
	private Label uyariLabel;
	
	
	Hashtable<String,String> accounts=new Hashtable<>();
		{accounts.put("admin","admin");}
	@FXML
	public void girisYapButonKod() throws IOException {
		// kullanıcı adı ve şifre kontrolü
		String kontrolUsername=usernameTextField.getText();
		String kontrolPassword=passwordField.getText();
		if(accounts.containsKey(kontrolUsername) && accounts.get(kontrolUsername).equals(kontrolPassword)) {
				// anaSayfa.fxml aç
				Routing.sayfaAc("anaSayfa","MAAŞ TAKİP SİSTEMİ", true);
				
				// girisYapButon'un bulunduğu stage'i kapat
				Stage aktifstage = (Stage) girisYapButon.getScene().getWindow(); // girisYapButon'un bulunduğu stage'i bir nesneye atama
				aktifstage.close(); // alınan stage'i kapatma
		}
		else {
			uyariLabel.setVisible(true);
		}
	}
	
	@FXML
	public void enterGiris(KeyEvent e)  {
		if(e.getCode()== KeyCode.ENTER) {
			try {
				girisYapButonKod();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
