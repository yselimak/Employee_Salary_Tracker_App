package dataStructure;

import java.util.List;
import personel.Personel;

public class Avl {
	String isim;
	String soyisim;
	String pozisyon;
	int yas;
	Long maas;
	String terfiBilgileri;
	List<String> terfiListesi;
	Long personelNo;
	public Avl sag = null;
    public Avl sol = null;
	
	
	public Avl(Personel personel) {
		this.isim = personel.getIsim() ;
        this.soyisim = personel.getSoyisim() ;
        this.pozisyon = personel.getPozisyon();
        this.yas = personel.getYas();
        this.maas = personel.getMaas();
        this.terfiBilgileri= personel.getTerfiBilgileri();
        this.terfiListesi = personel.getTerfiListesi();
        this.personelNo =personel.getPersonelNo();
	}

	public Avl ekle(Avl kok,Personel yeniPersonel) {
		if(kok==null) {
			return kok=new Avl(yeniPersonel);
		}
		kok.sag=ekle(kok.sag,yeniPersonel);
		
		
		// returnu değişmen gerekebilir!!!!!!!!!
		return kok;
		
	}






}
