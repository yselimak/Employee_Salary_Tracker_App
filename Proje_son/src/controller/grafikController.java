package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import personel.Personel;


public class grafikController extends Application implements Initializable{

	@FXML
    private BarChart<String, Number> barChart; // FXML'den bağladığımız BarChart

    @FXML
    private CategoryAxis xAxis;  // X ekseni, FXML ile bağlanıyor
    @FXML
    private NumberAxis yAxis;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		xAxis.setLabel("Departmanlar");
        yAxis.setLabel("Maaş Ortalaması");
        barChart.setTitle("Departman Maaş Ortalaması");

        // Veri Seti (Farklı departmanlar için maaş ortalamaları)
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Maaşlar");

       Long stajyerMaas = 0L;
       int stajyerSayac = 0;
       Long juniorMaas = 0L;
       int juniorSayac = 0;
       Long midMaas = 0L;
       int midSayac = 0;
       Long seniorMaas = 0L;
       int seniorSayac = 0;
       Long freelancerMaas = 0L;
       int freelancerSayac = 0;
       Long teamLeadMaas = 0L;
       int teamLeadSayac = 0;
       Long architectMaas = 0L;
       int architectSayac = 0;
       Long ctoMaas = 0L;
       int ctoSayac = 0;
        
        Personel temp = Main.bL.bas;
        
        while(temp != null) {
        	switch (temp.getPozisyon()) {
			case "STAJYER":
				stajyerSayac+=1;
				stajyerMaas+=temp.getMaas();
				break;
			case "JUNİOR":
				juniorSayac+=1;
				juniorMaas+=temp.getMaas();
				break;
			case "MİD LEVEL":
				midSayac+=1;
				midMaas+=temp.getMaas();
				break;
			case "SENİOR":
				seniorSayac+=1;
				seniorMaas+=temp.getMaas();
				break;
			case "FREELANCER":
				freelancerSayac+=1;
				freelancerMaas+=temp.getMaas();
				break;
			case "TEAM LEAD":
				teamLeadSayac+=1;
				teamLeadMaas+=temp.getMaas();
				break;
			case "SOFTWARE ARCHİTECT":
				architectSayac+=1;
				architectMaas+=temp.getMaas();
				break;
			case "CTO":
				ctoSayac+=1;
				ctoMaas+=temp.getMaas();
				break;
			default:
				break;
			}
        	temp = temp.sonraki;
        }
        
       
        
        if(stajyerSayac!=0) {	        	
        	series.getData().add(new XYChart.Data<>("Stajyer", stajyerMaas/stajyerSayac));
        }
        if(juniorSayac !=0) {	        	
        	series.getData().add(new XYChart.Data<>("JUNİOR", juniorMaas/juniorSayac));
        }
        if(midSayac!=0) {
        	series.getData().add(new XYChart.Data<>("MİD-LEVEL", midMaas/midSayac));	        	
        }
        if(seniorSayac!=0) {
        	series.getData().add(new XYChart.Data<>("SENİOR", seniorMaas/seniorSayac));	        	
        }
        if(freelancerSayac!=0) {	        	
        	series.getData().add(new XYChart.Data<>("FREELANCER", freelancerMaas/freelancerSayac));
        }
        if(teamLeadSayac!=0) {
        	series.getData().add(new XYChart.Data<>("TEAM-LEAD", teamLeadMaas/teamLeadSayac));	        	
        }
        if(architectSayac!=0) {
        	series.getData().add(new XYChart.Data<>("SOFTWARE ARCHITECT", architectMaas/architectSayac));	        	
        }
        if(ctoSayac!=0) {
        	series.getData().add(new XYChart.Data<>("CTO", ctoMaas/ctoSayac));	        	
        }

        
        // Grafiğe veri ekleme
        barChart.getData().add(series);

		
	}
	
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
    
	  

}
