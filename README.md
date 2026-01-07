# 🚀 Lazee Tracker (Android Native)

![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-7F52FF?style=for-the-badge\&logo=kotlin\&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge\&logo=android\&logoColor=white)
![Compose](https://img.shields.io/badge/Jetpack%20Compose-Material3-4285F4?style=for-the-badge\&logo=jetpackcompose\&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge\&logo=firebase\&logoColor=black)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-In%20Development-orange?style=for-the-badge)

---

## 📱 Tentang Project

**Lazee Tracker** adalah aplikasi Android berbasis *gamification* yang membantu pengguna mengelola tugas harian sekaligus memantau tingkat produktivitas (dan kemalasan 😄).

Project ini **dibuat untuk tim pemula**, sehingga:

* Struktur kode dibuat **jelas dan konsisten**
* Setiap layer punya tanggung jawab masing-masing
* Mudah dipelajari meskipun baru pertama kali Android Native

> 🎯 **Tujuan utama project ini:**
>
> * Belajar Android Native modern
> * Memahami arsitektur MVVM + Clean Architecture
> * Terbiasa kerja tim menggunakan Git & GitHub

---

## ✨ Fitur Utama

* **Google Sign-In**
  Login menggunakan akun Google (tanpa password)

* **Laziness Meter**
  Indikator visual untuk melihat seberapa produktif pengguna setiap hari

* **Kanban Board**
  Manajemen tugas dengan 3 kolom:

  * To Do
  * In Progress
  * Done

* **Weekly Analytics**
  Grafik produktivitas mingguan dengan batas *Zona Malas*

* **Cloud Sync**
  Data otomatis tersimpan di Firebase dan bisa dibuka di device lain

* **Dark Mode**
  Tema gelap untuk kenyamanan mata

---

## 🧠 Tech Stack (Penjelasan untuk Pemula)

| Teknologi              | Digunakan untuk                       |
| ---------------------- | ------------------------------------- |
| **Kotlin**             | Bahasa pemrograman utama Android      |
| **Jetpack Compose**    | Membuat UI tanpa XML                  |
| **Material3**          | Standar desain UI Android terbaru     |
| **MVVM**               | Memisahkan UI, Logic, dan Data        |
| **Clean Architecture** | Struktur project agar rapi & scalable |
| **Hilt**               | Mengelola dependency otomatis         |
| **Coroutines**         | Menjalankan proses async              |
| **StateFlow**          | Mengelola state UI                    |
| **Firebase Auth**      | Login Google                          |
| **Firestore**          | Database cloud                        |

---

## 🏗 Arsitektur Project (Wajib Dipahami)

Project ini **TIDAK BOLEH** ditulis asal-asalan.

### Alur Data Sederhana

```text
UI (Compose)
   ↓
ViewModel
   ↓
UseCase / Repository
   ↓
Firebase (Auth / Firestore)
```

❌ UI tidak boleh langsung akses Firebase
❌ ViewModel tidak boleh mengandung kode UI

---

## 📂 Struktur Folder (Detail)

```text
com.lazee.tracker
│
├── data/                  # Mengambil & menyimpan data
│   ├── model/             # Model dari Firebase (DTO)
│   ├── remote/            # Akses Firestore / Auth
│   └── repository/        # Implementasi repository
│
├── domain/                # Logika bisnis (Pure Kotlin)
│   ├── model/             # Model utama app (Task, User)
│   └── repository/        # Interface repository
│
├── di/                    # Dependency Injection
│   └── AppModule.kt
│
├── ui/                    # Tampilan (Compose)
│   ├── components/        # UI kecil & reusable
│   ├── theme/             # Warna, Font, Theme
│   ├── screens/           # Halaman
│   │   ├── login/
│   │   └── dashboard/
│   └── navigation/        # NavGraph
│
└── util/                  # Helper (Result, Formatter)
```

---

## ⚡ Setup Project (LANGKAH DEMI LANGKAH)

### 1️⃣ Install Tools (WAJIB)

* **Android Studio Hedgehog / Iguana**
* **JDK 17 atau 21**
* **Git**

Cek instalasi Git:

```bash
git --version
```

---

### 2️⃣ Clone Repository

```bash
git clone https://github.com/username-anda/lazee-tracker.git
cd lazee-tracker
```

---

### 3️⃣ Setup Firebase (PALING SERING SALAH)

❗ File `google-services.json` **TIDAK ADA** di GitHub

Langkah:

1. Buka Firebase Console
2. Pilih project Lazee Tracker
3. Download `google-services.json`
4. Simpan ke:

```text
lazee-tracker/app/google-services.json
```

---

### 4️⃣ Generate SHA-1 (UNTUK LOGIN GOOGLE)

1. Android Studio → **Gradle**
2. `Tasks > android > signingReport`
3. Double click `signingReport`
4. Cari **SHA-1 (debug)**
5. Daftarkan ke Firebase Console

---

### 5️⃣ Sync & Run

* File → Sync Gradle
* Pilih emulator / HP
* Klik ▶ Run

---

## 🤝 Cara Kerja Tim (WAJIB IKUT)

### Aturan Utama

* ❌ Dilarang commit ke `main`
* ✅ Semua kerja via branch

### Ambil Tugas

1. Buka tab **Issues**
2. Pilih tugas
3. Assign ke diri sendiri

### Buat Branch

```bash
git checkout -b feat/login-ui
```

### Commit

```bash
git add .
git commit -m "feat: add login screen UI"
```

### Push & Pull Request

```bash
git push origin feat/login-ui
```

Buat Pull Request ke `main`

---

## 🧼 Aturan Coding (Untuk Pemula)

### UI (Compose)

✅ Pisahkan UI besar jadi component kecil
❌ Jangan hardcode warna / ukuran

### ViewModel

* Gunakan `StateFlow`
* Gunakan `viewModelScope`
* Tidak boleh ada kode UI

### Penamaan

* File: `DashboardViewModel.kt`
* Icon: `ic_add_task.xml`
* String: `label_add_task`

---

## 🆘 Error Umum & Solusi

❓ **Build error**
➡ Cek JDK

❓ **Login Google gagal**
➡ SHA-1 belum didaftarkan

❓ **App crash**
➡ Baca Logcat (MERAH)

---

## 📌 Catatan Penting

> Lebih baik **kode sederhana tapi rapi** daripada kode kompleks tapi berantakan.

---

<p align="center">
Built with ❤️ by <b>Lazee Team</b>
</p>
