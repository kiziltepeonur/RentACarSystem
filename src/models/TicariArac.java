package models;

public class TicariArac extends Arac {

    public TicariArac(String marka, String plaka, double gunlukKiraBedeli) {
        super(marka, plaka, gunlukKiraBedeli);
    }

    @Override
    public double kiralamaBedeliHesapla(int gun) {
        return (getGunlukKiraBedeli() * gun) * 1.20;
    }
}
