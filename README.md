# 📒 Address Book
A bilingual (English/Persian) console phone book built with **Java 21** and **Maven**.
---
📖 **[Read this in Persian / مطالعه به فارسی ](README.fa.md)**
---
## ✨ Features
- Add, list, search, edit, and delete contacts
- Advanced contact search by name (even with partial name)
- Two contact types: plain and company
- Case-insensitive matching and alphabetical listing
- English and Persian UI, chosen at startup
- Robust error handling with a custom exception hierarchy
## 🎓 Skills demonstrated
- **OOP** — encapsulation, inheritance, polymorphism, abstraction
- **Functional Java** — `Predicate`, `Optional`, Stream API, method references
- **i18n** — `ResourceBundle` with a custom UTF-8 `Control`
- **Exception design** — clear separation between checked and unchecked
- **Testing** — JUnit 5 with nested test classes and helpers
- **Build** — Maven project layout and dependency management
## 📁 Project structure
```
src/ ├── main/java/com/myvazir/addressbook/
│ ├── Contact, CompanyContact — domain model
│ ├── ContactManager, AddressBook — business logic
│ ├── ConsoleUI, Main — presentation
│ ├── BundleManager, UTF8Control — i18n
│ └── *Exception — error hierarchy
├── main/resources/
│ └── messages*.properties — localization bundles
└── test/java/com/myvazir/addressbook/
└── *Test — JUnit 5 test suite
```
## 🔍 Technical highlights
- **Search** — `searchContacts` is overloaded: it accepts a keyword or any `Predicate<Contact>` (Open/Closed Principle)
- **Sorting** — `Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER)`
- **UI dispatch** — `Map<Integer, Runnable>` for menu actions
- **Storage** — in-memory `ArrayList<Contact>` (disk persistence planned)
- **Equality** — `Contact` defines `equals`/`hashCode` on case-insensitive name
### Exception hierarchy
```
Exception └── AddressBookException (checked) base for all domain errors
├── ContactNotFoundException (checked) contact to edit/delete not found
└── DuplicateContactException (checked) duplicate name on add
RuntimeException └── InvalidContactException (unchecked) invalid user input
```
Checked exceptions model recoverable domain errors. Unchecked exceptions model programmer errors. All userfacing
messages are localized.
## 🧪 Tests
Four JUnit 5 suites cover the domain model, business logic, and i18n:
-
**`ContactTest`** — construction, `nameContains`/`nameEquals` predicates, `equals`/`hashCode` contract
- **`CompanyContactTest`** — inheritance behavior and cross-type equality edge cases
- **`AddressBookTest`** — add (incl. duplicates), search (keyword and predicate), edit, delete, sorting
- **`ResourceBundleTest`** — key parity across English and Persian bundles
## 🚀 How to run
Requires **JDK 21** and **Maven**.
```bash
mvn test # run all tests
mvn package # build the jar
java -jar target/addressbook-1.0-SNAPSHOT.jar
```
## 📦 Releases
| Tag                                                                | Phase | Highlights |
|--------------------------------------------------------------------|---|---|
| [v1.0](https://github.com/Amir-vazir/phone-book/releases/tag/v1.0) | 1 | Console menu with fixed-array storage |
| [v2.0](https://github.com/Amir-vazir/phone-book/releases/tag/v2.0) | 2 | Bilingual support (i18n) |
| [v3.0](https://github.com/Amir-vazir/phone-book/releases/tag/v3.0) | 3 | `ArrayList`, edit and delete |
| [v3.5](https://github.com/Amir-vazir/phone-book/releases/tag/v3.5) | 3.5 | Substring search with multiple results |
| [v4.0](https://github.com/Amir-vazir/phone-book/releases/tag/v4.0) | 4 | Four OOP pillars, company contacts |
| [v5.0](https://github.com/Amir-vazir/phone-book/releases/tag/v5.0) | 5 | Exception hierarchy, duplicate prevention |
| [v6.0](https://github.com/Amir-vazir/phone-book/releases/tag/v6.0) | 6 | Functional refactor — Streams, Predicate, Optional |
| [v7.0](https://github.com/Amir-vazir/phone-book/releases/tag/v7.0) | 7 | Maven migration, JUnit 5 test suite |
## 📝 Notes
- Data is currently stored only in temporary memory (RAM) and will be lost after closing the program.
- For proper display of Persian messages, make sure your terminal uses UTF-8 encoding (on Windows: run `chcp 65001` before executing).
## 📜 License
MIT
