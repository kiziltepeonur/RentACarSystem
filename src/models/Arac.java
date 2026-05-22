package models;

public abstract class Arac {

    // Araç bilgileri
    private String marka;
    private String plaka;
    private double gunlukKiraBedeli;
    private AracDurumu durum = AracDurumu.MUSAIT;

    // Constructor
    public Arac(String marka, String plaka, double gunlukKiraBedeli) {
        this.marka = marka;
        this.plaka = plaka;
        this.gunlukKiraBedeli = gunlukKiraBedeli;
    }

    // Getter & Setter
    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getPlaka() {
        return plaka;
    }

    public void setPlaka(String plaka) {
        this.plaka = plaka;
    }

    public double getGunlukKiraBedeli() {
        return gunlukKiraBedeli;
    }

    public void setGunlukKiraBedeli(double gunlukKiraBedeli) {
        this.gunlukKiraBedeli = gunlukKiraBedeli;
    }

    public AracDurumu getDurum() {
        return durum;
    }

    public void setDurum(AracDurumu durum) {
        this.durum = durum;
    }

    // Aracı ekrana yazdırırken özel format kullan
    @Override
    public String toString() {
        return "Marka: " + marka + " | Plaka: " + plaka + " | Durum: " + durum;
    }


    // Alt sınıflar kendi kiralama hesabını yapar
    public abstract double kiralamaBedeliHesapla(int gun);

}
