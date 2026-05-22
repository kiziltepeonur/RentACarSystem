package models;

public class Otomobil extends Arac{


    public Otomobil(String marka, String plaka, double gunlukKiraBedeli) {
        super(marka, plaka, gunlukKiraBedeli);
    }

    @Override
    public double kiralamaBedeliHesapla(int gun) {

        return getGunlukKiraBedeli()*gun;
    }
}
