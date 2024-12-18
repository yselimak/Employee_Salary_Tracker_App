package routing;

import java.io.IOException;

import application.Main;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Routing {

	// parametre olarak alınan fxml'e göre yeni bir sayfa açan metod
	public static void sayfaAc(String fxml, String title , boolean resizable) throws IOException  {
	
		Stage stage=new Stage(); // yeni stage nesenesi oluştur
		Scene scene=new Scene(Main.loadFXML(fxml)); // "fxml" dosyasını içeren scene nesnesi oluştur
		
		stage.setResizable(false); // stage'i büyütüp küçültebilme kontrolü
		stage.setTitle(title); // stage başlığı
		stage.setScene(scene); // scene'i stage e atama
		stage.show(); // stage'i gösterme
		
	}
	
	
}
