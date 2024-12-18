package application;
	
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import personel.Personel;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import controller.Controller;
import dataStructure.BagliListe;

public class Main extends Application {
	
	public static BagliListe bL = new BagliListe();
	
	private static Scene scene;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		scene= new Scene(loadFXML("Giris"));
		primaryStage.setScene(scene);
		primaryStage.setTitle("MAAŞ TAKİP SİSTEMİ");
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	// parametre olarak gönderilen fxml dosyasını yükleyen metod
	public static Parent loadFXML(String FXML) throws IOException {
		
		FXMLLoader fxmlLoader= new FXMLLoader(Main.class.getResource("/views/"+FXML+".fxml"));
		Parent parent=fxmlLoader.load();
		
		return parent;
	
	}
	
	@Override
	public void stop() throws IOException {
		
	}
	


	public static void main(String[] args) throws IOException {
		launch(args);	
	}
	
	
	public static void saveData(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
        	writer.write(data);
        } catch (IOException e) {
            System.out.println("Dosya yazma hatası: " + e.getMessage());
        }
    }
	
	public static void readingData(String fileName) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	String[] data = line.split(";"); // Eğer veriler virgülle ayrılıyorsa
            	if (data.length == 6) {
                	String name = data[0].toUpperCase();
                    String surname = data[1].toUpperCase();
                    String position = data[2].toUpperCase();
                    int age = Integer.parseInt(data[3]);
                    Long personelNo = Long.parseLong(data[4]);
                    
                    String terfiBilgileriHamVeri = data[5]; // dosyadaki terfi bilgisini alma
                    String parantezsizVeri=terfiBilgileriHamVeri.replaceAll("[\\[\\]]", ""); // terfi bilgisindeki parantezleri kaldırma
                    String[] terfiBilgileriStringListe = parantezsizVeri.split(","); // terfi bilgisindeki virgülle ayrılmış her bir veriyi liste formatında alma 
                    // liste tanımlayıp bilgileri listeye aktarma
                    List<String> terfiList = new ArrayList<>();
                    for (String bilgi : terfiBilgileriStringListe) { // terfiBilgileriStringListe deki her bir terfi verisini listeye ekleme
                        terfiList.add(bilgi);
                    }
                    // personelin terfi bilgisini düzenlemek için 
                    // köşeli parantezden arınmış ve her veri bir satırda olacak şekilde
                    // düzenlenmiş terfiBilgileri stringi oluşturma
                    String terfiBilgileri=String.join("\n", terfiList); // terfiList deki her bir veriyi alıp terfiBilgileri Stringine ekleyip ardından alt satıra geçme 
                    terfiBilgileri.replaceAll("[\\[\\]]", ""); // terfiBilgileri Stringinde köşeli parantez varsa (olamayadabilir test etmedim) köşeli parantezleri silme
                    
                   
                    // Personel nesnesi oluşturun ve listeye ekleyin
                    Personel personel = new Personel(name,surname,position,age,personelNo);
                    personel.setTerfiBilgileri(terfiBilgileri);
                    personel.setTerfiListesi(terfiList);
                    bL.Ekle(personel);
                    
                    if (!Controller.personelList.contains(personel)) {
                    	Controller.personelList.add(personel);
                    }else {
                    	System.out.println("veri yükleme hatası");
                    }
                 }
        }}catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        }}	
	
	public static void clearFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            // Hiçbir şey yazmıyoruz, dosyayı sıfırlıyoruz
        } catch (IOException e) {
            System.out.println("Dosya temizleme hatası: " + e.getMessage());
        }
    }

}
