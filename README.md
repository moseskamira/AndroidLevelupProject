# 🚀 Android LevelUp Project (MVP)

<p align="center">
  <img src="https://img.shields.io/badge/Android-Kotlin-3DDC84?style=for-the-badge&logo=android" />
  <img src="https://img.shields.io/badge/Architecture-MVP-blue?style=for-the-badge" />
  <img src="https://img.shields.io/badge/API-GitHub-black?style=for-the-badge&logo=github" />
  <img src="https://img.shields.io/badge/Platform-Android-green?style=for-the-badge" />
</p>

<p align="center">
  <b>A clean Android app that displays GitHub developers in Nairobi using the GitHub API.</b>
</p>

---

## 📱 Overview

Android LevelUp Project is a native Android application that demonstrates real-world Android development using the **GitHub API**.

It allows users to:
- 👨‍💻 Browse developers in Nairobi
- 🔍 View detailed developer profiles
- 📤 Share developer profiles via Android intents

Built with a focus on **MVP architecture**, clean code, and scalability.

---

## ✨ Features

- 📡 Fetch developers from GitHub API
- 📃 Display users in Linear & Grid RecyclerView
- 👤 View detailed profile screen
- 📤 Share developer profile
- 🎨 Clean UI design
- 🧱 MVP architecture

---

## 🧠 Architecture (MVP)

### Layers

- **Model**
    - DTO (API response models)
    - Entity (Domain models)
    - Repository (Data handling)
    - Mapper (DTO ↔ Entity)

- **View**
    - Activities
    - Fragments
    - Adapters

- **Presenter**
    - Business logic
    - API coordination
    - UI state updates

---

### 🔁 Project Structure

```text

│
├── data
│   ├── network
│   │   ├── api
│   │   │   ├── APIClient
│   │   │   └── APIService
│   │   │
│   │   ├── responses
│   │   │   └── NetworkResponse
│   │   │
│   │   └── dto
│   │       ├── GithubUserDto
│   │       └── GithubUsersResponseDto
│   │
│   └── repositories
│       └── DeveloperRepositoryImpl
│
├── domain
│   ├── models
│   │   ├── GithubUser
│   │   └── GithubUsersResponse
│   │
│   └── repositories
│       └── DeveloperRepository
│
├── presentation
│   ├── developers
│   │   ├── DevelopersActivity
│   │   ├── DevelopersContract
│   │   ├── DevelopersPresenter
│   │   └── DevelopersAdapter
│   │
│   ├── developer_profile
│   │   ├── DeveloperProfileActivity
│   │   ├── DeveloperProfileContract
│   │   └── DeveloperProfilePresenter
│   │
│   └── common
│       ├── BasePresenter
│       ├── BaseView
│       └── UIState
│
├── di
│   └── AppModule
│
├── utils
│   ├── Constants
│   ├── Extensions
│   ├── NetworkUtils
│   └── Resource
│
└── MainApplication
```



### Linear Display of List of All Developers In Nairobi
![List developers](/wireframes/linear.gif)

### Grid Display of List of All Developers In Nairobi
![List developers](/wireframes/grid.png)


### Developer Profile Details
![List developers](/wireframes/profile2.png)

### Launched Share Intent of Developer Profile
![List developers](/wireframes/shared.png)

