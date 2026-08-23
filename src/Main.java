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

    private static String getMessage (String key){
        return bundle.getString(key);
    }


    public static void main(String[] args){
        int choice;
        Scanner input = new Scanner(System.in);

        System.out.println("select language/ انتخاب زبان: ");
        System.out.println("1 English");
        System.out.println("فارسی 2");
        System.out.println("Your choice (1/2):  ");
        int langChoice = input.nextInt();
        input.nextLine();

        Locale locale;
        if (langChoice == 1){
          locale = Locale.ENGLISH;
        }
        else {
            locale = new Locale("fa");
        }

        bundle = ResourceBundle.getBundle("messages", locale, new UTF8Control());


        String[] contacts = new String[40];
        String[] phoneNumbers = new String[40];

        do {
            System.out.println("====== " + getMessage("menu.title") + " ======");
            System.out.println(getMessage("menu.add"));
            System.out.println(getMessage("menu.list"));
            System.out.println(getMessage("menu.search"));
            System.out.println(getMessage("menu.exit"));
            System.out.println(getMessage("menu.prompt"));
            choice = input.nextInt();
            input.nextLine();

            switch(choice){
                case 1 :
                    addContact(contacts, input, phoneNumbers);
                    break;
                case 2 :
                    displayContacts(contacts , phoneNumbers);
                    break;
                case 3 :
                    searchContacts(contacts, input, phoneNumbers);
                    break;
                case 4 :
                    System.out.println(getMessage("exit.message"));
                    break;
                default:
                    System.out.println(getMessage("invalid.option"));
              }



        } while ( choice != 4);



        input.close();


    }



    public static void addContact (String[] contacts, Scanner input, String[] phoneNumbers){

        System.out.println(getMessage("add.name.prompt"));
        String name = input.nextLine().trim();
        System.out.println(getMessage("add.phone.prompt"));
        String phone = input.nextLine().trim();


        if (name.isEmpty()){
            System.out.println(getMessage("add.error.empty"));
            return;
        }




        int index = -1;
        for (int i = 0; i < contacts.length; i++){
         if (contacts[i] == null){
             index = i;
             break;
         }

        }

        if (index == -1){
            System.out.println(getMessage("add.error.full"));

        }
        else {
            contacts[index] = name;
            phoneNumbers[index] = phone;
            System.out.println(getMessage("add.success"));
        }

    }




    public static void displayContacts (String[] contacts, String[] phoneNumbers){
        System.out.println(getMessage("list.title"));
        boolean foundAny = false;
        for ( int i = 0; i < contacts.length; i++){
         if (contacts[i] != null){
             System.out.println((i + 1) + ". " + contacts[i] + " _ " + phoneNumbers[i]);
             foundAny = true;

         }

        }
        if (!foundAny){
            System.out.println(getMessage("list.empty"));
        }


    }



    public static void searchContacts (String[] contacts, Scanner input, String[] phoneNumbers){

        System.out.println(getMessage("search.name.prompt"));
        String searchName = input.nextLine().trim();

        boolean found = false;
        int foundIndex = -1 ;

        for (int i = 0 ; i < contacts.length ; i++) {
            if ( contacts[i] != null && contacts[i].equalsIgnoreCase(searchName) ){
              found = true;
              foundIndex = i;
              break;

            }



        }
        if (found){
            System.out.println(getMessage("search.found.title"));
            System.out.println( getMessage("search.found.name") + contacts[foundIndex] );
            System.out.println( getMessage("search.found.phone") + phoneNumbers[foundIndex] );
        }
        else {
            System.out.println(getMessage("search.not.found"));
        }

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



