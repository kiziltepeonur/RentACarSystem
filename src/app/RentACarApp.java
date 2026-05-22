package app;

import models.Arac;
import models.AracDurumu;
import models.Otomobil;
import models.TicariArac;
import services.KiralamaServisi;

import java.util.Scanner;

public class RentACarApp {

    // Programın başlangıç noktası
    public static void main(String[] args) {

        KiralamaServisi galeri = new KiralamaServisi();

        // Örnek araç verileri
        galeri.aracEkle(new Otomobil("Fiat Egea", "34ABC01", 500));
        galeri.aracEkle(new Otomobil("Renault Clio", "34ABC02", 600));
        galeri.aracEkle(new Otomobil("VW Golf", "34ABC03", 800));
        galeri.aracEkle(new Otomobil("Toyota Corolla", "34ABC04", 700));
        galeri.aracEkle(new Otomobil("Honda Civic", "34ABC05", 750));
        galeri.aracEkle(new Otomobil("Ford Focus", "34ABC06", 700));

        galeri.aracEkle(new TicariArac("Ford Transit", "34TCR01", 1000));
        galeri.aracEkle(new TicariArac("Fiat Doblo", "34TCR02", 800));
        galeri.aracEkle(new TicariArac("Renault Kangoo", "34TCR03", 850));
        galeri.aracEkle(new TicariArac("VW Transporter", "34TCR04", 1200));


        // İlk açılış ekranı
        System.out.println("\nRENT A CAR SİSTEMİNE HOŞ GELDİNİZ\n ");
        System.out.println("Mevcut Araç Filomuz:");
        galeri.filoyuListele();


        Scanner scanner = new Scanner(System.in);

        // Ana menü döngüsü
        while (true) {
            System.out.println("\nİŞLEM MENÜSÜ");
            System.out.println("1- Araçları Tekrar Listele");
            System.out.println("2- Araç Kirala");
            System.out.println("3- Araç İade Et");
            System.out.println("4- Gün Sonu Cirosunu Gör");
            System.out.println("5-Aracı Bakıma Gönder");
            System.out.println("6-Çıkış");
            System.out.print("Lütfen yapmak istediğiniz işlemi seçiniz: ");


            try {
                int secim = scanner.nextInt();
                scanner.nextLine();

                switch (secim) {
                    case 1:
                        System.out.println("\nGÜNCEL FİLO DURUMU ");
                        galeri.filoyuListele();
                        break;
                    case 2:
                        aracKiralamaAkisi(galeri, scanner);
                        break;
                    case 3:
                        // Sadece kiradaki araçları göster
                        System.out.println(" ŞU AN KİRADAKİ ARAÇLAR ");
                        galeri.kiradakiAraclariListele();

                        System.out.print("\nİade etmek istediğiniz aracın listedeki numarasını seçiniz: ");
                        int iadeNo = scanner.nextInt();
                        scanner.nextLine();

                        Arac iadeArac = galeri.kiradakiAraciNumaraIleBul(iadeNo);

                        if (iadeArac == null) {
                            System.out.println("Hata: Girdiğiniz numaraya ait kirada bir araç bulunamadı!");
                            break;
                        }

                        galeri.aracIadeEt(iadeArac.getPlaka());
                        break;

                    case 4:
                        galeri.ciroGoster();
                        break;
                    case 5:
                        // Sadece müsait araçları göster
                        System.out.println(" BAKIMA ALINABİLECEK (MÜSAİT) ARAÇLAR ");
                        galeri.musaitAraclariListele();

                        System.out.print("Bakıma alınacak aracın numarasını giriniz: ");
                        int aracNumarasi = scanner.nextInt();
                        scanner.nextLine();

                        Arac secilenAracinNumarasi = galeri.musaitAraciNumaraIleBul(aracNumarasi);
                        if (secilenAracinNumarasi == null) {
                            System.out.println("Girilen numarada müsait araç bulunamadı.");
                            continue;
                        }
                        galeri.bakimaAl(secilenAracinNumarasi.getPlaka());
                        break;
                    case 6:
                        System.out.println("Sistemden çıkılıyor. Bizi tercih ettiğiniz için teşekkürler!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Hatalı seçim yaptınız! Lütfen 1-6 arası bir rakam giriniz.");

                }
            } catch (Exception e) {
                // Sayı dışında giriş yapılırsa hata verir
                System.out.println("Lütfen sadece rakam giriniz!");
                scanner.nextLine();
            }

        }
    }

    // Araç kiralama işlemlerini yönetir
    private static void aracKiralamaAkisi(KiralamaServisi galeri, Scanner scanner) {
        galeri.musaitAraclariListele();
        System.out.print("Kiralamak istediğiniz aracın numarasını listeden seçiniz (Örn: 1): ");
        int aracNo = scanner.nextInt();
        scanner.nextLine();


        Arac secilenArac = galeri.musaitAraciNumaraIleBul(aracNo);

        // Araç kontrolü
        if (secilenArac == null) {
            System.out.println("Girdiğiniz numaraya ait araç bulunamadı!");
            return;
        }
        if (secilenArac.getDurum() != AracDurumu.MUSAIT) {
            System.out.println("Bu araç şu an müsait değil (Kirada veya Bakımda olabilir).");
            return;
        }


        boolean islemTamamlandiMi = false;

        // Kullanıcı onay verene kadar devam eder
        while (!islemTamamlandiMi) {
            System.out.print("Kaç gün kiralamak istiyorsunuz?: ");
            int gun = scanner.nextInt();
            scanner.nextLine();

            // Negatif veya sıfır gün kontrolüI
            if (gun <= 0) {
                System.out.println("Gün sayısı 0 veya negatif olamaz! Lütfen geçerli bir gün giriniz.\n");
                continue;
            }

            double hesaplananTutar = secilenArac.kiralamaBedeliHesapla(gun);
            System.out.println(" Seçilen Araç: " + secilenArac.getMarka() + " (" + secilenArac.getPlaka() + ")");
            System.out.println(" Hesaplanan Toplam Tutar: " + hesaplananTutar + " TL");
            System.out.print("Bu tutarı onaylıyor musunuz? (Evet için 'E', Günü değiştirmek için 'H', İptal için 'I'): ");
            String onay = scanner.nextLine().toUpperCase();

            if (onay.equals("E")) {

                String musteriAdSoyad = musteriAdiAl(scanner);


                galeri.aracKirala(secilenArac.getPlaka(), gun, musteriAdSoyad);
                islemTamamlandiMi = true;
            } else if (onay.equals("H")) {
                System.out.println("Lütfen gün sayısını yeniden belirleyin...\n");
            } else {
                System.out.println("Kiralama işlemi iptal edildi. Ana menüye dönülüyor.");
                islemTamamlandiMi = true;
            }
        }
    }

    // Kullanıcıdan boş olmayan ad soyad alır
    private static String musteriAdiAl(Scanner scanner) {
        String musteriAdSoyad = "";
        while (true) {
            System.out.print("Fatura için Lütfen Adınızı ve Soyadınızı giriniz: ");
            musteriAdSoyad = scanner.nextLine().trim();

            if (musteriAdSoyad.isEmpty()) {
                System.out.println("Ad Soyad alanı boş bırakılamaz! Lütfen geçerli bir isim giriniz.");
            } else {
                return musteriAdSoyad;
            }
        }
    }
}
