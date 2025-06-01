# 🐦 Penlingu – İngilizceyi Kalıcı Öğren

**Penlingu**, İngilizce kelimeleri uzun vadede kalıcı şekilde öğretmeyi hedefleyen, yapay zekâ destekli, eğitici ve etkileşimli bir mobil uygulamadır.

---

## 🚀 Özellikler

### 📚 Akıllı Quiz Sistemi
- Firestore’dan **Kullanıcının belirlediği kadar kelime** çekilir.
- Her kelime için ilgili bir **görsel ve o kelimenin karşılığı** gösterilir.
- Kullanıcı bilmediği kelimeyi atlayabilir.
- Quiz bittikten sonra yanlış yaptığı kelimeye geri dönüp doğrusunu görebilir.
- Quiz bittiğinde doğru,yanlış ve çözülen soru sayısını görebilir.
- Dilerse quizden onay verdiği taktirde çıkış yapabilir.

### 🧠 Kalıcı Öğrenme Aşamaları (Spaced Repetition)
Her kelimenin "öğrenilmiş" sayılması için şu 6 zaman aralığında doğru cevaplanması gerekir:
- 1 Gün  
- 1 Hafta  
- 1 Ay  
- 3 Ay  
- 6 Ay  
- 1 Yıl
Bu aşamaların herhangi birinde yanlış yapması o kelime için başa dönülmesine sebep olur.
(Sıralama sisteminde adil olması açısından yanlış yaptığında o kelime için doğru sayısı sıfırlanır.)

---

# 📱 Ana Sayfa (HomePageActivity)

Bu sınıf, uygulamanın kullanıcıya ilk gösterilen ana ekranıdır. Kullanıcı burada genel istatistiklerini, yanlış cevapladığı kelimeleri, liderlik sıralamasını ve bazı özel aksiyonları görebilir.

### 🎯 Temel Özellikler

| Özellik | Açıklama |
|--------|----------|
| 🔁 **Slider (ViewFlipper)** | Kullanıcıya kelime ekleme, quiz yapma, geri bildirim gönderme ve yapay zeka ile hikaye oluşturma gibi özellikleri tanıtan görsel kartlar. |
| 🏆 **Liderlik Tablosu** | Firebase üzerinden çekilen kullanıcı verileriyle başarı sıralaması listesi gösterilir (BottomSheet ile açılır). |
| ❌ **Yanlış Cevaplar** | Son 20 yanlış cevaplanan kelime listesi (aşama = 1 olanlar) kullanıcının tekrar çalışması için gösterilir. |
| 📧 **Geri Bildirim** | Tek tıklamayla e-posta uygulamasına yönlendirme yapılır. |
| 🤖 **PenAI Entegrasyonu** | Yapay zekayla kelimelerden hikaye oluşturma ve görsel üretme özelliğine geçiş. |
| 🎮 **Mini Oyun** | Kullanıcının kelime öğrenimini pekiştirmesi için bulmaca oyunu. |

![Yapay Zeka Sayfası Görseli](https://firebasestorage.googleapis.com/v0/b/englishwordapp-7fb3b.firebasestorage.app/o/uygulamagorsel%2Fanasayfa%5B2%5D.png?alt=media&token=bf55bf5b-efd3-4a7e-96b3-444b7efb1bd8) 



---

# 🅰️ Sözlük sayfası
Eklenen kelimeler alfabetik olarak listelenir listelenen içerik :
- Kelimenin ingilizcesi ve türkçesi
- İngilizce kelimenin baş harfi
  **Arama özelliği**
  -Hem ingilizce hemde türkçe kelimelere ilişkin arama özelliği
  -Kullanıcının aradığı kelimeyi hem baş harfi ile hemde arama ile kolayca bulabilmesi sağlanmıştır
   ### 🖱️Tıklanma
- Listelenen kelimeye tıklandığında
- Kelimenin türkçe ve ingilizcesi
- Kelimeye ilişkin görsel
- Kelimenin geçtiği iki adet cümle gösterilir.
- TTS özelliği aşağıda verilmiştir.
 

 
  

  ## 🖋️ Kelime ekleme sayfası
Kullanıcılar kendileri sisteme kelime ekleyebilirler kelime içeriği:
- Kelimeye ilişkin görsel
- Kelimenin ingilizcesi ve türkçe karşılığı
- İngilizce kelimenin kullanıldığı iki adet cümle

## 🔊 TTS (Text To Spech)
- Kelimeye ve cümleye tıklanınca:
  - İngilizcesi yüksek sesle okunur.
  - Eş zamanlı olarak **Lottie animasyonu** oynar.

  ![Sözlük Sayfası Görseli](https://firebasestorage.googleapis.com/v0/b/englishwordapp-7fb3b.firebasestorage.app/o/uygulamagorsel%2Fs%C3%B6zl%C3%BCk.png?alt=media&token=97ff8439-0abc-47a5-9563-7aea59fc2c35) ![Kelime Detayları Görseli](https://firebasestorage.googleapis.com/v0/b/englishwordapp-7fb3b.firebasestorage.app/o/uygulamagorsel%2FS%C3%B6zl%C3%BCk_kelime_detaylar%C4%B1%5B1%5D.png?alt=media&token=cb1e6f12-2a4d-42ab-87d9-b24788651baf)

---

# ProfileActivity.kt
## 📌 Temel Özellikler
- Firebase Authentication ile kullanıcı giriş bilgileri gösterilir.
- Kullanıcı adı, e-posta ve profil resmi gibi bilgiler görüntülenir.
- **Admin girişi** için gizli bir kod sistemi içerir.
- Kullanıcı ayarları menüsü popup olarak açılır.
- Sekmeli yapı: `Quiz Kelimeleri` ve `Öğrenilenler` tabları içerir.
- Geri bildirim için mail gönderme ve oturum kapatma özellikleri bulunur.

![Profil Sayfası Görseli](https://firebasestorage.googleapis.com/v0/b/englishwordapp-7fb3b.firebasestorage.app/o/uygulamagorsel%2Fprofil_sayfas%C4%B1%5B1%5D.png?alt=media&token=83bde368-8039-48b7-8177-e3bc3e762efd) 

### ✨ Yapay Zekâ Destekli Hikâye Üretimi
- Kullanıcı 5 İngilizce kelime seçer
- Bu kelimelerden:
  - Open Router API ile **hikâye yazılır**
  - Pollinations ile **hikâye temasına %100 uygun görsel** üretilir.

![Yapay Zeka Sayfası Görseli](https://firebasestorage.googleapis.com/v0/b/englishwordapp-7fb3b.firebasestorage.app/o/uygulamagorsel%2Fuapay%20zeka%202.png?alt=media&token=b76c52f3-ff00-4c4a-ad0b-63a282e4518f) 
 
---

# 🧠 Bulmaca Oyunu (5 Harfli İngilizce Kelime Tahmin Oyunu)

Bu ekran, kullanıcıların 5 harfli İngilizce kelimeleri tahmin etmeye çalıştığı interaktif bir bulmaca oyunudur. Oyunda kullanıcıya sınırlı sayıda hak verilir ve doğru tahminlerle skor kazanır. Wordle benzeri bir yapıya sahiptir.

## 🚀 Özellikler

- 📚 Üç farklı kelime kaynağından seçim:
  - Tüm kelimeler (veritabanındaki tüm 5 harfli kelimeler)
  - Öğrenilmiş kelimeler (kullanıcının daha önce öğrendiği kelimeler)
  - Quiz'de karşılaşılan kelimeler

- 🎮 6 tahmin hakkı
- 🌈 Renkli geri bildirim sistemi:
  - 🟩 Doğru harf ve doğru yer
  - 🟨 Doğru harf ama yanlış yer
  - ⬛ Yanlış harf
- 🧩 Grid tabanlı oyun alanı (EditText hücreleriyle)
- 💡 İpucu sistemi
- ⚙️ Ayarlar penceresi ile kelime kaynağı seçimi
- 📈 Skor takibi
- 🔄 Yeni oyun başlatma butonu
- 🔄 Firestore ile dinamik kelime verisi
- 🔐 Firebase Authentication ile kullanıcı bazlı kayıt

![Yapay Zeka Sayfası Görseli](https://firebasestorage.googleapis.com/v0/b/englishwordapp-7fb3b.firebasestorage.app/o/uygulamagorsel%2FPHONE%5B1%5D.png?alt=media&token=0cf286c8-5986-40be-bfb3-25e8d694d97a) 

---

# 📄 RaporPage - İngilizce Kelime Uygulaması Raporlama Ekranı
## 🚀 Temel Özellikler
- Kullanıcının doğru/yanlış cevap sayısını görüntüleme
- Son doğru yanıt verilen tarihi gösterme
- Başarı oranı hesaplama
- Kullanıcı verilerini Firestore'a güncelleme
- PDF formatında kişisel öğrenim raporu oluşturma ve paylaşma

![Rapor Sayfası Görseli](https://firebasestorage.googleapis.com/v0/b/englishwordapp-7fb3b.firebasestorage.app/o/uygulamagorsel%2Fraporlama%5B1%5D.png?alt=media&token=b183b4b3-498b-491f-82f7-c941654e3464) ![PDF Sayfası Görseli](https://firebasestorage.googleapis.com/v0/b/englishwordapp-7fb3b.firebasestorage.app/o/uygulamagorsel%2Fpdf_olu%C5%9Fturma%5B1%5D.png?alt=media&token=1e98eef8-b74d-45fd-8f3c-40c71a77d244) 


---

## 📦 Kullanılan Teknolojiler

| Katman               | Teknoloji / Araçlar                                                                 |
|----------------------|-------------------------------------------------------------------------------------|
| ☁️ **Backend**        | Firebase Authentication, Firebase Firestore, Firebase Storage                     |
| 🧠 **Yapay Zekâ**      | OpenAI API (GPT-4), PollinationsAI / Wiro AI                                      |
| 🔊 **Seslendirme**     | Android TextToSpeech (TTS)                                                        |
| 🎨 **UI / UX**         | Material Design, Lottie Animations, ViewFlipper, BottomSheetDialog, TabLayout, PopupMenu |
| 📱 **Android Geliştirme** | Kotlin, Android SDK, View Binding, Activity & Fragment yapısı, ViewPager2             |
| 🖼️ **Görsel İşleme**    | Picasso, RoundedCornersTransformation, Lottie                                     |
| 🧾 **PDF & Raporlama**  | PdfDocument (Android), FileProvider, Intent ile dosya paylaşımı                   |
| 🧩 **Eğitsel Oyun**     | Puzzle Game Logic (özelleştirilmiş)                                               |
| 🗂️ **Listeleme**        | RecyclerView, Custom Adapter                                                      |



## 📄 Lisans
```text
Bu proje eğitim ve kişisel kullanım amacıyla geliştirilmiştir.İzinsiz dağıtılması veya kullanılması önerilmez.
```

## 📬 İletişim

Her türlü soru, öneri veya iş birliği için aşağıdaki adresten bana ulaşabilirsiniz:

- 📧 E-posta: [penlinguapp@gmail.com](mailto:penlinguapp@gmail.com)


