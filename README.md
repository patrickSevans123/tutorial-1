Deploy link:
https://adprotutorial-patricksevans123.koyeb.app/
### SonarCloud Report
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=hilaldfzn_tutorial-1&metric=coverage)](https://sonarcloud.io/summary/new_code?id=patrickSevans123_tutorial-1)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=patrickSevans123_tutorial-1&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=patrickSevans123_tutorial-1)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=patrickSevans123_tutorial-1&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=patrickSevans123_tutorial-1)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=patrickSevans123_tutorial-1&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=hilaldfzn_tutorial-1)

# Tutorial 1
<details close>
<summary>Click here</summary>

## Reflection 1
Saya telah mencoba untuk melakukan beberapa prinsip clean code, seperti meaningful names dengan menamakan fungsi, file, dan variabel yang merepresentasikan kegunaannya. Saya juga telah menggunakan functions yang dapat mempersingkat kode dan meningkatkan readability. Kedua prinsip clean code ini sangat membantu saya dalam melakukan pemrograman karena saya dapat dengan mudah me-recall kembali apa yang dilakukan oleh kode yang saya tulis. Selain itu, saya sudah mencoba menulis beberapa comments. Akan tetapi, comments yang saya tulis belum cukup merepresentasikan detail dari pekerjaan saya. Saya juga belum menerapkan error-handling, sehingga error-error yang terjadi masih memunculkan tampilan error basic pada website. Kesalahan yang saya lakukan adalah membiarkan error masih terus terjadi dan belum melakukan validasi pada input. Hal ini mengakibatkan input-input yang tidak valid seperti angka negatif pada kuantitas dapat terinput. Ke depannya, saya akan belajar cara melakukan validasi input dan error-handling.
</br>

## Reflection 2
Saya merasa senang setelah mencoba membuat unit-test dan functional-test karena ini merupakan kali pertama saya membuat fungsi untuk testing. Menurut saya, kuantitas dari unit test tidak menjadi hal yang utama. Hal terpenting yang wajib diperhatikan pada error-handling adalah cakupan test case. Semakin banyak test case yang dapat dihandle, semakin baik pula unit-test tersebut. Menurut saya, 100% code coverage tidak menjamin suatu kode tidak memiliki error karena bisa saja ada test case yang tidak tercakup pada kode tersebut. Apabila saya harus membuat functional test untuk memverifikasi jumlah produk, saya tidak akan membuat class baru dan membuatnya di `CreateProductFunctionalTest.java` saja untuk mengurangi redundansi. Saya merasa test-test yang telah saya buat sudah cukup baik, tetapi dapat di-improve dengan meningkatkan cakupan test case yang dapat di-handle.
</details>
</br>

# Tutorial 2
<details close>
<summary>Click here</summary>

Selama mengerjakan *exercise*, saya menggunakan dua *code analyzer*, yaitu PMD dan Sonarcloud. Pertama, saya memperbaiki *code issues* yang ditemukan oleh PMD. Saya memperbaiki kode *import* yang tidak diperlukan. Kemudian, saya memperbaiki modifier public yang ada di berkas ProductService.java. Setelah itu, saya mencoba menggunakan SonarCloud Code Analyzer. SonarCloud tidak menemukan adanya *issue* pada kode saya. Setelah saya memperbaiki *issues*, saya rasa kode saya sudah cukup baik.

Saya merasa bahwa kode yang saya buat telah memenuhi persyaratan CI/CD karena saya telah melakukan beberapa hal. Pertama, saya sudah membuat `ci.yml` untuk melakukan *testing* setiap melakukan *push/pull* pada setiap *branch*. Saya juga telah membuat `pmd.yml` dan `sonarcloud.yml` untuk menguji kualitas kode. Selain itu, saya juga menggunakan `scorecard.yml` untuk menguji keamanan kode. Koyeb, PaaS yang saya gunakan, menyediakan beberapa CI/CD yang mengotomasi *deployment* setiap terdapat *push*.
</details>
</br>

# Tutorial 3
<details open>
<summary>Click here</summary>

Saya telah menggunakan prinsip-prinsip SOLID pada projek ini, yaitu:
- Single Responsibility Principle (SRP)</br>
Saya telah mengimplementasikan SRP dengan menerapkan konsep satu berkas satu tanggung jawab yang berarti tidak boleh ada suatu file yang memiliki dua tanggung jawab sekaligus. Hal ini saya terapkan dengan memisahkan ProductController dengan CarController pada berkas yang berbeda, sehingga setiap berkas memiliki tanggung jawabnya masing-masing.

- OCP (Open-Closed Principle)</br>
Saya membuat ProductService dan CarService *interface* untuk menerapkan konsep OCP. Adanya kedua interface tersebut memberikan kemampuan untuk melakukan suatu penambahan dari *interface* tanpa perlu mengubah konsep *method* yang ada pada *interface*.

- Liskov Substitution Principle (LSP)</br>
LSP merupakan suatu konsep dimana objek dari *superclass* dapat digantikan oleh objek dari *subclass* tanpa mengubah kebenaran dari kode. Objek CarServiceImpl dapat digunakan sebagai pengganti dari objek CarService yang merupakan *super class* dari CarServiceImpl.

- ISP (Interface Segregation Principle)</br>
Saya mengimplementasikan ISP dengan memisahkan CarService dan ProductService karena keduanya memiliki perbedaan perilaku. Hal ini juga dilakukan agar *interface* yang ada menjadi lebih kecil dan spesifik.

- DIP (Dependency Inversion Principle)</br>
DIP adalah konsep abstraksi dari suatu prosedur harus diutamakan dan abstraksi harus menjadi dasar dari konsep-konsep detail. Saya menerapkan konsep ini dengan menggunakan objek dari kelas CarService dibanding dengan objek dari kelas CarServiceImpl untuk meningkatkan fleksibilitas.

Keuntungan menerapkan prinsip SOLID:</br>
- Kode lebih mudah dibaca dan dipahami.
- Mudah melakukan perubahan karena kode lebih terstruktur.
- Kemudahan dalam pembuatan *test* karena *method-method* yang ada tidak terlalu panjang.
- Mudah melakukan *tracing error* karena kode yang ada terstruktur.

Kerugian menerapkan prinsip SOLID:</br>
- Kode sulit dibaca dan dipahami.
- Sulit melakukan perubahan karena kode yang tidak terstruktur.
- Kesulitan dalam membuat *test* karena *method-method* yang ada terlalu panjang dan sulit dimengerti.
- Mudah melakukan *tracing error* karena kode yang ada tidak terkonsep dengan baik.
</details>