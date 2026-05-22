package services;

import models.Arac;
import models.AracDurumu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class KiralamaServisi {

    // Araçları plakaya göre tutuyoruz
    private Map<String, Arac> aracFilosu;

    // Günlük toplam kazanç
    private double ciro = 0;

    // Constructor
    public KiralamaServisi() {
        this.aracFilosu = new LinkedHashMap<>();
    }

    // Filoya araç ekler
    public void aracEkle(Arac yeniArac) {
        aracFilosu.put(yeniArac.getPlaka(), yeniArac);
    }

    // Tüm araçları listeler
    public void filoyuListele() {
        int siraNo = 1; // Numaralandırma için sayaç
        for (Arac arac : aracFilosu.values()) {

            System.out.println(siraNo +"- "+ arac);
            siraNo++;
        }
    }

    // Sadece müsait araçları listeler
    public void musaitAraclariListele(){
        int siraNo = 1;
        for (Arac arac: aracFilosu.values()){
            if (arac.getDurum() == AracDurumu.MUSAIT){
                System.out.println(siraNo +"- "+ arac);
                siraNo++;
            }
        }
    }

    // Araç kiralama işlemi
    public void aracKirala(String plaka, int gun, String musteriAdSoyad) {

        Arac kiralanacakArac = aracFilosu.get(plaka);

        if (kiralanacakArac == null) {
            System.out.println("Bu plakaya ait araç bulunamadı!");
            return;
        }

        else if (kiralanacakArac.getDurum() == AracDurumu.KIRADA) {
            System.out.println("Bu araç şu an başka bir müşteride (Kirada)!");
            return;
        }

        kiralanacakArac.setDurum(AracDurumu.KIRADA);

        double tutar = kiralanacakArac.kiralamaBedeliHesapla(gun);

        ciro += tutar;
        System.out.printf("Başarılı! %s plakalı araç kiralandı. Toplam Tutar: %.2f TL\n", plaka, tutar);

        // Fatura oluşturma
        LocalDate bugun = LocalDate.now();
        String faturaMetni = "Tarih: " + bugun +
                "\nSayın: " + musteriAdSoyad +
                "\nPlaka: " + plaka +
                "\nKiralama Süresi: " + gun + " Gün" +
                "\nToplam Tutar: " + tutar + " TL";

        try {
            Files.writeString(Path.of("Fatura_" + plaka + ".txt"), faturaMetni);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Araç iade işlemi
    public void aracIadeEt(String plaka) {
        Arac iadeEdilecekArac = aracFilosu.get(plaka);


        if (iadeEdilecekArac == null) {
            System.out.println("Bu plakaya ait araç bulunamadı!");
            return;
        }

        if (iadeEdilecekArac.getDurum() == AracDurumu.MUSAIT) {
            System.out.println("Bu araç zaten galeride, kirada değil!");
            return;
        }

        iadeEdilecekArac.setDurum(AracDurumu.MUSAIT);
        System.out.println("Başarılı " + plaka + " plakalı araç iade alındı ve tekrar müsait duruma geçti.");
    }

    // Plakaya göre araç bulur
    public Arac araciBul(String plaka) {
        return aracFilosu.get(plaka);
    }

    // Liste numarasına göre araç bulur
    public Arac araciNumaraIleBul(int numara) {
        int siraNo = 1;
        for (Arac arac : aracFilosu.values()) {
            if (siraNo == numara) {
                return arac;
            }
            siraNo++;
        }
        return null;
    }

    // Güncel ciroyu gösterir
    public void ciroGoster(){
        System.out.println("Güncel ciro: " + ciro + " TL");
    }

    // Aracı bakım moduna alır
    public  void  bakimaAl(String plaka){
        Arac bakimaAlinacakArac =aracFilosu.get(plaka);

        if (bakimaAlinacakArac ==null){
            System.out.println("Araç bulunamadı");
            return;
        } else if (bakimaAlinacakArac.getDurum()==AracDurumu.BAKIMDA) {
            System.out.println("Bu araç zaten bakımda");
            return;
        } else if (bakimaAlinacakArac.getDurum()==AracDurumu.KIRADA) {
            System.out.println("Bu araç şuan müşteride");
            return;
        }
        bakimaAlinacakArac.setDurum(AracDurumu.BAKIMDA);
        System.out.println(bakimaAlinacakArac.getPlaka() + " plakalı araç bakıma alındı.");
    }

    // Müsait araçlar arasından seçim yapar
    public Arac musaitAraciNumaraIleBul(int numara) {
        int siraNo = 1;
        for (Arac arac : aracFilosu.values()) {

            if (arac.getDurum() == AracDurumu.MUSAIT) {
                if (siraNo == numara) {
                    return arac;
                }
                siraNo++;
            }
        }
        return null;
    }

    // Kiradaki araçları listeler
    public void kiradakiAraclariListele() {
        int siraNo = 1;
        boolean kiradaAracVarMi = false;
        for (Arac arac : aracFilosu.values()) {
            if (arac.getDurum() == AracDurumu.KIRADA) {
                System.out.println(siraNo + "- " + arac);
                siraNo++;
                kiradaAracVarMi = true;
            }
        }
        if (!kiradaAracVarMi) {
            System.out.println("Şu anda kirada olan herhangi bir araç bulunmamaktadır.");
        }
    }

    // Kiradaki araçlar arasından seçim yapar
    public Arac kiradakiAraciNumaraIleBul(int numara) {
        int siraNo = 1;
        for (Arac arac : aracFilosu.values()) {
            if (arac.getDurum() == AracDurumu.KIRADA) {
                if (siraNo == numara) {
                    return arac;
                }
                siraNo++;
            }
        }
        return null;
    }
}
