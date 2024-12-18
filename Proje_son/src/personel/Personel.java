package personel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.*;

public class Personel {
    private StringProperty isim;
    private StringProperty soyisim;
    private StringProperty pozisyon;
    private IntegerProperty yas;
    private LongProperty maas;
    private StringProperty terfiBilgileri;
    private List<String> terfiListesi;
    private Long personelNo=1L;
    public Personel onceki = null;
    public Personel sonraki = null;
    
    


	// Constructor
    public Personel(String isim, String soyisim, String pozisyon, int yas,long maas) {
        this.isim = new SimpleStringProperty(isim);
        this.soyisim = new SimpleStringProperty(soyisim);
        this.pozisyon = new SimpleStringProperty(pozisyon);
        this.yas = new SimpleIntegerProperty(yas);
        this.maas = new SimpleLongProperty(maas);
        this.terfiBilgileri= new SimpleStringProperty(" "+pozisyon+": "+LocalDate.now());
        this.terfiListesi = new ArrayList<>();
    }


	// Getter ve setter'lar
    public long getMaas() {
        return maas.get();
    }

    public void setMaas(long maas) {
        this.maas.set(maas);
    }

    public LongProperty maasProperty() {
        return maas;
    }

    public String getIsim() {
        return isim.get();
    }

    public void setIsim(String isim) {
        this.isim.set(isim);
    }

    public StringProperty isimProperty() {
        return isim;
    }

    public String getSoyisim() {
        return soyisim.get();
    }

    public void setSoyisim(String soyisim) {
        this.soyisim.set(soyisim);
    }

    public StringProperty soyisimProperty() {
        return soyisim;
    }

    public String getPozisyon() {
        return pozisyon.get();
    }

    public void setPozisyon(String pozisyon) {
        this.pozisyon.set(pozisyon);
    }

    public StringProperty pozisyonProperty() {
        return pozisyon;
    }

    public int getYas() {
        return yas.get();
    }

    public void setYas(int yas) {
        this.yas.set(yas);
    }

    public IntegerProperty yasProperty() {
        return yas;
    }
    
    public String getTerfiBilgileri() {
        return terfiBilgileri.get();
    }

    public void setTerfiBilgileri(String terfiBilgileri) {
        this.terfiBilgileri.set(terfiBilgileri);
    }
    public StringProperty terfiBilgileriProperty() {
        return terfiBilgileri;
    }

	public List<String> getTerfiListesi() {
		return terfiListesi;
	}

	public void setTerfiListesi(List<String> terfiListesi) {
		this.terfiListesi = terfiListesi;
	}
	
	public Long getPersonelNo() {
		return personelNo;
	}

	public void setPersonelNo(Long personelNo) {
		this.personelNo = personelNo;
	}
    
}