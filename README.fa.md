# 📒 دفترچه تلفن – AddressBook

یک برنامه ی دفتر تلفن مبتنی بر کنسول که با **جاوا** نوشته شده است و **قوانین شی گرایی** کاملاً رعایت شده و از **رابط دو زبانه** (انگلیسی/فارسی) پشتیبانی می کند
---
📖 [مطالعه به انگلیسی / Read in English](README.md)
---
## ✨ قابلیت ها
- افزودن مخاطب (نام و شماره تلفن)
- اعتبارسنجی ورودی (عدم پذیرش نام خالی)
- افزودن مخاطب شرکتی – امکان ثبت نام شرکت به صورت اختیاری
-  نمایش لیست کامل مخاطبین (به ترتیب حروف الفبا)
- جستجوی پیشرفته ی مخاطب بر اساس نام (حتی با بخشی از نام)
- ویرایش اطلاعات مخاطب
- حذف مخاطب
- رابط دو زبانه (انگلیسی/فارسی) – انتخاب زبان در شروع برنامه
- Exception مدیریت قدر تمند خطاها با یک سلسله مراتب اختصاصی    
- **ظرفیت نامحدود:**  `ArrayList`  استفاده از
- **رعایت چهار اصل شی گرایی:** محرمانه سازی، وراثت، چندریختی و انتزاع
- بازنویسی شده با رویکرد فانکشنال
---
## 🛠️ جزئیات فنی
- زبان:  جاوا 
-  `ArrayList<Contact> `(ساختار داده :  برای ذخیره ی نام ها و شماره ها (بدون محدودیت ظرفیت
-  (stream، lambda، `Predicate`، `Optional` و method reference)سبک برنامه نویسی : فانکشنال 
- نوع ذخیره سازی: در حافظه (رم) – موقتی
-    `UTF8Control`بین المللی سازی : سفارشی برای نمایش صحیح حروف فارسی
-  substring و case-insensitive جستجو :  با نمایش همه ی نتایج منطبق
-    `Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER)` مرتب سازی : حروف الفبا
-    `Map<Integer, Runnable>` رابط کاربری : کنسولی. مدیریت عملیات منو از طریق
---
## ⚠️ مدیریت خطا (Exception Handling)
از **فاز ۵** به بعد، تمام عملیات توسط یک سلسله مراتب اختصاصی محافظت می شوند
```
Exception 
└─ AddressBookException (checked)          ←  کلاس پایه برای تمام خطاهای حوزه ی دفتر تلفن 
  ├── ContactNotFoundException (checked)   ←  مخاطب مورد نظر برای ویرایش یا حذف پیدا نشود 
  └── DuplicateContactException (checked)  ←  مخاطبی با نام تکراری اضافه شود 
RuntimeException 
└── InvalidContactException (unchecked)    ←  ورودی کاربر نامعتبر باشد (مثلاً نام خالی)
```
---
##  🧠 برنامه نویسی فانکشنال(فاز ۶) 
از فاز ۶ به بعد، لایه ی داده با رویکرد فانکشنال جاوا بازنویسی شده است.
 
  جدید قلب این فاز است overload این
```java
List<Contact> searchContacts(Predicate<Contact> predicate);
```
.این متد اجازه می دهد برنامه نویس اگر خواست بتواند با هر قاعده ی دلخواهی دفتر تلفن را جستجو کند 

.خودش را بنویسد Predicate فقط کافیست

.Open/Closed نمایشی از اصل 

:مثلاً جستجو بر اساس پیش شماره ی تلفن 

```java
public List<Contact> searchByPhonePrefix(String prefix) {
return searchContacts(c -> c.getPhone().startsWith(prefix));
}
```
---
## 🚀 نحوه ی اجرا
 همه فایل های جاوا را کامپایل کنید . ۱ 
```bash
javac phonebook/*.java
```
کلاس اصلی را اجرا کنید . ۲
```bash
java phonebook.Main
```
حتما این فایل ها را در همان پوشه ای که فایل های کلاس قرار دارند، کپی کنید . ۳ messages_en.properties و messages_fa.properties .  

---
## 📦 نسخه ها
- [v1.0](https://github.com/Amir-vazir/phone-book/releases/tag/v1.0  )  –   منوی کنسول با آرایه ی ثابت :فاز اول (fixed array)  
- [v2.0](https://github.com/Amir-vazir/phone-book/releases/tag/v2.0) – فاز دوم: (i18n) پشتیبانی دو زبانه 
- [v3.0](https://github.com/Amir-vazir/phone-book/releases/tag/v3.0) –
  فاز سوم : `ArrayList`، ویرایش و حذف مخاطب
- [v3.5](https://github.com/Amir-vazir/phone-book/releases/tag/v3.5) –  جستجوی زیررشته ای و نمایش چند نتیجه (Substring)
- [v4.0](https://github.com/Amir-vazir/phone-book/releases/tag/v4.0  )  –   رعایت چهار اصل شی گرایی + مخاطب شرکتی :فاز چهارم (OOP Pillars)
- [v5.0](https://github.com/Amir-vazir/phone-book/releases/tag/v5.0  )  –   اختصاصی + جلوگیری از مخاطب تکراری :فاز پنجم (Exception)
- [v6.0](https://github.com/Amir-vazir/phone-book/releases/tag/v6.0) – فاز ششم: (Functional) بازنویسی فانکشنال 
---
## 📁 ساختار پروژه
```
phonebook/ ├── Displayable.java # این ترفیس برای نمایش اشیاء
├── Contact.java #  کلاس پایه مخاطب
├── CompanyContact.java #  زیرکلاس با نام شرکت
├── ContactManager.java #  این ترفیس عملیات CRUD
├── AddressBook.java #  پیاده سازی با ArrayList<Contact>
├── AddressBookException.java #  checked کلاس پایه برای خطاهای 
├── ContactNotFoundException.java #  وقتی مخاطب پیدا نشود
├── DuplicateContactException.java #  وقتی مخاطب تکراری اضافه شود
├── InvalidContactException.java #  Exception برای ورودی نامعتبر (unchecked)
├── BundleManager.java #    ResourceBundle برای   
├── UTF8Control.java #   properties کنترل سفارشی برای فایل های  
├── ConsoleUI.java #  منطق رابط کاربری
├── Main.java #  نقطه ورود برنامه
├── messages_en.properties #  بسته منابع انگلیسی
└── messages_fa.properties #  بسته منابع فارسی (UTF-8)
```
---
## 📝 نکات
- .این پروژه در حال توسعه است و قابلیت های بیشتری در آینده اضافه خواهد شد
-  داده ها فعلا فقط در حافظه ی موقت (رم) ذخیره می شوند و پس از بستن برنامه از بین می روند
- UTF-8  برای نمایش صحیح متون فارسی، مطمئن شوید که ترمینال شما از کدگذاری استفاده می کند.
---
## 📜 مجوز
MIT این پروژه تحت مجوز منتشر شده است.

