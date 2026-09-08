import java.util.ArrayList;
public class AddressBook implements ContactManager {
    private ArrayList<Contact> contacts;

    public AddressBook (){
        contacts = new ArrayList<>();
    }


    @Override
    public void addContact( Contact contact){
     contacts.add(contact);
    }

    @Override
    public void displayAll (){
      for (int i = 0; i < contacts.size(); i++){
          System.out.println((i + 1) + ". ");
          contacts.get(i).display();
      }
    }

    @Override
    public boolean isEmpty(){
        return contacts.isEmpty();
    }

    @Override
    public ArrayList<Contact> searchContacts(String keyWord) {
        ArrayList<Contact> result = new ArrayList<>();
        String lowerKeyword = keyWord.toLowerCase();
        for (Contact c : contacts){
            if (c.getName().toLowerCase().contains(lowerKeyword)){
                result.add(c);
            }
        }
        return result;
    }

    @Override
    public Contact findExactContact (String name){
        for (Contact c : contacts){
            if (c.getName().equalsIgnoreCase(name)){
                return c;
            }

        }
        return null;
    }

    @Override
    public void  editContact ( String oldName, Contact newContact){
        for (int i = 0 ; i < contacts.size(); i++){
            if (contacts.get(i).getName().equalsIgnoreCase(oldName)){
                contacts.set(i, newContact);
                return;
            }
        }
}

    @Override
    public void deleteContact(String nameDelete){
        contacts.removeIf(c -> c.getName().equalsIgnoreCase(nameDelete));
    }


}

