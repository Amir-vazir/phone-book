# 📒 AddressBook – Phonebook Project

A console‑based phonebook application built with **Java**, fully following **object‑oriented principles**, and supporting **bilingual interface** (English/Persian).
---
📖 **[Read this in Persian / مطالعه به فارسی ](README.fa.md)**
---
## ✨ Features
- Add a contact (name and phone number)
- Input validation (empty names are rejected)
- Add a company contact – optionally attach a company name
- Display the full contact list, sorted alphabetically (case‑insensitive)
- Advanced contact search by name (even with partial name)
- Edit contact information
- Delete a contact
- Bilingual interface (English/Persian) – language selection at startup
- Robust error handling with a custom Exception hierarchy
- Unlimited capacity (using ArrayList instead of fixed arrays)
- Adherence to four OOP principles: **Encapsulation, Inheritance, Polymorphism, and Abstraction**
- Functional refactor with **Stream API**, **Predicate factories**, and **Optional**
---
## 🛠️ Technical Details
- **Language:** Java
- **Data Model** : `Contact` base class, `CompanyContact` subclass (inheritance) 
- **Data Structure:** `ArrayList<Contact>` (dynamic, unlimited) 
- **Storage Type:** In-memory (RAM) – temporary
- **Internationalization:** `ResourceBundle` with custom `UTF8Control` for proper Persian character encoding
- **Search:** substring‑based and case‑insensitive, displaying all matching results
- **Sorting:** via `Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER)`
- **Error Handling:** custom exception hierarchy with a clear separation between checked and unchecked exceptions
- **UI** : Console‑based with `Scanner`, menu actions are dispatched through a `Map<Integer, Runnable>`
---
## ⚠️ Exception Handling
Starting from Phase 5, all operations are guarded by a dedicated exception hierarchy:
**Hierarchy overview:**
```
Exception
└─ AddressBookException (checked)  →  Base class for all domain errors
   ├── ContactNotFoundException (checked)  →  A contact to edit or delete cannot be found
   └── DuplicateContactException (checked)  →  A contact with the same name is added
RuntimeException 
└── InvalidContactException (unchecked)  →  User input is invalid (e.g. empty name)
```
**Design rationale:**
- **Checked** exceptions are used for *recoverable*, *predictable* domain errors that the caller **must** handle.
- **Unchecked** exceptions (`InvalidContactException`) are used for *programming mistakes* or invalid input that the caller should not be forced to catch.
- The base `AddressBookException` allows catching all domain errors in one place, and makes it easy to add new exception types in the future.
- All error messages shown to the user are localized and loaded from the `.properties` files.
---
## 🧠 Functional Programming (Phase 6)
Starting from Phase 6, the data layer was rewritten using Java's functional idioms.
At the heart of this phase is a new overload:
```java
List<Contact> searchContacts(Predicate<Contact> predicate);
```
This method lets a programmer search the address book with any custom rule, without touching the AddressBook class itself. All that's needed is to write
their own Predicate. It's a demonstration of the Open/Closed Principle.
For example, searching by phone prefix:
```java
public List<Contact> searchByPhonePrefix(String prefix) {
return searchContacts(c -> c.getPhone().startsWith(prefix));
}
```
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
- [v5.0](https://github.com/Amir-vazir/phone-book/releases/tag/v5.0) – Phase 5: Custom exception hierarchy + duplicate prevention
- [v6.0](https://github.com/Amir-vazir/phone-book/releases/tag/v6.0) – Phase 6: Functional refactor — Stream API, Predicate, Optional, Map dispatch
---
## 📁 Project Structure
```
phonebook/ ├── Displayable.java  -  Interface for displaying objects
├── Contact.java  -  Base contact class + Predicate factories
├── CompanyContact.java  -  Subclass with company name
├── ContactManager.java  -  Interface for CRUD operations + functional search
├── AddressBook.java  -  Stream‑based implementation using List<Contact>
├── AddressBookException.java  -  Base checked exception for domain errors
├── ContactNotFoundException.java  -  Thrown when a contact is not found
├── DuplicateContactException.java  -  Thrown when a duplicate contact is added
├── InvalidContactException.java  -  Unchecked exception for invalid input
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


