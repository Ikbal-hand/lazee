# 🚀 Lazee Tracker (Android Native)

![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-7F52FF?style=for-the-badge\&logo=kotlin\&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge\&logo=android\&logoColor=white)
![Compose](https://img.shields.io/badge/Jetpack%20Compose-Material3-4285F4?style=for-the-badge\&logo=jetpackcompose\&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge\&logo=firebase\&logoColor=black)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-In%20Development-orange?style=for-the-badge)

**Lazee Tracker** adalah aplikasi produktivitas modern berbasis *gamification* untuk memantau tugas harian dan tingkat "kemalasan" pengguna.
Aplikasi ini dibangun menggunakan **Android Native (Kotlin)** dengan arsitektur yang bersih, scalable, dan reaktif.

---

## ✨ Fitur Utama

* **Google Sign-In** — Login cepat dan aman tanpa password
* **Laziness Meter** — Visualisasi tingkat produktivitas harian
* **Kanban Board** — Manajemen tugas *drag-and-drop* (To Do, In Progress, Done)
* **Weekly Analytics** — Grafik mingguan dengan indikator *Zona Malas*
* **Cloud Sync** — Sinkronisasi real-time antar perangkat (Firestore)
* **Dark Mode** — Tampilan modern yang nyaman di mata

---

## 🛠 Tech Stack & Libraries

Project ini menggunakan standar pengembangan Android modern (2025):

| Kategori                 | Teknologi / Library         |
| ------------------------ | --------------------------- |
| **Language**             | Kotlin                      |
| **UI Framework**         | Jetpack Compose (Material3) |
| **Architecture**         | MVVM + Clean Architecture   |
| **Dependency Injection** | Hilt (Dagger)               |
| **Async / Threading**    | Coroutines & Kotlin Flow    |
| **Backend**              | Firebase Auth & Firestore   |
| **Navigation**           | Jetpack Navigation Compose  |
| **Image Loading**        | Coil                        |
| **Charts**               | Vico / MPAndroidChart       |

---

## 📂 Struktur Project

Project ini menerapkan **Simplified Clean Architecture**.
Mohon patuhi struktur berikut saat menambahkan file baru:

```text
com.lazee.tracker
├── data/
│   ├── model/
│   ├── remote/
│   └── repository/
│
├── di/
│   └── AppModule.kt
│
├── domain/
│   ├── model/
│   └── repository/
│
├── ui/
│   ├── components/
│   ├── theme/
│   ├── screens/
│   │   ├── login/
│   │   └── dashboard/
│   └── navigation/
│
└── util/
```

---

## ⚡ Cara Setup (Getting Started)

### 1. Clone Repository

```bash
git clone https://github.com/username-anda/lazee-tracker.git
cd lazee-tracker
```

### 2. Setup Firebase (WAJIB ⚠️)

File `google-services.json` **tidak di-commit** ke repository.

1. Minta file ke Project Manager atau Firebase Console
2. Letakkan di:

```text
lazee-tracker/app/google-services.json
```

### 3. Generate SHA-1

1. Android Studio → Gradle
2. `Tasks > android > signingReport`
3. Salin SHA-1 `debug`
4. Daftarkan ke Firebase

### 4. Sync & Run

* Gunakan JDK 17+
* Sync Gradle
* Pilih device
* Run ▶

---

## 🤝 Workflow Pengerjaan (Git Flow)

1. Branch `main` dilindungi
2. Gunakan Issues
3. Branch naming:

* `feat/nama-fitur`
* `fix/nama-bug`
* `ui/nama-ui`
* `chore/nama-task`

```bash
git checkout -b feat/dashboard-ui
```

4. Commit Message

* `feat:`
* `fix:`
* `ui:`
* `refactor:`

5. Pull Request ke `main`

---

## 📐 Coding Guidelines

### UI (Compose)

* Material3
* Gunakan MaterialTheme
* Hindari hardcode
* Reusable components

### ViewModel

* Gunakan StateFlow
* viewModelScope
* Tanpa android.*

### Resource Naming

* Icon: `ic_nama_icon.xml`
* String: `label_login_button`

---

## 🆘 Troubleshooting

**google-services.json missing** → cek folder `app/`

**Google Sign-In error** → cek SHA-1

**Gradle error** → cek JDK

---

<p align="center">
Built with ❤️ by <b>Lazee Team</b>
</p>
