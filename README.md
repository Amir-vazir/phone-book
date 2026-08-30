# 📒 AddressBook – Phonebook Project
A console-based
phonebook application built with Java. Supports bilingual interface (English/Persian) and dynamic contact management using ArrayList.
---
📖 **[Read this in Persian / مطالعه به فارسی ](README.fa.md)**
---
## ✨ Features
- Add a contact (name and phone number)
- Input validation (empty names are rejected)
- Display the full contact list
- Advanced contact search by name (even with partial name)
- Edit contact information
- Delete a contact
- Bilingual interface (English/Persian) – language selection at startup
- Unlimited capacity (using ArrayList instead of fixed arrays)
---
## 🛠️ Technical Details
- **Language:** Java
- **Data Structure:** `ArrayList<String>` for names and phone numbers (no capacity limit)
- **Storage Type:** In-memory (RAM) – temporary
- **Internationalization:** `ResourceBundle` with custom `UTF8Control` for proper Persian character encoding
- **Search:** substring‑based and case‑insensitive, displaying all matching results
---
## 🚀 How to Run
```bash
javac Main.java
java Main
```
---
## 📦 Releases
- [v1.0](https://github.com/Amir-vazir/phone-book/releases/tag/v1.0) – Phase 1: Console menu with array storage
- [v2.0](https://github.com/Amir-vazir/phone-book/releases/tag/v2.0) – Phase 2: Bilingual support (i18n)
- [v3.0](https://github.com/Amir-vazir/phone-book/releases/tag/v3.0) – Phase 3: `ArrayList`, edit and delete contacts
- [v3.5](https://github.com/Amir-vazir/phone-book/releases/tag/v3.5) – Phase 3.5: Advanced search (partial & multiple results)
---
## 📁 Project Structure
```
phoneBook/ ├── Main.java
├── messages_en.properties
└── messages_fa.properties
```
---
## 📝 Notes
- This project is currently in active development; more features will be added in future phases.
- Data is currently stored only in temporary memory (RAM) and will be lost after closing the program.
- For proper display of Persian messages, make sure your terminal uses UTF-8 encoding (on Windows: run `chcp 65001` before executing).
---
## 📜 License
This project is provided for educational purposes only and has no specific license.

