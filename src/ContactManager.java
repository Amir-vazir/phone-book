import java.util.ArrayList;
public interface ContactManager {

    void addContact(Contact contact);

    void displayAll();

    boolean isEmpty();

    ArrayList<Contact> searchContacts(String keyWord);

    Contact findExactContact(String name);

    void editContact(String oldName, Contact newContact);

    void deleteContact(String nameDelete);

}