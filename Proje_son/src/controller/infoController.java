package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import personel.Personel;

public class infoController implements Initializable {
	@FXML
	public TableView<Personel> infoTableView;
	@FXML
	private TableColumn<Personel,Double> maasColumn;
	@FXML
	private TableColumn<Personel, String> isimColumn;
	@FXML
	private TableColumn<Personel, String> soyisimColumn;
	@FXML
	private TableColumn<Personel, String> pozisyonColumn;
	@FXML
	private TableColumn<Personel, Integer> yasColumn;
	@FXML
	private TableColumn<Personel, String> terfiColumn;
	@FXML
	public static ObservableList<Personel> ınfoPersonelList = FXCollections.observableArrayList();

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		maasColumn.setCellValueFactory(new PropertyValueFactory<Personel,Double>("maas"));
	    isimColumn.setCellValueFactory(new PropertyValueFactory<Personel,String>("isim"));
	    soyisimColumn.setCellValueFactory(new PropertyValueFactory<Personel,String>("soyisim"));
	    pozisyonColumn.setCellValueFactory(new PropertyValueFactory<Personel,String>("pozisyon"));
	    yasColumn.setCellValueFactory(new PropertyValueFactory<Personel,Integer>("yas"));
	    terfiColumn.setCellValueFactory(new PropertyValueFactory<Personel,String>("terfiBilgileri"));// personelin böyle bir attribute'u yok
	    
	    infoTableView.setItems(ınfoPersonelList);//tableView a personelleri ekleme
	}
}
