import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;



public class Main {

    private static ResourceBundle bundle;

    private static String getMessage(String key) {
        return bundle.getString(key);
    }


    public static void main(String[] args) {
        int choice;
        Scanner input = new Scanner(System.in);

        System.out.println("select language / انتخاب زبان: ");
        System.out.println("1. English");
        System.out.println(" فارسی .2");
        System.out.println("Your choice: ");
        int langChoice = input.nextInt();
        input.nextLine();


        Locale locale;
        if (langChoice == 1) {
            locale = Locale.ENGLISH;

        } else {
            locale = new Locale("fa");
        }


        bundle = ResourceBundle.getBundle("messages", locale, new UTF8Control());


        ArrayList<String> contacts = new ArrayList<>();
        ArrayList<String> phoneNumbers = new ArrayList<>();


        do {
            System.out.println("======" + getMessage("menu.title") + "======");
            System.out.println(getMessage("menu.add"));
            System.out.println(getMessage("menu.list"));
            System.out.println(getMessage("menu.search"));
            System.out.println(getMessage("menu.edit"));
            System.out.println(getMessage("menu.delete"));
            System.out.println(getMessage("menu.exit"));
            System.out.println(getMessage("menu.prompt"));
            choice = input.nextInt();
            input.nextLine();

            switch (choice){

                case 1:
                    addContact(contacts, input, phoneNumbers);
                    break;
                case 2:
                    displayContacts(contacts, phoneNumbers);
                    break;
                case 3:
                    searchContacts(contacts, input, phoneNumbers);
                    break;
                case 4:
                    System.out.println("fgh");
                    break;
                case 5:
                    System.out.println("jgh");
                    break;
                case 6:
                    System.out.println(getMessage("exit.message"));
                    break;
                default:
                    System.out.println(getMessage("invalid.option"));

            }


        } while (choice != 6);

        input.close();

    }


    public static void addContact (ArrayList<String> contacts,Scanner input ,ArrayList<String> phoneNumbers){

        System.out.println(getMessage("add.name.prompt"));
        String name = input.nextLine().trim();
        System.out.println(getMessage("add.phone.prompt"));
        String phone = input.nextLine().trim();

        if (name.isEmpty()){
            System.out.println(getMessage("add.error.empty"));
            return;
        }

        contacts.add(name);
        phoneNumbers.add(phone);
        System.out.println(getMessage("add.success"));

    }


    public static void displayContacts ( ArrayList<String> contacts, ArrayList<String> phoneNumbers){
        System.out.println(getMessage("list.title"));

        if (contacts.isEmpty()){
            System.out.println(getMessage("list.empty"));
            return;
        }

        for (int i = 0; i < contacts.size(); i++){

            System.out.println( (i + 1) + ". " + contacts.get(i) + " - " + phoneNumbers.get(i));
        }


    }


    public static void searchContacts (ArrayList<String> contacts ,Scanner input , ArrayList<String> phoneNumbers){
        System.out.println(getMessage("search.name.prompt"));
        String searchName = input.nextLine().trim();

        int foundIndex = -1;
        for (int i = 0; i < contacts.size(); i++){
            if (contacts.get(i).equalsIgnoreCase(searchName)){
               foundIndex = i;
               break;

            }
        }

        if (foundIndex != -1){
            System.out.println(getMessage("search.found.title"));
            System.out.println(getMessage("search.found.name") + contacts.get(foundIndex));
            System.out.println(getMessage("search.found.phone") + phoneNumbers.get(foundIndex));
        }
        else {
            System.out.println(getMessage("search.not.found"));
        }
    }



    public static void editContact (ArrayList<String> contacts, Scanner input, ArrayList<String> phoneNumbers){
        System.out.println(getMessage("edit.name.prompt"));
        String oldName = input.nextLine().trim();

        int index = -1;
        for (int i = 0; i < contacts.size(); i++){
            if (contacts.get(i).equalsIgnoreCase(oldName)){
            index = i;
            break;
            }

        }

        if (index == -1){
            System.out.println(getMessage("edit.not.found"));
        return;
        }

        System.out.println(getMessage("edit.new.name.prompt"));
        String newName = input.nextLine().trim();
        System.out.println(getMessage("edit.new.phone.prompt"));
        String newPhone = input.nextLine().trim();

        if (newName.isEmpty()){
            newName = contacts.get(index);
        }
        if (newPhone.isEmpty()){
            newPhone = phoneNumbers.get(index);
        }

        contacts.set(index, newName);
        phoneNumbers.set(index, newPhone);
        System.out.println(getMessage("edit.success"));

    }


    public static void deleteContact (ArrayList<String> contacts, Scanner input, ArrayList<String> phoneNumbers){
        System.out.println(getMessage("delete.name.prompt"));
        String deleteName = input.nextLine().trim();

        int index = -1;
        for (int i = 0; i < contacts.size(); i++){
          if (contacts.get(i).equalsIgnoreCase(deleteName)){
              index = i;
              break;
          }

        }

        if (index == -1){
            System.out.println(getMessage("delete.not.found"));
            return;
        }

        contacts.remove(index);
        phoneNumbers.remove(index);
        System.out.println(getMessage("delete.success"));

    }



    private static class UTF8Control extends ResourceBundle.Control {
        @Override
        public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
                throws IllegalAccessException, InstantiationException, IOException {
            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, "properties");
            try (InputStream stream = loader.getResourceAsStream(resourceName)) {
                if (stream != null) {
                    try (Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                        return new java.util.PropertyResourceBundle(reader);
                    }
                }
            }
            return null;

        }
    }

}



