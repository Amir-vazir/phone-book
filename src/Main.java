import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        int choice;
        Scanner input = new Scanner(System.in);
        String[] contacts = new String[40];
        String[] phoneNumbers = new String[40];

        do {
            System.out.println("====== دفترچه تلفن ======");
            System.out.println(" اضافه کردن مخاطب .1");
            System.out.println(" لیست مخاطبین .2");
            System.out.println(" جستجوی مخاطب .3");
            System.out.println(" خروج از برنامه .4");
            System.out.println(" لطفا گزینه مورد نظر را وارد کنید (1,2,3,4) :  ");
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
                    System.out.println("خروج از برنامه");
                    break;
                default:
                    System.out.println("!!گزینه نامعتبر!!");
              }



        } while ( choice != 4);



        input.close();


    }



    public static void addContact (String[] contacts, Scanner input, String[] phoneNumbers){

        System.out.println("نام مخاطب را وارد کنید:  ");
        String name = input.nextLine().trim();
        System.out.println("شماره تلفن را وارد کنید:  ");
        String phone = input.nextLine().trim();


        if (name.isEmpty()){
            System.out.println("خطا! نام مخاطب نمی تواند خالی باشد.");
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
            System.out.println("!!دفترچه تلفن پر است! نمی توان مخاطب جدید اضافه کرد!!");

        }
        else {
            contacts[index] = name;
            phoneNumbers[index] = phone;
            System.out.println("مخاطب با موفقیت اضافه شد");
        }

    }




    public static void displayContacts (String[] contacts, String[] phoneNumbers){
        System.out.println("---- لیست مخاطبین ----");
        boolean foundAny = false;
        for ( int i = 0; i < contacts.length; i++){
         if (contacts[i] != null){
             System.out.println((i + 1) + ". " + contacts[i] + " _ " + phoneNumbers[i]);
             foundAny = true;

         }

        }
        if (!foundAny){
            System.out.println("!!دفترچه تلفن خالی است!!");
        }


    }



    public static void searchContacts (String[] contacts, Scanner input, String[] phoneNumbers){

        System.out.println("نام مخاطب مورد نظر را وارد کنید:  ");
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
            System.out.println("مخاطب پیدا شد:");
            System.out.println( "نام: " + contacts[foundIndex] );
            System.out.println( "شماره: " + phoneNumbers[foundIndex] );
        }
        else {
            System.out.println("مخاطب پیدا نشد.");
        }

    }


}
