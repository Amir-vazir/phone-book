# 📒 AddressBook – Phonebook Project
A consolebased
phonebook application built with Java. Currently supports bilingual interface (English/Persian) and inmemory
contact management.
---
## ✨ Features
- Add a contact (name and phone number)
- Input validation (empty names are rejected)
- Display the full contact list
- Search for a contact by name
- Bilingual interface (English/Persian) – language selection at startup
---
## 🛠️ Technical Details
- **Language:** Java
-
**Data Storage:** Two parallel arrays (`contacts[]` and `phoneNumbers[]`) with a fixed capacity of 40 contacts
- **Storage Type:** In-memory (RAM) – temporary
- **Internationalization:** `ResourceBundle` with custom `UTF8Control` for proper Persian character encoding
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
---
## 📁 Project Structure
```
phoneBook/ ├── Main.java
├── messages_en.properties
└── messages_fa.properties
```
---
## 📝 Notes
- This project is currently in active development.
- More features will be added in future phases.
---
## 📜 License
This project is provided for educational purposes only and has no specific license.




# 📒 دفترچه تلفن – AddressBook
یک برنامه ی دفترچه تلفن مبتنی بر کنسول که با جاوا نوشته شده است. در حال حا ضر از رابط دو زبانه (انگلیسی/
.فارسی) و مدیریت مخاطبین در حافظه پشتیبانی می کند
---
## ✨ قابلیت ها
- افزودن مخاطب (نام و شماره تلفن)
- اعتبارسنجی ورودی (عدم پذیرش نام خالی)
- نمایش لیست کامل مخاطبین
- جستجوی مخاطب بر اساس نام
- رابط دو زبانه (انگلیسی/فارسی) – انتخاب زبان در شروع برنامه
---
## 🛠️ جزئیات فنی
- زبان: جاوا
- ذخیره سازی داده با دو آرایه ی موازی با ظرفیت ثابت ۴۰ مخاطب
- نوع ذخیره سازی: در حافظه(رم) – موقتی
-  (i18n) بین ا لمللی سازی

---
## 🚀 نحوه ی اجرا
```bash
javac Main.java
java Main
```
---
## 📦 نسخه ها
- [v1.0](https://github.com/Amir-vazir/phone-book/releases/tag/v1.0)
  فاز اول: منوی کنسول با ذخیره سازی آرایه ای
- [v2.0](https://github.com/Amir-vazir/phone-book/releases/tag/v2.0)
  فاز دوم: پشتیبانی دو زبانه

---
## 📁 ساختار پروژه
```
phoneBook/ ├── Main.java
├── messages_en.properties
└── messages_fa.properties
```
---
## 📝 نکات
- .این پروژه در حال توسعه است
.قابلیت های بیشتری در فازهای آینده اضافه خواهد شد -
---
## 📜 مجوز
این پروژه صرفاً
برای اهداف آموزشی ارائه شده است و مجوز خاصی ندارد
.
