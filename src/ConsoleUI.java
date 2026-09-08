import java.util.*;
public class ConsoleUI {
private Scanner scanner;
private ResourceBundle bundle;
private ContactManager contactManager;

public ConsoleUI (){
    scanner =new Scanner(System.in);

    System.out.println("انتخاب زبان / Language choice");
    System.out.println("1. English");
    System.out.println(" فارسی .2");
    System.out.println("Your choice (1/2) : ");
    int langChoice = scanner.nextInt();
    scanner.nextLine();

    Locale locale;
    if (langChoice == 1){
        locale = Locale.ENGLISH;
    }
    else {
        locale = new Locale("fa");
    }

    bundle = ResourceBundle.getBundle("messages", locale, new UTF8Control());
    BundleManager.setBundle(bundle);

    contactManager = new AddressBook();

}

public void start(){
    int choice;

    do {

        System.out.println(getMessage("menu.title"));
        System.out.println(getMessage("menu.add"));
        System.out.println(getMessage("menu.list"));
        System.out.println(getMessage("menu.search"));
        System.out.println(getMessage("menu.edit"));
        System.out.println(getMessage("menu.delete"));
        System.out.println(getMessage("menu.exit"));
        System.out.println(getMessage("menu.prompt"));

        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice){
            case 1 -> addContact();
            case 2 -> {
                if (contactManager.isEmpty()) {
                    System.out.println(getMessage("list.empty"));
                }
                else { contactManager.displayAll();}
            }
            case 3 -> searchContacts();
            case 4 -> editContact();
            case 5 -> deleteContact();
            case 6 -> System.out.println(getMessage("exit.message"));
            default -> System.out.println(getMessage("invalid.option"));
        }


    }while (choice != 6);
    scanner.close();
}

private String getMessage (String key){
    return bundle.getString(key);
}

private void addContact (){
    Contact contact = createContactFromUser();
    if (contact != null){
        contactManager.addContact(contact);
        System.out.println(getMessage("add.success"));
    }
}



private Contact createContactFromUser (){
    System.out.println(getMessage("add.name.prompt"));
    String name = scanner.nextLine().trim();
    if (name.isEmpty()){
        System.out.println(getMessage("add.error.empty"));
        return null;
    }
    System.out.println(getMessage("add.phone.prompt"));
    String phone = scanner.nextLine().trim();

    System.out.println(getMessage("company.question"));
    String companyAnswer = scanner.nextLine().trim().toLowerCase();
    if (companyAnswer.startsWith("y")){
        System.out.println(getMessage("company.prompt"));
        String companyName = scanner.nextLine().trim();
        return new CompanyContact(companyName, name, phone);
    }
    else { return new Contact(name,phone); }
}


private void searchContacts (){
    System.out.println(getMessage("search.name.prompt"));
    String keyWord = scanner.nextLine().trim();
    if (keyWord.isEmpty()){
        System.out.println(getMessage("search.empty.error"));
        return;
    }
    ArrayList<Contact> results = contactManager.searchContacts(keyWord);
    if (results.isEmpty()){
        System.out.println(getMessage("search.not.found"));
    }
    else {
        System.out.println(getMessage("search.results"));
        for (Contact c : results ){
            c.display();
            System.out.println("----------");
        }

    }

}


private void editContact (){
    System.out.println(getMessage("edit.name.prompt"));
    String oldName = scanner.nextLine().trim();

    Contact found = contactManager.findExactContact(oldName);

    if (found == null){
        System.out.println(getMessage("edit.not.found"));
        return;
    }
   Contact newContact = createContactFromUser();
    if (newContact != null) {
        contactManager.editContact(oldName, newContact);
        System.out.println(getMessage("edit.success"));
    }
}


private void deleteContact(){
    System.out.println(getMessage("delete.name.prompt"));
    String nameDelete = scanner.nextLine().trim();

    Contact foundDelete = contactManager.findExactContact(nameDelete);


    if(foundDelete == null){
        System.out.println(getMessage("delete.not.found"));
        return;
    }

    contactManager.deleteContact(nameDelete);
    System.out.println(getMessage("delete.success"));
}


}

