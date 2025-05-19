# DevHub

## 📱 Overview

DevHub is an Android application that provides a clean and intuitive interface for browsing GitHub
users and their repositories. Built with modern Android development practices, the app showcases a
well-structured clean architecture approach.

## ✨ Features

- Browse GitHub users list
- View detailed user profiles
- Explore user repositories
- Open repositories in a built-in webview

## 🏗 Architecture

This project follows Clean Architecture principles with a modular approach:

- **app**: Main application module containing UI components, navigation, and DI setup
- **domain**: Contains use cases that encapsulate business logic
- **domain-model**: Holds the core business models
- **data**: Implements data sources, repositories, and API services
- **common**: Shared utilities and components

### Clean Architecture Layers

```
UI (app) -> Domain (domain) -> Data (data)
             ↑
      Models (domain-model)
```

## 🛠 Tech Stack

- **Kotlin**: 100% Kotlin codebase
- **Jetpack Compose**: Modern UI toolkit for native UI
- **Coroutines & Flow**: For asynchronous operations
- **Hilt**: For dependency injection
- **Retrofit**: Type-safe HTTP client
- **MVVM**: Architecture pattern with ViewModel
- **Navigation Compose**: For in-app navigation
- **Clean Architecture**: For separation of concerns

## 📦 Dependencies

- AndroidX libraries
- Kotlin Coroutines
- Jetpack Compose
- Material Design 3
- Retrofit & OkHttp
- Dagger Hilt

## 🚀 Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/nyinyiz/DevHub.git
```

### 2. Open in Android Studio

- Launch **Android Studio**
- Select **Open an existing project**
- Navigate to the cloned `devhub` repository folder and open it

### 3. Sync Project

- Click **File > Sync Project with Gradle Files** to download dependencies

### 4. Run the App

- Connect a physical Android device or start an emulator (API 24 or higher)
- Click the **Run** button ▶️ to build and install the app

## ⚙️ Configuration

### GitHub API Token

The app uses the public GitHub API. To increase rate limits and avoid hitting the public API limits:

1. Generate a [GitHub Personal Access Token](https://github.com/settings/tokens)
2. Add it to your `local.properties` file:

```properties
auth-token=your_token_here
```

## 🔧 Requirements

- Android Studio Arctic Fox or newer
- Minimum SDK: 24
- Target SDK: Current latest version
- JDK 17

## 👤 Author

Created by Nyi Nyi Zaw