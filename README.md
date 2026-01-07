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

## 📂 Struktur Folder (WAJIB IKUT – Untuk Tim Pemula)

Struktur ini **bukan hiasan**. Semua file **HARUS** ditempatkan sesuai kategori.
Jika file salah tempat, **Pull Request akan ditolak**.

---

### 🟦 UI Layer (Jetpack Compose)

```text
ui/
├── components/            # UI kecil & reusable (TIDAK ADA LOGIC)
│   ├── ProductivityCircleCard.kt
│   ├── WeeklyChart.kt
│   └── TaskCard.kt
│
├── screens/               # Halaman utama aplikasi
│   ├── login/
│   │   ├── LoginScreen.kt
│   │   └── LoginViewModel.kt
│   └── dashboard/
│       ├── DashboardScreen.kt
│       └── DashboardViewModel.kt
│
├── navigation/            # NavGraph & Route
│   └── NavGraph.kt
│
└── theme/                 # Warna, Font, Theme
    ├── Color.kt
    ├── Type.kt
    └── Theme.kt
```

📌 **Aturan UI:**

* `components` ➜ hanya UI, tidak tahu Firebase
* `screens` ➜ UI + ViewModel

---

### 🟨 Domain Layer (LOGIC MURNI – Tanpa Android)

```text
domain/
├── model/                 # Model utama aplikasi
│   ├── Task.kt
│   └── User.kt
│
└── repository/            # Kontrak (Interface)
    ├── AuthRepository.kt
    └── TaskRepository.kt
```

📌 **Aturan Domain:**

* Tidak boleh import `android.*`
* Tidak tahu Firebase

---

### 🟧 Data Layer (Firebase / API)

```text
data/
├── model/                 # DTO / Firebase Model
│   └── TaskDto.kt
│
├── remote/                # Akses Firebase langsung
│   ├── AuthDataSource.kt
│   └── TaskRemoteDataSource.kt
│
└── repository/            # Implementasi domain repository
    ├── AuthRepositoryImpl.kt
    └── TaskRepositoryImpl.kt
```

📌 **Aturan Data:**

* Semua Firebase hanya di sini

---

### 🟪 Dependency Injection

```text
di/
└── AppModule.kt
```

---

### ⚪ Utils

```text
util/
├── Result.kt
├── Constants.kt
└── Formatter.kt
```

---

text
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

````

---

## ⚡ Setup Project (LANGKAH DEMI LANGKAH)

### 1️⃣ Install Tools (WAJIB)

- **Android Studio Hedgehog / Iguana**
- **JDK 17 atau 21**
- **Git**

Cek instalasi Git:
```bash
git --version
````

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

---

## 🧑‍🤝‍🧑 Cara Kerja Tim Menggunakan GitHub Issues

### ❗ Aturan Penting

> **Setiap Issue WAJIB menyebutkan lokasi file yang harus dibuat.**
> PR akan ditolak jika file tidak sesuai struktur.

---

## 🧾 TEMPLATE GITHUB ISSUE (WAJIB DIPAKAI)

Gunakan template ini saat membuat Issue baru.

```markdown
## 🎯 Scope Tugas
Jelaskan secara singkat apa yang harus dikerjakan.

## 📁 Lokasi File (WAJIB)
Tuliskan folder & file yang harus dibuat atau diubah.

## 🚫 Batasan
Apa yang TIDAK boleh dilakukan di Issue ini.

## ✅ Definition of Done
Checklist tanda Issue selesai.
```

---

## 🗺️ Mapping Issue → Lokasi File

| Jenis Issue    | Folder               | Contoh File                 |
| -------------- | -------------------- | --------------------------- |
| UI Component   | `ui/components/`     | `ProductivityCircleCard.kt` |
| Screen UI      | `ui/screens/`        | `LoginScreen.kt`            |
| ViewModel      | `ui/screens/**/`     | `DashboardViewModel.kt`     |
| Auth Logic     | `domain/repository/` | `AuthRepository.kt`         |
| Firebase Auth  | `data/repository/`   | `AuthRepositoryImpl.kt`     |
| Firestore Task | `data/repository/`   | `TaskRepositoryImpl.kt`     |
| Navigation     | `ui/navigation/`     | `NavGraph.kt`               |
| Theme          | `ui/theme/`          | `Color.kt`                  |

---

📌 **Contoh Issue UI (Benar):**

```markdown
## 🎯 Scope Tugas
Membuat kartu ringkasan produktivitas.

## 📁 Lokasi File
ui/components/ProductivityCircleCard.kt

## 🚫 Batasan
- Tidak boleh akses Firebase
- Data via parameter

## ✅ Definition of Done
- UI tampil
- Preview tersedia
```

---

📌 **Contoh Issue Logic (Benar):**

```markdown
## 🎯 Scope Tugas
Membuat Auth Repository.

## 📁 Lokasi File
domain/repository/AuthRepository.kt
data/repository/AuthRepositoryImpl.kt

## 🚫 Batasan
- UI tidak disentuh

## ✅ Definition of Done
- Login berhasil
- Error handling
```

---

(WAJIB DIBACA PEMULA)

Bagian ini menjelaskan **bagaimana setiap anggota tim bekerja menggunakan Issue** yang sudah Anda buat.

> 🎯 Tujuan sistem Issue ini adalah agar:
>
> * Tidak ada kerja dobel
> * Semua orang tahu sedang mengerjakan apa
> * Progress mudah dipantau meskipun tim pemula

---

## 🔁 Alur Kerja Singkat (Overview)

```text
Pilih Issue → Assign diri sendiri → Buat Branch → Kerjakan → Commit → Pull Request → Review → Merge
```

❗ **Aturan utama:**

* 1 orang = 1 Issue
* 1 Issue = 1 Branch

---

## 1️⃣ Memahami Jenis Issue

Setiap Issue sudah dipisahkan agar **tidak saling tabrakan**:

### 🟦 UI / Widget Issue

Contoh:

* `[UI] Widget: Productivity Circle Card`
* `[SCREEN] Login Screen Implementation`

👉 Cocok untuk:

* Pemula
* Fokus tampilan
* Tidak perlu paham Firebase

Tugas yang dikerjakan:

* Jetpack Compose UI
* Layout, warna, typography
* Ambil data dari ViewModel (dummy dulu boleh)

---

### 🟨 Logic / Service Issue

Contoh:

* `[FEATURE] Authentication Service`
* `[FEATURE] Firestore Task Repository`

👉 Cocok untuk:

* Yang suka logic
* Sedikit lebih advance

Tugas yang dikerjakan:

* Repository
* Firebase Auth / Firestore
* Flow, Result, Error handling

---

### 🟩 Integration / Screen Issue

Contoh:

* `[SCREEN] Dashboard Screen & Integration`

👉 Cocok untuk:

* Yang sudah paham alur project

Tugas yang dikerjakan:

* Menggabungkan UI + ViewModel + Repository
* Navigation

---

## 2️⃣ Cara Mengambil Issue (STEP BY STEP)

1. Buka tab **Issues** di GitHub
2. Pilih Issue yang statusnya **To Do**
3. Baca deskripsi sampai habis
4. Klik **Assign yourself**

🚫 Jangan ambil Issue yang sudah di-assign orang lain

---

## 3️⃣ Membuat Branch dari Issue

Setelah assign:

```bash
git checkout main
git pull origin main
git checkout -b feat/nama-issue-singkat
```

Contoh:

```bash
git checkout -b feat/productivity-card
```

---

## 4️⃣ Cara Mengerjakan Issue dengan Benar

### Gunakan Checklist di Issue

Setiap Issue punya checklist:

```text
- [ ] UI Card dibuat
- [ ] Progress logic selesai
- [ ] Warna sesuai theme
```

👉 Checklist ini **HARUS dicentang satu per satu**

---

### Jika Issue Terlalu Besar

✅ Boleh:

* Tanya di grup
* Diskusi di komentar Issue

❌ Jangan:

* Mengubah scope tanpa izin

---

## 5️⃣ Commit yang Benar

Commit **SEDIKIT tapi JELAS**

Contoh:

```bash
git add .
git commit -m "ui: add productivity circle card"
```

❌ Jangan:

```text
update
fix bug
```

---

## 6️⃣ Push & Pull Request (PR)

```bash
git push origin feat/productivity-card
```

Lalu:

1. Buka GitHub
2. Klik **Compare & Pull Request**
3. Isi deskripsi PR:

```text
- Issue: #12
- Fitur: Productivity Card
- Catatan: UI only, logic dummy
```

---

## 7️⃣ Review & Merge

* Minimal **1 reviewer**
* Jika ada revisi → update commit
* Setelah approved → merge ke `main`

🎉 Issue dianggap **DONE**

---

## 🧠 Contoh Pembagian Kerja (Nyata)

| Nama  | Issue                 | Fokus           |
| ----- | --------------------- | --------------- |
| Andi  | UI Productivity Card  | Compose UI      |
| Budi  | Auth Repository       | Firebase        |
| Citra | Login Screen          | UI + Navigation |
| Deni  | Dashboard Integration | ViewModel       |

---

## ⚠️ Kesalahan Umum Pemula

❌ Langsung commit ke `main`
❌ Ambil Issue tanpa assign
❌ Kerja di satu branch rame-rame
❌ Tidak baca deskripsi Issue

---

## ✅ Prinsip Utama

> **Issue adalah kontrak kerja.**
> Kerjakan sesuai deskripsi, bukan asumsi.

---
