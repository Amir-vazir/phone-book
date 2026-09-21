import java.util.ArrayList;
import java.util.List;

public class AddressBook implements ContactManager {
    private ArrayList<Contact> contacts;

    public AddressBook (){
        contacts = new ArrayList<>();
    }


    @Override
    public void addContact( Contact contact) throws DuplicateContactException{
        if (contact == null){
            throw new IllegalArgumentException("Contact can not be null");
        }
        if (findExactContact(contact.getName()) != null ){
            throw new DuplicateContactException("Duplicate contact: " + contact.getName());
        }
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
    public List<Contact> searchContacts(String keyWord) {
        List<Contact> result = new ArrayList<>();
        String lowerKeyword = keyWord.toLowerCase();
        for (Contact c : contacts){
            if (c.getName().toLowerCase().contains(lowerKeyword)){
                result.add(c);
            }
        }
        return result;
    }


    private Contact findExactContact (String name){
        for (Contact c : contacts){
            if (c.getName().equalsIgnoreCase(name)){
                return c;
            }

        }
        return null;
    }

    @Override
    public void  editContact ( String oldName, Contact newContact) throws ContactNotFoundException{
        for (int i = 0 ; i < contacts.size(); i++){
            if (contacts.get(i).getName().equalsIgnoreCase(oldName)){
                contacts.set(i, newContact);
                return;
            }
        }
        throw new ContactNotFoundException("Contact not found: " + oldName);
}

    @Override
    public void deleteContact(String nameDelete) throws ContactNotFoundException{
    boolean  removed  =  contacts.removeIf(c -> c.getName().equalsIgnoreCase(nameDelete));

    if (!removed){
        throw new ContactNotFoundException("Contact not found: " + nameDelete );
        }

    }


}

