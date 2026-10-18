import java.util.*;

public class ConsoleUI {
private final Scanner scanner;
private final ResourceBundle bundle;
private final ContactManager contactManager;
private final Map<Integer, Runnable> actions;

public ConsoleUI (){
    scanner =new Scanner(System.in);

    System.out.println("انتخاب زبان / Language choice");
    System.out.println("1. English");
    System.out.println(" فارسی .2");
    System.out.println("Your choice (1/2) : ");
    int langChoice = readIntOrDefault(1);

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

    actions= Map.of(
            1, this::addContact,
            2, this::listContact,
            3, this::searchContacts,
            4, this::editContact,
            5, this::deleteContact
    );


}

public void start(){
    while (true) {
        printMenu();
        int choice = readIntOrDefault(-1);
        if (choice == 6) {
            System.out.println(getMessage("exit.message"));
            break;
        }
        actions.getOrDefault(choice, () -> System.out.println(getMessage("invalid.option")))
                .run();

    }
    scanner.close();
}

private void printMenu (){
    System.out.println(getMessage("menu.title"));
    System.out.println(getMessage("menu.add"));
    System.out.println(getMessage("menu.list"));
    System.out.println(getMessage("menu.search"));
    System.out.println(getMessage("menu.edit"));
    System.out.println(getMessage("menu.delete"));
    System.out.println(getMessage("menu.exit"));
    System.out.println(getMessage("menu.prompt"));
}


private int readIntOrDefault (int defaultValue){
    String line = scanner.nextLine().trim();
    try{
        return Integer.parseInt(line);
    } catch (NumberFormatException e){
        return defaultValue;
    }
}

private String getMessage (String key){
    return bundle.getString(key);
}

private void addContact (){

    try {
        Contact contact = createContactFromUser();
        contactManager.addContact(contact);
        System.out.println(getMessage("add.success"));
    }catch (DuplicateContactException e){
        System.out.println(getMessage("add.duplicate"));
    }catch (InvalidContactException e){
        System.out.println(e.getMessage());
    }

}




private Contact createContactFromUser (){
    System.out.println(getMessage("add.name.prompt"));
    String name = scanner.nextLine().trim();
    if (name.isEmpty()){
        throw new InvalidContactException(getMessage("add.error.empty"));
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


private void listContact (){
    if (contactManager.isEmpty()){
        System.out.println(getMessage("list.empty"));
        return;
    }
    contactManager.getContactSortedByName().forEach(this::printContact);
}

private void printContact (Contact contact) {
    contact.display();
    System.out.println("--------");
}


private void searchContacts () {
    System.out.println(getMessage("search.name.prompt"));
    String keyWord = scanner.nextLine().trim();
    if (keyWord.isEmpty()) {
        System.out.println(getMessage("search.empty.error"));
        return;
    }
    List<Contact> results = contactManager.searchContacts(keyWord);
    if (results.isEmpty()) {
        System.out.println(getMessage("search.not.found"));
    } else {
        System.out.println(getMessage("search.results"));
        results.forEach(this::printContact);
    }

}


private void editContact (){
    System.out.println(getMessage("edit.name.prompt"));
    String oldName = scanner.nextLine().trim();

   try {
       Contact newContact = createContactFromUser();
       contactManager.editContact(oldName, newContact);
       System.out.println(getMessage("edit.success"));
   }catch (ContactNotFoundException e){
       System.out.println(getMessage("edit.not.found"));
   }catch (InvalidContactException e){
       System.out.println(e.getMessage());
   } catch (DuplicateContactException e) {
       System.out.println(getMessage("add.duplicate"));
   }
}


private void deleteContact(){
    System.out.println(getMessage("delete.name.prompt"));
    String nameDelete = scanner.nextLine().trim();
    try {
        contactManager.deleteContact(nameDelete);
        System.out.println(getMessage("delete.success"));
    }catch (ContactNotFoundException e){
        System.out.println(getMessage("delete.not.found"));
    }
}

}
