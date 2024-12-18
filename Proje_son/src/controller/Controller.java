package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import routing.Routing;
import personel.Personel;
import application.Main;


public class Controller{
	@FXML
	public static Button ekleButon;
	@FXML
	public static Button maasOrtalamaBtn;
	@FXML
	public static Button kaydetButton;
	@FXML	
	private TextField aramaTextField;
	@FXML
	public TableView<Personel> tableView;
	@FXML
	private TableColumn<Personel,Double> maasColumn;
	@FXML
	private TableColumn<Personel, String> isimColumn;
	@FXML
	private TableColumn<Personel, String> soyisimColumn;
	@FXML
	private TableColumn<Personel, String> pozisyonColumn;
	@FXML
	private TableColumn<Personel, Long> personelNoColumn;
	@FXML
	private ChoiceBox<String> siralamaChoiceBox;
	@FXML
	private TableColumn<Personel, Integer> yasColumn = new TableColumn<>();
	@FXML
	private TableColumn<Personel, Void> actionColumn;
	@FXML
	public static ObservableList<Personel> personelList = FXCollections.observableArrayList();
	
	@FXML
	public void initialize() {
	    maasColumn.setCellValueFactory(new PropertyValueFactory<Personel, Double>("maas"));
	    isimColumn.setCellValueFactory(new PropertyValueFactory<Personel, String>("isim"));
	    soyisimColumn.setCellValueFactory(new PropertyValueFactory<Personel, String>("soyisim"));
	    pozisyonColumn.setCellValueFactory(new PropertyValueFactory<Personel, String>("pozisyon"));
	    yasColumn.setCellValueFactory(new PropertyValueFactory<Personel, Integer>("yas"));
	    personelNoColumn.setCellValueFactory(new PropertyValueFactory<Personel, Long>("personelNo"));

	    // ChoiceBox öğelerini doldurma
	    siralamaChoiceBox.setItems(FXCollections.observableArrayList(
	        " ", 
	        "Personel numarasına göre sırala (Büyükten Küçüğe)",
	        "Personel numarasına göre sırala (Küçükten Büyüğe)",
	        "Maaşı sırala (azdan çoğa)",
	        "Maaşı sırala (çoktan az)",
	        "İsme göre sırala(A-Z)", 
	        "İsme göre sırala(Z-A)", 
	        "Pozisyon sırala (A-Z)",
	        "Pozisyon sırala (Z-A)",
	        "Yaş sırala (Küçükten Büyüğe)",
	        "Yaş Sırala (Büyükten Küçüğe)"
	    ));
	    siralamaChoiceBox.getSelectionModel().selectFirst(); // Varsayılan olarak ilk öğe seçilir

	    // ChoiceBox seçimlerini dinleyerek sıralama işlemini çağır
	    siralamaChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	        handleChoiceBoxSelection(newValue);
	    });

	    tabloyaButonEkle();
	    tableView.setItems(personelList); // TableView'e personelleri ekleme
	    Main.readingData("eyyup.mydb");
	    
	}
	
	
	//choicebox için metod
	private void handleChoiceBoxSelection(String selectedOption) {
	    switch (selectedOption) {
	        case "Personel numarasına göre sırala (Büyükten Küçüğe)":
	            personelNoBKSıralama();
	            break;
	        case "Personel numarasına göre sırala (Küçükten Büyüğe)":
	            personelNoKBSıralama();
	            break;
	        case "Maaşı sırala (azdan çoğa)":
	            maasKBSıralama();
	            break;
	        case "Maaşı sırala (çoktan az)":
	            maasBKSıralama();
	            break;
	        case "İsme göre sırala(A-Z)":
	            isimKBSıralama();
	            break;
	        case "İsme göre sırala(Z-A)":
	            isimBKSıralama();
	            break;
	        case "Pozisyon sırala (A-Z)":
	            pozisyonKBSıralama();
	            break;
	        case "Pozisyon sırala (Z-A)":
	            pozisyonBKSıralama();
	            break;
	        case "Yaş sırala (Küçükten Büyüğe)":
	            yasKBSıralama();
	            break;
	        case "Yaş Sırala (Büyükten Küçüğe)":
	            yasBKSıralama();
	            break;
	        default:
	            // Varsayılan veya hiçbir şey yapma durumu
	            break;
	    }
	    tableView.setItems(personelList); // Tabloyu güncelle
	    tableView.refresh(); // Görünümü yenile
	}
	
	
	
	//veriyi dışarıya çıkarır - veritabanına kaydeder
	@FXML
	public void veriCikar() {
		Main.clearFile("eyyup.mydb");
		pozisyonKBSıralama();
		int a = personelList.size();
		int i = 0;
		
		while(a>i) {
			Personel aktifPersonel= Controller.personelList.get(i);
			String text=aktifPersonel.getIsim()+";" + aktifPersonel.getSoyisim()
			+ ";" + aktifPersonel.getPozisyon() + ";" + aktifPersonel.getYas()
			+ ";" + aktifPersonel.getMaas()+ ";" + aktifPersonel.getTerfiListesi();
			text = text + "\n";
			
			Main.saveData("eyyup.mydb",text);
			i+=1;
		}
	}
	
	private void tabloyaButonEkle() {
	    Callback<TableColumn<Personel, Void>, TableCell<Personel, Void>> cellFactory = new Callback<>() {
	        @Override
	        public TableCell<Personel, Void> call(final TableColumn<Personel, Void> param) {
	            return new TableCell<>() {

	            	FontIcon silIcon = new FontIcon(FontAwesomeSolid.TRASH);
	                {silIcon.setIconSize(10); // İkon boyutunu ayarlayın
	                silIcon.setFill(Color.RED);}
	            	
	                FontIcon infoIcon = new FontIcon(FontAwesomeSolid.INFO);
	                {silIcon.setIconSize(10); // İkon boyutunu ayarlayın
	                silIcon.setFill(Color.RED);}
	                
	                FontIcon editIcon = new FontIcon(FontAwesomeSolid.PENCIL_ALT);
	                {silIcon.setIconSize(10); // İkon boyutunu ayarlayın
	                silIcon.setFill(Color.RED);}
	            	
	            
	            	
	                private final Button deleteButton = new Button("");
	                private final Button editButton = new Button("");
	                private final Button infoButton = new Button("");
	                private final HBox hbox = new HBox(10, deleteButton, editButton,infoButton); // Butonları yatay hizala

	                {
	                	
	                	deleteButton.setGraphic(silIcon);
	                	deleteButton.setStyle("-fx-background-color: transparent;");
	                	
	                	infoButton.setGraphic(infoIcon);
	                	infoButton.setStyle("-fx-background-color: transparent;");
	                	
	                	editButton.setGraphic(editIcon);
	                	editButton.setStyle("-fx-background-color: transparent;");
	                	
	                    // Sil butonu işlemleri
	                    deleteButton.setOnAction(event -> {
	                        Personel personel = getTableView().getItems().get(getIndex());
	                        getTableView().getItems().remove(personel);
	                        Main.bL.Sil(personel);
	                        
	                    });
	                    
	                    // Düzenle butonu işlemleri
	                    editButton.setOnAction(event -> {
	                    	FXMLLoader loader = new FXMLLoader(Main.class.getResource("/views/guncellemeSayfasi.fxml"));
	                    	Parent root = null;
							try {
								root = loader.load();
							} catch (IOException e) {
								
								e.printStackTrace();
							}
	       
	                        Personel seciliPersonel = getTableView().getItems().get(getIndex());
	                        guncellemeController guncCont= loader.getController();
	                        guncCont.seciliPersonelMetod(seciliPersonel);
	                        Stage stage=new Stage();
	                        stage.setScene(new Scene(root));
	                        stage.setTitle("PERSONEL BİLGİLERİNİ GÜNCELLEYİNİZ");
	                        stage.show();
	                        tableView.refresh();
	                        
	                    });
	                    
	                    // info butonu işlemleri
	                    infoButton.setOnAction(event ->  {
	                    	Personel personel = getTableView().getItems().get(getIndex());// personel bilgilerini alma
	                        infoController.ınfoPersonelList.clear();
	                        infoController.ınfoPersonelList.add(personel);
	                        
	                        try {
								Routing.sayfaAc("infoSayfasi", "PERSONEL BİLGİLERİ", true);
							} catch (IOException e) {
								e.printStackTrace();
							}
	                    });
	                }

	                @Override
	                protected void updateItem(Void item, boolean empty) {
	                    super.updateItem(item, empty);
	                    if (empty) {
	                        setGraphic(null);
	                    } else {
	                        setGraphic(hbox); // Hücreye HBox (butonlar) ekle
	                    }
	                }
	            };
	        }
	    };

	    actionColumn.setCellFactory(cellFactory);
	}
	
	@FXML
	public void maasOrtalamaBTNKod() throws IOException {
		Routing.sayfaAc("grafik", "MAAŞ GRAFİĞİ", false);
	}
	
	
	@FXML
	public  void ekleButonKod() throws IOException {
		Routing.sayfaAc("ekleSayfasi", "Yeni Personel Ekleme", false);
	}
	
	@FXML
	public void aramaTextFieldKod() {
		aramaTextField.textProperty().addListener((observable, oldValue, newValue) ->{
			ObservableList<Personel> filtrelenmisListe = FXCollections.observableArrayList();
			if (newValue == null || newValue.isEmpty()) {
				filtrelenmisListe.setAll(filtrelenmisListe);
            }
			Personel temp = Main.bL.bas;
			while(temp !=null) {
				
				String text=temp.getIsim()+";" + temp.getSoyisim()
				+ ";" + temp.getPozisyon() + ";" + temp.getYas()
				+ ";" + temp.getMaas()+ ";" + temp.getTerfiListesi();
				if (text.toLowerCase().contains(newValue.toLowerCase())) {
		                filtrelenmisListe.add(temp);  // Koşula uyan elemanı ekle
		            }
				temp = temp.sonraki;
				
			}
			tableView.setItems(filtrelenmisListe);
			tableView.refresh();
			});	
	}

	
	//personel numarası küçükten büyüğe sıralam
			public void personelNoKBSıralama() {
				if(Main.bL.bas!=null) {
					personelList.clear();
					Personel temp =Main.bL.bas;
					while(temp!=null) {
						
						personelList.add(temp);
						temp = temp.sonraki;
						
					}
				}
				tableView.setItems(personelList);
				tableView.refresh();
			}
			
			//personel numarası büyükten küçüğe sıralama
			public void personelNoBKSıralama() {
				if(Main.bL.bas!=null) {
					personelList.clear();
					Personel temp =Main.bL.son;
					while(temp!=null) {
						
						personelList.add(temp);
						temp = temp.onceki;
						
					}
				}
				tableView.setItems(personelList);
				tableView.refresh();
			}
			
			//maaş küçükten büyüğe sıralama
			public void maasKBSıralama() {
				if (Main.bL.bas!=null) {
					personelList.clear();
					ArrayList<Personel> liste= new ArrayList<Personel>();
					Personel temp =Main.bL.bas;
					while(temp!=null) {
						
						liste.add(temp);
						temp = temp.sonraki;
						
					}
					liste.sort(Comparator.comparingLong(personel -> personel.getMaas()));
					
					for(Personel i : liste) {
						personelList.add(i);
					}
				}
				tableView.setItems(personelList);
				tableView.refresh();
				
			}
			
			//maaş büyükten küçüğe sıralama
			public void maasBKSıralama() {
				if (Main.bL.bas!=null) {
					personelList.clear();
					ArrayList<Personel> liste= new ArrayList<Personel>();
					Personel temp =Main.bL.bas;
					while(temp!=null) {
						
						liste.add(temp);
						temp = temp.sonraki;
						
					}
					liste.sort((p1,p2)->Long.compare(p2.getMaas(), p1.getMaas()));
					
					for(Personel i : liste) {
						personelList.add(i);
					}
				}
				tableView.setItems(personelList);
				tableView.refresh();
				
				
			}
			
			//isim A'dan Z'ye sıralama
			public void isimKBSıralama() {
				if (Main.bL.bas!=null) {
					personelList.clear();
					ArrayList<Personel> liste= new ArrayList<Personel>();
					Personel temp =Main.bL.bas;
					while(temp!=null) {
						
						liste.add(temp);
						temp = temp.sonraki;
						
					}
					liste.sort(Comparator.comparing(personel -> personel.getIsim()));
					
					for(Personel i : liste) {
						personelList.add(i);
					}
				}
				
				tableView.setItems(personelList);
				tableView.refresh();
				
			}
			
			
			
			//isim Z'den A'ya sıralama
			public void isimBKSıralama() {
				if (Main.bL.bas!=null) {
					personelList.clear();
					ArrayList<Personel> liste= new ArrayList<Personel>();
					Personel temp =Main.bL.bas;
					while(temp!=null) {
						
						liste.add(temp);
						temp = temp.sonraki;
						
					}
					liste.sort(Comparator.comparing(Personel::getIsim).reversed());
					
					for(Personel i : liste) {
						personelList.add(i);
					}
				}
				tableView.setItems(personelList);
				tableView.refresh();
			}

			//yaş küçükten büyüğe sıralama
			public void yasKBSıralama() {
				if (Main.bL.bas!=null) {
					personelList.clear();
					ArrayList<Personel> liste= new ArrayList<Personel>();
					Personel temp =Main.bL.bas;
					while(temp!=null) {
						
						liste.add(temp);
						temp = temp.sonraki;
						
					}
					liste.sort(Comparator.comparingInt(personel -> personel.getYas()));
					
					for(Personel i : liste) {
						personelList.add(i);
					}
				}
				tableView.setItems(personelList);
				tableView.refresh();
			}
			
			//yaş büyükten küçüğe sıralama
			public void yasBKSıralama() {
				if (Main.bL.bas!=null) {
					personelList.clear();
					ArrayList<Personel> liste= new ArrayList<Personel>();
					Personel temp =Main.bL.bas;
					while(temp!=null) {
						
						liste.add(temp);
						temp = temp.sonraki;
						
					}
					liste.sort((p1,p2)->Long.compare(p2.getYas(), p1.getYas()));
					
					for(Personel i : liste) {
						personelList.add(i);
					}
				}
				tableView.setItems(personelList);
				tableView.refresh();
			}
			
			//pozisyon A'dan Z'ye sıralama
			public void pozisyonKBSıralama() {
				if (Main.bL.bas!=null) {
					personelList.clear();
					ArrayList<Personel> liste= new ArrayList<Personel>();
					Personel temp =Main.bL.bas;
					while(temp!=null) {
						
						liste.add(temp);
						temp = temp.sonraki;
						
					}
					liste.sort(Comparator.comparing(personel -> personel.getPozisyon()));
					
					for(Personel i : liste) {
						personelList.add(i);
					}
				}
				tableView.setItems(personelList);
				tableView.refresh();
			}
			
			//pozisyon Z'den A'ya sıralama
			public void pozisyonBKSıralama() {
				if (Main.bL.bas!=null) {
					personelList.clear();
					ArrayList<Personel> liste= new ArrayList<Personel>();
					Personel temp =Main.bL.bas;
					while(temp!=null) {
						
						liste.add(temp);
						temp = temp.sonraki;
						
					}
					liste.sort(Comparator.comparing(Personel::getPozisyon).reversed());
					
					for(Personel i : liste) {
						personelList.add(i);
					}
				}
				tableView.setItems(personelList);
				tableView.refresh();
			}
			
			
			
			
			
			
			
			
			
}
