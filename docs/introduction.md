![Maven Central](https://img.shields.io/maven-central/v/ai.botstacks/chat-sdk) ![GitHub issues](https://img.shields.io/github/issues/botstacks/compose-example)

![BotStacks](https://private-user-images.githubusercontent.com/106978117/287741102-b3a09579-49e9-44e3-a054-cf8c290d01b8.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MDg5NzAwMTgsIm5iZiI6MTcwODk2OTcxOCwicGF0aCI6Ii8xMDY5NzgxMTcvMjg3NzQxMTAyLWIzYTA5NTc5LTQ5ZTktNDRlMy1hMDU0LWNmOGMyOTBkMDFiOC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQwMjI2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MDIyNlQxNzQ4MzhaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0xMDhmMzZjYzFjZTAwOTk0Yjk3YTM1MDkwMDMwMDFmNWJmZmVhMzI1NTM4M2NlYTA2OTAzNmUyYzY3ZWJlMjJmJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.QMKOsYTGa70taEz9XIRls-_93iaex3C0mOFwrNlxbfA)


# BotStacks Kotlin Compose Multiplatform SDK

> Delightful chat for your Android apps

&nbsp;

# 📃 Table of Contents

- [Overview](#-overview)
- [Installation](#-installation)
- [Help](#-help)

&nbsp;

# ✨ Overview

This SDK integrates a fully serviced chat experience on the [BotStacks](https://botstacks.ai) platform.

&nbsp;

# ⚙ Installation

### Compose Multiplatform

Add `ai.botstacks:chat-sdk:{version}` to your dependencies

```gradle
val commonMain by getting {
    dependencies {
        [...]
+       implementation("ai.botstacks:chat-sdk:{version}")
        [...]
    }
}
```

### Android Only

If you are only targeting Android the dependency is:

```gradle
dependencies {
    [...]
+   implementation("ai.botstacks:chat-sdk-android:{version}")
    [...]
}
```

&nbsp;

# 🙋‍♂️ Help

If you don't understand something in the documentation, you are experiencing problems, or you just need a gentle nudge in the right direction, please join our [Discord server](https://discord.com/invite/5kwyQCz3zZ)

---

**All Content Copyright © 2023 BotStacks**
