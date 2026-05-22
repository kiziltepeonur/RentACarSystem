# Rent A Car Otomasyon Sistemi 🚗

Java ile geliştirilmiş, konsol tabanlı bir araç kiralama otomasyonudur. Bu proje, Nesne Yönelimli Programlama (OOP) prensiplerini, veri yapılarını ve temiz kod (Clean Code) standartlarını pratik etmek amacıyla geliştirilmiştir.

## 📌 Özellikler
- **Dinamik Araç Yönetimi:** Otomobil, Ticari Araç ve  farklı araç tipleri için özel fiyatlandırma algoritmaları (Polymorphism).
- **Durum Takibi (State Management):** Araçların Müsait, Kirada veya Bakımda olma durumlarının Enum yapısı ile güvenli bir şekilde yönetilmesi.
- **Akıllı Listeleme:** Kiralama yaparken sadece müsait araçların, iade yaparken sadece kiradaki araçların listelenmesi.
- **Fatura Oluşturma:** Kiralama işlemi tamamlandığında `java.nio.file` kullanılarak müşteriye özel `.txt` formatında fatura kesilmesi.
- **Kasa (Ciro) Takibi:** Gün içinde yapılan kiralama işlemlerinden elde edilen toplam gelirin hesaplanması.
- **Güvenli Kullanıcı Girişi:** Hatalı tuşlamalara ve mantıksız veri girişlerine (örn: negatif gün sayısı) karşı `Try-Catch` ve Validation kalkanları.

## 🛠️ Kullanılan Teknolojiler
- **Core Java**
- **OOP Prensipleri** (Abstraction, Inheritance, Polymorphism, Encapsulation)
- **Collections Framework** (Veritabanı simülasyonu için `LinkedHashMap` kullanılmıştır)
- **Enums**
- **File I/O** (Dosya okuma/yazma işlemleri)
- **Exception Handling** (Hata yönetimi)

## 🚀 Nasıl Çalıştırılır?
Projeyi bilgisayarınıza indirdikten sonra herhangi bir Java IDE'si (IntelliJ IDEA, Eclipse vb.) ile açıp `app.RentACarApp` sınıfındaki `main` metodunu çalıştırmanız yeterlidir.
