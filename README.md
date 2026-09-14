<div align="center">

# The 7 Wonders

#### Unofficial companion app for 7 wonders board game

[![Release](https://img.shields.io/badge/release-2.2.1-blue)](https://github.com/ozarski/7W/releases/latest)

</div>

<img src="https://github.com/user-attachments/assets/93745b8a-1b52-40e6-9b1e-71136eae4124" alt="drawing" width="200" />
<img src="https://github.com/user-attachments/assets/93e0d9cb-017e-480b-9729-86c0ca68fc58" alt="drawing" width="200" /> 
<img src="https://github.com/user-attachments/assets/36609d52-8f13-4267-a783-70e0f0368690" alt="drawing" width="200" />
<br>
<img src="https://github.com/user-attachments/assets/816be0bc-7bfd-4198-8dc6-11ebdfa38730" alt="drawing" width="200" />
<img src="https://github.com/user-attachments/assets/25af502c-f6fc-42c6-a707-da6c5833011f" alt="drawing" width="200" />
<img src="https://github.com/user-attachments/assets/8228f24a-542b-4822-adf8-b3082b32d6b8" alt="drawing" width="200" />

## Overview

Android companion app for the [7 Wonders](https://en.wikipedia.org/wiki/7_Wonders_(board_game)) board
game. Track players, record games and their final points, and review a detailed point breakdown for
each player. Supports the official **Armada**, **Leaders**, **Cities** and **Buildings** DLCs,
including a green cards points calculator.

## Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose (Material 3)
- **Architecture:** MVVM + Repository pattern
- **DI:** Dagger Hilt
- **Local data:** Room (SQLite) with migrations and database import/export
- **Build:** Gradle (Kotlin DSL), Android Gradle Plugin, KSP

## Requirements

To build and run the project locally you need:

| Tool | Version |
|---|---|
| JDK | 17 or newer |
| Android Studio | Latest stable (Ladybug or newer) |
| Android SDK | `compileSdk` 36 with build tools |
| Android device | Physical device or emulator running **Android 8.0 (API 26) or higher** |

## Build & Run

### 1. Clone the repository

```bash
git clone git@github.com:ozarski/7W.git
cd 7W
```

### 2. Option A — Command line

Build a debug APK:

```bash
./gradlew assembleDebug
```

The APK will be generated at `app/build/outputs/apk/debug/app-debug.apk`.

To build, install and launch it on a running emulator or a connected device:

```bash
./gradlew installDebug
```

### 3. Option B — Android Studio

1. Open the project: **File → Open** and select the cloned `7W` directory.
2. Wait for Gradle sync to finish
3. Create an emulator if you don't have one: **Device Manager → Create virtual device** (any device profile with Android 8.0+).
4. Press **Run** with the emulator/device selected as target.

## Release

A ready-to-install APK is published on the [GitHub Releases](https://github.com/ozarski/7W/releases) page.

**Latest:** [2.2.1](https://github.com/ozarski/7W/releases/tag/2.2.1)

| | |
|---|---|
| Download APK | https://github.com/ozarski/7W/releases/latest/download/app-release.apk |

Installation: download the APK on your Android device, open the
file and confirm the install. If prompted, allow *install apps from unknown sources* for the
browser/file manager you're using.
