# 📒 AddressBook – Phonebook Project

A console‑based phonebook application built with **Java**, fully following **object‑oriented principles**, and supporting **bilingual interface** (English/Persian).
---
📖 **[Read this in Persian / مطالعه به فارسی ](README.fa.md)**
---
## ✨ Features
- Add a contact (name and phone number)
- Input validation (empty names are rejected)
- Add a company contact – optionally attach a company name
- Display the full contact list
- Advanced contact search by name (even with partial name)
- Edit contact information
- Delete a contact
- Bilingual interface (English/Persian) – language selection at startup
- Unlimited capacity (using ArrayList instead of fixed arrays)
- Adherence to four OOP principles: **Encapsulation, Inheritance, Polymorphism, and Abstraction**
---
## 🛠️ Technical Details
- **Language:** Java
- **Data Model** : `Contact` base class, `CompanyContact` subclass (inheritance) 
- **Data Structure:** `ArrayList<Contact>` (dynamic, unlimited) 
- **Storage Type:** In-memory (RAM) – temporary
- **Internationalization:** `ResourceBundle` with custom `UTF8Control` for proper Persian character encoding
- **Search:** substring‑based and case‑insensitive, displaying all matching results
- **UI** : Console‑based with `Scanner`, menu‑driven 
---
## 🚀 How to Run
1. Compile all Java files (they are in the `phonebook` package):
```bash
javac phonebook/*.java
```
2. Run the main class:
```bash
java phonebook.Main
```
3. Copy the messages_en.properties and messages_fa.properties files to the same folder as the compiled classes (inside the phonebook directory).
---
## 📦 Releases
- [v1.0](https://github.com/Amir-vazir/phone-book/releases/tag/v1.0) – Phase 1: Console menu with array storage
- [v2.0](https://github.com/Amir-vazir/phone-book/releases/tag/v2.0) – Phase 2: Bilingual support (i18n)
- [v3.0](https://github.com/Amir-vazir/phone-book/releases/tag/v3.0) – Phase 3: `ArrayList`, edit and delete contacts
- [v3.5](https://github.com/Amir-vazir/phone-book/releases/tag/v3.5) – Phase 3.5: Advanced search (partial & multiple results)
- [v4.0](https://github.com/Amir-vazir/phone-book/releases/tag/v4.0) – Phase 4: Adherence to four OOP principles + Company Contact
---
## 📁 Project Structure
```
phonebook/ ├── Displayable.java  -  Interface for displaying objects
├── Contact.java  -  Base contact class
├── CompanyContact.java  -  Subclass with company name
├── ContactManager.java  -  Interface for CRUD operations
├── AddressBook.java  -  Implementation using ArrayList<Contact>
├── BundleManager.java  -  Static wrapper for ResourceBundle
├── UTF8Control.java  -  Custom control for UTF‑8 properties
├── ConsoleUI.java  -  User interface logic
├── Main.java  -  Entry point
├── messages_en.properties  -  English resource bundle
└── messages_fa.properties  -  Persian resource bundle (UTF‑8)
```
---
## 📝 Notes
- This project is currently in active development; more features will be added in future phases.
- Data is currently stored only in temporary memory (RAM) and will be lost after closing the program.
- For proper display of Persian messages, make sure your terminal uses UTF-8 encoding (on Windows: run `chcp 65001` before executing).
---
## 📜 License
This project is licensed under the MIT License.


