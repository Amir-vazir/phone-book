# 📒 دفترچه تلفن 
 یک دفترچه تلفن خط فرمان، دوزبانه (فارسی/انگلیسی)، نوشته شده با جاوا 21
---
📖 [مطالعه به انگلیسی / Read in English](README.md)
---
## ✨ قابلیت ها
- افزودن، نمایش، جستجو، ویرایش و حذف مخاطب
- دو نوع مخاطب: عادی و شرکتی
- تطبیق بدون حساسیت به حروف بزرگ و کوچک در همه ی عملیات
- نمایش لیست کامل مخاطبین (به ترتیب حروف الفبا)
- جستجوی پیشرفته ی مخاطب بر اساس نام (حتی با بخشی از نام)
- رابط کاربری دوزبانه، با انتخاب زبان در شروع
- مدیریت خطا با یک سلسله مراتب اختصاصی
## 🎓 مهارت های به کار رفته
- **رعایت چهار اصل شی گرایی:** محرمانه سازی، وراثت، چندریختی و انتزاع
- **جاوا فانکشنال:** Predicate، Optional، Stream API و method reference
-    `UTF8Control`بین المللی سازی : سفارشی برای نمایش صحیح حروف فارسی
- Exception مدیریت قدر تمند خطاها با یک سلسله مراتب اختصاصی
- JUnit 5 تست نویسی : با کلاس های تست تودرتو
- Maven ساختار پروژه : ساختار استاندارد
## 📁 ساختار پروژه
```
src/ ├── main/java/com/myvazir/addressbook/
│ ├── Contact, CompanyContact — مدل دامنه
│ ├── ContactManager, AddressBook — منطق تجاری
│ ├── ConsoleUI, Main — لایه ی نمایش
│ ├── BundleManager, UTF8Control — بومی سازی
│ └── *Exception — سلسله مراتب خطا
├── main/resources/
│ └── messages*.properties — بسته های زبان
└── test/java/com/myvazir/addressbook/
└── *Test — مجموعه تست JUnit 5
```
## 🔍 نکات فنی
- مرتب سازی  — `Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER)`
- توزیع منو  — `Map<Integer, Runnable>`
-  `ArrayList<Contact>` ذخیره سازی —  با
- `hashCode` `equals` برابری —  این دو متد بر اساس نام (بدون حساسیت به حروف)، برابری را تعریف می کند 
### سلسله مراتب خطاها
```
Exception 
└── AddressBookException (checked) base for all domain errors
├── ContactNotFoundException (checked) contact to edit/delete not found
└── DuplicateContactException (checked) duplicate name on add
RuntimeException
└── InvalidContactException (unchecked) invalid user input
```
## 🧪 تست ها
چهار مجموعه تست JUnit :مدل دامنه، منطق تجاری، و بومی سازی را پوشش می دهند 5
- **`ContactTest`** — سازنده ،  `nameContains` و `nameEquals`، و  `equals` و `hashCode`
- **`CompanyContactTest`** — رفتار وراثت و حالت های مرزی برابری بین نوعی
- **`AddressBookTest`** —   جستجو، ویرایش، حذف، مرتب سازی، افزودن شامل تکراری ها 
- **`ResourceBundleTest`** — تطابق کلیدها بین بسته های انگلیسی و فارسی
## 🚀 نحوه ی اجرا
نیازمندی ها : **JDK و ** 21 **Maven**
```bash
mvn test # اجرای تست ها
mvn package # ساخت jar
java -jar target/addressbook-1.0-SNAPSHOT.jar
```
## 📦 نسخه ها
| Tag                                                                | Phase | Highlights                     |
|--------------------------------------------------------------------|---|--------------------------------|
| [v1.0](https://github.com/Amir-vazir/phone-book/releases/tag/v1.0) | 1 | منوی کنسول با آرایه ی ثابت     |
| [v2.0](https://github.com/Amir-vazir/phone-book/releases/tag/v2.0) | 2 | پشتیبانی دوزبانه               |
| [v3.0](https://github.com/Amir-vazir/phone-book/releases/tag/v3.0) | 3 | ArrayList، ویرایش و حذف        |
| [v3.5](https://github.com/Amir-vazir/phone-book/releases/tag/v3.5) | 3.5 | جستجوی زیررشته ای با چند نتیجه |
| [v4.0](https://github.com/Amir-vazir/phone-book/releases/tag/v4.0) | 4 | چهار اصل شی گرایی + مخاطب شرکتی |
| [v5.0](https://github.com/Amir-vazir/phone-book/releases/tag/v5.0) | 5 | جلوگیری از تکرار +  Exception  |
| [v6.0](https://github.com/Amir-vazir/phone-book/releases/tag/v6.0) | 6 | بازنویسی فانکشنال              |
| [v7.0](https://github.com/Amir-vazir/phone-book/releases/tag/v7.0) | 7 | Maven، مجموعه تست JUnit 5      |
## 📝 نکات
- .داده ها فقط در حافظه ی موقت (رم) ذخیره می شوند و با بستن برنامه از بین می روند؛ ذخیره سازی روی دیسک فاز بعدی است
-   UTF-8  برای نمایش صحیح متون فارسی، مطمئن شوید که ترمینال شما از کدگذاری استفاده می کند. 
- پس در ویندوز، قبل از اجرا در ترمینال دستور زیر را اجرا کنید.
- (on Windows: run `chcp 65001` before executing)
## 📜 مجوز
MIT
